package ru.itmo.wp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.exception.LinkException;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CodeLinkService;
import ru.itmo.wp.service.CodeService;
import ru.itmo.wp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/1/all")
public class GodController extends ApiController {
    private final CodeService codeService;
    private final UserService userService;
    private final CodeLinkService codeLinkService;

    @AnyRole(Access.Name.GOD)
    @GetMapping("code")
    public List<CodeDescription> findCode() {
        return codeService.findAll();
    }

    @Guest
    @GetMapping("codes")
    public List<CodeDescription> findCodes() {
        return codeService.findByUser(1);
    }

    @AnyRole(Access.Name.GOD)
    @GetMapping("user/{login}/codes")
    public List<CodeDescription> findProtectUserCodes(@PathVariable String login) {
        User user = userService.findByLogin(login);
        if (user != null)
            return user.getCodes();
        throw new LinkException();
    }

    @AnyRole(Access.Name.GOD)
    @GetMapping("user/{login}")
    public User findProtectUser(@PathVariable String login) {
        User user = userService.findByLogin(login);
        if (user != null)
            return user;
        throw new LinkException();
    }

    @AnyRole(Access.Name.GOD)
    @GetMapping("code/{id}")
    public CodeDescription findCodeById(@PathVariable String id) {
        CodeDescription code = codeService.findById(parseId(id));
        if (code == null)
            throw new LinkException();
        return code;
    }

    @AnyRole(Access.Name.GOD)
    @GetMapping("code/link/{link}")
    public CodeDescription findLink(@PathVariable String link) {
        CodeLink codeLink = codeLinkService.findByLink(link);
        if (codeLink == null)
            throw new LinkException();
        return codeLink.getCode();
    }

    public GodController(CodeService codeService, UserService userService, CodeLinkService codeLinkService) {
        this.codeService = codeService;
        this.userService = userService;
        this.codeLinkService = codeLinkService;
    }
}
