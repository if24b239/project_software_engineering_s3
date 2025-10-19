package org.data;

import java.sql.Connection;
import java.sql.SQLException;

public class Rating implements ParsableData {

    private Entry entry;
    private User user;
    private RatingStars stars;
    private String comment;
    private int likes = 0;

    public Rating(Entry entry, User user, RatingStars stars, String comment) {
        this.entry = entry;
        this.user = user;
        this.stars = stars;
        this.comment = comment;
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
