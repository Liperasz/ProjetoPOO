package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bo.EstoqueBO;
import model.vo.Estoque;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EstoqueController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnConcluir;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button jbtnCriarEstoque;

    private VBox verBox = new VBox();

    private Map<Integer, Estoque> estoquesModificados = new HashMap<>();

    @Override
    public void initialize (URL url, ResourceBundle rb) {

        scrollPane.setContent(verBox);

        Map<Integer, Estoque> listaEstoque = new HashMap<Integer, Estoque>();
        try {
            listaEstoque = EstoqueBO.ListarEstoque();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaEstoque.forEach((key, value) -> {

            try {
                ManipularEstoque(key, value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

        jbtnConcluir.setOnAction(e -> {
            estoquesModificados.forEach((key, value) -> {

                try {
                    EstoqueBO.ManipularEstoque(value, key);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
        });

    }

    public void Voltar() throws IOException {


        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }


    public void CriarEstoque() throws IOException {
        TrocadorTelas.TrocarTela("/view/CriarEstoque.fxml", (Stage) jbtnCriarEstoque.getScene().getWindow());

    }

    public void ManipularEstoque(Integer ID_estoque, Estoque estoque) throws IOException, SQLException, ClassNotFoundException {

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));




        Label ingrediente = new Label(estoque.getEstoqueingrediente().getNome());
        Label validade = new Label(String.valueOf("Validade: " + estoque.getValidade()));
        Label lote = new Label("Lote: " + String.valueOf(estoque.getLote()));
        Button adicionar = new Button("Adicionar");
        Button remover = new Button("Remover");
        Button excluir = new Button("Excluir");

        final int[] contador = {estoque.getQuantidade()};
        Label quant = new Label("Quantidade: " + contador[0]);

        verBox.getChildren().add(horBox);
        horBox.getChildren().addAll(ingrediente, validade, lote, adicionar, remover, quant, excluir);

        adicionar.setOnAction(e -> {

            contador[0]++;
            quant.setText("Quantidade: " + contador[0]);
            estoque.setQuantidade(contador[0]);
            estoquesModificados.put(ID_estoque, estoque);

        });

        remover.setOnAction(e -> {
            if (contador[0] > 0) {

                contador[0]--;
                quant.setText("Quantidade: " + contador[0]);
                estoque.setQuantidade(contador[0]);
                estoquesModificados.put(ID_estoque, estoque);
            }

        });

        excluir.setOnAction(e -> {

            try {
                EstoqueBO.deletarEstoque(ID_estoque);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

}
