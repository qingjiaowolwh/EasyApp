package com.edu.zum.easyapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lwh on 2016/6/16.
 */
public class ResultModel implements Serializable {

    boolean error;
    List<GanHuoBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GanHuoBean> getResults() {
        return results;
    }

    public void setResults(List<GanHuoBean> results) {
        this.results = results;
    }

}
