package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.form.CodeCredentials;
import ru.itmo.wp.form.LinkCredentials;
import ru.itmo.wp.repository.AccessRepository;
import ru.itmo.wp.repository.CodeDescriptionRepository;
import ru.itmo.wp.repository.CodeRepository;
import ru.itmo.wp.repository.LinkCodeRepository;
import org.hashids.*;

import java.util.List;
import java.util.Set;

@Service
public class CodeService {
    private final CodeDescriptionRepository codeDescriptionRepository;
    private final CodeRepository codeRepository;

    public CodeService(CodeDescriptionRepository codeDescriptionRepository, CodeRepository codeRepository) {
        this.codeDescriptionRepository = codeDescriptionRepository;
        this.codeRepository = codeRepository;
    }

    public CodeDescription findByIdAndUser(long codeId, long userId) {
        return codeDescriptionRepository.findById(codeId, userId);
    }

    public List<CodeDescription> findByUser(long id) {
        return codeDescriptionRepository.findByUser(id);
    }

    public List<CodeDescription> findAll() {
        return codeDescriptionRepository.findAll();
    }

    public void createCode(User user, CodeCredentials taskCredentials) {
        CodeDescription codeDescription = new CodeDescription();
        Code code = new Code();
        code.setCode(taskCredentials.getCode());
        user.addCode(codeDescription);
        //codeDescription.setLanguage();
        codeRepository.save(code);
        codeDescriptionRepository.save(codeDescription);
    }
}
