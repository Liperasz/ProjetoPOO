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
    private Button jbtnVoltar;
    @FXML
    private Button jbtnEnviar;
    @FXML
    private ScrollPane scrollPane;

    private VBox verBox = new VBox();

    private Map<Integer, Integer> comidas = new HashMap<>();

    private Pedido pedido = new Pedido();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

                System.out.println("Criando comida " + key);
                ListarComidas(key, value);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {

            System.out.println("Tentando Criar comida");
            Enviar(comidas, listaComidas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void Voltar() throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/RegistrarPedido.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    public void Enviar(Map<Integer, Integer> comidas, Map<Integer, Comida> listaComidas) throws IOException {


        jbtnEnviar.setOnAction(event -> {


            if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {


                try {
                    Atendente atendente = PedidoBO.BuscarAtendentePedido(AtendenteBO.getEmailLogado());
                    pedido.setFuncionario(atendente);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }



                try {
                    TrocadorTelas.TrocarTela("/view/RegistrarPedido.fxml", (Stage) jbtnEnviar.getScene().getWindow());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            } else {

                try {
                    Cliente cliente = PedidoBO.BuscarClientePedido(ClienteBO.getEmailLogado());
                    pedido.setCliente(cliente);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


                try {
                    TrocadorTelas.TrocarTela("/view/PagamentoCliente.fxml", (Stage) jbtnEnviar.getScene().getWindow());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

            System.out.println("Criando pedido dentro da função Enviar()");

            comidas.entrySet().removeIf(entry -> entry.getValue() == 0);
            pedido.setValor(0);

            comidas.forEach((key, value) -> {

                Comida comida = listaComidas.get(key);

                pedido.setValor(pedido.getValor() + (comida.getPreco() * value));

            });

            pedido.setPago(false);
            pedido.setEntregue(false);
            pedido.setMomentopedido(LocalDateTime.now());



            try {

                System.out.println("Chamando a função cadastrarpedidobo()");
                PedidoBO.CadastrarPedido(pedido, comidas);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }



        });
    }

    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }

    public void ListarComidas(Integer ID_Comida, Comida comida) throws IOException {

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));


        Label nome = new Label(comida.getNome());
        Button adicionar = new Button("Adicionar");
        Button remover = new Button("Remover");

        verBox.getChildren().add(horBox);

        final int[] contador = {0};
        Label quant = new Label("Quantidade: " + contador[0]);
        Label desc = new Label("Descricao: " + comida.getDescricao());
        Label preco = new Label("Preco: " + comida.getPreco());


        horBox.getChildren().addAll(nome, desc, preco, adicionar, remover, quant);


        adicionar.setOnAction(e -> {

            contador[0]++;
            quant.setText("Quantidade: " + contador[0]);
            comidas.put(ID_Comida, (contador[0]));
            System.out.println("ID = " + ID_Comida + " Quantidade na lista " + comidas.get(ID_Comida) + " Quantidade = " + contador[0]);

        });

        remover.setOnAction(e -> {
            if (contador[0] > 0) {

                contador[0]--;
                quant.setText("Quantidade: " + contador[0]);
                comidas.put(ID_Comida, (contador[0]));
            }
            System.out.println("ID = " + ID_Comida + " Quantidade na lista " + comidas.get(ID_Comida) + " Quantidade = " + contador[0]);
        });
    }

}
