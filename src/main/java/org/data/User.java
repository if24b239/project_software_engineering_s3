package org.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class User extends Profile implements ParsableData {

    private String username;
    private String password;
    private List<Entry> entries;
    private List<Rating> ratings;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void parseToDB(Connection connection) throws SQLException {

    }

    @Override
    public String parseToJson() throws IllegalStateException {
        return "";
    }

    @Override
    public void parseFromJson(String json) throws IllegalStateException {

    }

    @Override
    public void parseFromDB(Connection connection) throws SQLException {

    }

    public String getUsername() {
        return username;
    }

    public Object getPassword() {
        return password;
    }
}
