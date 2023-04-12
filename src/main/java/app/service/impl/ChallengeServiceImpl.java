package app.service.impl;

import app.enums.EntryChallenge;
import app.enums.Status;
import app.model.Challenge;
import app.model.User;
import app.model.UserChallengeRecord;
import app.service.ChallengeService;
import app.single_point_access.RepositorySinglePointAccess;
import app.utils.ComparatorUserChallengeRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChallengeServiceImpl implements ChallengeService {
    @Override
    public Challenge findById(Integer challengeId) {
        return RepositorySinglePointAccess.getChallengeRepository().findById(challengeId);
    }

    @Override
    public Challenge findChallengeByName(String name) {
        return RepositorySinglePointAccess.getChallengeRepository().getByName(name);
    }

    @Override
    public List<User> getRankings(Integer challengeId) {
        Challenge challenge = RepositorySinglePointAccess.getChallengeRepository().findById(challengeId);
        if(challenge == null)
            return null;
        List<UserChallengeRecord> userChallengeRecordList = challenge.getUserChallengeRecord();
        userChallengeRecordList.sort(new ComparatorUserChallengeRecord());
        List<User> users = new ArrayList<>();
        for (UserChallengeRecord userChallengeRecord : userChallengeRecordList)
            users.add(userChallengeRecord.getUser());
        return users;
    }

    @Override
    public List<Challenge> showAllChallenges() {
        return RepositorySinglePointAccess.getChallengeRepository().findAll();
    }

    @Override
    public EntryChallenge participateInChallenge(Integer userId, Integer challengeId) {
        User user = RepositorySinglePointAccess.getUserRepository().findById(userId);
        Challenge challenge = RepositorySinglePointAccess.getChallengeRepository().findById(challengeId);
        if (user.getTokensOwned() < challenge.getEntryFee())
            return EntryChallenge.NOT_ENOUGH_TOKENS;
        if (((Integer) challenge.getUserChallengeRecord().size()).equals(challenge.getPlaces()))
            return EntryChallenge.NO_FREE_SPACE;

        UserChallengeRecord userChallengeRecord = new UserChallengeRecord();
        userChallengeRecord.setChallenge(challenge);
        userChallengeRecord.setUser(user);
        userChallengeRecord.setStatus(Status.ON_GOING);
        userChallengeRecord.setLastDay(LocalDate.now());
        RepositorySinglePointAccess.getUserChallengeRecordRepository().save(userChallengeRecord);

        user.setTokensOwned(user.getTokensOwned() - challenge.getEntryFee());
        RepositorySinglePointAccess.getUserRepository().update(user);

        challenge.getUserChallengeRecord().add(userChallengeRecord);
        RepositorySinglePointAccess.getChallengeRepository().update(challenge);

        return EntryChallenge.ACCEPTED;
    }

    @Override
    public Challenge createChallenge(Challenge challenge, Integer userId) {
        if (RepositorySinglePointAccess.getChallengeRepository().getByName(challenge.getName()) != null)
            return null;

        User user = RepositorySinglePointAccess.getUserRepository().findById(userId);
        user.setTokensOwned(user.getTokensOwned() - challenge.getFirstPlaceReward() - challenge.getSecondPlaceReward() - challenge.getThirdPlaceReward());
        RepositorySinglePointAccess.getUserRepository().update(user);

        RepositorySinglePointAccess.getBadgeRepository().save(challenge.getBadge());

        return RepositorySinglePointAccess.getChallengeRepository().save(challenge);
    }

}
