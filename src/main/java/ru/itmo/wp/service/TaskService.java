package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Task;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.TaskCredentials;
import ru.itmo.wp.repository.ExecutableCodeRepository;
import ru.itmo.wp.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ExecutableCodeRepository executableCodeRepository;

    public TaskService(TaskRepository taskRepository, ExecutableCodeRepository executableCodeRepository) {
        this.taskRepository = taskRepository;
        this.executableCodeRepository = executableCodeRepository;
    }

    public Task findById(long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAllByOrderByCreationTimeDesc();
    }

    public void createTask(User user, TaskCredentials taskCredentials) {
        Task task = new Task();
        task.setName(taskCredentials.getName());
        task.setChecker(executableCodeRepository.findById(taskCredentials.getCheckerId()));
        task.setGenerator(executableCodeRepository.findById(taskCredentials.getGeneratorId()));
        task.setMinimizer(executableCodeRepository.findById(taskCredentials.getMinimizerId()));
        task.setDescription(taskCredentials.getDescription());
        user.addTask(task);
        taskRepository.save(task);
    }
}
