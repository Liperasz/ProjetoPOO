package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.vo.Comida;
import model.vo.Pedido;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoricoClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private ScrollPane scrollPane;

    private VBox verBox = new VBox();

    @Override
    public void initialize (URL url, ResourceBundle rb) {

        scrollPane.setContent(verBox);

    }

    public void Voltar(ActionEvent actionEvent) throws IOException {

        TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());


    }


}
