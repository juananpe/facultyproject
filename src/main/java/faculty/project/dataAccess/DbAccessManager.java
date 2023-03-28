package faculty.project.dataAccess;

import faculty.project.domain.*;
import faculty.project.exceptions.UnknownUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DbAccessManager {

    private Connection conn = null;
    private String dbpath;

    public void open() {
        try {
            String url = "jdbc:sqlite:" + dbpath;
            conn = DriverManager.getConnection(url);

            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server " + e);
        }
    }

    public void close() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        System.out.println("Database connection closed");
    }


    // singleton pattern
    private static final DbAccessManager instance = new DbAccessManager();


    public static DbAccessManager getInstance() {
        return instance;
    }

    private DbAccessManager() {
        this(false);

    }
    private DbAccessManager(boolean initialize) {


        dbpath = "faculty.db";

        if (initialize) {
            initializeDB();
        }
        // we will use a user-defined database name later, using a config.properties file
        // Remember, we have already seen how to deal with properties in a previous activity:
        // https://docs.google.com/document/d/1S4nDcf-tkcDaKzYgQFO-2qLDLH_dfZISd7JUJ7N_6RI/edit?usp=sharing


    }

    public void initializeDB() {


        Student oihane = new Student("Oihane", "123456", "Oihane Soraluze",
                "oihane@email.com", "c/ Melancolía 13", "678012345");

        Student aitor = new Student("Aitor", "123456", "Aitor Sarriegi",
                "aitor@email.com", "c/ Esperanza 14", "678999999");

        Subject softwareEngineering = new Subject("Software Engineering", 6, 50);

        oihane.enroll(softwareEngineering);
        aitor.enroll(softwareEngineering);


        Teacher juanan = new Teacher(230, "+34-123456", "juanan", "pasahitza");
        juanan.add(softwareEngineering);

        storeSubject(softwareEngineering);
        storeStudent(oihane);
        storeStudent(aitor);
        storeTeacher(juanan);

        System.out.println("The database has been initialized");

    }

    public void storeSubject(Subject subject) {

        this.open();
        String sql = "INSERT INTO subject (name, numCredits, maxStudents) VALUES(?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, subject.getName());
            pstmt.setInt(2, subject.getCreditNumber());
            pstmt.setInt(3, subject.getMaxStudents());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.close();
    }

    public void storeStudent(Student student) {
        this.open();
        String sql = "INSERT INTO student (username, password, completeName, email, address, phoneNumber) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getUserName());
            pstmt.setString(2, student.getPassword());
            pstmt.setString(3, student.getCompleteName());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getAddress());
            pstmt.setString(6, student.getPhoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.close();
    }


    public void storeTeacher(Teacher teacher) {
        this.open();
        String sql = "INSERT INTO teacher (username, password, completeName, email, address, phoneNumber, officeNumber, corporatePhone) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getUserName());
            pstmt.setString(2, teacher.getPassword());
            pstmt.setString(3, teacher.getCompleteName());
            pstmt.setString(4, teacher.getEmail());
            pstmt.setString(5, teacher.getAddress());
            pstmt.setString(6, teacher.getPhoneNumber());
            pstmt.setInt(7, teacher.getOfficeNumber());
            pstmt.setString(8, teacher.getCorporatePhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.close();
    }

    public List<Teacher> removeTeachingAssignments() {
        return null;
//
//        TypedQuery<Teacher> query = db.createQuery("SELECT t FROM Teacher t",
//                Teacher.class);
//        List<Teacher> teachers = query.getResultList();
//        for (Teacher teacher : teachers) {
//            db.getTransaction().begin();
//            teacher.clearSubjects();
//            db.getTransaction().commit();
//        }
//
//        return teachers;
    }

    public List<Subject> getAllSubjects() {
//        TypedQuery<Subject> query = db.createQuery("SELECT s FROM Subject s",
//                Subject.class);
//        List<Subject> subjects = query.getResultList();
//        return subjects;

        return null;
    }


    public User login(String username, String password) throws UnknownUser {
        this.open();
        String sql = "SELECT * FROM teacher WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                    return new User(rs.getString("username"), rs.getString("password"), rs.getString("completeName"),
                            rs.getString("email"), rs.getString("address"), rs.getString("phoneNumber"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Student> getUngradedStudentsEnrolledIn(Subject subject) {

        return  null;

//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//
//        List<Student> students;
//        TypedQuery<Student> query = db.createQuery(
//                "SELECT ar.student FROM AcademicRecord ar WHERE ar.subject =?1 AND ar.year =?2 AND ar.signedBy is null",
//                Student.class);
//        query.setParameter(1, subject);
//        query.setParameter(2, currentYear);
//        students = query.getResultList();
//        return students;

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
//
//        boolean ok = true;
//
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//
//        TypedQuery<AcademicRecord> query = db.createQuery("UPDATE AcademicRecord ar SET ar.grade=?1, ar.signedBy=?2 WHERE ar.student=?3 AND ar.year =?4 AND ar.subject=?5",
//                AcademicRecord.class);
//
//        query.setParameter(1, grade);
//        query.setParameter(2, teacher);
//        query.setParameter(3, student);
//        query.setParameter(4, currentYear);
//        query.setParameter(5, subject);
//
//        db.getTransaction().begin();
//        int updateCount = query.executeUpdate();
//        if (updateCount == 1) {
//            if (grade >= 5) {
//                Student st = db.find(Student.class, student.getId());
//                // update the earnedCredits value of the student
//                st.setEarnedCredits(student.getEarnedCredits() + subject.getCreditNumber());
//            }
//        } else {
//            ok = false;
//        }
//        db.getTransaction().commit();
//
//        return ok;

        return false;
    }

    public void assign(Subject subject, Teacher teacher) {
//        db.getTransaction().begin();
//        teacher.add(subject);
//        db.getTransaction().commit();
    }

    public boolean isFull(Subject subject) {
//        TypedQuery<Long> query = db.createQuery("SELECT COUNT(ar) FROM AcademicRecord ar WHERE ar.year =?1 AND ar.subject=?2",
//                Long.class);
//
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        query.setParameter(1, currentYear);
//        query.setParameter(2, subject);
//        Long numEnrolledStudents = query.getSingleResult();
//        int maxNumStudents = subject.getMaxNumStudents();
//        return numEnrolledStudents == maxNumStudents;

        return false;

    }

    public boolean hasPassed(Subject subject, Student student) {
        // See: https://stackoverflow.com/a/30841688/243532
//
//        TypedQuery<Boolean> query = db.createQuery("SELECT COUNT(ar) > 0 FROM AcademicRecord ar " +
//                        "WHERE ar.subject=?1 AND ar.student=?2 AND ar.grade>=5",
//                Boolean.class);
//
//        query.setParameter(1, subject);
//        query.setParameter(2, student);
//        return query.getSingleResult();

        return false;

    }


    public void enrol(Student currentStudent, Subject subject) {
//        db.getTransaction().begin();
//        AcademicRecord ar = new AcademicRecord(subject, currentStudent);
//        db.persist(ar);
//        db.getTransaction().commit();
    }

    /*

    public List<Pilot> getAllPilots() {

        var pilots = new ArrayList<Pilot>();
        this.open();

        try {
            String query = "SELECT id, name, nationality, points FROM pilots";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                pilots.add(new Pilot(rs.getInt("id"), rs.getString("name"), rs.getString("nationality"), rs.getInt("points")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.close();
        return pilots;
    }

    public List<Pilot> getPilotsByNationality(String nationality) {
        this.open();
        String sql = "SELECT id, name, nationality, points "
                + "FROM pilots WHERE nationality = ?";

        var result = new ArrayList<Pilot>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nationality);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Pilot(rs.getInt("id"), rs.getString("name"), rs.getString("nationality"), rs.getInt("points")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        this.close();
        return result;
    }

    public Pilot getPilotByName(String name) {

        this.open();
        String sql = "SELECT id, name, nationality, points "
                + "FROM pilots WHERE name = ?";

        Pilot pilot = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            // will return the first pilot that matches the condition
            // usually, there will be only one pilot with a given name
            if (rs.next()) {
                pilot = new Pilot(rs.getInt("id"), rs.getString("name"), rs.getString("nationality"), rs.getInt("points"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        this.close();
        return pilot;
    }

    public int deletePilotByName(String pilotName) {
        this.open();
        int amount = 0;
        String sql = "DELETE FROM pilots WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pilotName);
            amount = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        this.close();

        return amount;
    }

    public void addPointsToPilot(int morePoints, int pilotId) {
        this.open();
        String sql = "UPDATE pilots SET points = points + ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, morePoints);
            pstmt.setInt(2, pilotId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        this.close();
    }

    public static DbAccessManager getInstance() {
        return instance;
    }


    public void deletePilotById(int id) {
        this.open();
        String sql = "DELETE FROM pilots WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        this.close();
    }


     */
}
