package uz.excel.onlineexcel.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.dto.student.StudentDto;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.entity.Student;
import uz.excel.onlineexcel.mapper.base.BaseMapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface StudentMapper extends BaseMapper<Student, StudentDto, StudentCreateDto, StudentUpdateDto> {

    @Override
    StudentDto toDto(Student student);

    @Override
    List<StudentDto> toDto(List<Student> e);

    @Override
    Student fromCreateDto(StudentCreateDto studentCreateDto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student fromUpdateDto(StudentUpdateDto dto, @MappingTarget Student entity);
}
