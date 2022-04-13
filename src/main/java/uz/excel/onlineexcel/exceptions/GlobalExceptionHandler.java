package uz.excel.onlineexcel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.webjars.NotFoundException;
import uz.excel.onlineexcel.response.AppErrorDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;

@RestController
@ControllerAdvice("uz.excel")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handle500(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handle400(BadRequestException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handle404(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {CustomSQLException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handleSQL(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.CONFLICT, e.getMessage(), webRequest)));
    }

}
