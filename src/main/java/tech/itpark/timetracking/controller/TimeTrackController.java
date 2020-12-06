package tech.itpark.timetracking.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.timetracking.manager.TimeTrackingManager;
import tech.itpark.timetracking.model.TimeTrack;
import java.util.List;

@RestController
@RequestMapping("/timetrack")
@RequiredArgsConstructor
public class TimeTrackController {
    private final TimeTrackingManager manager;

    @GetMapping
    public List<TimeTrack> getAll() {
        return manager.getAll();
    }

    @GetMapping("/{id}")
    public TimeTrack getById(@PathVariable long id) {
        return manager.getById(id);
    }

    @PostMapping
    public TimeTrack save(@RequestBody TimeTrack item) {
        return manager.save(item);
    }

    @DeleteMapping("/{id}")
    public TimeTrack removeById(@PathVariable long id) {
        return manager.removeById(id);
    }
}
