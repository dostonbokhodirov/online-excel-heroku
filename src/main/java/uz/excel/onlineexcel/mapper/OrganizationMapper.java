package uz.excel.onlineexcel.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.excel.onlineexcel.dto.organization.OrganizationCreateDto;
import uz.excel.onlineexcel.dto.organization.OrganizationDto;
import uz.excel.onlineexcel.dto.organization.OrganizationUpdateDto;
import uz.excel.onlineexcel.entity.Organization;
import uz.excel.onlineexcel.mapper.base.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto> {
}
