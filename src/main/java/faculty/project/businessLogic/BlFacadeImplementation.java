package faculty.project.businessLogic;

import faculty.project.configuration.ConfigXML;
import faculty.project.dataAccess.DataAccess;
import faculty.project.domain.Student;
import faculty.project.domain.Subject;
import faculty.project.domain.Teacher;

public class BlFacadeImplementation implements BlFacade {

  DataAccess dbManager;
  ConfigXML config = ConfigXML.getInstance();


  public BlFacadeImplementation() {

    System.out.println("Creating BlFacadeImplementation instance");
    boolean initialize = config.getDataBaseOpenMode().equals("initialize");
    dbManager = new DataAccess(initialize);
    if (initialize)
      dbManager.initializeDB();
    dbManager.close();
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
