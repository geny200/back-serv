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
import java.util.Set;

/**
 * @noinspection unused
 */
@Entity
@Table(
        indexes = @Index(columnList = "creationTime"),
        uniqueConstraints = @UniqueConstraint(columnNames = "link")
)
public class CodeLink {
    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    @CreationTimestamp
    private Date creationTime;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 24)
    @Pattern(regexp = "[a-z]{2,24}")
    private String link;

    @NotNull
    private Date deathTime;

    @Value("false")
    private boolean lifeTime;

    @Value("0")
    private long views;

    @NotNull
    @ManyToMany
    private Set<Access> access;

    @ManyToOne
    @JoinColumn(name = "code_id", nullable = false)
    private CodeDescription code;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getDeathTime() {
        return deathTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setDeathTime(Date leaveTime) {
        this.deathTime = leaveTime;
    }

    public Set<Access> getAccess() {
        return access;
    }

    public void setAccess(Set<Access> access) {
        this.access = access;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getViews() {
        return views;
    }

    public void increment() {
        ++this.views;
    }

    public boolean isAvailable(Access userAccess, Date time) {
        if (!isLifeTime())
            return true;
        return time.before(deathTime);
    }

    public boolean isLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(boolean leave) {
        this.lifeTime = leave;
    }

    public void setCode(CodeDescription code) {
        this.code = code;
    }

    public CodeDescription getCode() {
        return code;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
