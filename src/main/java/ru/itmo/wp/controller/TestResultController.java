package ru.itmo.wp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.wp.service.CodeService;

@RestController
@RequestMapping("/api/1")
public class TestResultController extends ApiController {
    private final CodeService codeService;

    public TestResultController(CodeService codeService) {
        this.codeService = codeService;
    }
}
