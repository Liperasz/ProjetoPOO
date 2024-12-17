package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {
    @FXML
    private Button jbtnCardapio;
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

    public void acessarCardapio() throws IOException {

        TrocadorTelas.TrocarTela("/view/CardapioCliente.fxml", (Stage) jbtnCardapio.getScene().getWindow());

    }
    public void enviarComentario() {
        String c = jtfComentario.getText();
        System.out.println(c);
    }
    public void acessarLogin() throws IOException {

        TrocadorTelas.TrocarTela("/view/LoginCliente.fxml", (Stage) jbtnLogin.getScene().getWindow());

    }
    public void acessarCadastro() throws IOException {

        TrocadorTelas.TrocarTela("/view/CadastrarCliente.fxml", (Stage) jbtnCadastrar.getScene().getWindow());

    }


}
