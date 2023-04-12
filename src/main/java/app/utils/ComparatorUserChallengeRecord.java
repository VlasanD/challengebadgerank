package app.utils;

import app.model.UserChallengeRecord;

import java.util.Comparator;

public class ComparatorUserChallengeRecord implements Comparator<UserChallengeRecord> {
    @Override
    public int compare(UserChallengeRecord o1, UserChallengeRecord o2) {
        return -(o1.getHighScore() - o2.getHighScore());
    }
}
