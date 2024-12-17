package view;
import javafx.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.vo.Curso;
import model.vo.Sexo;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastarClienteController implements Initializable {

    @FXML
    private TextField jtfNomeAluno;
    @FXML
    private TextField jtfEmailAluno;
    @FXML
    private TextField jtfCelularAluno;
    @FXML
    private PasswordField jpfSenhaAluno;
    @FXML
    private TextField jtfCPFAluno;
    @FXML
    private TextField jtfDNAluno;
    @FXML
    private TextField jtfMNAluno;
    @FXML
    private TextField jtfRA;
    @FXML
    private TextField jtfANAluno;
    @FXML
    private ChoiceBox<Sexo> jcbSexoAluno;
    @FXML
    private ChoiceBox<Curso> jcbCursoAluno;
    @FXML
    private Button jbtnVoltarAluno;
    @FXML
    private Button jbtnCadastrarAluno;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cadastrarCliente() {
        jbtnCadastrarAluno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });
    }
}
