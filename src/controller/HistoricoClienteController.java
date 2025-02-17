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
    private Button jbtnVoltar; // Botão para retornar à tela anterior
    @FXML
    private ScrollPane scrollPane; // ScrollPane para exibição de conteúdo

    private VBox verBox = new VBox(); // Caixa vertical para organizar os pedidos

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Define a VBox como conteúdo do ScrollPane
        scrollPane.setContent(verBox);

        // Mapa para armazenar os pedidos
        Map<Integer, Pedido> listaPedido = new HashMap<>();
        try {
            // Obtém a lista de pedidos do cliente
            listaPedido = PedidoBO.ListarPedidosCliente();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e); // Trata exceções de banco de dados ou classe não encontrada
        }

        // Para cada pedido na lista, adiciona-o à interface
        listaPedido.forEach((key, value) -> {
            try {
                ListarPedidos(value); // Processa e exibe cada pedido
            } catch (IOException e) {
                throw new RuntimeException(e); // Trata exceções de I/O
            }
        });
    }

    // Retorna à tela de ClienteLogado
    public void Voltar(ActionEvent actionEvent) throws IOException {
        TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    // Lista e exibe os detalhes de um pedido
    public void ListarPedidos(Pedido pedido) throws IOException {
        HBox horBox = new HBox(); // Caixa horizontal para os detalhes do pedido
        horBox.setSpacing(15); // Espaçamento horizontal entre os itens
        horBox.setPadding(new Insets(10, 10, 10, 10)); // Margem interna

        // Labels para cliente e atendente
        Label Cliente = new Label();
        Label Atendente = new Label();

        // Define o nome do cliente ou mensagem de "indefinido"
        if (pedido.getCliente() != null) {
            Cliente.setText(pedido.getCliente().getNome());
        } else {
            Cliente.setText("Cliente indefinido");
        }

        // Define o nome do funcionário (atendente) ou mensagem de "indefinido"
        if (pedido.getFuncionario() != null) {
            Atendente.setText(pedido.getFuncionario().getNome());
        } else {
            Atendente.setText("Funcionario indefinido");
        }

        // Mostra o valor do pedido
        Label Preco = new Label(String.valueOf(pedido.getValor()));

        // Lista os itens do pedido (comidas e suas quantidades)
        VBox vBox = new VBox(); // Caixa vertical para itens do pedido
        pedido.getPedidos().forEach(Comida -> {
            Label quantidade = new Label();
            Label comida = new Label();
            comida.setText(Comida.getNome()); // Nome do item
            quantidade.setText("Quantidade: " + Comida.getQuantidade()); // Quantidade do item

            // Caixa horizontal para exibir comida e quantidade
            HBox hbox2 = new HBox();
            hbox2.setSpacing(15);
            hbox2.setPadding(new Insets(0, 5, 0, 5));
            hbox2.getChildren().addAll(comida, quantidade);

            // Adiciona cada item ao VBox
            vBox.getChildren().add(hbox2);
        });

        // Mostra o horário do pedido
        Label Horario = new Label(Timestamp.valueOf(pedido.getMomentopedido()).toString());

        // Mostra o status de pagamento do pedido
        Label pago = new Label();
        if (pedido.isPago()) {
            pago.setText("Pago");
        } else {
            pago.setText("Não foi pago!");
        }

        // Mostra o status de entrega do pedido
        Label entregue = new Label();
        if (pedido.isEntregue()) {
            entregue.setText("Entregue");
        } else {
            entregue.setText("Não foi entregue!");
        }

        // Adiciona todos os detalhes do pedido à HBox principal
        horBox.getChildren().addAll(Cliente, Atendente, vBox, Preco, Horario, pago, entregue);

        // Adiciona a HBox principal ao VBox global
        verBox.getChildren().add(horBox);
    }
}