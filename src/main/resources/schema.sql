CREATE TABLE employees (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    position TEXT NOT NULL,
    clock_rate INTEGER NOT NULL DEFAULT (162)
);

CREATE TABLE time_tracking (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    date TEXT DEFAULT CURRENT_DATE,
    id_employee INTEGER REFERENCES employees (id),
    arrival_time TEXT DEFAULT CURRENT_TIME,
    leaving_time TEXT DEFAULT CURRENT_TIME,
    hours_worked INTEGER CHECK ( hours_worked >= 0 )
);