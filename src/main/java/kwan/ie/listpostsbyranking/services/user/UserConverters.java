package kwan.ie.listpostsbyranking.services.user;

import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.presentation.user.User;
import kwan.ie.listpostsbyranking.presentation.user.UserBody;

public class UserConverters {
    public static User userEntityToUser(UserEntity entity){
        return new User(entity.getId(), entity.getUsername(), entity.getPassword());
    }

    public static UserEntity userBodyToUserEntity(UserBody body){
        UserEntity user = new UserEntity();
        user.setUsername(body.getUsername());
        user.setPassword(body.getPassword());
        return user;
    }
}
