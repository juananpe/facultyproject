package faculty.project.dataAccess;

import faculty.project.configuration.ConfigXML;
import faculty.project.domain.*;
import faculty.project.exceptions.UnknownUser;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess {

  protected EntityManager db;
  protected EntityManagerFactory emf;

  ConfigXML config = ConfigXML.getInstance();

  public DataAccess(boolean initializeMode) {
    System.out.println("Creating DataAccess instance => isDatabaseLocal: " +
        config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());
    open(initializeMode);
  }

  public DataAccess() {
    this(false);
  }


  public void initializeDB() {

    db.getTransaction().begin();

    Student oihane = new Student("Oihane", "123456", "Oihane Soraluze",
        "oihane@email.com", "c/ Melancolía 13", "678012345");

    Student aitor = new Student("Aitor", "123456", "Aitor Sarriegi",
        "aitor@email.com", "c/ Esperanza 14", "678999999");

    Subject softwareEngineering = new Subject("Software Engineering", 6, 50);

    oihane.enroll(softwareEngineering);
    aitor.enroll(softwareEngineering);


    Teacher juanan = new Teacher(230, "+34-123456", "juanan", "pasahitza");
    juanan.add(softwareEngineering);

    db.persist(softwareEngineering);
    db.persist(oihane);
    db.persist(aitor);
    db.persist(juanan);

    db.getTransaction().commit();
    System.out.println("The database has been initialized");

  }


  public void open() {
    open(false);
  }

  public void open(boolean initializeMode) {

    System.out.println("Opening DataAccess instance => isDatabaseLocal: " +
        config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());

    String fileName = config.getDataBaseFilename();
    if (initializeMode) {
      fileName = fileName + ";drop";
      System.out.println("Deleting the DataBase");
    }

    if (config.isDataAccessLocal()) {
      emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
      db = emf.createEntityManager();
    } else {
      Map<String, String> properties = new HashMap<String, String>();
      properties.put("javax.persistence.jdbc.user", config.getDataBaseUser());
      properties.put("javax.persistence.jdbc.password", config.getDataBasePassword());

      emf = Persistence.createEntityManagerFactory("objectdb://" + config.getDataAccessNode() +
          ":" + config.getDataAccessPort() + "/" + fileName, properties);

      db = emf.createEntityManager();
    }
  }


  public void close() {
    db.close();
    System.out.println("DataBase is closed");
  }


  public List<Teacher> removeTeachingAssignments() {

    TypedQuery<Teacher> query = db.createQuery("SELECT t FROM Teacher t",
        Teacher.class);
    List<Teacher> teachers = query.getResultList();
    for (Teacher teacher : teachers) {
      db.getTransaction().begin();
      teacher.clearSubjects();
      db.getTransaction().commit();
    }

    return teachers;
  }

  public List<Subject> getAllSubjects() {
    TypedQuery<Subject> query = db.createQuery("SELECT s FROM Subject s",
        Subject.class);
    List<Subject> subjects = query.getResultList();
    return subjects;
  }


  public User login(String username, String password) throws UnknownUser {
    TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.userName =?1 AND u.password =?2",
        User.class);
    query.setParameter(1, username);
    query.setParameter(2, password);
    User user = query.getSingleResult();
    return user;
  }

  public List<Student> getUngradedStudentsEnrolledIn(Subject subject) {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    List<Student> students;
    TypedQuery<Student> query = db.createQuery(
        "SELECT ar.student FROM AcademicRecord ar WHERE ar.subject =?1 AND ar.year =?2 AND ar.signedBy is null",
        Student.class);
    query.setParameter(1, subject);
    query.setParameter(2, currentYear);
    students = query.getResultList();
    return students;

  }

  /**
   * Add the grade (if passed -> update the earnedCredits value of the student)
   * sign the record
   *
   * @param student
   * @param subject
   * @param grade
   * @param teacher
   * @return successfully updated
   */
  public boolean gradeStudent(Student student, Subject subject, float grade, Teacher teacher) {
    // maybe we should update the grade in the AcademicRecord inside a transaction...

    boolean ok = true;

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    TypedQuery<AcademicRecord> query = db.createQuery("UPDATE AcademicRecord ar SET ar.grade=?1, ar.signedBy=?2 WHERE ar.student=?3 AND ar.year =?4 AND ar.subject=?5",
        AcademicRecord.class);

    query.setParameter(1, grade);
    query.setParameter(2, teacher);
    query.setParameter(3, student);
    query.setParameter(4, currentYear);
    query.setParameter(5, subject);

    db.getTransaction().begin();
    int updateCount = query.executeUpdate();
    if (updateCount == 1) {
      if (grade >= 5) {
        Student st = db.find(Student.class, student.getId());
        // update the earnedCredits value of the student
        st.setEarnedCredits(student.getEarnedCredits() + subject.getCreditNumber());
      }
    } else {
      ok = false;
    }
    db.getTransaction().commit();

    return ok;

  }

  public void assign(Subject subject, Teacher teacher) {
    db.getTransaction().begin();
    teacher.add(subject);
    db.getTransaction().commit();
  }

  public boolean isFull(Subject subject) {
    TypedQuery<Long> query = db.createQuery("SELECT COUNT(ar) FROM AcademicRecord ar WHERE ar.year =?1 AND ar.subject=?2",
        Long.class);

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    query.setParameter(1, currentYear);
    query.setParameter(2, subject);
    Long numEnrolledStudents = query.getSingleResult();
    int maxNumStudents = subject.getMaxNumStudents();
    return numEnrolledStudents == maxNumStudents;

  }

  public boolean hasPassed(Subject subject, Student student) {
    // See: https://stackoverflow.com/a/30841688/243532

    TypedQuery<Boolean> query = db.createQuery("SELECT COUNT(ar) > 0 FROM AcademicRecord ar " +
            "WHERE ar.subject=?1 AND ar.student=?2 AND ar.grade>=5",
        Boolean.class);

    query.setParameter(1, subject);
    query.setParameter(2, student);
    return query.getSingleResult();

  }


  public void enrol(Student currentStudent, Subject subject) {
    db.getTransaction().begin();
    AcademicRecord ar = new AcademicRecord(subject, currentStudent);
    db.persist(ar);
    db.getTransaction().commit();
  }

  public static void main(String[] args) {
    // Testing...
    DataAccess da = new DataAccess(true);
    da.initializeDB();
    List<Teacher> allTeachers = da.removeTeachingAssignments();
    List<Subject> allSubjects = da.getAllSubjects();

    Subject softEng = allSubjects.get(0);
    Teacher juanan = allTeachers.get(0);
    da.assign(softEng, juanan);


    try {
      Student oihane = (Student)da.login("Oihane", "123456");
      System.out.println("Oihane has passed SoftEng: " + da.hasPassed(softEng, oihane));
      da.gradeStudent(oihane, softEng, 8, juanan);
      System.out.println("Oihane has passed SoftEng: " + da.hasPassed(softEng, oihane));


      Subject webSystems = new Subject("Web Systems", 6, 50);
      da.db.getTransaction().begin();
      da.db.persist(webSystems);
      da.db.getTransaction().commit();


      Student aitor = (Student)da.login("Aitor", "123456");
      da.enrol(aitor, webSystems);
      System.out.println("Aitor has passed WebSystems: " + da.hasPassed(webSystems, aitor));

    } catch (UnknownUser e) {
      e.printStackTrace();
    }

    System.out.println("Is full: " + da.isFull(softEng));


  }


}
