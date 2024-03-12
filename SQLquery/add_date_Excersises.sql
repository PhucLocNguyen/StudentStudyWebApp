ALTER TABLE Excercises
ADD end_date datetime NULL;

ALTER TABLE Excercises
ADD CONSTRAINT ck_end_date CHECK (end_date > created_date OR end_date IS NULL);

ALTER TABLE Excercises
ADD start_date datetime NULL;

ALTER TABLE Excercises  
ADD CONSTRAINT ck_dates CHECK (
   (end_date > start_date OR (end_date IS NULL AND start_date IS NULL))
   OR (end_date IS NULL AND start_date IS NOT NULL)
);