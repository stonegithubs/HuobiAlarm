package com.tdx.demo.myapplication.model;

import java.util.List;

/**
 * 合约指数信息
 * Created on 2018/12/13.
 **/
public class ContractIndexModel {

    /**
     * status : ok
     * data : [{"symbol":"BTC","index_price":471.0817}]
     * ts : 1490759594752
     */

    private String status;
    private long ts;
    private List<DataEntity> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * symbol : BTC
         * index_price : 471.0817
         */

        private String symbol;
        private double index_price;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getIndex_price() {
            return index_price;
        }

        public void setIndex_price(double index_price) {
            this.index_price = index_price;
        }
    }
}
