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
lecturer_id int FOREIGN KEY REFERENCES Lecturers(lecturer_id)
)
CREATE TABLE "Enroll"(
student_id int FOREIGN KEY REFERENCES Students(student_id),
class_id int FOREIGN KEY REFERENCES Classes(class_id),
PRIMARY KEY(student_id,class_id)
)

CREATE TABLE "Excercises"(
excercise_id int identity(1,1) PRIMARY KEY,
title nvarchar(255) not null,
description ntext,
Status NVARCHAR(10) CHECK (Status IN ('active','Inactive', 'Pending')) DEFAULT 'Pending',
created_date datetime default GETDATE(),
class_id int FOREIGN KEY REFERENCES Classes(class_id),
lecturer_id int FOREIGN KEY REFERENCES Lecturers(lecturer_id)
)
CREATE TABLE "Do"(
excercise_id int FOREIGN KEY REFERENCES Excercises(excercise_id),
student_id int FOREIGN KEY REFERENCES Students(student_id),
solution text not null,
score tinyInt DEFAULT 0,
created_date datetime default GETDATE(),
PRIMARY KEY (excercise_id,student_id)
)

CREATE TABLE "Notifications"(
notification_id int identity (1,1) PRIMARY KEY,
message text not null,
created_date datetime default GETDATE(),
EXCERCISE_id int FOREIGN KEY REFERENCES EXCERCISEs(EXCERCISE_id),
)

CREATE TABLE "Receive"(
notification_id int FOREIGN KEY REFERENCES Notifications(notification_id),
student_id int FOREIGN KEY REFERENCES Students(student_id),
is_read BIT default 0,
PRIMARY KEY (notification_id,student_id)
)