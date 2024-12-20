package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable { ;


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

        if (TrocadorTelas.getUsuario() != Usuario.CLIENTE) {
            jbtnVoltar.setVisible(false);
        }
    }

    public void Voltar() throws IOException {

        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    public void Logar() throws IOException {

        if (jtfEmail.getText().isEmpty() || jtfSenha.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Email ou senha Invalido");
        }

        if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {
            TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());
        } else  if (TrocadorTelas.getUsuario() == Usuario.ADMINISTRADOR) {
            TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnLogin.getScene().getWindow());
        }

    }

    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }
}
