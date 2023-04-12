package app.service.impl;

import app.enums.Status;
import app.model.*;
import app.service.UserService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User authenticate(String username, String password) {
        User user = RepositorySinglePointAccess.getUserRepository().findByUsername(username);
        if (user.getPassword().equals(password))
            return user;
        return null;
    }

    @Override
    public User registerUser(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword))
            return null;
        User user = RepositorySinglePointAccess.getUserRepository().findByUsername(username);
        if (user != null)
            return null;
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTokensOwned(100);
        RepositorySinglePointAccess.getUserRepository().save(user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return RepositorySinglePointAccess.getUserRepository().findById(id);
    }

    @Override
    public List<Badge> showBadges(Integer userId) {
        List<UserChallengeRecord> userChallengeRecords=RepositorySinglePointAccess.getUserChallengeRecordRepository().getCompleteRecord(userId);
        List<Badge> badges=new ArrayList<>();
        for(UserChallengeRecord userChallengeRecord:userChallengeRecords){
            if(userChallengeRecord.getStatus().equals(Status.FINISHED))
                badges.add(userChallengeRecord.getChallenge().getBadge());
        }
        return badges;
    }

    @Override
    public List<Challenge> showOngoingChallenges(Integer userId) {
        User user = RepositorySinglePointAccess.getUserRepository().findById(userId);
        List<UserChallengeRecord> records = RepositorySinglePointAccess.getUserChallengeRecordRepository().getCompleteRecord(user.getId());
        List<Challenge> challenges = new ArrayList<>();
        for (UserChallengeRecord userChallengeRecord : records) {
            Challenge toAdd=userChallengeRecord.getChallenge();
            toAdd.setUserChallengeRecord(null);
            challenges.add(toAdd);
        }
        return challenges;
    }

}
