package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialClienteController implements Initializable {

    @FXML
    private TextField jtfComentario;
    @FXML
    private Button jbtnEnviar;
    @FXML
    private Button jbtnLogin;
    @FXML
    private Button jbtnCadastrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void EnviarComentario() {

        String c = jtfComentario.getText();
        System.out.println(c);
    }
    public void AcessarLogin() throws IOException {

        TrocadorTelas.TrocarTela("/view/Login.fxml", (Stage) jbtnLogin.getScene().getWindow());

    }
    public void AcessarCadastro() throws IOException {

        TrocadorTelas.TrocarTela("/view/Cadastrar.fxml", (Stage) jbtnCadastrar.getScene().getWindow());

    }


}
