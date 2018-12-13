package com.tdx.demo.myapplication.model;

import java.util.List;

/**
 * 最近成交记录
 * Created on 2018/12/13.
 **/
public class LastTradeModel {


    /**
     * ch : market.ETH_NW.trade.detail
     * status : ok
     * tick : {"data":[{"amount":"18","direction":"buy","id":16258531282586558,"price":"89.538","ts":1544714752273}],"id":1544714752436,"ts":1544714752436}
     * ts : 1544714752436
     */

    private String ch;
    private String status;
    private TickEntity tick;
    private long ts;

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TickEntity getTick() {
        return tick;
    }

    public void setTick(TickEntity tick) {
        this.tick = tick;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public static class TickEntity {
        /**
         * data : [{"amount":"18","direction":"buy","id":16258531282586558,"price":"89.538","ts":1544714752273}]
         * id : 1544714752436
         * ts : 1544714752436
         */

        private long id;
        private long ts;
        private List<DataEntity> data;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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
             * amount : 18
             * direction : buy
             * id : 16258531282586558
             * price : 89.538
             * ts : 1544714752273
             */

            private String amount;
            private String direction;
            private long id;
            private String price;
            private long ts;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public long getTs() {
                return ts;
            }

            public void setTs(long ts) {
                this.ts = ts;
            }
        }
    }
}
