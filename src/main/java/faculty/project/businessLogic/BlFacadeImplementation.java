package faculty.project.businessLogic;

import faculty.project.configuration.ConfigXML;
import faculty.project.dataAccess.DataAccess;
import faculty.project.domain.Student;
import faculty.project.domain.Subject;
import faculty.project.domain.Teacher;
import faculty.project.domain.User;
import faculty.project.exceptions.UnknownUser;

import java.util.Collection;
import java.util.List;

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
  public boolean gradeStudent(Student student, Subject subject, float grade) {

    Teacher teacher = (Teacher) this.currentUser;
    dbManager.open();

    boolean ok = dbManager.gradeStudent(student, subject, grade, teacher);

    dbManager.close();

    return ok;
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
  public List<Student> getUngradedStudentsEnrolledIn(Subject subject) {
    List<Student> students;
    dbManager.open();
    students = dbManager.getUngradedStudentsEnrolledIn(subject);
    dbManager.close();
    return students;
  }

  @Override
  public List<Subject> getAllSubjects() {
    List<Subject> subjects;
    dbManager.open();
    subjects = dbManager.getAllSubjects();
    dbManager.close();
    return subjects;
  }

  @Override
  public void assign(Subject subject, Teacher teacher) {
    dbManager.open();
    dbManager.assign(subject, teacher);
    dbManager.close();
  }

  @Override
  public boolean isEligible(Subject subject) {

    boolean eligible;
    Student currentStudent = (Student)currentUser;
    dbManager.open();
    eligible = !dbManager.isFull(subject);
    Collection<Subject> prereq = subject.getPreRequisites();
    prereq.add(subject); // we want to check not only if the student has passed the prerequisites but also if s/he has passed the actual subject before!
    for (Subject sub : prereq) {
      eligible = eligible && dbManager.hasPassed(sub, currentStudent);
    }
    int tcr = subject.getCreditNumber(); // total credits
    eligible = eligible && currentStudent.isEligibleForCredits(tcr);
    dbManager.close();

    return eligible;
  }

  @Override
  public void enrol(List<Subject> subjects) {
    Student currentStudent = (Student)currentUser;
    dbManager.open();
    for (Subject subject : subjects) {
      dbManager.enrol(currentStudent, subject);
    }
    dbManager.close();
  }

}
