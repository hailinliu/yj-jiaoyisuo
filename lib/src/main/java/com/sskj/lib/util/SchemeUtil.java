package com.sskj.lib.util;

import android.widget.TextView;

import com.github.yoojia.inputs.Loader1A;
import com.github.yoojia.inputs.Scheme;
import com.github.yoojia.inputs.verifiers.EqualsVerifier;
import com.github.yoojia.inputs.verifiers.NotEmptyVerifier;
import com.sskj.common.base.App;
import com.sskj.lib.R;
import com.sskj.lib.box.verifier.CheckBoxVerifier;
import com.sskj.lib.box.verifier.EmailPhoneVerifier;
import com.sskj.lib.box.verifier.EmailVerifier;
import com.sskj.lib.box.verifier.PhoneVerifier;
import com.sskj.lib.box.verifier.PwdVerifier;
import com.sskj.lib.box.verifier.SmsCodeVerifier;

public class SchemeUtil {
    public static Scheme pwd() {
        return new Scheme(new PwdVerifier()).msg(App.INSTANCE.getString(R.string.libschemeUtil1));
    }

    public static Scheme smsCode() {
        return new Scheme(new SmsCodeVerifier()).msg(App.INSTANCE.getString(R.string.libschemeUtil2));
    }

    public static Scheme emailPhone() {
        return new Scheme(new EmailPhoneVerifier()).msg(App.INSTANCE.getString(R.string.libschemeUtil3));
    }

    public static Scheme phone() {
        return new Scheme(new PhoneVerifier()).msg(App.INSTANCE.getString(R.string.libschemeUtil4));
    }

    public static Scheme email() {
        return new Scheme(new EmailVerifier()).msg(App.INSTANCE.getString(R.string.libschemeUtil5));
    }

    public static Scheme notEmpty(TextView textView) {
        return new Scheme(new NotEmptyVerifier()).msg(textView.getHint().toString());
    }

    public static Scheme checkbox(TextView textView) {
        return new Scheme(new CheckBoxVerifier()).msg(textView.getHint().toString());
    }

    /**
     * 输入内容与加载器的内容相同
     *
     * @param lazyLoader 相同内容延迟加载器
     * @return Scheme
     */
    public static Scheme equalsPwd(final Loader1A<String> lazyLoader) {
        return new Scheme(new EqualsVerifier(lazyLoader)).msg(App.INSTANCE.getString(R.string.libschemeUtil6));
    }
}
