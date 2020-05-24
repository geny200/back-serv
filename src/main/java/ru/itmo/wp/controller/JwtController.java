package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.TaskCredentials;
import ru.itmo.wp.form.RegisterCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.validator.UserCredentialsEnterValidator;
import ru.itmo.wp.form.validator.UserCredentialsRegisterValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.TaskService;
import ru.itmo.wp.service.UserService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1")
public class JwtController extends ApiController {
    private final JwtService jwtService;
    private final UserService userService;
    private final TaskService taskService;
    private final UserCredentialsEnterValidator userCredentialsEnterValidator;
    private final UserCredentialsRegisterValidator userCredentialsRegisterValidator;

    public JwtController(JwtService jwtService, UserService userService, TaskService taskService, UserCredentialsEnterValidator userCredentialsEnterValidator, UserCredentialsRegisterValidator userCredentialsRegisterValidator) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.taskService = taskService;
        this.userCredentialsEnterValidator = userCredentialsEnterValidator;
        this.userCredentialsRegisterValidator = userCredentialsRegisterValidator;
    }

    @InitBinder("userCredentials")
    public void initEnterBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsEnterValidator);
    }

    @InitBinder("registerCredentials")
    public void initRegisterBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegisterValidator);
    }

    @PostMapping("jwt")
    public String createJwt(@RequestBody @Valid UserCredentials userCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        return jwtService.create(userCredentials.getLogin(), userCredentials.getPassword());
    }

    @PostMapping("jwt/register")
    public String createJwt(@RequestBody @Valid RegisterCredentials registerCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        return jwtService.create(userService.register(registerCredentials));
    }
}
