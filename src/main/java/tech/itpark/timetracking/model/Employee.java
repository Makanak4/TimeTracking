package tech.itpark.timetracking.model;

import lombok.Value;

@Value
public class Employee {
    long id;
    String name;
    String position;
    int clock_rate;
}
