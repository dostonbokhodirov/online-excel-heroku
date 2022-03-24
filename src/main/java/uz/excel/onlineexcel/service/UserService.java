package uz.excel.onlineexcel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.auth.AuthUserCreateDto;
import uz.excel.onlineexcel.dto.auth.AuthUserDto;
import uz.excel.onlineexcel.dto.auth.AuthUserUpdateDto;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.enums.AuthRole;
import uz.excel.onlineexcel.mapper.AuthUserMapper;
import uz.excel.onlineexcel.repository.AuthUserRepository;
import uz.excel.onlineexcel.response.AppErrorDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.GenericCrudService;
import uz.excel.onlineexcel.service.base.GenericService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService extends AbstractService<AuthUserMapper, AuthUserRepository>
        implements GenericCrudService<AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto>, GenericService<AuthUserDto> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AuthUserMapper mapper, AuthUserRepository repository, PasswordEncoder passwordEncoder) {
        super(mapper, repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(AuthUserCreateDto dto) {
        try {
            AuthUser authUser = mapper.fromCreateDto(dto);
            authUser.setRole(AuthRole.EMPLOYEE);
            authUser.setPicture(dto.getPicture().getBytes());
            authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            AuthUser save = repository.save(authUser);
            return new ResponseEntity<>(new DataDto<>(save.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message(e.getMessage())
                    .status(HttpStatus.NOT_FOUND)
                    .build()), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new DataDto<>(), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<AuthUserDto>> get(Long id) {
        Optional<AuthUser> authUser = repository.findById(id);
        if (authUser.isPresent()) {
            AuthUserDto authUserDto = mapper.toDto(authUser.get());
            return new ResponseEntity<>(new DataDto<>(authUserDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message("USER_NOT_FOUND")
                    .status(HttpStatus.NOT_FOUND)
                    .build()));
        }
    }

    public ResponseEntity<DataDto<List<AuthUserDto>>> getAll() {
        List<AuthUser> authUserList = repository.findAll();
        List<AuthUserDto> authUserDtoList = mapper.toDto(authUserList);
        return new ResponseEntity<>(new DataDto<>(authUserDtoList));
    }

    public ResponseEntity<DataDto<Long>> update(AuthUserUpdateDto dto) {
        try {
            Optional<AuthUser> authUser = repository.findById(dto.getId());
            AuthUser authUser1 = mapper.fromUpdateDto(dto, authUser.get());
            if (Objects.nonNull(dto.getPicture())) {
                authUser1.setPicture(dto.getPicture().getBytes());
            }
            return new ResponseEntity<>(new DataDto<>(repository.save(authUser1).getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message("USER_NOT_FOUND")
                    .status(HttpStatus.NOT_FOUND)
                    .build()));
        }
    }
}
