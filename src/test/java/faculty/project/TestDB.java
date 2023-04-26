package faculty.project;

import faculty.project.configuration.UtilDate;
import faculty.project.dataAccess.DataAccess;
import faculty.project.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.Calendar;
import java.util.List;

public class TestDB {

    DataAccess dbManager;

    @BeforeEach
    public void setUp() {
        dbManager = new DataAccess();
    }

    @AfterEach
    public void tearDown() {
        // dbManager.reset();
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

    @Test
    public void testGradeStudent(){
        dbManager.initializeDB();
        Student oihane = (Student) dbManager.getUser("Oihane", User.Role.Student);
        Subject softEng = dbManager.getSubject("Software Engineering");
        Teacher juanan = (Teacher) dbManager.getUser("Juanan", User.Role.Teacher);

        dbManager.gradeStudent(oihane, softEng, 9.5F, juanan);

        // get current year as int
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        AcademicRecord ar = dbManager.getAcademicRecord(oihane, softEng, currentYear);

        assertThat(ar.getGrade()).isEqualTo(9.5F);
    }
}
