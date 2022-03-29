package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.EnumService;

import java.util.List;

@RestController
@RequestMapping("/enum/")
public class EnumController extends AbstractController<EnumService> {

    public EnumController(EnumService service) {
        super(service);
    }

    @GetMapping("study-type-list")
    public ResponseEntity<DataDto<List<String>>> getAllStudyTypes(String language) {
        return service.getAllStudyTypes(language);
    }

    @GetMapping("academic-type-list")
    public ResponseEntity<DataDto<List<String>>> getAllAcademicTypes(String language) {
        return service.getAllAcademicTypes(language);
    }

    @GetMapping("academic-level-list")
    public ResponseEntity<DataDto<List<String>>> getAllAcademicLevels(String language) {
        return service.getAllAcademicLevels(language);
    }


}
