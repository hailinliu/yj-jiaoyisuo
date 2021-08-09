package com.sskj.lib.bean.enums;

import com.sskj.common.base.App;
import com.sskj.lib.R;

public enum CodeTypeEnum {
    STATUS_1(1, App.INSTANCE.getString(R.string.libcodeTypeEnum1)),
    STATUS_2(2,App.INSTANCE.getString(R.string.libcodeTypeEnum2)),
    STATUS_3(3,App.INSTANCE.getString(R.string.libcodeTypeEnum3)),
    STATUS_4(4,App.INSTANCE.getString(R.string.libcodeTypeEnum4)),
    STATUS_5(5,App.INSTANCE.getString(R.string.libcodeTypeEnum5)),
    STATUS_6(6,App.INSTANCE.getString(R.string.libcodeTypeEnum6)),
    STATUS_7(7,App.INSTANCE.getString(R.string.libcodeTypeEnum7)),
    STATUS_8(8,App.INSTANCE.getString(R.string.libcodeTypeEnum8))
    ;
    private int type;
    private String desc;

    CodeTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
