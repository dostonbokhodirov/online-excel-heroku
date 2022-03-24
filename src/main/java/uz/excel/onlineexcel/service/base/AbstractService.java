package uz.excel.onlineexcel.service.base;

import uz.excel.onlineexcel.dto.student.StudentCreateDto;
import uz.excel.onlineexcel.mapper.base.Mapper;
import uz.excel.onlineexcel.repository.BaseRepository;

/**
 * @param <M> -> mapper
 * @param <R> -> repository
 */

public abstract class AbstractService<M extends Mapper, R extends BaseRepository> {

    protected final M mapper;
    protected final R repository;

    public AbstractService(M mapper, R repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
}
