package com.tdx.demo.myapplication.model;

import java.util.List;

/**
 * Created on 2018/10/4.
 **/
public class DataModel {
    int version;
    List<MathModel> math;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<MathModel> getMath() {
        return math;
    }

    public void setMath(List<MathModel> math) {
        this.math = math;
    }
}
