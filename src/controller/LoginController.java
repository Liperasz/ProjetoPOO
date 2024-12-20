package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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
    public void Voltar() throws IOException {

        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void Logar() throws IOException {

        TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());

    }

}
