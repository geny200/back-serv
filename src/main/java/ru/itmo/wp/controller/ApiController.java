package ru.itmo.wp.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.LinkException;

import javax.servlet.http.HttpServletRequest;

public abstract class ApiController {
    @ModelAttribute
    public User getUser(HttpServletRequest httpServletRequest) {
        return (User) httpServletRequest.getAttribute("user");
    }

    protected long parseId(String id) throws LinkException {
        if (!id.matches("[0-9]+")) {
            throw new LinkException();
        }

        try {
            return Long.parseLong(id);
        }
        catch (NumberFormatException e){
            throw new LinkException();
        }
    }
}
