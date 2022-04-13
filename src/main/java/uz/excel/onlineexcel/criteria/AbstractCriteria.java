package uz.excel.onlineexcel.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCriteria {
    protected int size;
    protected int page;

    public AbstractCriteria() {
    }

    public AbstractCriteria(int size, int page) {
        this.size = size;
        this.page = page;
    }

    public AbstractCriteria(int page) {
        this.page = page;
        this.size = 50;
    }
}
