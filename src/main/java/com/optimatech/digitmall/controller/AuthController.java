package com.optimatech.digitmall.controller;

import com.optimatech.digitmall.model.dto.Login;
import com.optimatech.digitmall.model.dto.SignUp;
import com.optimatech.digitmall.model.entity.Account;
import com.optimatech.digitmall.repository.AccountRepository;
import com.optimatech.digitmall.respone.Response;
import com.optimatech.digitmall.respone.Token;
import com.optimatech.digitmall.security.JwtService;
import com.optimatech.digitmall.serviceimp.AccountServiceImp;
import com.optimatech.digitmall.serviceimp.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Đăng kí/Đăng nhập", description = "truy cập không cần token")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AccountServiceImp accountService;
    private final AuthorizationService authorizationService;

    public AuthController(AccountRepository accountRepository,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          AccountServiceImp accountService,
                          AuthorizationService authorizationService) {

        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.authorizationService = authorizationService;
    }
    //private final EmailService emailService;

    @Operation(
            summary = "client gửi request theo form Json bên dưới để đăng kí thông tin user",
            description = "client sẽ báo lỗi nếu như username hoặc email đã tồn tại. Nếu thông tin" +
                    " hợp lệ thì server sẽ trả về một gói tin chứa token đăng nhập"

    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "User không có quyền (90%) hoặc lỗi tiềm ẩn server")
    })

//    @Parameters({
//            @Parameter(name = "username", description = "Nhập username"),
//            @Parameter(name = "password", description = "Nhập password - tạm thời chưa cần mã hóa"),
//            @Parameter(name = "email", description = "yêu cầu verifi email giúp server")
//    })
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUp accountDto){
        if(accountService.createAccount(accountDto)){
            String jwt = jwtService.generateToken(new Account(accountDto));
            return new ResponseEntity<>(new Response("Thành Công",new Token(jwt),"200","")
                    , HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(new Response("Thất bại",null,"400",
                                    "Username hoặc email đã tồn tại!"),
                                    HttpStatus.valueOf(200));
    }


    @Operation(
            summary = "client gửi request theo form Json bên dưới để đăng nhập và xử dụng token" +
                    " trả về để call các API khác",
            description = "Id trong message là customer ID đang đăng nhập! Dùng nó để call API có" +
                    " path /{cusid}. Nên lưu vào storage để sử dụng lâu dài!!"

    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400",description = "bad request"),
            @ApiResponse(responseCode = "500", description = "Lỗi phía server - Thông báo lại với server để fix"),
            @ApiResponse(responseCode = "403", description = "User không có quyền (90%) hoặc lỗi tiềm ẩn server")

    })
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login accountRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                accountRequest.getUsername(), accountRequest.getPassword()
        ));
        Optional<Account> account = accountRepository.findByUsername(accountRequest.getUsername());
        if(account.isPresent()){
            String jwt = jwtService.generateToken(account.get());
            return new ResponseEntity<>(new Response("Thành công",new Token(jwt),"200",
                    authorizationService.getCustomerId()),
                    HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(new Response("Thất bại",null,"400","Server không bao giờ lỗi \nLỗi do người dùng!OK"),
                HttpStatus.valueOf(200));

    }


}