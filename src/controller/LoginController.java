package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bo.AdministradorBO;
import model.bo.AtendenteBO;
import model.bo.ClienteBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public void Logar() throws IOException, SQLException, ClassNotFoundException {

        if (jtfEmail.getText().isEmpty() || jtfSenha.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Email ou senha Invalido");
        }

        if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {

            if (ClienteBO.loginCliente(jtfEmail.getText(), jtfSenha.getText())) {
                TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());

            }


        } else  if (TrocadorTelas.getUsuario() == Usuario.ADMINISTRADOR) {

            if (AdministradorBO.loginAdministrador(jtfEmail.getText(), jtfSenha.getText())) {
                TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());

            }

        } else {

            if (AtendenteBO.loginAtendente(jtfEmail.getText(), jtfSenha.getText())) {
                TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnLogin.getScene().getWindow());

            }


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
