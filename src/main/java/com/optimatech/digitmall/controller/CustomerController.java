package com.optimatech.digitmall.controller;


import com.optimatech.digitmall.model.dto.AddressRequest;
import com.optimatech.digitmall.model.dto.CustomerInforRequest;
import com.optimatech.digitmall.respone.Response;
import com.optimatech.digitmall.serviceimp.AuthorizationService;
import com.optimatech.digitmall.serviceimp.CustomerSeviveImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("http://localhost:5500")
@Tag(name = "API cung cấp các tính năng phía người dùng")
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerSeviveImp customerSeviveImp;
    private final AuthorizationService authorizationService;

    public CustomerController(CustomerSeviveImp customerSeviveImp, AuthorizationService authorizationService) {
        this.customerSeviveImp = customerSeviveImp;
        this.authorizationService = authorizationService;
    }

    @Operation(summary = "xem thông tin của một customer thông qua customer id",
    description = "client gửi id của customer cầm xem thông qua đường dẫn Path\n" +
            "Id của customer sẽ được gửi về sau khi đăng nhập kèm với token\n" +
            "Cần gửi kèm token này trong phần header của request")
    @GetMapping("/{cusid}")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> findCusById(@PathVariable("cusid") Long cusid){
       return new ResponseEntity<>(new Response("Thành Công", customerSeviveImp.getCustomer(cusid),
               "200", ""), HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Thay đổi thông tin của một customer",
    description = "Note: {cusid} phải trùng với id của customer gửi request. Nếu không server sẽ báo không có quyền\n" +
            "      Trường dữ liệu 'birthday' cần được gửi đúng định dạng sau: yyyy-mm-dd ")
    @PutMapping("/{cusid}/change-infor")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> updateInfor(@PathVariable("cusid") Long cusid,
                                         @RequestBody CustomerInforRequest customerInforRequest){
        if(authorizationService.checkAuthor(cusid) && customerSeviveImp.updateInfor(customerInforRequest,cusid))
            return new ResponseEntity<>(new Response("Thành Công","", "201",""),
                    HttpStatusCode.valueOf(200));
        return new ResponseEntity<>(new Response("Thất bại",null, "", "Kiểm tra lại format dữ liệu! hoặc không có quyền"),
                HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Xem trước avatar trước khi cập nhật",
    description = "khi ấn nút import file ảnh lên thì call API này. API này sẽ gửi về 1 url của ảnh" +
            " vừa được tải lên. Dùng url này để render ra ảnh xem trước. Lúc này ảnh chưa được lưu!")
    @PostMapping("/{cusid}/avatar/preview")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> xemTruocAvatar(@RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(customerSeviveImp.xemTruocAvatar(file),
                HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Đây là API lưu ảnh avatar xuống database nè", description = "Cứ call bình" +
            " thường thôi ko có vấn đề gì đặc biệt cả!")
    @PutMapping("/{cusid}/avatar/update")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> updateAvatar(@PathVariable("cusid") Long cusid, @RequestParam("url") String url){
        if(authorizationService.checkAuthor(cusid)) {
            customerSeviveImp.updateAvatar(cusid,url);
            return new ResponseEntity<>(new Response("Thành Công",url,"200",""),
                    HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(new Response("Thất bại",url,"403","User không có quyền"),
                HttpStatusCode.valueOf(200));
    }


    @Operation(summary = "Hãy tạo cho mình 1 giỏ hàng để bắt đầu mua sắp!",
            description = "Mỗi 1 customer chỉ được phép tạo tối đa 1 giỏ hàng. API này chỉ được call 1 lần duy" +
                    " nhất với mỗi new customer")
    @GetMapping("/new-a-cart")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> creatCart(){
        if(customerSeviveImp.addCart()) // không check auth ở đây vì func addCart đã verify rùi!
                                        // tuy hơi vi phạm SOLID nhưng thôi kệ !!
            return new ResponseEntity<>(new Response("Thành Công","","200",""),
                    HttpStatusCode.valueOf(200));

        return new ResponseEntity<>(new Response("Thất bại",null,"400","Customer này" +
                " đã có giỏ hàng rồi!"), HttpStatusCode.valueOf(200));
    }


    @Operation(summary = "Thêm mới địa chỉ nhận hàng")
    @PostMapping("/{cusid}/creat-address")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> createAddress(@PathVariable("cusid") Long cusid,
                                           @RequestBody AddressRequest addressRequest){
        if(authorizationService.checkAuthor(cusid))
            customerSeviveImp.createAddress(cusid,addressRequest);
        return new ResponseEntity<>("Thành Công", HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Tạo kênh người bán từ một người dùng bình thường",
    description = "Sau khi tạo xong, seller Id sẽ được gửi lên thông qua data response." +
            " Có thể translate cusid -> sellerid thông qua 1 API khác")
    @GetMapping("/init-seller")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> chanelSeller(){
        if(customerSeviveImp.createSellerFromCustomer())
            return new ResponseEntity<>(new Response("Thành Công","","200",""),
                    HttpStatusCode.valueOf(200));
        return new ResponseEntity<>(new Response("Thất bại","","400","Không thể" +
                " tạo thêm seller từ 1 customer!"),
                HttpStatusCode.valueOf(200));
    }

    @GetMapping("/how-to-cusid")
    @Operation(summary = "Không tìm thấy cusid trong store thì call api này!",
    description = "cusid ở trong data kìa!")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> getCustomerIdNow() {
        return new ResponseEntity<>(new Response("Thành công", authorizationService.getCustomerId(),
                "200", ""), HttpStatusCode.valueOf(200));
    }


    @GetMapping("/{cusid}/how-to-sellerid")
    @Operation(summary = "API này mapping cusid với sellerid",
    description = "Nếu không thể chuyển đổi sẽ trả về -1. Trường hợp customer chưa khởi tạo seller mà call thì sẽ báo lỗi 403")
    @CrossOrigin("http://localhost:5500")
    public ResponseEntity<?> converstID(@PathVariable("cusid") Long cusid){
        if(customerSeviveImp.translateCusidToSellerId(cusid) == -1)
            return new ResponseEntity<>(new Response("Thất bại", customerSeviveImp.translateCusidToSellerId(cusid),
                    "400", "Không thể chuyển đổi! Có thế là do customer chưa tạo kênh người bán" +
                    " hoặc cusid không hợp lệ"), HttpStatusCode.valueOf(200));
        return new ResponseEntity<>(new Response("Thành công", customerSeviveImp.translateCusidToSellerId(cusid),
                "200", ""), HttpStatusCode.valueOf(200));
    }

}
