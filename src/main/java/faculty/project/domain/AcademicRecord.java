package faculty.project.domain;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "ACADEMICRECORD")
public class AcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float grade;

    @Column(name = "ACADEMIC_YEAR") // Renaming 'year' to 'academicYear'
    private Integer academicYear;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher signedBy;

    // Default constructor
    public AcademicRecord() {
    }

    public AcademicRecord(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
        this.academicYear = Calendar.getInstance().get(Calendar.YEAR);
    }
    // Constructor with all fields
    public AcademicRecord(Float grade, int year, Subject subject, Student student, Teacher signedBy) {
        this.grade = grade;
        this.academicYear = year;
        this.subject = subject;
        this.student = student;
        this.signedBy = signedBy;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public int getYear() {
        return academicYear;
    }

    public void setYear(int year) {
        this.academicYear = year;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(Teacher signedBy) {
        this.signedBy = signedBy;
    }
}
