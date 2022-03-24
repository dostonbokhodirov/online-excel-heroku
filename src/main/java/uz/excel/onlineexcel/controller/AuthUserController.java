package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.*;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.dto.auth.*;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.AuthService;
import uz.excel.onlineexcel.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthUserController extends AbstractController<AuthService> {

    public final UserService userService;

    public AuthUserController(AuthService service, UserService userService) {
        super(service);
        this.userService = userService;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> getToken(@RequestBody LoginDto dto) {
        return service.getToken(dto);
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<DataDto<SessionDto>> getToken(HttpServletRequest request, HttpServletResponse response) {
        return service.refreshToken(request, response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(@RequestBody AuthUserCreateDto dto) {
        return userService.create(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> update(@RequestBody AuthUserUpdateDto dto) {
        return userService.update(dto);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<AuthUserDto>> get(@PathVariable Long id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<AuthUserDto>>> getAll() {
        return userService.getAll();
    }

}
