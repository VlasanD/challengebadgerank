package app.repository;

import app.model.UserChallengeRecord;

import java.util.List;

public interface UserChallengeRepository extends CRUDRepository<UserChallengeRecord, Integer> {
    List<UserChallengeRecord> getCompleteRecord(Integer id);

    UserChallengeRecord getRecordByChallengeAndUser(Integer userId, Integer challengeId);
}
