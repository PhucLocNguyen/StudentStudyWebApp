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
student_id int FOREIGN KEY REFERENCES Students(student_id),
class_id int FOREIGN KEY REFERENCES Classes(class_id),
enroll_date datetime default GETDATE(),
PRIMARY KEY(student_id,class_id)
)

CREATE TABLE "Exercises"(
excercise_id int identity(1,1) PRIMARY KEY,
title nvarchar(255) not null,
media varchar(255),
description ntext,
status NVARCHAR(10) CHECK (status IN ('active','inactive', 'pending')) DEFAULT 'pending',
created_date datetime default GETDATE(),
start_date datetime NULL CHECK (
   (end_date > start_date OR (end_date IS NULL AND start_date IS NULL))
   OR (end_date IS NULL AND start_date IS NOT NULL)
),
end_date datetime NULL CHECK (end_date > created_date OR end_date IS NULL),

class_id int FOREIGN KEY REFERENCES Classes(class_id),
lecturer_id int FOREIGN KEY REFERENCES Lecturers(lecturer_id)
)
CREATE TABLE "Do"(
excercise_id int FOREIGN KEY REFERENCES Excercises(excercise_id),
student_id int FOREIGN KEY REFERENCES Students(student_id),
solution ntext not null,
score float CHECK(score>=0 AND score <= 10) DEFAULT 0,
is_grade bit default 0,
created_date datetime default GETDATE(),
PRIMARY KEY (excercise_id,student_id)
)

CREATE TABLE "Notifications"(
notification_id int identity (1,1) PRIMARY KEY,
message ntext not null,
created_date datetime default GETDATE(),
excercise_id int FOREIGN KEY REFERENCES Excercises(excercise_id),
)

CREATE TABLE "Receive"(
notification_id int FOREIGN KEY REFERENCES Notifications(notification_id),
student_id int FOREIGN KEY REFERENCES Students(student_id),
is_read BIT default 0,
PRIMARY KEY (notification_id,student_id)
)