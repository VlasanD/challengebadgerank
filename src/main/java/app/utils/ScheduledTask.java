package app.utils;

import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask{

    @Scheduled(cron = "0 0 0 * * *")
    public void run() {
        ServiceSinglePointAccess.getRecordService().updateAllRecords();
    }
}
