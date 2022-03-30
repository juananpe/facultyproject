	package faculty.project.dataAccess;

    import faculty.project.configuration.ConfigXML;
    import faculty.project.configuration.UtilDate;
		import faculty.project.domain.Student;
		import faculty.project.domain.Subject;
		import faculty.project.domain.Teacher;


		import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.Persistence;
    import javax.persistence.TypedQuery;
    import java.util.*;

/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess  {

	protected EntityManager  db;
	protected EntityManagerFactory emf;

	ConfigXML config = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode)  {
		System.out.println("Creating DataAccess instance => isDatabaseLocal: " +
				config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());
		open(initializeMode);
	}

	public DataAccess()  {
		this(false);
	}


	public void initializeDB(){

		db.getTransaction().begin();

		Student oihane = new Student("Oihane", "123456", "Oihane Soraluze",
				"oihane@email.com", "c/ Melancolía 13", "678012345");

		Student aitor = new Student("Aitor", "123456", "Aitor Sarriegi",
				"aitor@email.com", "c/ Esperanza 14", "678999999");

		Subject softwareEngineering = new Subject("Software Engineering", 6, 50);

		oihane.enroll(softwareEngineering);
		aitor.enroll(softwareEngineering);


		Teacher juanan= new Teacher(230, "+34-123456");
		juanan.add(softwareEngineering);

		db.persist(softwareEngineering);
		db.persist(oihane);
		db.persist(aitor);
		db.persist(juanan);

		db.getTransaction().commit();
		System.out.println("The database has been initialized");

	}




	public void open(boolean initializeMode){

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
					":"+config.getDataAccessPort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}


	public void close(){
		db.close();
		System.out.println("DataBase is closed");
	}
}
