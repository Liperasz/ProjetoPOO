package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Button jbtnConcluir;
    @FXML
    private Button jbtnVoltar;

    @Override
    public void initialize (URL url, ResourceBundle rb) {

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

        concluir.setOnAction(e -> {CriarIngrediente();});

    }

    public void ConcluirEstoque() {

    }

    public void CriarIngrediente() {


    }

}
