package ru.itmo.wp.domain;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
@Entity
@Table(
        indexes = @Index(columnList = "creationTime")
)
public class Test {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @OrderBy("creationTime desc")
    private List<ExecutableCode> generator;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    private String data;

    @CreationTimestamp
    private Date creationTime;

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setGenerator(List<ExecutableCode> generator) {
        this.generator = generator;
    }

    public List<ExecutableCode> getGenerator() {
        return generator;
    }
}
