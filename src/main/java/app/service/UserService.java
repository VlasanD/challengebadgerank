package app.service;

import app.model.Badge;
import app.model.Challenge;
import app.enums.EntryChallenge;
import app.model.User;
import app.model.UserChallengeRecord;

import java.util.List;

public interface UserService {
    User authenticate(String username, String password);

    User registerUser(String username, String password, String confirmPassword);

    User findById(Integer id);

    List<Badge> showBadges(Integer userId);

    List<Challenge> showOngoingChallenges(Integer userId);

}
