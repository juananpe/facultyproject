package faculty.project.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "\"AcademicRecord\"")
@IdClass(AcademicRecord.AcademicRecordPK.class)
public class AcademicRecord implements Serializable {


    private Float grade;

    @Id
    @Column(name = "ACADEMICYEAR")
    private int year;

    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject")
    private Subject subject;

    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student")
    private Student student;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher")
    private Teacher signedBy;

    public AcademicRecord() {

    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Teacher getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(Teacher signedBy) {
        this.signedBy = signedBy;
    }

    public AcademicRecord(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }


    public static class AcademicRecordPK implements Serializable {
        private String student;
        private String subject;
        private int year;

        public AcademicRecordPK() {
        }

        public AcademicRecordPK(String student, String subject, int year) {
            this.student = student;
            this.subject = subject;
            this.year = year;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AcademicRecordPK that = (AcademicRecordPK) o;

            if (year != that.year) return false;
            if (!Objects.equals(student, that.student)) return false;
            return Objects.equals(subject, that.subject);
        }

        @Override
        public int hashCode() {
            int result = student != null ? student.hashCode() : 0;
            result = 31 * result + (subject != null ? subject.hashCode() : 0);
            result = 31 * result + year;
            return result;
        }
    }

}
