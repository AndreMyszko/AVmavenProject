package view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import util.GenericTableButton;
import dao.StudentDAO;
import entity.Student;

public class StudentController implements Initializable {

	private static Stage stage;
	
	private StudentDAO dao = new StudentDAO();
	
	public static Student student;
	
	
	//Table Objects
	@FXML
	private TableView<Student> tableView;
	
    @FXML
    private TableColumn<Student, String> cNome;

    @FXML
    private TableColumn<Student, String> cCurso;

    @FXML
    private TableColumn<Student, String> cEndereco;

    @FXML
    private TableColumn<Student, String> cRa;

    @FXML
    private TableColumn<Student, String> cPeriodo;

    @FXML
    private TableColumn<Student, String> cEmail;

    @FXML
    private TableColumn<Student, String> cFone;

    @FXML
    private TableColumn<Student, Integer> cCod;
	
    @FXML
    private TableColumn<Student, Student> cEditar;
    
    @FXML
    private TableColumn<Student, Student> cExcluir;
    
    
	// SVG ICONS (EDITAR e EXCLUIR)
	public static final String PEN_SOLID = "M290.74 93.24l128.02 128.02-277.99 277.99-114.14 12.6C11.35 513.54-1.56 500.62.14 485.34l12.7-114.22 277.9-277.88zm207.2-19.06l-60.11-60.11c-18.75-18.75-49.16-18.75-67.91 0l-56.55 56.55 128.02 128.02 56.55-56.55c18.75-18.76 18.75-49.16 0-67.91z";
	public static final String TRASH_SOLID = "M432 32H312l-9.4-18.7A24 24 0 0 0 281.1 0H166.8a23.72 23.72 0 0 0-21.4 13.3L136 32H16A16 16 0 0 0 0 48v32a16 16 0 0 0 16 16h416a16 16 0 0 0 16-16V48a16 16 0 0 0-16-16zM53.2 467a48 48 0 0 0 47.9 45h245.8a48 48 0 0 0 47.9-45L416 128H32z";
	
	
	//FXML Objects
    @FXML
    private Button btnReturn;
    
    @FXML
    private Button btnClose;

    @FXML
    private Button btnNovo;
    
    @FXML
    private Button btnPesquisar;
    
    @FXML
    private TextField txtCodPesquisa;

    @FXML
    private TextField txtNomePesquisa;
    
    
    //ActionEvents
    @FXML
    void btnPesquisar(ActionEvent event) {
    	listStudent();
    }
    
    @FXML
    void btnNovo(ActionEvent event) {
    	try {
        	stage.close();
        	stage = null;
        	student = null;
        	
        	new RegStudentController().getStage().show();
			
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
    void btnReturn(ActionEvent event) {
    	try {
        	stage.close();
        	stage = null;
        	
        	new HomeController().getStage().show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	    	
    }
    
	private void listStudent() {
		ObservableList<Student> listStud;

		try {

			listStud = FXCollections.observableArrayList(dao.list());

			cCod.setCellValueFactory(new PropertyValueFactory<Student, Integer>("cod"));
			cNome.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
			cEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
			cFone.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
			cEndereco.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
			cRa.setCellValueFactory(new PropertyValueFactory<Student, String>("ra"));
			cCurso.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
			cPeriodo.setCellValueFactory(new PropertyValueFactory<Student, String>("period"));

			tableView.setItems(listStud);

			//btnEditar
			GenericTableButton.initButtons(cEditar, 15, PEN_SOLID, "svg-gray",
					(Student student, ActionEvent event) -> {
						try {
							if (student != null) {
								RegStudentController.student = student;							
								stage.close();
								stage = null;
								new RegStudentController().getStage().show();							
							}
						} catch (Exception e) {
							e.printStackTrace();
							Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
							alert.showAndWait();
						}
					});

			//btnExcluir
			GenericTableButton.initButtons(cExcluir, 15, TRASH_SOLID, "svg-red",
					(Student student, ActionEvent event) -> {
						try {
							Alert alert = new Alert(AlertType.CONFIRMATION);

							alert.setTitle("Excluir Aluno");
							alert.setHeaderText(
									" Caso o aluno seja excluído seus dados serão perdidos permanentemente! \n Se deseja editar o aluno vá para guia de edição.");
							Optional<ButtonType> result = alert.showAndWait();
							if (result.isPresent() && result.get() == ButtonType.OK) {
								if (student != null) {

									dao.delet(student.getCod());

									ObservableList<Student> listStudUp;
									listStudUp = FXCollections.observableArrayList(dao.list());
									tableView.setItems(listStudUp);
									//student = null;
								}
							}
						} catch (Exception e) {
							Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
							alert.setHeaderText("Atenção");
							alert.showAndWait();
						}

					});

			if (listStud != null) {
				if (txtCodPesquisa != null && conferirNumero(txtCodPesquisa.getText(), "Insira um número")) {
					List<Student> listaCod = new ArrayList<Student>();
					for (Student s : listStud) {
						if ((s.getCod() == Integer.parseInt(txtCodPesquisa.getText()))) {
							listaCod.add(s);
							tableView.setItems(listStud);
							
						}
					}
					listStud = FXCollections.observableArrayList(listaCod);
					
				}
				
				if (txtNomePesquisa != null) {
					List<Student> listaNome = new ArrayList<Student>();
					for (Student s : listStud) {
						if (s.getName().matches(".*" + txtNomePesquisa.getText() + ".*")) {
							listaNome.add(s);
						}
					}
					listStud = FXCollections.observableArrayList(listaNome);
				}
				tableView.setItems(listStud);
				
			}
			

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
			alert.showAndWait();
		}

	}

	private Boolean conferirNumero(String texto, String msg) {
		try {
			if (!texto.equals("")) {
				Integer.parseInt(texto);
			} else if (texto.equals("")) {
				return false;
			}
		} catch (Exception e) {
			throw new NumberFormatException(msg);
		}
		return true;
	}
		
	
    //Stage
	public Stage getStage() {
		if (stage == null) {
			try {
				Stage primaryStage = new Stage();
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Student.fxml"));
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
		listStudent();
	}

}
