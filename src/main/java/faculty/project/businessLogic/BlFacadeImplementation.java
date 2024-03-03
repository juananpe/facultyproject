package faculty.project.businessLogic;

import faculty.project.configuration.Config;
import faculty.project.dataAccess.DataAccess;
import faculty.project.domain.Student;
import faculty.project.domain.Subject;
import faculty.project.domain.Teacher;
import faculty.project.domain.User;
import faculty.project.exceptions.UnknownUser;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BlFacadeImplementation implements BlFacade {

  DataAccess dbManager;
  Config config = Config.getInstance();

  private User currentUser;

  public BlFacadeImplementation() {

    System.out.println("Creating BlFacadeImplementation instance");
    boolean initialize = config.getDataBaseOpenMode().equals("initialize");
    dbManager = new DataAccess();
    if (initialize)
      dbManager.initializeDB();


    // hardcode current user for testing purposes
//    try {
//      login("juanan","pasahitza", User.Role.Teacher);
//    } catch (UnknownUser e) {
//      e.printStackTrace();
//    }

  }


  @Override
  public void consultStudentsRecords() {

  }

  @Override
  public void gradeStudent(Student student, Subject subject, float grade) {

    Teacher teacher = (Teacher) this.currentUser;
    dbManager.open();

    dbManager.gradeStudent(student, subject, grade, teacher);


  }

  @Override
  public void restartSystem() {
    // for each subject, reset the 'enrolledIn' association for this year's students
  }

  @Override
  public Set<Subject> getSubjects() {
      Teacher teacher = (Teacher) this.currentUser;
      return teacher.getSubjects();
  }

  @Override
  public void setCurrentUser(User user) {
    this.currentUser = user;
  }

  public void login(String username, String password) throws UnknownUser {
    User user;

    user = dbManager.login(username, password);


    this.currentUser = user;
  }

  @Override
  public List<Student> getUngradedStudentsEnrolledIn(Subject subject) {
    List<Student> students;

    students = dbManager.getUngradedStudentsEnrolledIn(subject);

    return students;
  }

  @Override
  public List<Subject> getAllSubjects() {
    List<Subject> subjects;

    subjects = dbManager.getAllSubjects();

    return subjects;
  }

  @Override
  public void assign(Subject subject, Teacher teacher) {

    dbManager.assign(subject, teacher);

  }

  @Override
  public boolean isEligible(Subject subject) {

    boolean eligible;
    Student currentStudent = (Student)currentUser;
    dbManager.open();
    eligible = !dbManager.isFull(subject);
    Collection<Subject> prereq = subject.getPrerequisiteFor();
    prereq.add(subject); // we want to check not only if the student has passed the prerequisites but also if s/he has passed the actual subject before!
    for (Subject sub : prereq) {
      eligible = eligible && dbManager.hasPassed(sub, currentStudent);
    }
    int tcr = subject.getCredits();
    eligible = eligible && currentStudent.isEligibleForCredits(tcr);


    return eligible;
  }

  @Override
  public void enrol(List<Subject> subjects) {
    Student currentStudent = (Student)currentUser;

    for (Subject subject : subjects) {
      dbManager.enrol(currentStudent, subject);
    }

  }

}
