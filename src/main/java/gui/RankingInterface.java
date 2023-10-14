package gui;

import com.sun.javafx.css.StyleManager;
import gui.model.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingInterface extends Application {
   /* @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/test.fxml")));

        Scene s = new Scene(root, 400, 400);

        primaryStage.setScene(s);

        primaryStage.show();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {

        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet("/css/global.css");

        Font.loadFont(getClass().getResourceAsStream("/css/global.css"), 12);

        StackPane stackPane = new StackPane();

        Scene s = new Scene(stackPane, 400, 400);

        s.getStylesheets().add("css/global.css");

        Label lblTitle = new Label("Rang des joueurs");
        lblTitle.getStyleClass().add("title");

        StackPane.setAlignment(lblTitle, Pos.TOP_CENTER);
        StackPane.setMargin(lblTitle, new Insets(10, 0, 0, 0));

        TableView<Player> tableView = new TableView<>();
        tableView.setMaxSize(300, 300);
        TableColumn<Player, String> playerNameCol = new TableColumn<>("Nom joueur");
        TableColumn<Player, Double> playerScoreCol = new TableColumn<>("Scores");

        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerNameCol.setPrefWidth(150);
        playerScoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        playerScoreCol.setPrefWidth(150);

        tableView.getColumns().add(playerNameCol);
        tableView.getColumns().add(playerScoreCol);

        tableView.getItems().setAll(parsePlayerList());

        // Histogramme

      /*  CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Solutions de méthodes de recherche");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.getData().add(new XYChart.Data<>("NMCS", 10));
        dataSeries.getData().add(new XYChart.Data<>("NMCS Multithread", 20));
        dataSeries.getData().add(new XYChart.Data<>("Autre ...", 15));

        barChart.getData().addAll(dataSeries);

        stackPane.getChildren().add(lblTitle);
        stackPane.getChildren().add(tableView);
        stackPane.getChildren().add(barChart);
*/

        stackPane.getChildren().add(lblTitle);
        stackPane.getChildren().add(tableView);
        primaryStage.setScene(s);

        primaryStage.show();
    }

    private List<Player> parsePlayerList() {
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list
        List<Player> players = new ArrayList<>();
        players.add(new Player("Josko", 50));
        players.add(new Player("Cisco", 100));
        players.add(new Player("Dansko", 22));

        players.sort(Comparator.comparing(Player::getScore));

        return players;
    }
}
