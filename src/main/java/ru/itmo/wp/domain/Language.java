package ru.itmo.wp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/** @noinspection unused*/
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Language {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Language.Lang name;

    public Language() {
    }

    public Language(@NotNull Language.Lang name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lang getName() {
        return name;
    }

    public void setName(Lang lang) {
        this.name = lang;
    }

    public enum Lang {
        JAVA,
        CPP,
        HASKELL
    }
}
