package faculty.project.businessLogic;


import faculty.project.domain.Student;
import faculty.project.domain.Subject;
import faculty.project.domain.Teacher;
import faculty.project.domain.User;
import faculty.project.exceptions.UnknownUser;

import java.util.List;

/**
 * Interface that specifies the business logic.
 */
public interface BlFacade {

  /**
   * ************** Teacher *******************/
  void consultStudentsRecords();
  boolean gradeStudent(Student student, Subject subject, float gradeValue);
  /**
   * Academic Officer ***/
  void restartSystem();

  /**
   *  User ***/
  void login(String login, String password, User.Role role) throws UnknownUser;

  /**
   * Get subjects for current teacher
   */
  List<Subject> getSubjects();
  void setCurrentUser(User user);

  List<Student> getUngradedStudentsEnrolledIn(Subject subject);

  List<Subject> getAllSubjects();

  void assign(Subject subject, Teacher teacher);

  boolean isEligible(Subject subject);

  void enrol(List<Subject> subjects);

  }
