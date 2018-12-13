package com.tdx.demo.myapplication.model;

import java.util.List;

/**
 * Created on 2018/10/4.
 **/
public class MathModel {

    private int no;
    private String date;
    private String date2;
    private List<Integer> mathData;
    private int max;
    private int min;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public List<Integer> getMathData() {
        return mathData;
    }

    public void setMathData(List<Integer> mathData) {
        this.mathData = mathData;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
