package com.sskj.lib.box.verifier;

import com.github.yoojia.inputs.EmptyableVerifier;
import com.github.yoojia.inputs.Texts;

public class PwdVerifier extends EmptyableVerifier {
    public PwdVerifier() {
    }

    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return Texts.regexMatch(notEmptyInput, "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$");

    }
}
