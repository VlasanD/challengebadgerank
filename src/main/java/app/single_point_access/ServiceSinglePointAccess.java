package app.single_point_access;

import app.service.*;
import app.service.impl.*;

public class ServiceSinglePointAccess {
    private static final UserService userService;
    private static final ChallengeService challengeService;
    private static final RecordService recordService;

    static {
        userService = new UserServiceImpl();
        challengeService = new ChallengeServiceImpl();
        recordService = new RecordServiceImpl();
    }

    public static RecordService getRecordService() {
        return recordService;
    }

    public static ChallengeService getChallengeService() {
        return challengeService;
    }

    public static UserService getUserService() {
        return userService;
    }
}
