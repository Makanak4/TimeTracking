package tech.itpark.timetracking.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.timetracking.manager.EmployeeManager;
import tech.itpark.timetracking.model.Employee;
import tech.itpark.timetracking.model.TimeTrack;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeManager manager;

    @GetMapping
    public List<Employee> getAll() {
        return manager.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable long id) {
        return manager.getById(id);
    }

    @GetMapping("/search")
    public List<Employee> search(@RequestParam String name) {
        return manager.search(name);
    }

    @PostMapping
    public Employee save(@RequestBody Employee item) {
        return manager.save(item);
    }

    @DeleteMapping("/{id}")
    public Employee removeById(@PathVariable long id) {
        return manager.removeById(id);
    }
}
