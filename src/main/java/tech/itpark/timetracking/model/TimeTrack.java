package tech.itpark.timetracking.model;

import lombok.Value;

@Value
public class TimeTrack {
    long id;
    String date;
    long id_employee;
    String arrival_time;
    String leaving_time;
    int hours_worked;
}
