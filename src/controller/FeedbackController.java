package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FeedbackController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private ScrollPane scrollpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Relatorio.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }
}
