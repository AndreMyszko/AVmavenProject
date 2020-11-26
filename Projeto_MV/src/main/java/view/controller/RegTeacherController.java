package view.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.TeacherDAO;
import entity.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RegTeacherController implements Initializable {
	
    private static Stage stage;
    
    private static TeacherDAO dao = new TeacherDAO();
    
    public static Teacher teacher;
    	
	@FXML
    private Button btnClose;

    @FXML
    private Button btnReturn;

    @FXML
    void btnClose(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	
    	alert.setTitle("Sair");
    	alert.setHeaderText("Deseja realmente sair?");  	
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	 if (result.isPresent() && result.get() == ButtonType.OK) {
    		 //CLASS.//staticMETHOD = null //variable;
    	     System.exit(0);
    	 }  
    }

    @FXML
    void btnReturn(ActionEvent event) {
    	try {
        	stage.close();
        	stage = null;
        	
        	new HomeController().getStage().show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	    	
    }
    
    //Stage
	public Stage getStage() {
		if (stage == null) {
			try {
				Stage primaryStage = new Stage();
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/RegistroProfessor.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.initStyle(StageStyle.TRANSPARENT);
				scene.setFill(Color.TRANSPARENT);
				stage = primaryStage;
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
				System.out.println("tela não está confirgurada de forma correta");
				alert.showAndWait();
			}
		}
		return stage;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
