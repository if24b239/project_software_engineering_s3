package org.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Entry implements ParsableData {

    private User user;
    private EntryType type;
    private String title;
    private String description;
    private int releaseYear = 0;
    private int ageRestriction = 0;
    private List<String> genres;

    public Entry(User user, EntryType type, String title, String description, int releaseYear, int ageRestriction, List<String> genres) {
        this.user = user;
        this.type = type;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.ageRestriction = ageRestriction;
        this.genres = genres;
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
}
