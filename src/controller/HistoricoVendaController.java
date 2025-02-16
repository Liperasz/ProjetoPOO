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
    private Button jbtnVoltar;
    @FXML
    private ChoiceBox<String> jcbFiltro;
    @FXML
    private TextField jtfPesquisa;
    @FXML
    private Button jbtnPesquisar;
    @FXML
    private ScrollPane scrollpane;

    private VBox verBox = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        scrollpane.setContent(verBox);

        jcbFiltro.getItems().add("Cliente");
        jcbFiltro.getItems().add("Atendente");
        jcbFiltro.getItems().add("Comida");
        jtfPesquisa.setDisable(true);

        jcbFiltro.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                jtfPesquisa.setDisable(true);
            } else {
                jtfPesquisa.setDisable(false);
            }
        });

        Map<Integer, Pedido> listaPedido = new HashMap<>();
        try {
            listaPedido = PedidoBO.BuscarHistoricoPedido();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaPedido.forEach((key, value) -> {

            try {
                ListarVendas(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Map<Integer, Pedido> finalListaPedido = listaPedido;
        jbtnPesquisar.setOnAction(e -> {

           verBox.getChildren().clear();

            if (jcbFiltro.getValue() == null || jcbFiltro.getValue().isEmpty()) {

               finalListaPedido.forEach((key, value) -> {

                   try {
                       ListarVendas(value);
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
               });

           } else {

               switch (jcbFiltro.getValue()) {
                   case "Cliente":

                       Map<Integer, Pedido> listaPedidosCliente = new HashMap<>();
                       try {
                           listaPedidosCliente = PedidoBO.BuscarHistoricoPedidoCliente(jtfPesquisa.getText());
                       } catch (SQLException ex) {
                           throw new RuntimeException(ex);
                       } catch (ClassNotFoundException ex) {
                           throw new RuntimeException(ex);
                       }
                       listaPedidosCliente.forEach((key, value) -> {

                           try {
                               ListarVendas(value);
                           } catch (IOException ex) {
                               throw new RuntimeException(ex);
                           }
                       });

                       break;

                   case "Atendente":

                       Map<Integer, Pedido> listaPedidosAtendente = new HashMap<>();

                       try {
                           listaPedidosAtendente = PedidoBO.BuscarHistoricoPedidoAtendente(jtfPesquisa.getText());
                       } catch (SQLException ex) {
                           throw new RuntimeException(ex);
                       } catch (ClassNotFoundException ex) {
                           throw new RuntimeException(ex);
                       }

                       listaPedidosAtendente.forEach((key, value) -> {

                           try {
                               ListarVendas(value);
                           } catch (IOException ex) {
                               throw new RuntimeException(ex);
                           }
                       });

                       break;

                   case "Comida":

                       Map<Integer, Pedido> listaPedidosComida = new HashMap<>();

                       try {
                           listaPedidosComida = PedidoBO.BuscarHistoricoPedidoComidas(jtfPesquisa.getText());
                       } catch (SQLException ex) {
                           throw new RuntimeException(ex);
                       } catch (ClassNotFoundException ex) {
                           throw new RuntimeException(ex);
                       }

                       listaPedidosComida.forEach((key, value) -> {
                           try {
                               ListarVendas(value);
                           } catch (IOException ex) {
                               throw new RuntimeException(ex);
                           }

                       });

                       break;

                   default:

                       System.out.println("Erro ao selecionar filtro");
               }

           }






        });
    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Relatorio.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void ListarVendas(Pedido pedido) throws IOException {

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
