package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialAtendenteController implements Initializable {

    @FXML
    private Button jbtnLogin;
    @FXML
    private TextField jtfEmail;
    @FXML
    private PasswordField jpfSenha;


    @Override
    public void initialize (URL url, ResourceBundle rb) {

    }

    public void getEmail() {

    }

    public void getSenha() {

    }

    public void Logar() throws IOException {
        TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnLogin.getScene().getWindow());

    }
}
