package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bo.PedidoBO;
import model.vo.Comida;
import model.vo.Pedido;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


        Map<Integer, Pedido> listaPedido = new HashMap<>();
        try {
            listaPedido = PedidoBO.ListarPedidosCliente();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaPedido.forEach((key, value) -> {

            try {
                ListarPedidos(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void Voltar(ActionEvent actionEvent) throws IOException {

        TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());


    }

    public void ListarPedidos(Pedido pedido) throws IOException {

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));

        Label Cliente = new Label();
        Label Atendente = new Label();

        if (pedido.getCliente() != null) {
            Cliente.setText(pedido.getCliente().getNome());
        } else {
            Cliente.setText("Cliente indefinido");
        }

        if (pedido.getFuncionario() != null) {
            Atendente.setText(pedido.getFuncionario().getNome());
        } else {
            Atendente.setText("Funcionario indefinido");
        }

        Label Preco = new Label(String.valueOf(pedido.getValor()));
        VBox vBox = new VBox();
        pedido.getPedidos().forEach(Comida -> {
            Label quantidade = new Label();
            Label comida = new Label();
            comida.setText(Comida.getNome());
            quantidade.setText("Quantidade: " + String.valueOf(Comida.getQuantidade()));
            HBox hbox2 = new HBox();
            hbox2.setSpacing(15);
            hbox2.setPadding(new Insets(0, 5, 0, 5));
            hbox2.getChildren().addAll(comida, quantidade);
            vBox.getChildren().add(hbox2);
        });

        Label Horario = new Label(Timestamp.valueOf(pedido.getMomentopedido()).toString());

        Label pago = new Label();
        if (pedido.isPago()) {
            pago.setText("Pago");
        } else {
            pago.setText("Não foi pago!");
        }

        Label entregue = new Label();
        if (pedido.isEntregue()) {
            entregue.setText("Entregue");
        } else {
            entregue.setText("Não foi entregue!");
        }

        horBox.getChildren().addAll(Cliente, Atendente, vBox, Preco, Horario, pago, entregue);
        verBox.getChildren().add(horBox);


    }

}
