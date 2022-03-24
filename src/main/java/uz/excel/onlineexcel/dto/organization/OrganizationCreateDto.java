package uz.excel.onlineexcel.dto.organization;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.excel.onlineexcel.dto.base.BaseDto;
import uz.excel.onlineexcel.entity.AuthUser;

@Getter
@Setter
@ToString
public class OrganizationCreateDto implements BaseDto {
    private String name;
    private String registrationNumber;
    private AuthUser owner;
}
