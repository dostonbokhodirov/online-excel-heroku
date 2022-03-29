package uz.excel.onlineexcel.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.enums.AcademicLevel;
import uz.excel.onlineexcel.enums.AcademicType;
import uz.excel.onlineexcel.enums.StudyType;
import uz.excel.onlineexcel.response.AppErrorDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnumService implements BaseService {

    public ResponseEntity<DataDto<List<String>>> getAllStudyTypes(String language) {
        List<String> studyTypeList = new ArrayList<>();
        if (language.equalsIgnoreCase("latin")) {
            for (StudyType studyType : StudyType.values()) {
                studyTypeList.add(studyType.getLatin());
            }
            return new ResponseEntity<>(new DataDto<>(studyTypeList));
        }
        if (language.equalsIgnoreCase("cyrillic")) {
            for (StudyType studyType : StudyType.values()) {
                studyTypeList.add(studyType.getCyrillic());
            }
            return new ResponseEntity<>(new DataDto<>(studyTypeList));
        }
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, "Language is not available")));
    }

    public ResponseEntity<DataDto<List<String>>> getAllAcademicTypes(String language) {
        List<String> academicTypeList = new ArrayList<>();
        if (language.equalsIgnoreCase("latin")) {
            for (AcademicType academicType : AcademicType.values()) {
                academicTypeList.add(academicType.getLatin());
            }
            return new ResponseEntity<>(new DataDto<>(academicTypeList));
        }
        if (language.equalsIgnoreCase("cyrillic")) {
            for (AcademicType academicType : AcademicType.values()) {
                academicTypeList.add(academicType.getCyrillic());
            }
            return new ResponseEntity<>(new DataDto<>(academicTypeList));
        }
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, "Language is not available")));
    }

    public ResponseEntity<DataDto<List<String>>> getAllAcademicLevels(String language) {
        List<String> academicLevelList = new ArrayList<>();
        if (language.equalsIgnoreCase("latin")) {
            for (AcademicLevel academicLevel : AcademicLevel.values()) {
                academicLevelList.add(academicLevel.getLatin());
            }
            return new ResponseEntity<>(new DataDto<>(academicLevelList));
        }
        if (language.equalsIgnoreCase("cyrillic")) {
            for (AcademicLevel academicLevel : AcademicLevel.values()) {
                academicLevelList.add(academicLevel.getCyrillic());
            }
            return new ResponseEntity<>(new DataDto<>(academicLevelList));
        }
        return new ResponseEntity<>
                (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, "Language is not available")));
    }
}
