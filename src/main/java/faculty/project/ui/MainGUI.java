package faculty.project.ui;

import faculty.project.businessLogic.BlFacade;
import faculty.project.exceptions.UnknownUser;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import faculty.project.uicontrollers.*;

import java.io.IOException;
import java.lang.reflect.Constructor;

public class MainGUI {

  private Window loginWin, gradingWin;

  private BlFacade businessLogic;
  private Stage stage;
  private Scene scene;

  public BlFacade getBusinessLogic() {
    return businessLogic;
  }

  public void setBusinessLogic(BlFacade afi) {
    businessLogic = afi;
  }

  public MainGUI(BlFacade bl) {
    Platform.startup(() -> {
      try {
        setBusinessLogic(bl);
        init(new Stage());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }


  class Window {
    Controller c;
    Parent ui;
  }

  private Window load(String fxmlfile) throws IOException {
    Window window = new Window();
    FXMLLoader loader = new FXMLLoader(MainGUI.class.getResource(fxmlfile) /*, ResourceBundle.getBundle("Etiquetas", Locale.getDefault())*/ );
    loader.setControllerFactory(controllerClass -> {

      try {
        Constructor<?> cons = controllerClass.getConstructor(BlFacade.class);
        // Return a new instance of the controller
        return cons.newInstance(businessLogic);

      } catch (Exception exc) {
        exc.printStackTrace();
        throw new RuntimeException(exc); // fatal, just bail...
      }

    });

    window.ui = loader.load();
    ((Controller) loader.getController()).setMainApp(this);
    window.c = loader.getController();
    return window;
  }

  public void init(Stage stage) throws IOException {

    this.stage = stage;

    // just for testing we are hardcoding the login
    try {
      businessLogic.login("juanan", "pasahitza");
    } catch (UnknownUser e) {
      throw new RuntimeException(e);
    }
    showGrading();

  }

  public void showLogin() throws IOException {

    if (loginWin==null)
      loginWin = load("/login.fxml");

    setupScene(loginWin.ui, "Login", 480, 320);
  }
  public void showGrading() throws IOException {

    if (gradingWin==null)
        gradingWin = load("/grading.fxml");

    setupScene(gradingWin.ui, "Grading", 520, 420);
  }

  private void setupScene(Parent ui, String title, int width, int height) {
    if (scene == null){
      scene = new Scene(ui, width, height);
      scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
      stage.setScene(scene);
    }
    stage.setWidth(width);
    stage.setHeight(height);
    stage.setTitle(title);
    scene.setRoot(ui);
    stage.show();
  }

}
