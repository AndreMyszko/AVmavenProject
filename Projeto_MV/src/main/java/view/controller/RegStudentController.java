package view.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.StudentDAO;
import entity.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegStudentController implements Initializable {

	private static Stage stage;
	
	private static StudentDAO dao = new StudentDAO();
	
	public static Student student;
	
    @FXML
    private Button btnClose;

    @FXML
    private Button btnReturn;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtCurso;

    @FXML
    private TextField txtRa;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPeriodo;

    @FXML
    private TextField txtFone;

    //ActionEvents
    @FXML
    void btnReturn(ActionEvent event) {
    	try {
    		fechar();			
		} catch (Exception e) {
			e.printStackTrace();
		}	

    }

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
    void btnSalvar(ActionEvent event) {
    	try {
    		
    		if (student != null) {
            	dao.edit(new Student(
            			txtNome.getText(),
            			txtEmail.getText(),
            			txtFone.getText(),
            			txtEndereco.getText(),
            			txtRa.getText(),
            			txtCurso.getText(),
            			txtPeriodo.getText(),
            			student.getCod()
            			));
    		}
 	   		
    		if (student == null) {
            	dao.insert(new Student(
            			txtNome.getText(),
            			txtEmail.getText(),
            			txtFone.getText(),
            			txtEndereco.getText(),
            			txtRa.getText(),
            			txtCurso.getText(),
            			txtPeriodo.getText()
            			));
            	student = null;
			}
        	
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Aluno registrado com sucesso", ButtonType.OK);
    		
    		alert.setHeaderText("Aluno cadastrado");
    		alert.showAndWait();

		} catch (Exception e) {
			// TODO: handle exception
		}
		fechar();
    }
    
    @FXML
    void btnEditar(ActionEvent event) {

    }

    
    //Stage
	public Stage getStage() {
		if (stage == null) {
			try {
				Stage primaryStage = new Stage();
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/RegStudent.fxml"));
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
	
	private void fechar() {
		new StudentController().getStage().show();
		student = null;
		stage.close();

	}

	
	private void editStudent() {
		txtNome.setText(student.getName());
		txtEmail.setText(student.getEmail());
		txtEndereco.setText(student.getAddress());
		txtFone.setText(student.getPhone());
		txtRa.setText(student.getRa());
		txtCurso.setText(student.getCourse());
		txtPeriodo.setText(student.getPeriod());

		btnSalvar.setVisible(false);
		btnEditar.setVisible(true);
	}

	private void saveStudent() {
		btnSalvar.setVisible(true);
		btnEditar.setVisible(false);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (student != null) {
			editStudent();
		} else {
			saveStudent();
		}
	}

}
