-- we don't know how to generate root <with-no-name> (class Root) :(
create table "Prerequisites"
(
    SUBJECT      CHARACTER VARYING not null,
    PREREQUISITE CHARACTER VARYING not null,
    constraint "Prerequisites_pk"
        primary key (SUBJECT, PREREQUISITE)
);

create table "Student"
(
    USERNAME        CHARACTER VARYING not null,
    PASSWORD        CHARACTER VARYING,
    "completeName"  CHARACTER VARYING,
    EMAIL           CHARACTER VARYING,
    ADDRESS         CHARACTER VARYING,
    "phoneNumber"   CHARACTER VARYING,
    "earnedCredits" INTEGER,
    constraint "STUDENT_pk"
        primary key (USERNAME)
);

create table "Subject"
(
    NAME         CHARACTER VARYING not null,
    TEACHER      CHARACTER VARYING,
    "numCredits" INTEGER,
    constraint "SUBJECT_pk"
        primary key (NAME)
);

create table "AcademicRecord"
(
    ID             INTEGER auto_increment,
    STUDENT        CHARACTER VARYING,
    GRADE          DOUBLE PRECISION,
    "academicYear" INTEGER,
    SUBJECT        CHARACTER VARYING,
    TEACHER        CHARACTER VARYING,
    constraint "ACADEMICRECORD_pk"
        primary key (ID),
    constraint ACADEMICRECORD_STUDENT_USERNAME_FK
        foreign key (STUDENT) references "Student",
    constraint ACADEMICRECORD_SUBJECT_NAME_FK
        foreign key (SUBJECT) references "Subject"
);

create table "Teacher"
(
    USERNAME         CHARACTER VARYING,
    PASSWORD         CHARACTER VARYING,
    "completeName"   CHARACTER VARYING,
    EMAIL            INTEGER,
    ADDRESS          CHARACTER VARYING,
    "phoneNumber"    CHARACTER VARYING,
    "officeNumber"   INTEGER,
    "corporatePhone" CHARACTER VARYING
);

