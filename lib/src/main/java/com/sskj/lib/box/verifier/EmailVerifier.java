package com.sskj.lib.box.verifier;

import com.github.yoojia.inputs.EmptyableVerifier;
import com.github.yoojia.inputs.Texts;

public class EmailVerifier extends EmptyableVerifier {
    public EmailVerifier() {
    }

    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return Texts.regexMatch(notEmptyInput, "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$")
                ;
    }
}
