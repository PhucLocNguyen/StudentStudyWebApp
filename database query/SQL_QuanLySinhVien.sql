CREATE DATABASE QuanLySinhVien;
go
use QuanLySinhVien;
go

CREATE TABLE "Students"(
student_id int identity(1,1) PRIMARY KEY,
name nvarchar(255) not null,
thumbnail varchar(255),
email varchar(255) UNIQUE not null,
password varchar(255),
)
CREATE TABLE "Lecturers"(
lecturer_id int identity(1,1) PRIMARY KEY,
name nvarchar(255) not null,
thumbnail varchar(255),
email varchar(255) UNIQUE not null,
password varchar(255),
)

CREATE TABLE "Classes"(
class_id int identity(1,1) PRIMARY KEY,
name nvarchar(255) not null,
thumbnail varchar(255),
password varchar(255) not null,
description ntext,
created_date datetime default GETDATE(),
lecturer_id int FOREIGN KEY REFERENCES Lecturers(lecturer_id)
)
CREATE TABLE "Enroll"(
student_id int FOREIGN KEY REFERENCES Students(student_id) ON DELETE CASCADE,
class_id int FOREIGN KEY REFERENCES Classes(class_id) ON DELETE CASCADE,
enroll_date datetime default GETDATE(),
PRIMARY KEY(student_id,class_id)
)

CREATE TABLE "Exercises"(
exercise_id int identity(1,1) PRIMARY KEY,
title nvarchar(255) not null,
media varchar(255),
description ntext,
status NVARCHAR(10) CHECK (status IN ('active','inactive', 'pending')) DEFAULT 'pending',
created_date datetime default GETDATE(),
end_date datetime default NULL,
start_date datetime default NULL,
class_id int FOREIGN KEY REFERENCES Classes(class_id) ON DELETE CASCADE,
lecturer_id int FOREIGN KEY REFERENCES Lecturers(lecturer_id)
)
CREATE TABLE "Do"(
exercise_id int FOREIGN KEY REFERENCES Exercises(exercise_id) ON DELETE CASCADE,
student_id int FOREIGN KEY REFERENCES Students(student_id) ON DELETE CASCADE,
solution ntext not null,
score float CHECK(score>=0 AND score <= 10) DEFAULT 0,
is_grade bit default 0,
created_date datetime default GETDATE(),
PRIMARY KEY (exercise_id,student_id)
)

CREATE TABLE "Notifications"(
notification_id int identity (1,1) PRIMARY KEY,
message ntext not null,
created_date datetime default GETDATE(),
exercise_id int FOREIGN KEY REFERENCES Exercises(exercise_id),
)

CREATE TABLE "Receive"(
notification_id int FOREIGN KEY REFERENCES Notifications(notification_id),
student_id int FOREIGN KEY REFERENCES Students(student_id),
is_read BIT default 0,
PRIMARY KEY (notification_id,student_id)
)
GO
CREATE TRIGGER trg_InsertNotification
ON Notifications
AFTER INSERT
AS
BEGIN
    DECLARE @notification_id int
    DECLARE @class_id int

    SELECT @notification_id = notification_id,
           @class_id = e.class_id
    FROM inserted INNER JOIN Exercises e on e.exercise_id = inserted.exercise_id;

    DECLARE student_cursor CURSOR FOR
    SELECT student_id
    FROM Enroll
    WHERE class_id = @class_id

    OPEN student_cursor
    DECLARE @student_id int

    FETCH NEXT FROM student_cursor INTO @student_id
    WHILE @@FETCH_STATUS = 0
    BEGIN
        INSERT INTO Receive (notification_id, student_id)
        VALUES (@notification_id, @student_id)

        FETCH NEXT FROM student_cursor INTO @student_id
    END

    CLOSE student_cursor
    DEALLOCATE student_cursor
END
GO
