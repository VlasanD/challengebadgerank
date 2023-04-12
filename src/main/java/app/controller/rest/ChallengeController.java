package app.controller.rest;

import app.enums.EntryChallenge;
import app.enums.Status;
import app.model.Challenge;
import app.model.User;
import app.service.ChallengeService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService = ServiceSinglePointAccess.getChallengeService();

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable Integer id) {
        Challenge challenge = challengeService.findById(id);
        if (challenge == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(challenge);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Challenge> getChallengeByName(@PathVariable String name) {
        Challenge challenge = challengeService.findChallengeByName(name);
        if (challenge == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(challenge);
    }

    @GetMapping("/ranking/{id}")
    public ResponseEntity<List<User>> getRanks(@PathVariable Integer id) {
        List<User> users = challengeService.getRankings(id);
        if (users == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Challenge>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.showAllChallenges());
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Challenge> makeChallenge(@RequestBody Challenge challenge, @PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.createChallenge(challenge, userId));
    }

    @PutMapping("/{challengeId}/participate/{userId}")
    public ResponseEntity<EntryChallenge> participate(@PathVariable Integer userId, @PathVariable Integer challengeId) {
        return ResponseEntity.status(HttpStatus.OK).body(challengeService.participateInChallenge(userId, challengeId));
    }

    @PutMapping("/{challengeId}/update/{userId}/{score}")
    public ResponseEntity<Status> updateChallenge(@PathVariable Integer userId, @PathVariable Integer challengeId, @PathVariable int score) {
        return ResponseEntity.status(HttpStatus.OK).body(ServiceSinglePointAccess.getRecordService().updateRecord(challengeId, userId, score));
    }

}
