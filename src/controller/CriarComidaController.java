package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private boolean selecionado = false;

    private List<Ingrediente> ingredientes = new ArrayList<>();

    Comida comida = new Comida();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        scrollPane.setContent(verBox);

        Map<Integer, Ingrediente> listaIngredientes = new HashMap<Integer, Ingrediente>();
        try {
            listaIngredientes = ComidaBO.SelecionarIngredientes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaIngredientes.forEach((key, value) -> {
            try {
                ListarIngredientes(key, value);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Cardapio.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void Concluir(Integer ID_Ingrediente, Ingrediente ingrediente) throws IOException {

        jbtnConcluir.setOnAction(event -> {

            if (jtfValor.getText().isEmpty() || jtfNome.getText().isEmpty() || jtfDescricao.getText().isEmpty()) {
                AlertaErro();
                try {
                    throw new IOException("Erro ao cadastrar");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            if (selecionado) {

                ingredientes.add(ingrediente);
                comida.setNome(jtfNome.getText());
                comida.setDescricao(jtfDescricao.getText());
                comida.setPreco(Double.valueOf(jtfValor.getText()));
                comida.setIngredientes(ingredientes);

                try {
                    ComidaBO.CadastrarComida(comida, ID_Ingrediente);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    TrocadorTelas.TrocarTela("/view/Cardapio.fxml", (Stage) jbtnVoltar.getScene().getWindow());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
        horBox.getChildren().addAll(nome, adicionar, remover);

        adicionar.setOnAction(e -> {

            selecionado = true;

        });

        remover.setOnAction(e -> {

            selecionado = false;
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