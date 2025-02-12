package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.MascarasFX;
import model.bo.EstoqueBO;
import model.bo.IngredienteBO;
import model.dao.IngredienteDAO;
import model.vo.Estoque;
import model.vo.Ingrediente;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CriarEstoqueController implements Initializable {

    @FXML
    private ChoiceBox<String> jcbEscolherIngrediente;
    @FXML
    private TextField jtfQuantidade;
    @FXML
    private TextField jtfValidade;
    @FXML
    private TextField jtfLote;
    @FXML
    private Button jbtnAddIngrediente;
    @FXML
    private Button jbtnConcluirEstoque;
    @FXML
    private Button jbtnVoltar;

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        try {
            jcbEscolherIngrediente.getItems().addAll(IngredienteBO.ListarIngrediente());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        MascarasFX.mascaraData(jtfValidade);
    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Estoque.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void AdicionarIngrediente() {
        VBox layout = new VBox();
        Label Nome = new Label("Nome do Ingrediente: ");
        TextField NomeIngrediente = new TextField();
        Button concluir = new Button("Concluir");
        layout.getChildren().addAll(Nome, NomeIngrediente, concluir);

        Scene scene = new Scene(layout, 300, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        concluir.setOnAction(e -> {
            try {
                CriarIngrediente(NomeIngrediente);
                stage.close();
            } catch (IOException | SQLException | ClassNotFoundException ex) {
                System.out.println("Erro ao criar ingrediente: " + ex.getMessage());
            }
        });


    }

    public void ConcluirEstoque() throws IOException, SQLException, ClassNotFoundException {
        if (jcbEscolherIngrediente.getValue() == null|| jtfLote.getText().isEmpty() || jtfQuantidade.getText().isEmpty()|| jtfValidade.getText().isEmpty() ||
                !jtfValidade.getText().matches("\\d{2}/\\d{2}/\\d{4}")
        ) {
            AlertaErro();
            throw new IOException("Os campos não foram devidamente preenchidos");
        }


        Ingrediente i = new Ingrediente(jcbEscolherIngrediente.getValue());
        Estoque estoque = new Estoque(i, LocalDate.of(Integer.parseInt(jtfValidade.getText(6, 10)), Integer.parseInt(jtfValidade.getText(3, 5)),
                Integer.parseInt(jtfValidade.getText(0, 2))), Integer.parseInt(jtfQuantidade.getText()), Integer.parseInt(jtfLote.getText()));


        EstoqueBO.CriarEstoque(estoque);

    }
    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }
    public void CriarIngrediente(TextField NomeIngrediente) throws IOException, SQLException, ClassNotFoundException {
        if (NomeIngrediente.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Os campos não foram devidamente preenchidos");
        }

        Ingrediente ingrediente = new Ingrediente(NomeIngrediente.getText());
        IngredienteBO.CriarIngrediente(ingrediente);

    }

}