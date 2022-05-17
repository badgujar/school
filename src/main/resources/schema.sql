CREATE DATABASE IF NOT EXISTS schoolproject;

CREATE TABLE IF NOT EXISTS schoolproject.student_master (
    id  INTEGER  NOT NULL AUTO_INCREMENT,
    primary key (id),
    registration_ID VARCHAR(8),
    student_name VARCHAR(50),
    mother_name VARCHAR(50),
    father_name VARCHAR(50),
    sex ENUM('M','F'),
    community VARCHAR(1),
    stream INTEGER(1),
    stream_category VARCHAR(1),
    appearing_code VARCHAR(1),
    previous_registration_ID VARCHAR(20),
    total_subjects INTEGER(1),
    is_pass BOOLEAN
);


CREATE TABLE IF NOT EXISTS schoolproject.registration(
   id  INTEGER  NOT NULL AUTO_INCREMENT,
   primary key (id),
   registration_ID VARCHAR(8),
   district_code VARCHAR(8),
   college_code VARCHAR(8),
   stream_code VARCHAR(8),
   serial_no VARCHAR(8),
   center_code VARCHAR(8),
   registration_year VARCHAR(8),
   registration_number VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS schoolproject.marks(
  id  INTEGER  NOT NULL AUTO_INCREMENT,
  primary key (id),
  registration_ID VARCHAR(8),
  subject_order VARCHAR(8),
  subject_code VARCHAR(8),
  subject_tag VARCHAR(8),
  obtained_mark INTEGER(8),
  grace_mark INTEGER(8),
  total_mark INTEGER(8),
  is_pass BOOLEAN,
  remark VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS schoolproject.subjects(
  id  INTEGER  NOT NULL AUTO_INCREMENT,
  primary key (id),
  code VARCHAR(8),
  description VARCHAR(8),
  is_theroy VARCHAR(8),
  max_marks INTEGER(8),
  min_marks INTEGER(8),
  pass_marks INTEGER(8),
  is_optional INTEGER(1),
  stream VARCHAR(30)
);
