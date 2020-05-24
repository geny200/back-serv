package ru.itmo.wp.domain;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

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
public class TestResult {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "code_id", nullable = false)
    private Code code;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    private int result;

    @NotNull
    @Size(max = 200)
    private String comment;

    @Value("false")
    private boolean reTest;

    @CreationTimestamp
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public boolean isReTest() {
        return reTest;
    }

    public String getComment() {
        return comment;
    }

    public Task getTask() {
        return task;
    }

    public Test getTest() {
        return test;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setReTest(boolean reTest) {
        this.reTest = reTest;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
