package revature.pro0.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import revature.pro0.service.AuthService;

public class AuthControl {
    private final AuthService authService;

    public AuthControl(AuthService authService) {this.authService = authService;}

    public void register(Context cont){
        authService.register(cont);
    }

    public void login(Context cont){
        authService.login(cont);
    }

    public void logout(Context cont){
        authService.logout(cont);
    }
}
