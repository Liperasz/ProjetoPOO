package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.vo.Cliente;
import model.vo.Comida;
import model.vo.Ingrediente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginClienteController implements Initializable {
    @FXML
    private Button jbtnLogin;
    @FXML
    private Button jbtnVoltar;
    @FXML
    private PasswordField jtfSenha;
    @FXML
    private TextField jtfEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void voltar() throws IOException {

        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void logar() throws IOException {

        TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());


    }

}
