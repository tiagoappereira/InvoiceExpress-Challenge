package kwan.ie.listpostsbyranking.services.user;

import javafx.util.Pair;
import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.dal.repository.UserRepository;
import kwan.ie.listpostsbyranking.exception.AuthorizationException;
import kwan.ie.listpostsbyranking.exception.UserConflictException;
import kwan.ie.listpostsbyranking.presentation.user.User;
import kwan.ie.listpostsbyranking.presentation.user.UserBody;
import kwan.ie.listpostsbyranking.util.BasicAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repo;

    @Override
    public User getUser(String username) {
        Optional<UserEntity> entity = repo.findByUsername(username);
        if(!entity.isPresent()) throw new AuthorizationException("Username is wrong");
        return UserConverters.userEntityToUser(entity.get());
    }

    @Override
    public UserEntity getCredentials(String basicToken) {
        Pair<String, String> userAndPwd = BasicAuthentication.decode(basicToken);
        Optional<UserEntity> userOptional = repo.findByUsername(userAndPwd.getKey());
        if(!userOptional.isPresent() || !userOptional.get().getPassword().equals(userAndPwd.getValue()))
            throw new AuthorizationException("Access was denied because the required authorization was not granted!");
        return userOptional.get();
    }

    @Override
    public boolean userExists(String username) {
        return repo.findByUsername(username).isPresent();
    }

    @Override
    public User add(UserBody body) {
        if(userExists(body.getUsername()))
            throw new UserConflictException(String.format("Username %s already exists!", body.getUsername()));
        UserEntity userEntity = UserConverters.userBodyToUserEntity(body);
        repo.save(userEntity);
        return UserConverters.userEntityToUser(userEntity);
    }
}
