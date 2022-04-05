package uz.excel.onlineexcel.dto.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
