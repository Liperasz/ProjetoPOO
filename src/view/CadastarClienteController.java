package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.vo.Sexo;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastarClienteController implements Initializable {

    @FXML
    private TextField jtfNome;
    @FXML
    private TextField jtfEmail;
    @FXML
    private TextField jtfCelular;
    @FXML
    private PasswordField jpfSenha;
    @FXML
    private TextField jtfCPF;
    @FXML
    private TextField jtfDN;
    @FXML
    private TextField jtfMN;
    @FXML
    private TextField jtfAN;
    @FXML
    private ChoiceBox<Sexo> jcbSexo;
    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnCadastrar;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cadastrarCliente() {
        jbtnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });
    }
}
