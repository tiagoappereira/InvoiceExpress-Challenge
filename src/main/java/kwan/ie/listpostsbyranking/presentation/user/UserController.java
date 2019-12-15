package kwan.ie.listpostsbyranking.presentation.user;

import kwan.ie.listpostsbyranking.exception.AuthorizationException;
import kwan.ie.listpostsbyranking.exception.UserConflictException;
import kwan.ie.listpostsbyranking.services.user.UserService;
import kwan.ie.listpostsbyranking.util.BasicAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/users"}, produces = {"application/json"})
public class UserController {

    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = {"/signUp"})
    public Token signUp(@RequestBody UserBody body) {
        if(svc.userExists(body.getUsername()))
            throw new UserConflictException("Username " + body.getUsername() + " already exists!");
        svc.add(body);
        return new Token(BasicAuthentication.encode(body.getUsername(), body.getPassword()));
    }

    @PostMapping(path = {"/auth"})
    public boolean login(@RequestBody UserBody body) {
        if(!svc.userExists(body.getUsername()) || !svc.getUser(body.getUsername()).getPassword().equals(body.getPassword()))
            throw new AuthorizationException("Username or password are wrong");
        return true;
    }
}