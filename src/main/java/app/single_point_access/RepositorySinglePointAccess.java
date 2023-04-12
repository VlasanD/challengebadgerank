package app.single_point_access;

import app.model.UserChallengeRecord;
import app.repository.*;
import app.repository.impl.*;

public class RepositorySinglePointAccess {
    private static BadgeRepository badgeRepository;
    private static ChallengeRepository challengeRepository;
    private static UserRepository userRepository;
    private static UserChallengeRepository userChallengeRecordRepository;

    static {
        badgeRepository = new BadgeRepositoryImpl();
        challengeRepository = new ChallengeRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        userChallengeRecordRepository = new UserChallengeRepositoryImpl();

    }

    public static UserChallengeRepository getUserChallengeRecordRepository() {
        return userChallengeRecordRepository;
    }

    public static BadgeRepository getBadgeRepository() {
        return badgeRepository;
    }

    public static ChallengeRepository getChallengeRepository() {
        return challengeRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }
}
