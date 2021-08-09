package com.sskj.level.bean;

import java.io.Serializable;
import java.util.List;

public class WSFiveBean implements Serializable{

    /**
     * asks : [{"price":30442.74,"amount":0.1926},{"price":30442.75,"amount":0.0184},{"price":30443.86,"amount":0.0015},{"price":30443.99,"amount":0.0016},{"price":30444.48,"amount":0.0027},{"price":30444.56,"amount":2.0E-4},{"price":30444.81,"amount":5.0E-4},{"price":30445.42,"amount":0.2},{"price":30445.97,"amount":2.0E-4},{"price":30446.16,"amount":0.0098},{"price":30446.26,"amount":0.001},{"price":30446.35,"amount":0.0034},{"price":30446.8,"amount":0.0014},{"price":30447.21,"amount":7.0E-4},{"price":30448.04,"amount":0.0027},{"price":30448.31,"amount":0.0106},{"price":30448.52,"amount":0.0053},{"price":30449.28,"amount":0.0048},{"price":30449.29,"amount":0.328},{"price":30449.85,"amount":0.0173},{"price":30450,"amount":2.0025},{"price":30450.02,"amount":3.0E-4},{"price":30450.03,"amount":4.0E-4},{"price":30450.08,"amount":0.0016},{"price":30450.46,"amount":0.0493},{"price":30450.5,"amount":2.0E-4},{"price":30451.19,"amount":8.0E-4},{"price":30451.57,"amount":1.1494},{"price":30451.67,"amount":0.0657},{"price":30451.71,"amount":0.0029},{"price":30451.72,"amount":0.001},{"price":30451.74,"amount":2.0E-4},{"price":30451.93,"amount":0.001},{"price":30452.03,"amount":2.0E-4},{"price":30452.16,"amount":0.2},{"price":30452.17,"amount":0.0179},{"price":30452.38,"amount":5.0E-4},{"price":30452.55,"amount":0.0018},{"price":30452.63,"amount":2.0E-4},{"price":30452.79,"amount":0.0095},{"price":30452.9,"amount":0.0329},{"price":30452.91,"amount":2.0E-4},{"price":30453.21,"amount":0.0016},{"price":30453.3,"amount":7.0E-4},{"price":30453.7,"amount":0.0185},{"price":30453.86,"amount":0.0343},{"price":30454.23,"amount":4.0E-4},{"price":30454.56,"amount":0.0052},{"price":30454.7,"amount":2.0E-4},{"price":30455.22,"amount":0.0017},{"price":30455.29,"amount":0.0022},{"price":30455.77,"amount":0.0486},{"price":30455.98,"amount":0.0011},{"price":30455.99,"amount":0.0038},{"price":30456.07,"amount":0.001},{"price":30456.16,"amount":0.0016},{"price":30456.38,"amount":0.0029},{"price":30456.74,"amount":0.06},{"price":30456.77,"amount":0.0351},{"price":30456.87,"amount":0.0184},{"price":30456.97,"amount":0.007},{"price":30457.3,"amount":0.0343},{"price":30457.6,"amount":0.5},{"price":30457.9,"amount":2.0E-4},{"price":30458.2,"amount":0.1},{"price":30458.21,"amount":0.0171},{"price":30458.48,"amount":3.0E-4},{"price":30458.95,"amount":5.0E-4},{"price":30459.12,"amount":7.0E-4},{"price":30459.15,"amount":0.5},{"price":30459.16,"amount":0.0019},{"price":30459.39,"amount":7.0E-4},{"price":30459.65,"amount":7.0E-4},{"price":30460.02,"amount":0.0986},{"price":30460.19,"amount":0.0022},{"price":30460.42,"amount":0.0066},{"price":30460.66,"amount":0.15},{"price":30460.71,"amount":4.0E-4},{"price":30460.75,"amount":0.001},{"price":30460.86,"amount":1.5196},{"price":30460.98,"amount":6.0E-4},{"price":30461.22,"amount":0.0065},{"price":30461.82,"amount":0.25},{"price":30461.93,"amount":0.001},{"price":30462.25,"amount":0.0016},{"price":30462.74,"amount":0.5},{"price":30462.82,"amount":0.08},{"price":30462.97,"amount":8.0E-4},{"price":30463.06,"amount":0.7503},{"price":30463.32,"amount":9.0E-4},{"price":30463.35,"amount":0.0686},{"price":30463.72,"amount":0.0099},{"price":30463.73,"amount":7.0E-4},{"price":30463.8,"amount":0.0056},{"price":30463.82,"amount":0.0175},{"price":30463.85,"amount":6.0E-4},{"price":30464.2,"amount":0.2572},{"price":30464.28,"amount":0.025},{"price":30464.63,"amount":0.257},{"price":30465.04,"amount":0.001},{"price":30465.16,"amount":2.0E-4},{"price":30465.19,"amount":7.0E-4},{"price":30465.48,"amount":7.0E-4},{"price":30465.64,"amount":0.0098},{"price":30465.71,"amount":0.0012},{"price":30465.88,"amount":0.5142},{"price":30466.17,"amount":2.0E-4},{"price":30466.23,"amount":6.0E-4},{"price":30466.36,"amount":2.0E-4},{"price":30466.67,"amount":0.0095},{"price":30466.92,"amount":0.0686},{"price":30467.02,"amount":3.0E-4},{"price":30467.1,"amount":0.0027},{"price":30467.18,"amount":0.0018},{"price":30467.69,"amount":0.16},{"price":30467.78,"amount":1.55},{"price":30467.89,"amount":4.0E-4},{"price":30468.19,"amount":5.0E-4},{"price":30468.24,"amount":7.0E-4},{"price":30468.35,"amount":0.0016},{"price":30468.57,"amount":0.003},{"price":30469.03,"amount":0.0157},{"price":30469.12,"amount":0.002},{"price":30469.18,"amount":0.32},{"price":30469.29,"amount":0.002},{"price":30469.52,"amount":2.0E-4},{"price":30469.68,"amount":0.0686},{"price":30469.93,"amount":6.0E-4},{"price":30470.19,"amount":0.0154},{"price":30470.23,"amount":0.0029},{"price":30470.36,"amount":4.0E-4},{"price":30470.8,"amount":0.0045},{"price":30470.83,"amount":0.0165},{"price":30470.99,"amount":0.0184},{"price":30471.04,"amount":0.64},{"price":30471.09,"amount":0.007},{"price":30471.32,"amount":0.0104},{"price":30471.52,"amount":0.99},{"price":30471.57,"amount":7.0E-4},{"price":30471.7,"amount":0.15},{"price":30471.74,"amount":0.01},{"price":30471.92,"amount":7.0E-4},{"price":30472.17,"amount":3.0E-4},{"price":30472.39,"amount":0.5085},{"price":30472.5,"amount":3.0E-4},{"price":30473,"amount":0.0013},{"price":30473.46,"amount":2.0E-4},{"price":30474.11,"amount":1.28},{"price":30474.13,"amount":2.0E-4},{"price":30474.34,"amount":2.0E-4}]
     * bids : [{"price":30442.73,"amount":0.0472},{"price":30440.59,"amount":0.0863},{"price":30440.58,"amount":0.0056},{"price":30440.29,"amount":0.1904},{"price":30439.53,"amount":0.08},{"price":30438.89,"amount":0.0056},{"price":30436.75,"amount":0.6055},{"price":30436.74,"amount":0.0384},{"price":30436.73,"amount":0.0063},{"price":30435.76,"amount":0.03},{"price":30434.94,"amount":0.03},{"price":30434.57,"amount":0.006},{"price":30433.87,"amount":0.03},{"price":30433.83,"amount":0.12},{"price":30431.26,"amount":0.03},{"price":30429.21,"amount":0.033},{"price":30429.19,"amount":0.2},{"price":30429.09,"amount":0.03},{"price":30427.98,"amount":0.16},{"price":30427.97,"amount":0.15},{"price":30427.77,"amount":0.32},{"price":30426.89,"amount":0.25},{"price":30426.88,"amount":0.5},{"price":30426.47,"amount":0.001},{"price":30426.26,"amount":0.001},{"price":30426.08,"amount":0.0147},{"price":30424.81,"amount":0.1},{"price":30424.41,"amount":0.0986},{"price":30424.27,"amount":1.28},{"price":30424.21,"amount":3.0E-4},{"price":30423.52,"amount":0.75},{"price":30421.8,"amount":1.4135},{"price":30421.79,"amount":0.4},{"price":30421.3,"amount":0.0013},{"price":30420,"amount":0.0173},{"price":30419.99,"amount":0.03},{"price":30419.78,"amount":0.025},{"price":30419.73,"amount":0.05},{"price":30419.23,"amount":0.5},{"price":30419.05,"amount":0.1},{"price":30418.36,"amount":0.0173},{"price":30418.09,"amount":0.125},{"price":30418,"amount":0.3293},{"price":30417.75,"amount":0.037},{"price":30417.5,"amount":0.0164},{"price":30417.17,"amount":0.2572},{"price":30416.26,"amount":0.001},{"price":30415.96,"amount":0.5143},{"price":30415.06,"amount":0.0343},{"price":30414.08,"amount":0.2572},{"price":30414.07,"amount":0.0163},{"price":30414,"amount":0.0354},{"price":30413.7,"amount":0.0346},{"price":30412.87,"amount":0.0018},{"price":30411.21,"amount":0.0163},{"price":30408.26,"amount":0.5},{"price":30407.97,"amount":0.0658},{"price":30407.41,"amount":0.15},{"price":30407.31,"amount":0.002},{"price":30407.04,"amount":0.3286},{"price":30406.26,"amount":0.001},{"price":30405.91,"amount":0.1},{"price":30405.73,"amount":0.99},{"price":30404.93,"amount":0.0986},{"price":30404.17,"amount":0.0098},{"price":30403.72,"amount":0.0822},{"price":30401.63,"amount":0.0023},{"price":30401.29,"amount":0.1},{"price":30401.13,"amount":0.0843},{"price":30400.32,"amount":0.0686},{"price":30399.66,"amount":0.0394},{"price":30399.17,"amount":0.2},{"price":30396.67,"amount":0.1048},{"price":30396.49,"amount":0.103},{"price":30396.26,"amount":0.001},{"price":30395.65,"amount":0.0686},{"price":30395.24,"amount":0.004},{"price":30394.44,"amount":0.007},{"price":30394.02,"amount":0.0017},{"price":30394,"amount":0.3866},{"price":30393.79,"amount":0.3288},{"price":30393.34,"amount":0.1248},{"price":30393.23,"amount":0.06},{"price":30392.34,"amount":0.0011},{"price":30392.31,"amount":0.1544},{"price":30392.14,"amount":8.0E-4},{"price":30391.21,"amount":0.0026},{"price":30391.09,"amount":0.06},{"price":30389.92,"amount":0.03},{"price":30389.8,"amount":0.5},{"price":30389.48,"amount":0.2},{"price":30387.77,"amount":0.1544},{"price":30387.47,"amount":0.0686},{"price":30386.37,"amount":0.007},{"price":30386.27,"amount":0.0184},{"price":30386.26,"amount":0.001},{"price":30386.15,"amount":0.06},{"price":30385.43,"amount":0.0027},{"price":30384.74,"amount":0.0098},{"price":30384.19,"amount":0.0012},{"price":30383.39,"amount":0.0095},{"price":30383.34,"amount":0.003},{"price":30383.22,"amount":0.0037},{"price":30382.62,"amount":0.01},{"price":30382.6,"amount":0.0038},{"price":30382.54,"amount":8.0E-4},{"price":30382.5,"amount":0.0012},{"price":30382.49,"amount":0.2},{"price":30381.26,"amount":0.0062},{"price":30380.61,"amount":0.06},{"price":30380.51,"amount":0.0347},{"price":30379.65,"amount":0.1199},{"price":30379.27,"amount":9.0E-4},{"price":30378.48,"amount":0.0347},{"price":30378.34,"amount":0.0036},{"price":30377.47,"amount":0.0347},{"price":30377.05,"amount":0.06},{"price":30376.54,"amount":0.002},{"price":30376.26,"amount":0.001},{"price":30375.69,"amount":0.2},{"price":30374.64,"amount":0.0017},{"price":30374,"amount":0.06},{"price":30373.28,"amount":0.0029},{"price":30372.93,"amount":0.01},{"price":30372.71,"amount":0.06},{"price":30372.25,"amount":0.007},{"price":30372.15,"amount":0.0184},{"price":30371.8,"amount":0.2},{"price":30371.78,"amount":0.0347},{"price":30371.39,"amount":0.0157},{"price":30371.14,"amount":0.0037},{"price":30371.13,"amount":0.0052},{"price":30370.93,"amount":7.0E-4},{"price":30370.73,"amount":0.0347},{"price":30370.58,"amount":3.0E-4},{"price":30370.44,"amount":4.0E-4},{"price":30369.51,"amount":0.0095},{"price":30369.01,"amount":0.0085},{"price":30368.83,"amount":8.0E-4},{"price":30367.24,"amount":0.06},{"price":30365.9,"amount":0.0056},{"price":30365.77,"amount":0.0212},{"price":30365.31,"amount":0.0098},{"price":30364.8,"amount":0.1921},{"price":30364.76,"amount":8.0E-4},{"price":30364.68,"amount":5.0E-4},{"price":30364.47,"amount":0.01},{"price":30363.44,"amount":0.1907},{"price":30363.32,"amount":2.0062},{"price":30361.28,"amount":0.06}]
     * symbol : BTC/USDT
     * time : 1611285951
     */

    private String symbol;
    private String time;
    private String name;
    private String code;

    public String getName() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return symbol;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private List<FiveBean> asks;
    private List<FiveBean> bids;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<FiveBean> getAsks() {
        return asks;
    }

    public void setAsks(List<FiveBean> asks) {
        this.asks = asks;
    }

    public List<FiveBean> getBids() {
        return bids;
    }

    public void setBids(List<FiveBean> bids) {
        this.bids = bids;
    }

    public static class FiveBean implements Serializable {
        /**
         * price : 30442.74
         * amount : 0.1926
         */
        private int percent = 1;

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
        private String price;
        private String amount;

        public FiveBean(String price, String amount) {
            this.price = price;
            this.amount = amount;
        }
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

}
