package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Access;
import ru.itmo.wp.domain.Task;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.LinkException;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.TaskCredentials;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.TaskService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1/task")
public class TaskController extends ApiController {
    private final TaskService taskService;

    @AnyRole(Access.Name.WRITER)
    @PostMapping("add")
    public void addTask(@RequestBody @Valid TaskCredentials taskCredentials, BindingResult bindingResult, User user) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        taskService.createTask(user, taskCredentials);
    }

    @Guest
    @GetMapping("")
    public List<Task> findTasks() {
        return taskService.findAll();
    }

    @Guest
    @GetMapping("id/{id}")
    public Task findTask(@PathVariable String id) {
        Task task = taskService.findById(parseId(id));
        if (task == null)
            throw new LinkException();
        return task;
    }

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
}
