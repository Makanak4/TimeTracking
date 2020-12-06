INSERT INTO employees (name, position)
VALUES
       ('Ivan Ivanov', 'Manager'),
       ('Petr Petrov', 'Manager'),
       ('Maksim Maksimov', 'Administrator'),
       ('Elena Nikolaevna', 'Bookkeeper'),
       ('Olga Tarasova', 'Human resources')
       ;

INSERT INTO time_tracking (date, id_employee, arrival_time, leaving_time, hours_worked)
VALUES  ( '2020-12-05', 1, '09:00:00', '18:00:00', 8 ),
        ( '2020-12-05', 2, '09:00:00', '18:00:00', 8 ),
        ( '2020-12-05', 3, '09:00:00', '18:00:00', 8 ),
        ( '2020-12-05', 4, '09:00:00', '18:00:00', 8 ),
        ( '2020-12-05', 5, '09:00:00', '18:00:00', 8 )
;