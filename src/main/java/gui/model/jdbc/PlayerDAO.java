package gui.model.jdbc;

import gui.model.Player;
import gui.view.util.ConnectionInputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDAO.class);

    public static void insertNewPlayer(ConnectionInputDTO inputs, Connection connection) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO PLAYER (username, password) VALUES (?,?);");
        insertStatement.setString(1, inputs.getUsername());
        insertStatement.setString(2, inputs.getPassword());
        insertStatement.executeUpdate();
    }

    public static Optional<Player> readPlayer(String playerName, Connection connection) throws SQLException {
        LOGGER.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM PLAYER WHERE username = ?;");
        readStatement.setString(1, playerName);

        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            LOGGER.info("There is no data in the database!");
            return Optional.empty();
        }
        LOGGER.info("Data is extracted !");

        return Optional.of(new Player(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password")));
    }

    public static List<Player> getTopTenPlayers(Connection connection) throws SQLException {
        List<Player> topPlayers = new ArrayList<>();
        LOGGER.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM PLAYER p INNER JOIN GAME g on p.id = g.player_id ORDER BY g.obtained_score   ");

        ResultSet resultSet = readStatement.executeQuery();

        while (resultSet.next()) {
            topPlayers.add(new Player(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getDouble("obtained_score")));
        }
        LOGGER.info("Data is extracted !");
        return topPlayers;
    }
}
