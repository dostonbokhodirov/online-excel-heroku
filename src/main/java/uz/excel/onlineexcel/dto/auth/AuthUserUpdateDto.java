package uz.excel.onlineexcel.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.excel.onlineexcel.dto.base.GenericDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserUpdateDto extends GenericDto {

    private Long id;
    private String username;

    private String password;

    private String fullName;

    private String phone;

    private MultipartFile picture;

}
