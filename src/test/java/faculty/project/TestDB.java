package faculty.project;

import faculty.project.dataAccess.DataAccess;
import faculty.project.domain.Student;
import faculty.project.domain.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.List;

public class TestDB {

    DataAccess dbManager;

    @BeforeEach
    public void setUp() {
        dbManager = new DataAccess();
    }

    @AfterEach
    public void tearDown() {
        dbManager.reset();
        dbManager.close();
    }

    @Test
    public void testReset() {
        dbManager.reset();
        List<Student> students = dbManager.getAllStudents();
        assertThat(students.size()).isEqualTo(0);
    }


    @Test
    public void testDB() {
        dbManager.initializeDB();
        List<Student> students = dbManager.getAllStudents();
        System.out.println("Students: " + students);
        assertThat(students.size()).isGreaterThan(0);

        List<Subject> subjects = dbManager.getAllSubjects();
        System.out.println("Subjects: " + subjects);
        assertThat(subjects.size()).isGreaterThan(0);

    }
}
