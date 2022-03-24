package uz.excel.onlineexcel.service.base;


import uz.excel.onlineexcel.dto.base.GenericDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;

import java.util.List;


public interface GenericService<D extends GenericDto> extends BaseService {

    ResponseEntity<DataDto<List<D>>> getAll();

    ResponseEntity<DataDto<D>> get(Long id);

}
