package tech.itpark.timetracking.mapper;

import org.springframework.jdbc.core.RowMapper;
import tech.itpark.timetracking.model.TimeTrack;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeTrackRowMapper implements RowMapper<TimeTrack> {
    public TimeTrack mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TimeTrack(
                rs.getLong("id"),
                rs.getString("date"),
                rs.getLong("id_employee"),
                rs.getString("arrival_time"),
                rs.getString("leaving_time"),
                rs.getInt("hours_worked")

        );
    }
}
