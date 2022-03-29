package faculty.project.businessLogic;


import faculty.project.domain.Subject;

/**
 * Interface that specifies the business logic.
 */
public interface BlFacade {

  /**************** Teacher *******************/
  public void consultStudentsRecords();

  /**
   *
   * @param subject the subject is from this academic course
   */

  public void gradeStudents(Subject subject);

  /***  Academic Officer ***/
  public void restartSystem();

  /*** User ***/
  public void authenticate(String login, String password);

}
