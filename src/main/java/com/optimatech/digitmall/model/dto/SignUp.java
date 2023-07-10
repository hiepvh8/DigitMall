package com.optimatech.digitmall.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Tutorial Model Information")
public class SignUp {
    @Schema(description = "Người dùng nhập username. Nếu username đã tồn tại -> nhận được thông báo lỗi (cái này Backend xủ lí)",
    example = "customer007")
    private String username;
    @Schema(description = "Yêu cầu frontend xử lí verifi password có độ dài tối thiểu 6 kí tự hoặc viết hoa/chứa kí tự đặc biệt/...",
    example = "A142536a@")
    private String password;
    @Schema(description = "Yêu cầu Frontend verifi email: xác nhận rằng email là hợp lệ/tồn tại",
    example = "hieubons1998@gmail.com")
    private String email;
}
