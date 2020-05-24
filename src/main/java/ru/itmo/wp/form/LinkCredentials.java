package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class LinkCredentials {
    private long codeId;

    private boolean leave;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(ALL)|(FRIEND)", message = "Expected \"ALL\" or \"FRIEND\"")
    private String access;

    @NotNull
    private Date leaveTime;

    public void setLeave(boolean leave) {
        this.leave = leave;
    }

    public boolean isLeave() {
        return leave;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public long getCodeId() {
        return codeId;
    }

    public String getAccess() {
        return access;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }
}
