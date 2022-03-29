package faculty.project.businessLogic;

import faculty.project.domain.Student;
import faculty.project.domain.Subject;

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
  public void gradeStudents(Subject subject) {

  }

  @Override
  public void restartSystem() {

  }

  @Override
  public void authenticate(String login, String password) {

  }

}
