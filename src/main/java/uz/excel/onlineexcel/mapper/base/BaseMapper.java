package uz.excel.onlineexcel.mapper.base;


import org.mapstruct.MappingTarget;
import uz.excel.onlineexcel.dto.student.StudentUpdateDto;
import uz.excel.onlineexcel.entity.Student;

import java.util.List;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 */

public interface BaseMapper<E, D, CD, UD> extends Mapper {

    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD cd);

    E fromUpdateDto(UD dto);

    E fromUpdateDto(UD dto ,@MappingTarget E entity);

}
