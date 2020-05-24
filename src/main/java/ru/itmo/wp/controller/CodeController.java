package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.exception.ForbiddenException;
import ru.itmo.wp.exception.LinkException;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.CodeCredentials;
import ru.itmo.wp.form.LinkCredentials;
import ru.itmo.wp.security.AUser;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CodeLinkService;
import ru.itmo.wp.service.CodeService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/1/code")
public class CodeController extends ApiController {
    private final CodeService codeService;
    private final CodeLinkService codeLinkService;

    @AUser
    @PostMapping("add")
    public void addCode(@RequestBody @Valid CodeCredentials codeCredentials, BindingResult bindingResult, User user) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        codeService.createCode(user, codeCredentials);
    }

    @AUser
    @PostMapping("post")
    public CodeLink postCode(@RequestBody @Valid LinkCredentials linkCredentials, BindingResult bindingResult, User user) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        CodeDescription code = codeService.findByIdAndUser(linkCredentials.getCodeId(), user.getId());
        if (code == null || !code.getUser().equals(user)) {
            throw new ForbiddenException();
        }
        return codeLinkService.createLink(code, linkCredentials);
    }

    @AUser
    @GetMapping("")
    public List<CodeDescription> findCodesByUser(User user) {
        return user.getCodes();
    }

    @AUser
    @GetMapping("id/{id}")
    public Code findCodeById(@PathVariable String id, User user) {
        CodeDescription codeDescription = codeService.findByIdAndUser(parseId(id), user.getId());
        if (codeDescription == null)
            throw new ForbiddenException();
        return codeDescription.getCode();
    }

    @Guest
    @GetMapping("link/{link}")
    public CodeDescription findCodeByLink(@PathVariable String link, User user) {
        CodeLink codeLink = codeLinkService.findByLink(link);
        if (codeLink == null)
            throw new LinkException();

        if (codeLink.isLifeTime()) {
            Date date = new Date();
            if (date.after(codeLink.getDeathTime()))
                throw new ForbiddenException();
        }

        if (user == null) {
            for (Access accessLink : codeLink.getAccess()) {
                if (accessLink.getName().equals(Access.Name.ALL)) {
                    codeLink.increment();
                    codeLinkService.updateViews(codeLink);
                    return codeLink.getCode();
                }
            }
        } else {
            for (Access accessLink : codeLink.getAccess()) {
                if (user.getAccess().contains(accessLink)) {
                    codeLink.increment();
                    codeLinkService.updateViews(codeLink);
                    return codeLink.getCode();
                }
                if (accessLink.getName().equals(Access.Name.FRIEND)) {
                    if (codeLink.getCode().getUser().getFriends().contains(user)) {
                        codeLink.increment();
                        codeLinkService.updateViews(codeLink);
                        return codeLink.getCode();
                    }
                }
            }
        }
        throw new ForbiddenException();
    }

    public CodeController(CodeService codeService, CodeLinkService codeLinkService) {
        this.codeService = codeService;
        this.codeLinkService = codeLinkService;
    }
}
