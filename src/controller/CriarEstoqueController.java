package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        jcbEscolherIngrediente.getItems().addAll("eita", "eita 2", "eita 3");
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
            } catch (IOException ex) {
                System.out.println("Erro ao criar ingrediente: " + ex.getMessage());
            }
        });


    }

    public void ConcluirEstoque() throws IOException {
        if (jcbEscolherIngrediente.getValue() == null|| jtfLote.getText().isEmpty() || jtfQuantidade.getText().isEmpty()|| jtfValidade.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Os campos não foram devidamente preenchidos");
        }
    }
    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }
    public void CriarIngrediente(TextField NomeIngrediente) throws IOException{
        if (NomeIngrediente.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Os campos não foram devidamente preenchidos");
        }

    }

}