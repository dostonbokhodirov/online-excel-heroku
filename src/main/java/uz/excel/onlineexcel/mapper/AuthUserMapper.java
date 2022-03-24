package uz.excel.onlineexcel.mapper;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import uz.excel.onlineexcel.dto.auth.AuthUserCreateDto;
import uz.excel.onlineexcel.dto.auth.AuthUserDto;
import uz.excel.onlineexcel.dto.auth.AuthUserUpdateDto;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.mapper.base.BaseMapper;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthUserMapper extends BaseMapper<AuthUser,
        AuthUserDto,
        AuthUserCreateDto,
        AuthUserUpdateDto> {

    @Override
    @Mapping(target = "picture",ignore = true)
    AuthUserDto toDto(AuthUser authUser);

    @Override
    List<AuthUserDto> toDto(List<AuthUser> e);

    @Override
    @Mapping(target="picture",ignore = true)
    @Mapping(target="password",ignore = true)
    AuthUser fromCreateDto(AuthUserCreateDto authUserCreateDto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="picture",ignore = true)
    AuthUser fromUpdateDto(AuthUserUpdateDto authUserUpdateDto,@MappingTarget AuthUser dto);

    @Override
    @Mapping(target="picture",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUser fromUpdateDto(AuthUserUpdateDto dto);
}
