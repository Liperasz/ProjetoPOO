package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.vo.Comida;
import model.vo.Ingrediente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PedidoClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnEnviar;
    @FXML
    private ScrollPane scrollPane;

    private VBox verBox = new VBox();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Ingrediente ingredientefoda = new Ingrediente("Carne");
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(ingredientefoda);
        Comida comida = new Comida("Carne Assada", ingredientes, 30, "Uma carne de boi assada no ponto pra mal passada!");

        for (int i = 0; i < 20; i++) {
            AdicionarComidas(comida);
        }

        scrollPane.setContent(verBox);

    }

    public void Voltar() throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/RegistrarPedido.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    public void Enviar(int quant) throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/RegistrarPedido.fxml", (Stage) jbtnEnviar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/PagamentoCliente.fxml", (Stage) jbtnEnviar.getScene().getWindow());
        }
    }

    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }

    public void AdicionarComidas(Comida comida) {

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));

        Label nome = new Label(comida.getNome() + " : ");
        Label desc = new Label(comida.getDescricao());
        Label preco = new Label(String.valueOf(comida.getPreco()));
        Button adicionar = new Button("Adicionar");
        Button remover = new Button("Remover");

        final int[] contador = {0};
        Label quant = new Label("Quantidade: 0");

        verBox.getChildren().add(horBox);
        horBox.getChildren().addAll(nome, desc, preco, adicionar, remover, quant);

        adicionar.setOnAction(e -> {

            contador[0]++;
            quant.setText("Quantidade: " + contador[0]);

        });

        remover.setOnAction(e -> {
            if (contador[0] > 0) {

                contador[0]--;
                quant.setText("Quantidade: " + contador[0]);
            }

        });

    }

}
