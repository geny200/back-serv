package ru.itmo.wp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @noinspection unused
 */
@Entity
@Table(
        indexes = @Index(columnList = "creationTime")
)
public class CodeDescription {
    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "code", cascade = CascadeType.ALL)
    @OrderBy("creationTime desc")
    private List<CodeLink> links;

    @CreationTimestamp
    private Date creationTime;

    @NotNull
    @ManyToOne
    private Language language;

    @JsonIgnore
    @NotNull
    @NotEmpty
    @OneToOne
    @JoinColumn(name = "code_id", nullable = false)
    private Code code;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CodeLink> getLinks() {
        return links;
    }

    public void setLinks(List<CodeLink> links) {
        this.links = links;
    }

    public void addLink(CodeLink link) {
        link.setCode(this);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
