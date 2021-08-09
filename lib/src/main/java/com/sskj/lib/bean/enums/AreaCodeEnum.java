package com.sskj.lib.bean.enums;

import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.R;
import com.sskj.lib.SPConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AreaCodeEnum implements Serializable {
//    AREA_CODE_86("+86", App.INSTANCE.getString(R.string.libareaCodeEnum1))
//    ,AREA_CODE_01("+01",App.INSTANCE.getString(R.string.libareaCodeEnum2))
    ;
    private String areaCode;
    private String areaName;

    public AreaCodeEnum(String areaCode, String areaName) {
        this.areaCode = areaCode;
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }


    private static List<AreaCodeEnum> list = new ArrayList<>();

    public static List<AreaCodeEnum> list() {
        if (list.isEmpty()) {
            list = (List<AreaCodeEnum>) SPUtil.getBean(SPConfig.AREA_CODE_LIST);
            if (list==null){
               // AreaCodeEnum areaCodeEnum = new AreaCodeEnum("+1", App.INSTANCE.getString(R.string.libareaCodeEnum2));
                list=new ArrayList<>();
               // list.add(areaCodeEnum);
                return list;
            }
            return list;

//            return Arrays.asList(AreaCodeEnum.values());
        }
        return list;
    }
}

