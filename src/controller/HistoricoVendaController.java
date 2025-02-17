package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bo.PedidoBO;
import model.vo.Pedido;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HistoricoVendaController implements Initializable {
    @FXML
    private Button jbtnVoltar; // Botão para voltar à tela anterior
    @FXML
    private ChoiceBox<String> jcbFiltro; // Caixa de seleção para escolha do filtro
    @FXML
    private TextField jtfPesquisa; // Campo de texto para pesquisa
    @FXML
    private Button jbtnPesquisar; // Botão de ação para iniciar a pesquisa
    @FXML
    private ScrollPane scrollpane; // Área de rolagem para exibir a lista
    private VBox verBox = new VBox(); // Contêiner para exibir os pedidos no layout

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollpane.setContent(verBox); // Define o VBox como conteúdo do scrollpane

        // Adiciona opções ao ChoiceBox de filtros
        jcbFiltro.getItems().add("Cliente");
        jcbFiltro.getItems().add("Atendente");
        jcbFiltro.getItems().add("Comida");

        jtfPesquisa.setDisable(true); // Desativa o campo de pesquisa inicialmente

        // Define comportamento ao alterar valor do filtro selecionado
        jcbFiltro.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                jtfPesquisa.setDisable(true); // Desativa o campo de pesquisa se nenhum filtro estiver selecionado
            } else {
                jtfPesquisa.setDisable(false); // Ativa o campo de pesquisa
            }
        });

        // Busca histórico de pedidos e armazena no mapa
        Map<Integer, Pedido> listaPedido = new HashMap<>();
        try {
            listaPedido = PedidoBO.BuscarHistoricoPedido(); // Consulta os pedidos no banco
        } catch (SQLException e) {
            throw new RuntimeException(e); // Lança exceção de consulta
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // Lança exceção de classe não encontrada
        }

        // Exibe todos os pedidos no VBox
        listaPedido.forEach((key, value) -> {
            try {
                ListarVendas(value); // Adiciona o pedido à visualização
            } catch (IOException e) {
                throw new RuntimeException(e); // Lida com exceção
            }
        });

        Map<Integer, Pedido> finalListaPedido = listaPedido; // Cria referência final ao mapa de pedidos

        // Define ação do botão de pesquisa
        jbtnPesquisar.setOnAction(e -> {
            verBox.getChildren().clear(); // Limpa os pedidos exibidos anteriormente

            if (jcbFiltro.getValue() == null || jcbFiltro.getValue().isEmpty()) {
                // Reexibe todos os pedidos se nenhum filtro for selecionado
                finalListaPedido.forEach((key, value) -> {
                    try {
                        ListarVendas(value); // Lista todos os pedidos
                    } catch (IOException ex) {
                        throw new RuntimeException(ex); // Lida com exceção
                    }
                });
            } else {
                // Verifica o filtro selecionado e executa a busca correspondente
                switch (jcbFiltro.getValue()) {
                    case "Cliente": // Busca por cliente
                        Map<Integer, Pedido> listaPedidosCliente = new HashMap<>();
                        try {
                            listaPedidosCliente = PedidoBO.BuscarHistoricoPedidoCliente(jtfPesquisa.getText()); // Busca pedidos pelo nome do cliente
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex); // Lida com exceção de consulta
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex); // Lida com exceção de classe
                        }
                        listaPedidosCliente.forEach((key, value) -> {
                            try {
                                ListarVendas(value); // Exibe os resultados
                            } catch (IOException ex) {
                                throw new RuntimeException(ex); // Lida com exceções
                            }
                        });
                        break;
                    case "Atendente": // Busca por atendente
                        Map<Integer, Pedido> listaPedidosAtendente = new HashMap<>();
                        try {
                            listaPedidosAtendente = PedidoBO.BuscarHistoricoPedidoAtendente(jtfPesquisa.getText()); // Busca pedidos pelo nome do atendente
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex); // Lida com exceção de consulta
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex); // Lida com exceção de classe
                        }
                        listaPedidosAtendente.forEach((key, value) -> {
                            try {
                                ListarVendas(value); // Exibe os resultados
                            } catch (IOException ex) {
                                throw new RuntimeException(ex); // Lida com exceção
                            }
                        });
                        break;
                    case "Comida": // Busca por comida
                        Map<Integer, Pedido> listaPedidosComida = new HashMap<>();
                        try {
                            listaPedidosComida = PedidoBO.BuscarHistoricoPedidoComidas(jtfPesquisa.getText()); // Busca pedidos pelo nome da comida
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex); // Lida com exceção
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex); // Lida com exceção de classe
                        }
                        listaPedidosComida.forEach((key, value) -> {
                            try {
                                ListarVendas(value); // Adiciona resultados à tela
                            } catch (IOException ex) {
                                throw new RuntimeException(ex); // Lida com exceção
                            }
                        });
                        break;
                    default: // Trata erro no filtro
                        System.out.println("Erro ao selecionar filtro");
                }
            }
        });
    }

    // Função para trocar a tela ao clicar no botão Voltar
    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Relatorio.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    // Exibe os detalhes de um pedido
    public void ListarVendas(Pedido pedido) throws IOException {
        HBox horBox = new HBox(); // Cria contêiner horizontal para exibir os elementos
        horBox.setSpacing(15); // Define o espaçamento entre os elementos
        horBox.setPadding(new Insets(10, 10, 10, 10)); // Define os preenchimentos do contêiner

        // Exibe o cliente ou mensagem padrão
        Label Cliente = new Label();
        if (pedido.getCliente() != null) {
            Cliente.setText(pedido.getCliente().getNome());
        } else {
            Cliente.setText("Cliente indefinido");
        }

        // Exibe o atendente ou mensagem padrão
        Label Atendente = new Label();
        if (pedido.getFuncionario() != null) {
            Atendente.setText(pedido.getFuncionario().getNome());
        } else {
            Atendente.setText("Funcionario indefinido");
        }

        // Exibe o preço do pedido
        Label Preco = new Label(String.valueOf(pedido.getValor()));

        VBox vBox = new VBox(); // Contêiner vertical para itens do pedido
        pedido.getPedidos().forEach(Comida -> {
            Label quantidade = new Label(); // Exibe a quantidade da comida
            Label comida = new Label(); // Exibe o nome da comida
            comida.setText(Comida.getNome());
            quantidade.setText("Quantidade: " + String.valueOf(Comida.getQuantidade()));
            HBox hbox2 = new HBox(); // Contêiner horizontal para exibir os dados de cada comida
            hbox2.setSpacing(15);
            hbox2.setPadding(new Insets(0, 5, 0, 5));
            hbox2.getChildren().addAll(comida, quantidade); // Adiciona os elementos no contêiner
            vBox.getChildren().add(hbox2); // Adiciona ao layout vertical
        });

        // Exibe o horário do pedido
        Label Horario = new Label(Timestamp.valueOf(pedido.getMomentopedido()).toString());

        // Exibe o status de pagamento
        Label pago = new Label();
        if (pedido.isPago()) {
            pago.setText("Pago");
        } else {
            pago.setText("Não foi pago!");
        }

        // Exibe o status de entrega
        Label entregue = new Label();
        if (pedido.isEntregue()) {
            entregue.setText("Entregue");
        } else {
            entregue.setText("Não foi entregue!");
        }

        // Adiciona os elementos ao contêiner principal
        horBox.getChildren().addAll(Cliente, Atendente, vBox, Preco, Horario, pago, entregue);
        verBox.getChildren().add(horBox); // Adiciona o pedido ao VBox na tela
    }
}