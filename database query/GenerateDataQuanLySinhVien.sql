INSERT INTO Students (name, thumbnail, email, password)
VALUES ('John Doe', 'thumbnail1.jpg', 'john.doe@example.com', 'password1'),
       ('Alice Smith', 'thumbnail2.jpg', 'alice.smith@example.com', 'password2'),
       ('Bob Johnson', 'thumbnail3.jpg', 'bob.johnson@example.com', 'password3'),
       ('Emily Brown', 'thumbnail4.jpg', 'emily.brown@example.com', 'password4'),
       ('Michael Davis', 'thumbnail5.jpg', 'michael.davis@example.com', 'password5'),
       ('Jessica Wilson', 'thumbnail6.jpg', 'jessica.wilson@example.com', 'password6'),
       ('David Taylor', 'thumbnail7.jpg', 'david.taylor@example.com', 'password7'),
       ('Sarah Martinez', 'thumbnail8.jpg', 'sarah.martinez@example.com', 'password8'),
       ('Daniel Anderson', 'thumbnail9.jpg', 'daniel.anderson@example.com', 'password9'),
       ('Jennifer Thomas', 'thumbnail10.jpg', 'jennifer.thomas@example.com', 'password10'),
       ('Matthew Jackson', 'thumbnail11.jpg', 'matthew.jackson@example.com', 'password11'),
       ('Samantha White', 'thumbnail12.jpg', 'samantha.white@example.com', 'password12'),
       ('Christopher Harris', 'thumbnail13.jpg', 'christopher.harris@example.com', 'password13'),
       ('Elizabeth Martin', 'thumbnail14.jpg', 'elizabeth.martin@example.com', 'password14'),
       ('Andrew Thompson', 'thumbnail15.jpg', 'andrew.thompson@example.com', 'password15'),
       ('Olivia Garcia', 'thumbnail16.jpg', 'olivia.garcia@example.com', 'password16'),
       ('Joshua Rodriguez', 'thumbnail17.jpg', 'joshua.rodriguez@example.com', 'password17'),
       ('Ava Martinez', 'thumbnail18.jpg', 'ava.martinez@example.com', 'password18'),
       ('James Lee', 'thumbnail19.jpg', 'james.lee@example.com', 'password19'),
       ('Mia Young', 'thumbnail20.jpg', 'mia.young@example.com', 'password20');
	   INSERT INTO Lecturers (name, thumbnail, email, password) VALUES ('Nguyen Loc sensei', 'lecture.jpg', 'locnpse@fe.edu.vn', '123')
INSERT INTO Lecturers (name, thumbnail, email, password)
VALUES ('Prof. Smith', 'lecturer_thumbnail1.jpg', 'prof.smith@example.com', 'lecturer_password1'),
       ('Dr. Johnson', 'lecturer_thumbnail2.jpg', 'dr.johnson@example.com', 'lecturer_password2'),
       ('Prof. Williams', 'lecturer_thumbnail3.jpg', 'prof.williams@example.com', 'lecturer_password3'),
       ('Dr. Brown', 'lecturer_thumbnail4.jpg', 'dr.brown@example.com', 'lecturer_password4'),
       ('Prof. Davis', 'lecturer_thumbnail5.jpg', 'prof.davis@example.com', 'lecturer_password5');

INSERT INTO Classes (name, thumbnail, password, description, created_date, lecturer_id)
VALUES ('Mathematics 101', 'class_thumbnail1.jpg', 'class_password1', 'Introduction to Mathematics', GETDATE(), 1),
       ('Physics 101', 'class_thumbnail2.jpg', 'class_password2', 'Introduction to Physics', GETDATE(), 2),
       ('Biology 101', 'class_thumbnail3.jpg', 'class_password3', 'Introduction to Biology', GETDATE(), 3),
       ('Chemistry 101', 'class_thumbnail4.jpg', 'class_password4', 'Introduction to Chemistry', GETDATE(), 4),
       ('Computer Science 101', 'class_thumbnail5.jpg', 'class_password5', 'Introduction to Computer Science', GETDATE(), 5);

INSERT INTO Enroll (student_id, class_id, enroll_date)
VALUES (1, 1, GETDATE()),
       (2, 1, GETDATE()),
       (3, 2, GETDATE()),
       (4, 2, GETDATE()),
       (5, 3, GETDATE()),
       (6, 3, GETDATE()),
       (7, 4, GETDATE()),
       (8, 4, GETDATE()),
       (9, 5, GETDATE()),
       (10, 5, GETDATE()),
       (11, 1, GETDATE()),
       (12, 1, GETDATE()),
       (13, 2, GETDATE()),
       (14, 2, GETDATE()),
       (15, 3, GETDATE()),
       (16, 3, GETDATE()),
       (17, 4, GETDATE()),
       (18, 4, GETDATE()),
       (19, 5, GETDATE()),
       (20, 5, GETDATE());
