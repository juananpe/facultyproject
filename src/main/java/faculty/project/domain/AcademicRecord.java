package faculty.project.domain;

import java.util.Calendar;

public class AcademicRecord {
    private float grade;
    private int year;
    private Subject subject;
    private Student student;
    private Teacher signedBy;

    public AcademicRecord(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }
}
