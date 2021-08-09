package com.sskj.lib.box.verifier;

import com.github.yoojia.inputs.EmptyableVerifier;
import com.github.yoojia.inputs.Texts;

public class SmsCodeVerifier extends EmptyableVerifier {
    public SmsCodeVerifier() {
    }

    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return Texts.regexMatch(notEmptyInput, "\\d{6,6}$");

    }
}
