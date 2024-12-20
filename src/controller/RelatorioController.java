package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RelatorioController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private ListView<String> jlvMaisVendaMes;
    @FXML
    private ListView<String> jlvMaisVendaAno;
    @FXML
    private ListView<String> jlvMnVendaMes;
    @FXML
    private ListView<String> jlvMnVendaAno;
    @FXML
    private HBox jhbMes;
    @FXML
    private HBox jhbAno;
    @FXML
    private Button jbtnHistoricoVenda;
    @FXML
    private Button jbtnFeedback;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void AcessarHistoricoVenda() throws IOException {
        TrocadorTelas.TrocarTela("/view/HistoricoVenda.fxml", (Stage) jbtnHistoricoVenda.getScene().getWindow());

    }

    public void AcessarFeedback() throws IOException {
        TrocadorTelas.TrocarTela("/view/Feedback.fxml", (Stage) jbtnFeedback.getScene().getWindow());

    }
}
