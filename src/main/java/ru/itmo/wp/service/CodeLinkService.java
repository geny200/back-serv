package ru.itmo.wp.service;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.form.CodeCredentials;
import ru.itmo.wp.form.LinkCredentials;
import ru.itmo.wp.repository.AccessRepository;
import ru.itmo.wp.repository.CodeDescriptionRepository;
import ru.itmo.wp.repository.CodeRepository;
import ru.itmo.wp.repository.LinkCodeRepository;

import java.util.List;
import java.util.Set;

@Service
public class CodeLinkService {
    private final String SALT_LINK = "SALT_LINK";
    private final AccessRepository accessRepository;
    private final LinkCodeRepository linkCodeRepository;
    private final Hashids createHash = new Hashids(SALT_LINK);

    public CodeLinkService(AccessRepository accessRepository, LinkCodeRepository linkCodeRepository) {
        this.accessRepository = accessRepository;
        this.linkCodeRepository = linkCodeRepository;
    }

    public CodeLink findByLink(String link) {
        return linkCodeRepository.findByLink(link);
    }

    public void deleteLink(CodeLink link) {
        linkCodeRepository.delete(link);
    }

    public CodeLink createLink(CodeDescription code, LinkCredentials linkCredentials) {
        Access access = accessRepository.findByName(linkCredentials.getAccess());
        String strLink = createHash.encode(code.getId(), access.getPriority());
        CodeLink link = findByLink(strLink);
        if (link == null) {
            link = new CodeLink();
            link.setAccess(Set.of(access));
            link.setLink(strLink);
            link.setLifeTime(linkCredentials.isLeave());
            if (linkCredentials.isLeave())
                link.setDeathTime(linkCredentials.getLeaveTime());
            link.setViews(0);
            code.addLink(link);
            linkCodeRepository.save(link);
        }
        return link;
    }

    public void updateViews(CodeLink codeLink) {
        linkCodeRepository.updateViews(codeLink.getLink(), codeLink.getViews());
    }
}
