package com.sskj.lib.box.verifier;

import com.github.yoojia.inputs.AndroidNextInputs;
import com.sskj.common.box.androidinput.ToastMessageDisplay;

public class ToastNextInputs extends AndroidNextInputs {
    public  ToastNextInputs() {
        setMessageDisplay(new ToastMessageDisplay());

    }
}
