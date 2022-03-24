package uz.excel.onlineexcel.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.FilterDto;
import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.mapper.StudentMapper;
import uz.excel.onlineexcel.repository.StudentRepository;
import uz.excel.onlineexcel.response.AppErrorDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;
import uz.excel.onlineexcel.service.base.GenericCrudService;
import uz.excel.onlineexcel.service.base.GenericService;

import java.sql.*;
import java.util.*;

@Service
public class StudentService
        extends AbstractService<StudentMapper, StudentRepository>
        implements GenericCrudService<StudentDto, StudentCreateDto, StudentUpdateDto>,
        GenericService<StudentDto>, BaseService {

    public StudentService(StudentMapper mapper, StudentRepository repository) {
        super(mapper, repository);
    }


    public ResponseEntity<DataDto<List<StudentDto>>> getByFilter(FilterDto filterDto) {

        List<StudentDto> returnStudent = new ArrayList<>(Collections.emptyList());

        if (Objects.isNull(filterDto)) {
            return getAll();
        } else {

            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/online_excel", "postgres", "iPhone0303");

                boolean isJoin = false;
                StringBuilder query = new StringBuilder("select  * from auth.student where ");

                if (Objects.nonNull(filterDto.getFullName())) {
                    query.append("full_name = ").append(filterDto.getFullName());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getUniversityName())) {
                    if (isJoin) {
                        query.append(" and university_name = ").append(filterDto.getUniversityName());
                    }
                    query.append("university_name = ").append(filterDto.getUniversityName());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getEntranceYear())) {
                    if (isJoin) {
                        query.append(" and entrance_year = ").append(filterDto.getEntranceYear());
                    }
                    query.append("entrance_year = ").append(filterDto.getEntranceYear());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getGraduationYear())) {
                    if (isJoin) {
                        query.append(" and graduation_year = ").append(filterDto.getGraduationYear());
                    }
                    query.append("graduation_year = ").append(filterDto.getGraduationYear());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getFaculty())) {
                    if (isJoin) {
                        query.append(" and faculty = ").append(filterDto.getFaculty());
                    }
                    query.append("faculty = ").append(filterDto.getFaculty());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getSpeciality())) {
                    if (isJoin) {
                        query.append(" and speciality = ").append(filterDto.getSpeciality());
                    }
                    query.append("speciality = ").append(filterDto.getSpeciality());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getStudyType())) {
                    if (isJoin) {
                        query.append(" and study_type = ").append(filterDto.getStudyType());
                    }
                    query.append("study_type = ").append(filterDto.getStudyType());
                    isJoin = true;
                }


                if (Objects.nonNull(filterDto.getAcademicType())) {
                    if (isJoin) {
                        query.append(" and academic_type = ").append(filterDto.getAcademicType());
                    }
                    query.append("academic_type = ").append(filterDto.getAcademicType());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getDiplomaSerial())) {
                    if (isJoin) {
                        query.append(" and diploma_serial = ").append(filterDto.getDiplomaSerial());
                    }
                    query.append("diploma_serial = ").append(filterDto.getDiplomaSerial());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getDiplomaRegistrationNumber())) {
                    if (isJoin) {
                        query.append(" and diploma_registration_number = ").append(filterDto.getDiplomaRegistrationNumber());
                    }
                    query.append("diploma_registration_number = ").append(filterDto.getDiplomaRegistrationNumber());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getGivenDate())) {
                    if (isJoin) {
                        query.append(" and given_date = ").append(filterDto.getGivenDate());
                    }
                    query.append("given_date = ").append(filterDto.getGivenDate());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getAcademicLevel())) {
                    if (isJoin) {
                        query.append(" and academic_level = ").append(filterDto.getAcademicLevel());
                    }
                    query.append("academic_level = ").append(filterDto.getAcademicLevel());
                    isJoin = true;
                }

                if (Objects.nonNull(filterDto.getAppendixNumber())) {
                    if (isJoin) {
                        query.append(" and appendix_number = ").append(filterDto.getAppendixNumber());
                    }
                    query.append("appendix_number = ").append(filterDto.getAppendixNumber());
                }

                query.append(" order by entrance_year desc limit 50");


                PreparedStatement statement = connection.prepareStatement(query.toString());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {

                    StudentDto studentDto = StudentDto.builder()
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

                    returnStudent.add(studentDto);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(new DataDto<>(returnStudent));
        }

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
    public ResponseEntity<DataDto<List<StudentDto>>> getAll() {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAllByCount())));
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

}
