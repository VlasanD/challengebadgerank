package app.controller.rest;

import app.model.Badge;
import app.model.Challenge;
import app.model.User;
import app.requestclasses.LogInRequest;
import app.requestclasses.SignInRequest;
import app.service.UserService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService = ServiceSinglePointAccess.getUserService();

    @PostMapping("/signIn")
    ResponseEntity<User> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(signInRequest.getUsername(), signInRequest.getPassword(), signInRequest.getConfirmPassword()));
    }

    @PostMapping("/login")
    ResponseEntity<User> logIn(@RequestBody LogInRequest logInRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.authenticate(logInRequest.getUsername(), logInRequest.getPassword()));
    }

    @GetMapping("/badges/{id}")
    ResponseEntity<List<Badge>> personalBadges(@PathVariable Integer id) {
        if(userService.showBadges(id)== null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.showBadges(id));
    }

    @GetMapping("/challenges/{id}")
    ResponseEntity<List<Challenge>> challenges(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.showOngoingChallenges(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}


