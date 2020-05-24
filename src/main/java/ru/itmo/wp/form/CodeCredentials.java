package ru.itmo.wp.form;

import ru.itmo.wp.domain.Language;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CodeCredentials {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Language.Lang language;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLanguage(Language.Lang language) {
        this.language = language;
    }

    public Language.Lang getLanguage() {
        return language;
    }
}
