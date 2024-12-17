package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarPedidoController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private TextField jtfCliente;
    @FXML
    private TextField jtfFuncionario;
    @FXML
    private TextField jtfValor;
    @FXML
    private Button jbtnEscolherPedidos;
    @FXML
    private Button jbtnCadastrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cadastrarPedido() {
        jbtnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });
    }

    public void escolherPedidos() {
        jbtnEscolherPedidos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });
    }

    public void Voltar() {
        jbtnVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


            }
        });
    }
}
