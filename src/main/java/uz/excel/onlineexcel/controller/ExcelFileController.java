package uz.excel.onlineexcel.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.service.ExcelFileService;
import uz.excel.onlineexcel.utils.MediaTypeUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/excel/")
public class ExcelFileController extends AbstractController<ExcelFileService> {

    private final ServletContext servletContext;

    public ExcelFileController(ExcelFileService service, ServletContext servletContext) {
        super(service);
        this.servletContext = servletContext;
    }

    @PostMapping("/download")
    public ResponseEntity<InputStreamResource> getExcelFile(@RequestBody ArrayList<StudentDto> dtoList) throws IOException {

//        List<StudentDto> dtoList = new ArrayList<>();
//        StudentDto dto = StudentDto.builder()
//                .fullName("a")
//                .universityName("a")
//                .entranceYear("a")
//                .graduationYear("a")
//                .faculty("a")
//                .speciality("a")
//                .studyType("a")
//                .academicType("a")
//                .diplomaSerial("a")
//                .diplomaRegistrationNumber("a")
//                .givenDate("a")
//                .academicLevel("a")
//                .appendixNumber("a")
//                .organizationId(1L).build();
//        dtoList.add(dto);

        String excelFileName = service.createExcelFile(dtoList);
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, excelFileName);
        File file = new File(excelFileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

    @GetMapping(value = "/upload/{id}")
    public ResponseEntity<Boolean> uploadFile(@PathVariable String id) {
        return !id.equals("123123")
                ? new ResponseEntity<>(false, HttpStatus.FORBIDDEN)
                : service.upload()
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }


}
