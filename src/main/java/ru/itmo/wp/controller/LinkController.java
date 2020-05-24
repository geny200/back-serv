package ru.itmo.wp.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.CodeLink;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ForbiddenException;
import ru.itmo.wp.security.AUser;
import ru.itmo.wp.service.CodeLinkService;

@RestController
@RequestMapping("/api/1/link")
public class LinkController extends ApiController {
    private final CodeLinkService codeLinkService;

    @AUser
    @PostMapping("deactivate")
    public void postCode(@RequestBody String link, User user) {
        CodeLink codeLink = codeLinkService.findByLink(link);
        if (codeLink == null || !codeLink.getCode().getUser().equals(user))
            throw new ForbiddenException();
        codeLinkService.deleteLink(codeLink);
    }

    @AUser
    @GetMapping("{link}")
    public CodeLink findLink(@PathVariable String link, User user) {
        CodeLink codeLink = codeLinkService.findByLink(link);
        if (codeLink == null || !codeLink.getCode().getUser().equals(user))
            throw new ForbiddenException();
        return codeLink;
    }

    public LinkController(CodeLinkService codeService) {
        this.codeLinkService = codeService;
    }
}
