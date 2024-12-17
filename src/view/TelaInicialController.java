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
        Stage fecha = (Stage) jbtnCardapio.getScene().getWindow();
        fecha.close();
        Parent cardapio = FXMLLoader.load(getClass().getResource("/view/CardapioCliente.fxml"));
        Scene scene = new Scene(cardapio);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void enviarComentario() {
        String c = jtfComentario.getText();
        System.out.println(c);
    }
    public void acessarLogin() throws IOException {
        Stage fecha = (Stage) jbtnLogin.getScene().getWindow();
        fecha.close();
        Parent login = FXMLLoader.load(getClass().getResource("/view/LoginCliente.fxml"));
        Scene scene = new Scene(login);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void acessarCadastro() throws IOException {
        Stage fecha = (Stage) jbtnCadastrar.getScene().getWindow();
        fecha.close();
        Parent cadastro = FXMLLoader.load(getClass().getResource("/view/CadastrarCliente.fxml"));
        Scene scene = new Scene(cadastro);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}
