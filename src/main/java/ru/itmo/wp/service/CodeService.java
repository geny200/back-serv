package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.form.CodeCredentials;
import ru.itmo.wp.repository.CodeDescriptionRepository;
import ru.itmo.wp.repository.CodeRepository;
import ru.itmo.wp.repository.LanguageRepository;

import java.util.List;

@Service
public class CodeService {
    private final CodeDescriptionRepository codeDescriptionRepository;
    private final CodeRepository codeRepository;
    private final LanguageRepository languageRepository;

    public CodeService(CodeDescriptionRepository codeDescriptionRepository, CodeRepository codeRepository, LanguageRepository languageRepository) {
        this.codeDescriptionRepository = codeDescriptionRepository;
        this.codeRepository = codeRepository;
        this.languageRepository = languageRepository;
    }

    public CodeDescription findByIdAndUser(long codeId, long userId) {
        return codeDescriptionRepository.findById(codeId, userId);
    }

    public List<CodeDescription> findByUser(long id) {
        return codeDescriptionRepository.findByUser(id);
    }

    public CodeDescription findById(long id) {
        return codeDescriptionRepository.findById(id);
    }

    public List<CodeDescription> findAll() {
        return codeDescriptionRepository.findAll();
    }

    public void createCode(User user, CodeCredentials codeCredentials) {
        CodeDescription codeDescription = new CodeDescription();
        Code code = new Code();
        code.setCode(codeCredentials.getCode());
        user.addCode(codeDescription);
        codeDescription.setLanguage(languageRepository.findByName(codeCredentials.getLanguage().name()));
        codeRepository.save(code);
        codeDescriptionRepository.save(codeDescription);
    }
}
