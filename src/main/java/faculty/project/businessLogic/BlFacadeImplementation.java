package faculty.project.businessLogic;

import faculty.project.configuration.ConfigXML;
import faculty.project.dataAccess.DataAccess;
import faculty.project.domain.Student;
import faculty.project.domain.Subject;
import faculty.project.domain.Teacher;
import faculty.project.domain.User;
import faculty.project.exceptions.UnknownUser;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class BlFacadeImplementation implements BlFacade {

  DataAccess dbManager;
  ConfigXML config = ConfigXML.getInstance();

  private User currentUser;

  public BlFacadeImplementation() {

    System.out.println("Creating BlFacadeImplementation instance");
    boolean initialize = config.getDataBaseOpenMode().equals("initialize");
    dbManager = new DataAccess(initialize);
    if (initialize)
      dbManager.initializeDB();
    dbManager.close();

    // hardcode current user for testing purposes
    try {
      login("juanan","pasahitza");
    } catch (UnknownUser e) {
      e.printStackTrace();
    }

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

  @Override
  public List<Subject> getSubjects() {
      Teacher teacher = (Teacher) this.currentUser;
      return teacher.getSubjects();
  }

  @Override
  public void setCurrentUser(User user) {
    this.currentUser = user;
  }

  public void login(String username, String password) throws UnknownUser {
    User user;

    dbManager.open();
    user = dbManager.login(username, password);
    dbManager.close();

    this.currentUser = user;
  }

  @Override
  public List<Student> getStudentsEnrolledIn(Subject subject) {
    List<Student> students;
    dbManager.open();
    students = dbManager.getStudentsEnrolledIn(subject);
    dbManager.close();
    return students;
  }

}
