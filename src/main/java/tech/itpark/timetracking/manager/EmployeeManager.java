package tech.itpark.timetracking.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import tech.itpark.timetracking.mapper.EmployeeRowMapper;
import tech.itpark.timetracking.model.Employee;
import tech.itpark.timetracking.model.TimeTrack;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmployeeManager {
    private final NamedParameterJdbcTemplate template;
    private final EmployeeRowMapper rowMapper = new EmployeeRowMapper();

    public List<Employee> getAll() {
        return template.query(
                "SELECT id, name, position, clock_rate FROM employees ORDER BY id LIMIT 50",
                rowMapper
        );

    }

    public Employee getById(long id) {
        return template.queryForObject("SELECT id, name, position, clock_rate FROM employees WHERE id = :id",
                Map.of("id", id),
                rowMapper
        );
    }

    public List<Employee> search(String name) {
        return template.query(
                "SELECT id, name, position, clock_rate FROM employees WHERE name = :name",
                Map.of("name", name),
                rowMapper
        );
    }

    public Employee save(Employee item) {
        if (item.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            template.update(
                    "INSERT INTO employees(name, position, clock_rate) VALUES (:name, :position, :clock_rate)",
                    new MapSqlParameterSource(Map.of(
                            "name", item.getName(),
                            "position", item.getPosition(),
                            "clock_rate", item.getClock_rate()
                    )),
                    keyHolder
            );
            long id = keyHolder.getKey().longValue();
            return getById(id);
        }

        template.update(
                "UPDATE employees SET name = :name, position = :position, clock_rate = :clock_rate WHERE id = :id",
                Map.of(
                        "name", item.getName(),
                        "position", item.getPosition(),
                        "clock_rate", item.getClock_rate(),
                        "id", item.getId()
                )
        );
        return getById(item.getId());
    }

    public Employee removeById(long id) {
        Employee item = getById(id);

        template.update(
                "DELETE FROM employees WHERE id = :id",
                Map.of("id", item.getId()));
        return item;
    }
}
