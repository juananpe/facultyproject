package faculty.project.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer credits;
    private Integer maximumStudentNumber;

    @ManyToMany
    @JoinTable(
            name = "subject_prerequisites",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private Set<Subject> prerequisites = new HashSet<>();

    @ManyToMany(mappedBy = "prerequisites")
    private Set<Subject> prerequisiteFor = new HashSet<>();

    @OneToMany(mappedBy = "subject")
    private Set<AcademicRecord> academicRecords = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // Constructors
    // Parameterized constructor
    public Subject(String name, Integer credits, Integer maximumStudentNumber) {
        this.name = name;
        this.credits = credits;
        this.maximumStudentNumber = maximumStudentNumber;
    }

    public Subject() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getMaximumStudentNumber() {
        return maximumStudentNumber;
    }

    public void setMaximumStudentNumber(Integer maximumStudentNumber) {
        this.maximumStudentNumber = maximumStudentNumber;
    }

    public Set<Subject> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Set<Subject> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Set<Subject> getPrerequisiteFor() {
        return prerequisiteFor;
    }

    public void setPrerequisiteFor(Set<Subject> prerequisiteFor) {
        this.prerequisiteFor = prerequisiteFor;
    }

    public Set<AcademicRecord> getAcademicRecords() {
        return academicRecords;
    }

    public void setAcademicRecords(Set<AcademicRecord> academicRecords) {
        this.academicRecords = academicRecords;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
