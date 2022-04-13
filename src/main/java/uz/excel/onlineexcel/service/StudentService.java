package uz.excel.onlineexcel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.criteria.StudentCriteria;
import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.exceptions.CustomSQLException;
import uz.excel.onlineexcel.mapper.StudentMapper;
import uz.excel.onlineexcel.property.ConnectionProperties;
import uz.excel.onlineexcel.repository.StudentRepository;
import uz.excel.onlineexcel.response.AppErrorDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;
import uz.excel.onlineexcel.service.base.GenericCrudService;
import uz.excel.onlineexcel.service.base.GenericService;

import javax.validation.Valid;
import java.sql.*;
import java.util.*;

@Service
public class StudentService
        extends AbstractService<StudentMapper, StudentRepository>
        implements GenericCrudService<StudentDto, StudentCreateDto, StudentUpdateDto>,
        GenericService<StudentDto>, BaseService {

    private final ConnectionProperties properties;

    public StudentService(StudentMapper mapper, StudentRepository repository, ConnectionProperties properties) {
        super(mapper, repository);
        this.properties = properties;
    }

    @Override
    public ResponseEntity<DataDto<StudentDto>> get(Long id) {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent()) {
            StudentDto studentDto = mapper.toDto(student.get());
            return new ResponseEntity<>(new DataDto<>(studentDto));
        } else {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message("STUDENT_NOT_FOUND")
                    .status(HttpStatus.NOT_FOUND)
                    .build()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(StudentCreateDto dto) {
        Student student = mapper.fromCreateDto(dto);
        Student save = repository.save(student);
        return new ResponseEntity<>(new DataDto<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(StudentUpdateDto dto) {
        Student student = mapper.fromUpdateDto(dto);
        Student save = repository.save(student);
        return new ResponseEntity<>(new DataDto<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new DataDto<>(true));
    }

    @Override
    public ResponseEntity<DataDto<List<StudentDto>>> getAll() {
        Pageable pageable = PageRequest.of(0, 50, Sort.by("entranceYear").descending());
        List<Student> studentList = repository.findAll(pageable).getContent();
//        List<Student> studentList = repository.findAllByCount();
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(studentList), (long) studentList.size()));
    }

    public ResponseEntity<DataDto<List<StudentDto>>> getAll(StudentCriteria criteria) {
        List<StudentDto> students = new ArrayList<>(Collections.emptyList());
        if (Objects.isNull(criteria)) return getAll();
        try {
            Connection connection =
                    DriverManager.getConnection(
                            properties.getUrl(),
                            properties.getUsername(),
                            properties.getPassword());
            boolean isJoin = false;
            StringBuilder query = new StringBuilder("select  *  from public.student where");

            if (Objects.nonNull(criteria.getFullName())) {
                query.append(" full_name ilike '%").append(criteria.getFullName()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getUniversityName())) {
                if (isJoin)
                    query.append(" and university_name ilike '%").append(criteria.getUniversityName()).append("%'");
                else query.append(" university_name ilike '%").append(criteria.getUniversityName()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getEntranceYear())) {
                if (isJoin) query.append(" and entrance_year ilike '%").append(criteria.getEntranceYear()).append("%'");
                else query.append(" entrance_year ilike '%").append(criteria.getEntranceYear()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getGraduationYear())) {
                if (isJoin)
                    query.append(" and graduation_year ilike '%").append(criteria.getGraduationYear()).append("%'");
                else query.append(" graduation_year ilike '%").append(criteria.getGraduationYear()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getFaculty())) {
                if (isJoin) query.append(" and faculty ilike '%").append(criteria.getFaculty()).append("%'");
                else query.append(" faculty ilike '%").append(criteria.getFaculty()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getSpeciality())) {
                if (isJoin) query.append(" and speciality ilike '%").append(criteria.getSpeciality()).append("%'");
                else query.append(" speciality ilike '%").append(criteria.getSpeciality()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getStudyType())) {
                if (isJoin) query.append(" and study_type ilike '%").append(criteria.getStudyType()).append("%'");
                else query.append(" study_type ilike '%").append(criteria.getStudyType()).append("%'");
                isJoin = true;
            }


            if (Objects.nonNull(criteria.getAcademicType())) {
                if (isJoin) query.append(" and academic_type ilike '%").append(criteria.getAcademicType()).append("%'");
                else query.append(" academic_type ilike '%").append(criteria.getAcademicType()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getDiplomaSerial())) {
                if (isJoin)
                    query.append(" and diploma_serial ilike '%").append(criteria.getDiplomaSerial()).append("%'");
                else query.append(" diploma_serial ilike '%").append(criteria.getDiplomaSerial()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getDiplomaRegistrationNumber())) {
                if (isJoin)
                    query.append(" and diploma_registration_number ilike '%").append(criteria.getDiplomaRegistrationNumber()).append("%%'");
                else query.append(" diploma_registration_number ilike '%")
                        .append(criteria.getDiplomaRegistrationNumber()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getGivenDate())) {
                if (isJoin) query.append(" and given_date ilike '%").append(criteria.getGivenDate()).append("%'");
                else query.append(" given_date ilike '%").append(criteria.getGivenDate()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getAcademicLevel())) {
                if (isJoin)
                    query.append(" and academic_level ilike '%").append(criteria.getAcademicLevel()).append("%'");
                else query.append(" academic_level ilike '%").append(criteria.getAcademicLevel()).append("%'");
                isJoin = true;
            }

            if (Objects.nonNull(criteria.getAppendixNumber())) {
                if (isJoin)
                    query.append(" and appendix_number ilike '%").append(criteria.getAppendixNumber()).append("%'");
                else query.append(" appendix_number ilike '%").append(criteria.getAppendixNumber()).append("%'");
            }

            PreparedStatement statement = connection.prepareStatement(query.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StudentDto studentDto = StudentDto.builder()
                        .id(resultSet.getLong("id"))
                        .fullName(resultSet.getString("full_name"))
                        .universityName(resultSet.getString("university_name"))
                        .entranceYear(resultSet.getString("entrance_year"))
                        .graduationYear(resultSet.getString("graduation_year"))
                        .faculty(resultSet.getString("faculty"))
                        .speciality(resultSet.getString("speciality"))
                        .studyType(resultSet.getString("study_type"))
                        .academicType(resultSet.getString("academic_type"))
                        .diplomaSerial(resultSet.getString("diploma_serial"))
                        .diplomaRegistrationNumber(resultSet.getString("diploma_registration_number"))
                        .givenDate(resultSet.getString("given_date"))
                        .academicLevel(resultSet.getString("academic_level"))
                        .appendixNumber(resultSet.getString("appendix_number"))
                        .organizationId(resultSet.getLong("organization_id"))
                        .build();
                students.add(studentDto);
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e.getMessage());
        }
        return new ResponseEntity<>(new DataDto<>(students, (long) students.size()));
    }

}
