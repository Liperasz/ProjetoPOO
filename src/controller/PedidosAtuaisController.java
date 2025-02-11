package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PedidosAtuaisController implements Initializable {


    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnNovoPedido;
    @FXML
    private Button jbtnEstoque;
    @FXML
    private ScrollPane scrollPane;

    private VBox verBox = new VBox();

    @Override
    public void initialize (URL url, ResourceBundle rb) {

        scrollPane.setContent(verBox);
        if (TrocadorTelas.getUsuario() != Usuario.ATENDENTE) {
            jbtnEstoque.setVisible(false);
            jbtnNovoPedido.setVisible(false);
        }

    }


    public void Voltar() throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/Login.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }


    }

    public void NovoPedido() throws IOException {
        TrocadorTelas.TrocarTela("/view/RegistrarPedido.fxml", (Stage) jbtnNovoPedido.getScene().getWindow());

    }

    public void AcessarEstoque() throws IOException {
        TrocadorTelas.TrocarTela("/view/Estoque.fxml", (Stage) jbtnEstoque.getScene().getWindow());

    }

}
