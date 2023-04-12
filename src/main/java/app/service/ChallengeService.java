package app.service;

import app.enums.EntryChallenge;
import app.model.Challenge;
import app.model.User;

import java.util.List;

public interface ChallengeService {
    Challenge findById(Integer challengeId);

    Challenge findChallengeByName(String name);

    List<User> getRankings(Integer challengeId);

    List<Challenge> showAllChallenges();

    Challenge createChallenge(Challenge challenge, Integer userId);

    EntryChallenge participateInChallenge(Integer userId, Integer challengeId);

}
