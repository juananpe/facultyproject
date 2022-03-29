package faculty.project.domain;

import java.util.Collection;

public class Subject {
    private String name;
    private int numCredits;
    private int maxNumStudents;


    public Subject(String name, int numCredits, int maxNumStudents) {
        this.name = name;
        this.numCredits = numCredits;
        this.maxNumStudents = maxNumStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }

    public int getMaxNumStudents() {
        return maxNumStudents;
    }

    public void setMaxNumStudents(int maxNumStudents) {
        this.maxNumStudents = maxNumStudents;
    }

    public Collection<Subject> getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(Collection<Subject> preRequisites) {
        this.preRequisites = preRequisites;
    }

    private Collection<Subject> preRequisites;
}
