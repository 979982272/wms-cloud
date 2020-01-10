package cn.czcxy.xj.core.platform.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class DataSourceResult implements Serializable {
    private long total;
    private List<?> data;
    private Map<String, Object> aggregates;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Map<String, Object> getAggregates() {
        if (aggregates == null) {
            aggregates = new HashMap<String, Object>();
        }
        return aggregates;
    }

    public void setAggregates(Map<String, Object> aggregates) {
        this.aggregates = aggregates;
    }
}
