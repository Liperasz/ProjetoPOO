package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FuncionarioController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnCadastrarFuncionario;
    @FXML
    private Button jbtnSalvar;
    @FXML
    private ScrollPane scrollPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void AcessarCadastrarFuncionario() throws IOException {
        TrocadorTelas.TrocarTela("/view/Cadastrar.fxml", (Stage) jbtnCadastrarFuncionario.getScene().getWindow());

    }

    public void Salvar() {

    }
}
