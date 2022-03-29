package faculty.project.domain;

import java.util.Collection;

public class Subject {
    private String name;
    private int numCredits;
    private int maxNumStudents;

    private Collection<Subject> preRequisites;
}
