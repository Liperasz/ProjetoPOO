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
import model.bo.PedidoBO;
import model.vo.Comida;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CardapioController implements Initializable { // Classe que implementa a interface Initializable para controlar a inicialização do JavaFX

    @FXML
    private Button jbtnVoltar; // Botão na interface para voltar à tela anterior

    @FXML
    private Button jbtnCriarComida; // Botão na interface para ir à tela de criação de uma nova comida

    @FXML
    private ScrollPane scrollPane; // Scroll pane para permitir a rolagem do conteúdo caso haja itens de cardápio excedendo o tamanho da tela

    private VBox verBox = new VBox(); // VBox (contêiner vertical) onde os itens do cardápio serão exibidos

    @Override
    public void initialize(URL url, ResourceBundle rb) { // Método chamado automaticamente quando a interface gráfica é inicializada
        // Define a VBox (verBox) como o conteúdo do ScrollPane
        scrollPane.setContent(verBox);

        // Mapa para armazenar as comidas listadas (chave: ID da comida, valor: objeto Comida)
        Map<Integer, Comida> listaComidas = new HashMap<>();

        try {
            System.out.println("Criando lista de comidas"); // Mensagem no console para indicar que a lista está sendo criada
            listaComidas = PedidoBO.ListarComidas(); // Obtém a lista de comidas da classe PedidoBO (provavelmente conectada ao banco de dados)
        } catch (SQLException e) { // Trata possíveis erros de SQL
            throw new RuntimeException(e); // Encapsula e re-lança a exceção como uma RuntimeException
        } catch (ClassNotFoundException e) { // Trata possíveis erros relacionados à ausência de classes
            throw new RuntimeException(e); // Encapsula e re-lança a exceção como uma RuntimeException
        }

        // Para cada item na lista de comidas (chave: ID, valor: objeto Comida), executa o código abaixo
        listaComidas.forEach((key, value) -> {
            try {
                // Chama o método ListarComidas para criar a UI dessa comida
                ListarComidas(key, value);
            } catch (IOException e) { // Trata possíveis erros de entrada/saída
                throw new RuntimeException(e); // Encapsula e re-lança a exceção
            }
        });
    }

    public void Voltar() throws IOException { // Método chamado ao clicar no botão Voltar
        // Utiliza a classe TrocadorTelas para trocar a tela para "AdmLogado.fxml"
        // O estágio é identificado a partir do botão clicado (jbtnVoltar)
        TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    public void CriarComida() throws IOException { // Método chamado ao clicar no botão CriarComida
        // Utiliza a classe TrocadorTelas para trocar para "CriarComida.fxml"
        // O estágio baseia-se no botão (jbtnCriarComida)
        TrocadorTelas.TrocarTela("/view/CriarComida.fxml", (Stage) jbtnCriarComida.getScene().getWindow());
    }

    public void ListarComidas(Integer ID_Comida, Comida comida) throws IOException { // Método responsável por criar os elementos visuais para exibir um item do cardápio
        // HBox (contêiner horizontal) para organizar as informações de maneira visual dentro da interface
        HBox horBox = new HBox();
        horBox.setSpacing(15); // Define o espaçamento horizontal entre os elementos
        horBox.setPadding(new Insets(10, 10, 10, 10)); // Define o espaçamento interno entre o conteúdo e as bordas

        // Cria um rótulo (Label) com o nome da comida
        Label nome = new Label(comida.getNome());

        // Adiciona o contêiner horizontal (HBox) ao VBox (verBox), que organiza os elementos verticalmente
        verBox.getChildren().add(horBox);

        // Cria e configura rótulos para exibir a descrição e o preço da comida
        Label desc = new Label("Descricao: " + comida.getDescricao());
        Label preco = new Label("Preco: " + comida.getPreco());

        // Adiciona os rótulos ao contêiner horizontal (horBox)
        horBox.getChildren().addAll(nome, desc, preco);
    }
}