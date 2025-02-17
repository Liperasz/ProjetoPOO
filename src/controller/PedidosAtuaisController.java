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
    private Button jbtnVoltar; // Botão para voltar à tela anterior
    @FXML
    private Button jbtnNovoPedido; // Botão para criar um novo pedido
    @FXML
    private Button jbtnEstoque; // Botão para acessar a tela de estoque
    @FXML
    private ScrollPane scrollPane; // Elemento que contém a lista de pedidos em um scroll
    private VBox verBox = new VBox(); // Contêiner para exibir os elementos dos pedidos

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Define o VBox como conteúdo da scrollPane
        scrollPane.setContent(verBox);

        // Oculta botões "Novo Pedido" e "Estoque" caso o usuário não seja um atendente
        if (TrocadorTelas.getUsuario() != Usuario.ATENDENTE) {
            jbtnEstoque.setVisible(false);
            jbtnNovoPedido.setVisible(false);
        }

        // Inicializa o mapa para armazenar os pedidos
        Map<Integer, Pedido> listaPedidos = new HashMap<>();
        try {
            // Busca os pedidos do banco de dados
            listaPedidos = PedidoBO.BuscarPedidos();
        } catch (SQLException e) {
            throw new RuntimeException(e); // Trata exceção de SQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // Trata exceção de classe não encontrada
        }

        // Adiciona os pedidos ao VBox, processando cada um deles
        listaPedidos.forEach((key, value) -> {
            try {
                ListarPedidos(key, value); // Exibe o pedido na interface
            } catch (IOException e) {
                throw new RuntimeException(e); // Trata exceção de IO
            }
        });
    }

    public void Voltar() throws IOException {
        // Retorna para a tela correspondente ao tipo de usuário (atendente ou admin)
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/Login.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    public void NovoPedido() throws IOException {
        // Alterna para a tela de criação de novo pedido
        TrocadorTelas.TrocarTela("/view/PedidoCliente.fxml", (Stage) jbtnNovoPedido.getScene().getWindow());
    }

    public void AcessarEstoque() throws IOException {
        // Alterna para a tela de estoque
        TrocadorTelas.TrocarTela("/view/Estoque.fxml", (Stage) jbtnEstoque.getScene().getWindow());
    }

    public void ListarPedidos(Integer id_pedido, Pedido pedido) throws IOException {
        // Criação de um componente HBox para exibir informações do pedido
        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));

        Label Cliente = new Label(); // Nome do cliente
        Label Atendente = new Label(); // Nome do atendente

        // Verifica se o cliente existe, caso contrário exibe uma mensagem padrão
        if (pedido.getCliente() != null) {
            Cliente.setText(pedido.getCliente().getNome());
        } else {
            Cliente.setText("Cliente indefinido");
        }

        // Verifica se o atendente existe, caso contrário exibe uma mensagem padrão
        if (pedido.getFuncionario() != null) {
            Atendente.setText(pedido.getFuncionario().getNome());
        } else {
            Atendente.setText("Funcionario indefinido");
        }

        Label Preco = new Label(String.valueOf(pedido.getValor())); // Exibe o valor do pedido
        VBox vBox = new VBox(); // Contêiner para exibir a lista de itens do pedido

        // Itera pelos itens no pedido e os adiciona ao VBox
        pedido.getPedidos().forEach(Comida -> {
            Label quantidade = new Label(); // Quantidade do item
            Label comida = new Label(); // Nome do item
            comida.setText(Comida.getNome());
            quantidade.setText("Quantidade: " + String.valueOf(Comida.getQuantidade()));

            // Define um novo HBox para cada item do pedido
            HBox hbox2 = new HBox();
            hbox2.setSpacing(15);
            hbox2.setPadding(new Insets(0, 5, 0, 5));
            hbox2.getChildren().addAll(comida, quantidade);
            vBox.getChildren().add(hbox2); // Adiciona o item ao VBox
        });

        // Exibe o horário do pedido
        Label Horario = new Label(Timestamp.valueOf(pedido.getMomentopedido()).toString());

        // Criação de choice boxes para status de pagamento e entrega
        ChoiceBox<Boolean> pago = new ChoiceBox<>();
        ChoiceBox<Boolean> entregue = new ChoiceBox<>();
        pago.getItems().addAll(Boolean.TRUE, Boolean.FALSE); // Opções para o status "pago"
        entregue.getItems().addAll(Boolean.TRUE, Boolean.FALSE); // Opções para o status "entregue"

        // Define os valores padrão baseados no estado do pedido
        pago.setValue(pedido.isPago());
        entregue.setValue(pedido.isEntregue());

        Button salvar = new Button("Salvar"); // Botão para salvar alterações no pedido

        // Rótulos para os status do pedido
        Label lentregue = new Label("Entregue: ");
        Label lpago = new Label("Pago: ");

        // Adiciona os elementos ao HBox do pedido
        horBox.getChildren().addAll(Cliente, Atendente, vBox, Preco, Horario, lpago, pago, lentregue, entregue, salvar);
        verBox.getChildren().add(horBox); // Adiciona o HBox ao VBox principal

        // Define a ação do botão "Salvar"
        salvar.setOnAction(event -> {
            // Atualiza os estados de "pago" e "entregue" do pedido
            pedido.setPago(pago.getValue());
            pedido.setEntregue(entregue.getValue());

            try {
                // Chama o método para alterar os status do pedido no banco de dados
                PedidoBO.AlterarStatusPedido(id_pedido, pedido);
            } catch (SQLException e) {
                throw new RuntimeException(e); // Trata exceção de SQL
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e); // Trata exceção de classe não encontrada
            }
        });
    }
}