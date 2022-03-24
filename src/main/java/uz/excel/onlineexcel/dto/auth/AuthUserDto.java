package uz.excel.onlineexcel.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.excel.onlineexcel.dto.base.GenericDto;

@Getter
@Setter
public class AuthUserDto extends GenericDto {
//    private String userName;
//
//    private String password;

    private String userName;

    private String password;

    private String fullName;

    private String phone;

    private String picture;
}
