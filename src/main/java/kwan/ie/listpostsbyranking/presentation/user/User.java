package kwan.ie.listpostsbyranking.presentation.user;

public class User {
    private final long id;

    public long getId() {
        return id;
    }

    private final String username;

    public String getUsername() {
        return username;
    }

    private final String password;

    public String getPassword() {
        return password;
    }

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
