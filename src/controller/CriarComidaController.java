package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CriarComidaController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnConcluir;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField jtfNome;
    @FXML
    private TextField jtfDescricao;
    @FXML
    private TextField jtfValor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Cardapio.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void Concluir() throws IOException {
        if (jtfValor.getText().isEmpty() || jtfNome.getText().isEmpty() || jtfDescricao.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Erro ao cadastrar");
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