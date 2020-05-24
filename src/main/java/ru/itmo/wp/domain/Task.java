package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@SuppressWarnings({"unused", "WeakerAccess"})
@Entity
@Table(
        indexes = @Index(columnList = "creationTime")
)
public class Task {
    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    //@JoinColumn(name = "executable_code_id", nullable = false)
    private ExecutableCode generator;

    @ManyToOne
    //@JoinColumn(name = "executable_code_id", nullable = false)
    private ExecutableCode minimizer;

    @ManyToOne
    //@JoinColumn(name = "executable_code_id", nullable = false)
    private ExecutableCode checker;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private long memoryLimit;

    private long timeLimit;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    private String description;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExecutableCode getChecker() {
        return checker;
    }

    public ExecutableCode getGenerator() {
        return generator;
    }

    public ExecutableCode getMinimizer() {
        return minimizer;
    }

    public void setChecker(ExecutableCode checker) {
        this.checker = checker;
    }

    public void setGenerator(ExecutableCode generator) {
        this.generator = generator;
    }

    public void setMinimizer(ExecutableCode minimizer) {
        this.minimizer = minimizer;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }
}
