module com.example.facultyproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.xml;
    requires javax.persistence;
    requires java.desktop;

    exports faculty.project.dataAccess;
    opens faculty.project.dataAccess to javafx.fxml;
    exports faculty.project.ui;
    opens faculty.project.ui to javafx.fxml;
    exports faculty.project.controllers;
    opens faculty.project.controllers to javafx.fxml;
}
