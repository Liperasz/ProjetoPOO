package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoricoVendaController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private ChoiceBox<String> jcbFiltro;
    @FXML
    private TextField jtfPesquisa;
    @FXML
    private ScrollPane scrollpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Relatorio.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }
}
