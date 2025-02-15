package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.MascarasFX;
import model.bo.ComidaBO;
import model.bo.IngredienteBO;
import model.dao.ComidaDAO;
import model.vo.Comida;
import model.vo.Ingrediente;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CriarComidaController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnConcluir;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField jtfNome;
    @FXML
    private TextField jtfDescricao;
    @FXML
    private TextField jtfValor;

    private VBox verBox = new VBox();

    private Map<Integer, Integer> ingredientes = new HashMap<>();

    Comida comida = new Comida();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MascarasFX.mascaraNumero(jtfNome);

        scrollPane.setContent(verBox);

        Map<Integer, Ingrediente> listaIngredientes = new HashMap<Integer, Ingrediente>();
        try {

            System.out.println("Criando lista de ingredientes");
            listaIngredientes = ComidaBO.SelecionarIngredientes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaIngredientes.forEach((key, value) -> {
            try {

                System.out.println("Criando ingrediente " + key);
                ListarIngredientes(key, value);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {

            System.out.println("Tentando Criar comida");
            Concluir(ingredientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Cardapio.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void Concluir(Map<Integer, Integer> ingredientes) throws IOException {

        jbtnConcluir.setOnAction(event -> {

            if (jtfValor.getText().isEmpty() || jtfNome.getText().isEmpty() || jtfDescricao.getText().isEmpty()) {
                AlertaErro();
                try {
                    throw new IOException("Erro ao cadastrar");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            ingredientes.entrySet().removeIf(entry -> entry.getValue() == 0);

            System.out.println("Criando comida dentro da função concluir()");

            comida.setNome(jtfNome.getText());
            comida.setDescricao(jtfDescricao.getText());
            comida.setPreco(Double.valueOf(jtfValor.getText()));

            try {

                System.out.println("Chamando a função ComidaBO.CadastrarComida()");
                ComidaBO.CadastrarComida(comida, ingredientes);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            try {
                TrocadorTelas.TrocarTela("/view/CriarComida.fxml", (Stage) jbtnConcluir.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        });

    }

    public void ListarIngredientes(Integer ID_Ingrediente, Ingrediente ingrediente) throws IOException {

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));


        Label nome = new Label(ingrediente.getNome());
        Button adicionar = new Button("Adicionar");
        Button remover = new Button("Remover");

        verBox.getChildren().add(horBox);

        final int[] contador = {0};
        Label quant = new Label("Quantidade: " + contador[0]);
        horBox.getChildren().addAll(nome, adicionar, remover, quant);


        adicionar.setOnAction(e -> {

            contador[0]++;
            quant.setText("Quantidade: " + contador[0]);
            ingredientes.put(ID_Ingrediente, (contador[0]));
            System.out.println("ID = " + ID_Ingrediente + " Quantidade na lista " + ingredientes.get(ID_Ingrediente) + " Quantidade = " + contador[0]);

        });

        remover.setOnAction(e -> {
            if (contador[0] > 0) {

                contador[0]--;
                quant.setText("Quantidade: " + contador[0]);
                ingredientes.put(ID_Ingrediente, (contador[0]));
            }
            System.out.println("ID = " + ID_Ingrediente + " Quantidade na lista " + ingredientes.get(ID_Ingrediente) + " Quantidade = " + contador[0]);
        });
    }

    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }


}