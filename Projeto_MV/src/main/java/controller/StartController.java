package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.controller.HomeController;

public class StartController extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			new HomeController().getStage().show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
