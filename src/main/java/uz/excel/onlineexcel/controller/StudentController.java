package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.*;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.dto.FilterDto;
import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student/")
public class StudentController extends AbstractController<StudentService> {

    public StudentController(StudentService service) {
        super(service);
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<DataDto<StudentDto>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping(value = "create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody StudentCreateDto dto) {
        return service.create(dto);
    }

    @PatchMapping(value = "update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody StudentUpdateDto dto) {
        return service.update(dto);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("list")
    public ResponseEntity<DataDto<List<StudentDto>>> getAll() {
        return service.getAll();
    }

    @GetMapping("filter")
    public ResponseEntity<DataDto<List<StudentDto>>> getAllByFilter(@RequestBody FilterDto filterDto) {
        return service.getByFilter(filterDto);
    }

}
