CREATE TABLE employees (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    position TEXT NOT NULL,
    clock_rate INTEGER NOT NULL DEFAULT (162)
);

CREATE TABLE time_tracking (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    date TEXT NOT NULL DEFAULT 0,
    id_employee INTEGER REFERENCES employees (id),
    arrival_time TEXT NOT NULL DEFAULT 0,
    leaving_time TEXT NOT NULL DEFAULT 0,
    hours_worked INTEGER CHECK ( hours_worked >= 0 )
);