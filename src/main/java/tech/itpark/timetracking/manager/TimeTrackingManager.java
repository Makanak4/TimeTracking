package tech.itpark.timetracking.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import tech.itpark.timetracking.mapper.TimeTrackRowMapper;
import tech.itpark.timetracking.model.TimeTrack;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TimeTrackingManager {
    private final NamedParameterJdbcTemplate template;
    private final TimeTrackRowMapper rowMapper = new TimeTrackRowMapper();

    public List<TimeTrack> getAll() {
        return template.query(
                "SELECT id, date, id_employee, arrival_time, leaving_time, hours_worked FROM time_tracking ORDER BY id LIMIT 50",
                rowMapper
        );
    }

    public TimeTrack getById(long id) {
        return template.queryForObject("SELECT id, date, id_employee, arrival_time, leaving_time, hours_worked FROM time_tracking WHERE id = :id",
                Map.of("id", id),
                rowMapper
        );
    }

    public TimeTrack save(TimeTrack item) {
        if (item.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            template.update(
                    "INSERT INTO time_tracking(date, id_employee, arrival_time, leaving_time, hours_worked) " +
                            "VALUES (CURRENT_DATE, :id_employee, CURRENT_TIME, CURRENT_TIME, :hours_worked)",
                    new MapSqlParameterSource(Map.of(
                            "date", item.getDate(),
                            "id_employee", item.getId_employee(),
                            "arrival_time", item.getArrival_time(),
                            "leaving_time", item.getLeaving_time(),
                            "hours_worked", item.getHours_worked()
                    )),
                    keyHolder
            );
            long id = keyHolder.getKey().longValue();
            return getById(id);
        }
        template.update(
                "UPDATE time_tracking " +
                        "SET date = CURRENT_DATE, id_employee = :id_employee, arrival_time = CURRENT_TIME, leaving_time = CURRENT_TIME, hours_worked = :hours_worked " +
                        "WHERE id = :id",
                Map.of(
                        "date", item.getDate(),
                        "id_employee", item.getId_employee(),
                        "arrival_time", item.getArrival_time(),
                        "leaving_time", item.getLeaving_time(),
                        "hours_worked", item.getHours_worked(),
                        "id", item.getId()
                )
        );
        return getById(item.getId());
    }

    public TimeTrack removeById(long id) {
        TimeTrack item = getById(id);
        template.update(
                "DELETE FROM time_tracking WHERE id = :id",
                Map.of("id", item.getId()));
        return item;
    }
}
