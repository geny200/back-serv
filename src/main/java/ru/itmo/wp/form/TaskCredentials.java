package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TaskCredentials {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private long generatorId;

    @NotNull
    private long minimizerId;

    @NotNull
    private long checkerId;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public long getCheckerId() {
        return checkerId;
    }

    public long getGeneratorId() {
        return generatorId;
    }

    public long getMinimizerId() {
        return minimizerId;
    }

    public void setCheckerId(long checkerId) {
        this.checkerId = checkerId;
    }

    public void setGeneratorId(long generatorId) {
        this.generatorId = generatorId;
    }

    public void setMinimizerId(long minimizerId) {
        this.minimizerId = minimizerId;
    }
}
