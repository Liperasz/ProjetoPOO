package controller;

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
import java.util.Map;
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

        Map<Integer, Pedido> listaPedidos= new HashMap<>();
        try {
            listaPedidos = PedidoBO.BuscarPedidos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaPedidos.forEach((key, value) -> {

            try {
                ListarPedidos(key, value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


    public void Voltar() throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/Login.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }


    }

    public void NovoPedido() throws IOException {
        TrocadorTelas.TrocarTela("/view/PedidoCliente.fxml", (Stage) jbtnNovoPedido.getScene().getWindow());

    }

    public void AcessarEstoque() throws IOException {
        TrocadorTelas.TrocarTela("/view/Estoque.fxml", (Stage) jbtnEstoque.getScene().getWindow());

    }

    public void ListarPedidos(Integer id_pedido, Pedido pedido) throws IOException {

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
            Label comida = new Label();
            comida.setText(Comida.getNome());
            vBox.getChildren().add(comida);
        });

        Label Horario = new Label(Timestamp.valueOf(pedido.getMomentopedido()).toString());

        ChoiceBox<Boolean> pago = new ChoiceBox<>();
        ChoiceBox<Boolean> entregue = new ChoiceBox<>();
        pago.getItems().addAll(Boolean.TRUE, Boolean.FALSE);
        entregue.getItems().addAll(Boolean.TRUE, Boolean.FALSE);
        pago.setValue(pedido.isPago());
        entregue.setValue(pedido.isEntregue());
        Button salvar = new Button("Salvar");

        Label lentregue = new Label("Entregue: ");
        Label lpago = new Label("Pago: ");

        horBox.getChildren().addAll(Cliente, Atendente, vBox, Preco, Horario, lpago, pago, lentregue, entregue, salvar);
        verBox.getChildren().add(horBox);

        salvar.setOnAction(event -> {

            pedido.setPago(pago.getValue());
            pedido.setEntregue(entregue.getValue());

            try {
                PedidoBO.AlterarStatusPedido(id_pedido, pedido);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    

}
