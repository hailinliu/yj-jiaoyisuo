package com.sskj.lib.bean.enums;

import com.sskj.lib.R;

import java.util.ArrayList;
import java.util.List;


public enum CoinEnum {
    BTC(R.mipmap.lib_icon_btc, "BTC"),
    ETC(R.mipmap.lib_icon_etc, "ETC"),
    EOS(R.mipmap.lib_icon_eos, "EOS"),
    ETH(R.mipmap.lib_icon_eth, "ETH"),
    LTC(R.mipmap.lib_icon_ltc, "LTC"),
    XRP(R.mipmap.lib_icon_xrp, "XRP"),
    FEC(R.mipmap.lib_icon_fec, "FEC"),
    BCH(R.mipmap.lib_icon_bch, "BCH"),
    GNT(R.mipmap.lib_icon_gnt, "GNT"),
    MANA(R.mipmap.lib_icon_mana, "MANA"),
    OMG(R.mipmap.lib_icon_omg, "OMG"),
    PST(R.mipmap.lib_icon_pst, "PST"),
    ZRX(R.mipmap.lib_icon_zrx, "ZRX"),
    USDT(R.mipmap.lib_icon_usdt, "USDT"),
    CQTF(R.mipmap.lib_icon_cqtf, "CQTF"),
    BAT(R.mipmap.lib_icon_bat, "BAT"),
    TRX(R.mipmap.lib_icon_trx, "TRX"),
    XMR(R.mipmap.lib_icon_xmr, "XMR"),
    DIG(R.mipmap.lib_icon_dig,"DIG");

    private int res;
    private String code;
    private static List<CoinEnum> coinEnums;


    CoinEnum(int res, String code) {
        this.res = res;
        this.code = code;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static List<CoinEnum> list() {
        if (coinEnums == null) {
            coinEnums = new ArrayList<CoinEnum>();
//            coinEnums.add(BTC);
//            coinEnums.add(ETC);
//            coinEnums.add(EOS);
//            coinEnums.add(BCH);
//            coinEnums.add(LTC);
//            coinEnums.add(XRP);
//            coinEnums.add(FEC);
//            coinEnums.add(ETH);
//            coinEnums.add(GNT);
//            coinEnums.add(MANA);
//            coinEnums.add(OMG);
//            coinEnums.add(PST);
//            coinEnums.add(ZRX);
//            coinEnums.add(USDT);
//            coinEnums.add(CQTF);
//            coinEnums.add(BAT);
//            coinEnums.add(XMR);
//            coinEnums.add(TRX);
//            coinEnums.add(DIG);
        }
        return coinEnums;
    }

    /**
     * 根据币种返回实体，默认返回BTC
     *
     * @param code
     * @return
     */
    public static CoinEnum getByCode(String code) {
        for (CoinEnum coinEnum : list()) {
            if (coinEnum.code.contains(code.toUpperCase().replace("_", "/"))) {
                return coinEnum;
            }
        }
        return BTC;
    }
}
