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

    public TimeTrack saveArrivalTime(TimeTrack item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (item.getId() == 0) {
            template.update(
                    "INSERT INTO time_tracking(date, id_employee, arrival_time) " +
                            "VALUES (CURRENT_DATE, :id_employee, CURRENT_TIME)",
                    new MapSqlParameterSource(Map.of(
                            "id_employee", item.getId_employee()
                    )),
                    keyHolder
            );
        }
        long id = keyHolder.getKey().longValue();
        return getById(id);
    }

    public TimeTrack saveLeavingTime(TimeTrack item) {
        if (item.getId() != 0) {
            template.update(
                    "UPDATE time_tracking SET leaving_time = CURRENT_TIME WHERE id = :id",
                    Map.of(
                            "id", item.getId()
                    )
            );
        }
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
