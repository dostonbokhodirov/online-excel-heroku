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

    @GetMapping("study-type-latin")
    public ResponseEntity<DataDto<List<String>>> getAllStudyTypesLatin() {
        return service.getAllStudyTypes("latin");
    }

    @GetMapping("study-type-cyrillic")
    public ResponseEntity<DataDto<List<String>>> getAllStudyTypesCyrillic() {
        return service.getAllStudyTypes("cyrillic");
    }

    @GetMapping("academic-type-latin")
    public ResponseEntity<DataDto<List<String>>> getAllAcademicTypesLatin() {
        return service.getAllAcademicTypes("latin");
    }

    @GetMapping("academic-type-cyrillic")
    public ResponseEntity<DataDto<List<String>>> getAllAcademicTypesCyrillic() {
        return service.getAllAcademicTypes("cyrillic");
    }

    @GetMapping("academic-level-latin")
    public ResponseEntity<DataDto<List<String>>> getAllAcademicLevelsLatin() {
        return service.getAllAcademicLevels("latin");
    }

    @GetMapping("academic-level-cyrillic")
    public ResponseEntity<DataDto<List<String>>> getAllAcademicLevelsCyrillic() {
        return service.getAllAcademicLevels("cyrillic");
    }

}
