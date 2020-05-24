package ru.itmo.wp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"unused", "WeakerAccess"})
@Entity
@Table(
        indexes = @Index(columnList = "creationTime"),
        uniqueConstraints = @UniqueConstraint(columnNames = "login")
)
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 24)
    @Pattern(regexp = "[a-z]{2,24}")
    private String login;

    @Value("user.jpg")
    private String img;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<Task> tasks;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<CodeDescription> codes;

    @JsonIgnore
    @ManyToMany
    @OrderBy("creationTime desc")
    private Set<User> friends;

    @CreationTimestamp
    private Date creationTime;

    @JsonIgnore
    @ManyToMany
    private Set<Access> access;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void addTask(Task task) {
        task.setUser(this);
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setCodes(List<CodeDescription> codes) {
        this.codes = codes;
    }

    public List<CodeDescription> getCodes() {
        return codes;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setAccess(Set<Access> access) {
        this.access = access;
    }

    public Set<Access> getAccess() {
        return access;
    }

    public void addCode(CodeDescription codeDescription) {
        codeDescription.setUser(this);
    }
}
