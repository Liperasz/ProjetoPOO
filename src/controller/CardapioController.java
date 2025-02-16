package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bo.PedidoBO;
import model.vo.Comida;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CardapioController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnCriarComida;
    @FXML
    private ScrollPane scrollPane;

    private VBox verBox = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        scrollPane.setContent(verBox);

        Map<Integer, Comida> listaComidas = new HashMap<Integer, Comida>();
        try {

            System.out.println("Criando lista de comidas");
            listaComidas = PedidoBO.ListarComidas();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaComidas.forEach((key, value) -> {

            try {
                ListarComidas(key, value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }


    public void CriarComida() throws IOException {
        TrocadorTelas.TrocarTela("/view/CriarComida.fxml", (Stage) jbtnCriarComida.getScene().getWindow());

    }

    public void ListarComidas(Integer ID_Comida, Comida comida) throws IOException {

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));


        Label nome = new Label(comida.getNome());

        verBox.getChildren().add(horBox);

        Label desc = new Label("Descricao: " + comida.getDescricao());
        Label preco = new Label("Preco: " + comida.getPreco());


        horBox.getChildren().addAll(nome, desc, preco);
    }
}
