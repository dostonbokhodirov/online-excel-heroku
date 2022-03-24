package uz.excel.onlineexcel.service.base;

import uz.excel.onlineexcel.dto.base.BaseDto;
import uz.excel.onlineexcel.dto.base.GenericDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;

/**
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 */
public interface GenericCrudService<
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto> extends GenericService<D> {

    ResponseEntity<DataDto<Long>> create(CD createDto);

    ResponseEntity<DataDto<Boolean>> delete(Long id);

    ResponseEntity<DataDto<Long>> update(UD updateDto);

}
