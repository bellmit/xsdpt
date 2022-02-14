package com.nju.sdpt.model;

import com.nju.sdpt.entity.StatisticsFgModel1;

import java.util.List;

/**
 * @author jiaweiq
 * @date 2022/1/8 13:59
 */
public class StatisticsFg {
    private StatisticsFgModel1 total;
    private List<StatisticsFgModel> details;

    public StatisticsFg() {
    }

    public StatisticsFgModel1 getTotal() {
        return total;
    }

    public void setTotal(StatisticsFgModel1 total) {
        this.total = total;
    }

    public List<StatisticsFgModel> getDetails() {
        return details;
    }

    public void setDetails(List<StatisticsFgModel> details) {
        this.details = details;
    }
}
