package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @noinspection unused
 */
@Entity
@Table(
        indexes = @Index(columnList = "creationTime")
)
public class ServiceCode {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "code_id", nullable = false)
    private Code code;

    @NotNull
    @Size(max = 65000)
    @Lob
    private String parameter;

    @CreationTimestamp
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}

