package com.sskj.lib.box.verifier;

import com.github.yoojia.inputs.EmptyableVerifier;
import com.github.yoojia.inputs.Texts;

public class PhoneVerifier extends EmptyableVerifier {
    public PhoneVerifier() {
    }

    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return Texts.regexMatch(notEmptyInput, "\\d.*");
    }
}
