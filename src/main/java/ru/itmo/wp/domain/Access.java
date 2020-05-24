package ru.itmo.wp.domain;

import ru.itmo.wp.security.Guest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Access {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Name name;

    /** @noinspection unused*/
    public Access() {
    }

    public Access(@NotNull Name name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public long getPriority() {
        switch (name) {
            case GOD:
                return 0;
            case ADMIN:
                return 1;
            case FRIEND:
                return 2;
            case USER:
                return 3;
            case ALL:
                return 9;
            default:
                return 10;
        }
    }

    public enum Name {
        WRITER,
        ALL,
        USER,
        FRIEND,
        ADMIN,
        GOD
    }
}
