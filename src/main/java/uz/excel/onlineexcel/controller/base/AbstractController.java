package uz.excel.onlineexcel.controller.base;

import uz.excel.onlineexcel.service.base.BaseService;


public abstract class AbstractController<S extends BaseService> {

    protected final S service;

    public AbstractController(S service) {
        this.service = service;
    }
}
