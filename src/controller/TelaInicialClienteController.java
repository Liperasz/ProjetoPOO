package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bo.FeedBackBO;
import model.vo.Feedback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
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


    public void EnviarComentario() throws IOException, SQLException, ClassNotFoundException {

        if (jtfComentario.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Erro ao cadastrar comentario");
        }

        Feedback feedback = new Feedback(jtfComentario.getText(), LocalDateTime.now());
        FeedBackBO.CadastrarFeedBack(feedback);
    }
    public void AcessarLogin() throws IOException {

        TrocadorTelas.TrocarTela("/view/Login.fxml", (Stage) jbtnLogin.getScene().getWindow());

    }
    public void AcessarCadastro() throws IOException {

        TrocadorTelas.TrocarTela("/view/Cadastrar.fxml", (Stage) jbtnCadastrar.getScene().getWindow());

    }
    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }

}