package kwan.ie.listpostsbyranking.presentation.user;

public class UserBody {
    private final String username;

    public String getUsername() {
        return username;
    }

    private final String password;

    public String getPassword() {
        return password;
    }

    public UserBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
