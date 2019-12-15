package kwan.ie.listpostsbyranking.services.user;

import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.presentation.user.User;
import kwan.ie.listpostsbyranking.presentation.user.UserBody;

public interface UserService {
    User getUser(String username);
    UserEntity getCredentials(String token);
    boolean userExists(String username);
    User add(UserBody user);
}
