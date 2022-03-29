package faculty.project.businessLogic;

import faculty.project.domain.Student;
import faculty.project.domain.Subject;
import faculty.project.domain.Teacher;

public class BlFacadeImplementation implements BlFacade {

  public BlFacadeImplementation() {
    initializeDB();
  }

  private void initializeDB() {

    Student oihane = new Student("Oihane", "123456", "Oihane Soraluze",
        "oihane@email.com", "c/ Melancolía 13", "678012345");

    Student aitor = new Student("Aitor", "123456", "Aitor Sarriegi",
        "aitor@email.com", "c/ Esperanza 14", "678999999");

    Subject softwareEngineering = new Subject("Software Engineering", 6, 50);

    oihane.enroll(softwareEngineering);
    aitor.enroll(softwareEngineering);

  }


  @Override
  public void consultStudentsRecords() {

  }

  @Override
  public void gradeStudents(Teacher teacher, Subject subject) {
    // each Subject has an assigned teacher, so we can start from Subject
    // query the DB, asking for all the academic records (students) of this year and Subject
    // now, for each student of the subject, get her academicRecord for this year and subject
    //      add the grade (if passed -> update the earnedCredits value of the student)
    //      sign the record

  }

  @Override
  public void restartSystem() {
    // for each subject, reset the 'enrolledIn' association for this year's students
  }

  @Override
  public void authenticate(String login, String password) {

  }

}
