package com.optimatech.digitmall.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Json Login")
public class Login {

    @Schema(example = "customer02")
    private String username;
    @Schema(example = "12345")
    private String password;
}
