package fr.angelsky.angelskycoalitions.managers.sql;

import fr.angelsky.angelskyapi.api.managers.SQLManager;
import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.Coalition;
import fr.angelsky.angelskycoalitions.coalition.CoalitionPlayer;
import fr.angelsky.angelskycoalitions.coalition.CoalitionType;

import java.sql.SQLException;
import java.util.UUID;

public class SQLCoalitionPlayer {

    private AngelSkyCoalitions angelSkyCoalitions;
    private SQLManager sqlManager;
    private final String COALITION_TABLE = "players_coalition";

    public SQLCoalitionPlayer(AngelSkyCoalitions angelSkyCoalitions) {
        this.angelSkyCoalitions = angelSkyCoalitions;
        this.sqlManager = this.angelSkyCoalitions.getAngelSkyApiInstance().getApiManager().getSqlManager();
    }

    public boolean accountExists(UUID uuid){
        String query = "SELECT * FROM %s WHERE player_uuid = '%s'";
        return (Boolean) sqlManager.getMySQL().query(String.format(query, COALITION_TABLE, uuid.toString()), resultSet -> {
            try {
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public void createAccount(UUID uuid, String player_name, CoalitionType coalitionType){
        String query = "INSERT INTO %s (player_uuid, player_name, coalition_id) VALUES ('%s', '%s', '%s')";
        sqlManager.getMySQL().update(String.format(query, COALITION_TABLE, uuid.toString(), player_name, coalitionType.getId()));
    }

    public void loadPlayer(CoalitionPlayer coalitionPlayer) {
        String query = "SELECT * FROM %s WHERE player_uuid = %s";
        sqlManager.getMySQL().query(String.format(query, COALITION_TABLE, coalitionPlayer.getPlayer().getUniqueId().toString()), resultSet -> {
            try {
                if (resultSet.next()) {
                    coalitionPlayer.setCoalitionPoints(resultSet.getInt("coalition_points"));
                    coalitionPlayer.setEventPoints(resultSet.getInt("event_points"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void save(CoalitionPlayer coalitionPlayer) {
        String query = "UPDATE %s SET event_points = '%s', coalition_points = '%s' WHERE player_uuid = %s";
        sqlManager.getMySQL().update(String.format(query, COALITION_TABLE, coalitionPlayer.getEventPoints(), coalitionPlayer.getCoalitionPoints(), coalitionPlayer.getPlayer().getUniqueId()));
    }
}