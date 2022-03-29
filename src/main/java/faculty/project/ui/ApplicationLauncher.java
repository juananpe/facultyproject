package faculty.project.ui;

import faculty.project.businessLogic.BlFacade;
import faculty.project.businessLogic.BlFacadeImplementation;

import java.util.Locale;

public class ApplicationLauncher {

  public static void main(String[] args) {

    System.out.println("Locale: " + Locale.getDefault());

    BlFacade businessLogic = new BlFacadeImplementation();

    new MainGUI(businessLogic);

  }


}
