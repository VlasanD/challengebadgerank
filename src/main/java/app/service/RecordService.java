package app.service;

import app.enums.Status;

public interface RecordService {
    Status updateRecord(Integer challengeId, Integer userId, int score);

    void updateAllRecords();
}
