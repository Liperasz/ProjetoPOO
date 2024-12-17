package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.vo.Sexo;

import java.io.IOException;
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

    public void Voltar() throws IOException {
        Stage fecha = (Stage) jbtnVoltar.getScene().getWindow();
        fecha.close();
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/TelaInicialCliente.fxml"));
        Scene scene = new Scene(voltar);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void CadastrarCliente() throws IOException {
        Stage fecha = (Stage) jbtnCadastrar.getScene().getWindow();
        fecha.close();
        Parent cadastrar = FXMLLoader.load(getClass().getResource("/view/PedidoCliente.fxml"));
        Scene scene = new Scene(cadastrar);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }





}
