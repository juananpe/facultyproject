create table "Prerequisites"
(
    "Subject_name"       CHARACTER VARYING not null,
    "preRequisites_name" CHARACTER VARYING not null
);

create table "Student"
(
    USERNAME      CHARACTER VARYING not null,
    PASSWORD      CHARACTER VARYING,
    EMAIL         CHARACTER VARYING,
    ADDRESS       CHARACTER VARYING,
    COMPLETENAME  CHARACTER VARYING,
    PHONENUMBER   CHARACTER VARYING,
    EARNEDCREDITS INTEGER,
    constraint "STUDENT_pk"
        primary key (USERNAME)
);

create table "Subject"
(
    NAME           CHARACTER VARYING not null,
    TEACHER        CHARACTER VARYING,
    MAXNUMSTUDENTS INTEGER,
    NUMCREDITS     INTEGER,
    constraint "SUBJECT_pk"
        primary key (NAME)
);

create table "AcademicRecord"
(
    ID           INTEGER auto_increment,
    STUDENT      CHARACTER VARYING,
    GRADE        DOUBLE PRECISION,
    SUBJECT      CHARACTER VARYING,
    TEACHER      CHARACTER VARYING,
    ACADEMICYEAR INTEGER,
    constraint "ACADEMICRECORD_pk"
        primary key (ID),
    constraint ACADEMICRECORD_STUDENT_USERNAME_FK
        foreign key (STUDENT) references "Student",
    constraint ACADEMICRECORD_SUBJECT_NAME_FK
        foreign key (SUBJECT) references "Subject"
);

create table "Teacher"
(
    USERNAME       CHARACTER VARYING,
    PASSWORD       CHARACTER VARYING,
    EMAIL          INTEGER,
    ADDRESS        CHARACTER VARYING,
    COMPLETENAME   CHARACTER VARYING,
    PHONENUMBER    CHARACTER VARYING,
    CORPORATEPHONE CHARACTER VARYING,
    OFFICENUMBER   INTEGER
);

create table "User"
(
    USERNAME         CHARACTER VARYING not null,
    PASSWORD         CHARACTER VARYING,
    "completeName"   CHARACTER VARYING,
    EMAIL            CHARACTER VARYING,
    ADDRESS          CHARACTER VARYING,
    "phoneNumber"    CHARACTER VARYING,
    "officeNumber"   INTEGER,
    "corporatePhone" CHARACTER VARYING,
    "earnedCredits"  INTEGER,
    TYPE             INTEGER,
    constraint "User_pk"
        primary key (USERNAME)
);

