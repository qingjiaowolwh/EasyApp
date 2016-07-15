package com.edu.zum.easyapp.model;

import com.ganhuo.entity.Ganhuo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lwh on 2016/6/16.
 */
public class ResultModel implements Serializable {

    boolean error;
    List<Ganhuo> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Ganhuo> getResults() {
        return results;
    }

    public void setResults(List<Ganhuo> results) {
        this.results = results;
    }

}
