package app.service.impl;

import app.enums.Status;
import app.model.Challenge;
import app.model.User;
import app.model.UserChallengeRecord;
import app.service.RecordService;
import app.single_point_access.RepositorySinglePointAccess;

import java.time.LocalDate;
import java.util.List;

public class RecordServiceImpl implements RecordService {
    @Override
    public void updateAllRecords() {
        List<Challenge> challenges = RepositorySinglePointAccess.getChallengeRepository().findAll();
        for (Challenge challenge : challenges) {
            if (LocalDate.now().compareTo(challenge.getLastDate()) <= 0) {
                for (UserChallengeRecord userChallengeRecord : challenge.getUserChallengeRecord()) {
                    if (!challenge.getLastDate().equals(LocalDate.now().minusDays(1)))
                        userChallengeRecord.setStatus(Status.ELIMINATED);
                    else if (challenge.getLastDate().equals(LocalDate.now().minusDays(1))) {
                        userChallengeRecord.setStatus(Status.FINISHED);

                        User user = RepositorySinglePointAccess.getUserRepository().findById(userChallengeRecord.getUser().getId());
                        RepositorySinglePointAccess.getUserRepository().update(user);

                    }
                    RepositorySinglePointAccess.getUserChallengeRecordRepository().update(userChallengeRecord);
                }
            }
        }
    }

    @Override
    public Status updateRecord(Integer challengeId, Integer userId, int score) {
        UserChallengeRecord userChallengeRecord = RepositorySinglePointAccess.getUserChallengeRecordRepository().getRecordByChallengeAndUser(userId, challengeId);
        if (userChallengeRecord.getHighScore() < score)
            userChallengeRecord.setHighScore(score);
        if(score < userChallengeRecord.getChallenge().getMinimumCondition()){
            userChallengeRecord.setStatus(Status.ELIMINATED);
            return Status.ELIMINATED;
        }
        userChallengeRecord.setLastDay(LocalDate.now());
        RepositorySinglePointAccess.getUserChallengeRecordRepository().update(userChallengeRecord);
        return Status.ON_GOING;
    }
}
