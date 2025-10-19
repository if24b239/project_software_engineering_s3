package org.data;


import java.sql.Connection;
import java.sql.SQLException;

public interface ParsableData {

    public void parseToDB(Connection connection) throws SQLException;
    public String parseToJson() throws IllegalStateException;
    public void parseFromJson(String json) throws IllegalStateException;
    public void parseFromDB(Connection connection) throws SQLException;
}
