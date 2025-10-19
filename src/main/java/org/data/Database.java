package org.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database {

    private User staticUser = new User("Test", "blabla");
    private Entry staticEntry = new Entry(staticUser, EntryType.MOVIE, "Test", "Test", 2019, 12, null);
    private Rating staticRating = new Rating(staticEntry, staticUser, RatingStars.ONE, "Test");

    private List<User> tempUsers = new ArrayList<>();

    protected Database() {

    }

    public User getUsers(UUID uuid) {
        return staticUser;
    }

    public List<User> getUsers(UUID... uuids) {
        return null;
    }

    public Entry getEntries(UUID uuid) {
        return staticEntry;
    }

    public List<Entry> getEntries(UUID... uuids) {
        return null;
    }

    public Rating getRatings(UUID uuid) {
        return staticRating;
    }

    public List<Rating> getRatings(UUID... uuids) {
        return null;
    }

    public boolean createUser(User user) {
        if (tempUsers.stream().anyMatch(u -> u.getUsername().equals(user.getUsername())))
            return false;

        tempUsers.add(user);
        return true;
    }

    public User getUserByUsername(String username) {
        if ("Test".equals(username))
            return staticUser;
        return tempUsers.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void storeToken(String username, String token) {
    }
}
