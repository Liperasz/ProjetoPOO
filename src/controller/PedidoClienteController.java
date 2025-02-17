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
import model.bo.AtendenteBO;
import model.bo.ClienteBO;
import model.bo.ComidaBO;
import model.bo.PedidoBO;
import model.vo.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class PedidoClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar; // Botão para voltar à tela anterior
    @FXML
    private Button jbtnEnviar; // Botão para enviar o pedido
    @FXML
    private ScrollPane scrollPane; // Painel para exibir a lista de comidas

    private VBox verBox = new VBox(); // Contêiner vertical onde as comidas serão listadas
    private Map<Integer, Integer> comidas = new HashMap<>(); // Mapa para armazenar IDs das comidas e suas quantidades
    private Pedido pedido = new Pedido(); // Objeto que representa o pedido atual

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura o ScrollPane para usar o VBox como conteúdo
        scrollPane.setContent(verBox);

        // Mapa para armazenar a lista de comidas obtidas
        Map<Integer, Comida> listaComidas = new HashMap<>();

        try {
            System.out.println("Criando lista de comidas");
            // Obtém a lista de comidas do banco de dados
            listaComidas = PedidoBO.ListarComidas();
        } catch (SQLException | ClassNotFoundException e) {
            // Lança uma exceção caso ocorra um erro ao buscar as comidas
            throw new RuntimeException(e);
        }

        // Para cada comida na lista, cria os elementos visuais correspondente
        listaComidas.forEach((key, value) -> {
            try {
                System.out.println("Criando comida " + key);
                ListarComidas(key, value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            System.out.println("Tentando Criar comida");
            // Configura o botão para enviar o pedido
            Enviar(comidas, listaComidas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Voltar() throws IOException {
        // Verifica o tipo de usuário e redireciona para a tela apropriada
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    public void Enviar(Map<Integer, Integer> comidas, Map<Integer, Comida> listaComidas) throws IOException {
        // Configura a ação para o botão de envio
        jbtnEnviar.setOnAction(event -> {
            System.out.println("Criando pedido dentro da função Enviar()");

            // Remove do mapa os itens com quantidade 0
            comidas.entrySet().removeIf(entry -> entry.getValue() == 0);
            pedido.setValor(0); // Inicializa o valor do pedido em 0

            // Calcula o valor do pedido com base nas quantidades e preços
            comidas.forEach((key, value) -> {
                Comida comida = listaComidas.get(key);
                pedido.setValor(pedido.getValor() + (comida.getPreco() * value));
            });

            // Define informações do pedido
            pedido.setPago(false);
            pedido.setEntregue(false);
            pedido.setMomentopedido(LocalDateTime.now());
            PedidoBO.setValor(pedido.getValor());

            // Configura o pedido com base no tipo de usuário
            if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
                try {
                    Atendente atendente = PedidoBO.BuscarAtendentePedido(AtendenteBO.getEmailLogado());
                    pedido.setFuncionario(atendente);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnEnviar.getScene().getWindow());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Cliente cliente = PedidoBO.BuscarClientePedido(ClienteBO.getEmailLogado());
                    pedido.setCliente(cliente);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    TrocadorTelas.TrocarTela("/view/PagamentoCliente.fxml", (Stage) jbtnEnviar.getScene().getWindow());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                System.out.println("Chamando a função cadastrarpedidobo()");
                // Cadastra o pedido no banco de dados
                PedidoBO.CadastrarPedido(pedido, comidas);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void AlertaErro() {
        // Exibe um alerta para casos de erro
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }

    public void ListarComidas(Integer ID_Comida, Comida comida) throws IOException {
        // Cria um painel horizontal para cada comida
        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));

        // Cria elementos para exibir o nome, descrição, preço e controle de quantidade
        Label nome = new Label(comida.getNome());
        Button adicionar = new Button("Adicionar");
        Button remover = new Button("Remover");
        verBox.getChildren().add(horBox);

        final int[] contador = {0};
        Label quant = new Label("Quantidade: " + contador[0]);
        Label desc = new Label("Descricao: " + comida.getDescricao());
        Label preco = new Label("Preco: " + comida.getPreco());

        horBox.getChildren().addAll(nome, desc, preco, adicionar, remover, quant);

        // Define a ação para adicionar uma quantidade
        adicionar.setOnAction(e -> {
            contador[0]++;
            quant.setText("Quantidade: " + contador[0]);
            comidas.put(ID_Comida, contador[0]);
            System.out.println("ID = " + ID_Comida + " Quantidade na lista " + comidas.get(ID_Comida) + " Quantidade = " + contador[0]);
        });

        // Define a ação para remover uma quantidade
        remover.setOnAction(e -> {
            if (contador[0] > 0) {
                contador[0]--;
                quant.setText("Quantidade: " + contador[0]);
                comidas.put(ID_Comida, contador[0]);
            }
            System.out.println("ID = " + ID_Comida + " Quantidade na lista " + comidas.get(ID_Comida) + " Quantidade = " + contador[0]);
        });
    }
}