package fr.angelsky.angelskycoalitions.managers.sql;

import fr.angelsky.angelskyapi.api.managers.SQLManager;
import fr.angelsky.angelskycoalitions.AngelSkyCoalitions;
import fr.angelsky.angelskycoalitions.coalition.Coalition;
import fr.angelsky.angelskycoalitions.coalition.CoalitionType;

import java.sql.SQLException;

public class SQLCoalition {

    private AngelSkyCoalitions angelSkyCoalitions;
    private SQLManager sqlManager;
    private final String COALITION_TABLE = "coalitions";

    public SQLCoalition(AngelSkyCoalitions angelSkyCoalitions){
        this.angelSkyCoalitions = angelSkyCoalitions;
        this.sqlManager = this.angelSkyCoalitions.getAngelSkyApiInstance().getApiManager().getSqlManager();
    }

    public Coalition loadCoalition(CoalitionType coalitionType)
    {
        String query = "SELECT * FROM %s WHERE coalition_id = %";
        return (Coalition) sqlManager.getMySQL().query(String.format(query, COALITION_TABLE, coalitionType.getId()), resultSet -> {
            try {
                if (resultSet.next()) {
                    return new Coalition(coalitionType,
                            resultSet.getInt("event_points"),
                            resultSet.getInt("montly_event_points"),
                            resultSet.getInt("coalition_points"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    public void save(Coalition coalition)
    {
        String query = "UPDATE %s SET event_points = '%s', monthly_event_points = '%s', coalition_points = '%s' WHERE coalition_id = %s";
        sqlManager.getMySQL().update(String.format(query, COALITION_TABLE, coalition.getEventPoints(), coalition.getMonthlyEventPoints(), coalition.getCoalitionPoints(), coalition.getCoalitionType().getId()));
    }

}
