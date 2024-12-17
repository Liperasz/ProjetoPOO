package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CardapioClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Label jlData;
    @FXML
    private ListView<String> listaDia;
    @FXML
    private ListView<String> listaNoite;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data();
        cardapiodia();
        cardapionoite();
    }
    public void data(){
        jlData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM")));
    }
    public void cardapiodia(){
        listaDia.getItems().add("pinto");
        listaDia.getItems().add("pinto1");
        listaDia.getItems().add("pinto2");
        listaDia.getItems().add("pinto3");
        listaDia.getItems().add("pinto4");
        listaDia.getItems().add("pinto5");
        listaDia.getItems().add("pinto6");
        listaDia.getItems().add("pinto7");
        listaDia.getItems().add("pinto8");
        listaDia.getItems().add("pinto9");
        listaDia.getItems().add("pinto10");

    }
    public void cardapionoite(){
        /*dia.getItems().add("pinto");
        dia.getItems().add("pinto1");
        dia.getItems().add("pinto2");
        dia.getItems().add("pinto3");*/
    }
    public void voltar() throws IOException {
        Stage fecha = (Stage) jbtnVoltar.getScene().getWindow();
        fecha.close();
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/TelaInicialCliente.fxml"));
        Scene scene = new Scene(voltar);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
