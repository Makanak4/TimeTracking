package tech.itpark.timetracking.mapper;

import org.springframework.jdbc.core.RowMapper;
import tech.itpark.timetracking.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("position"),
                rs.getInt("clock_rate")
        );
    }
}
