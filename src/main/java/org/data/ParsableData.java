package org.data;


import java.sql.Connection;
import java.sql.SQLException;

public interface ParsableData {

    public void parseToDB(Connection connection) throws SQLException;
    public String parseToJson() throws IllegalStateException;
}
