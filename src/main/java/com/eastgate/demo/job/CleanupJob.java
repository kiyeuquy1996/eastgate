package com.eastgate.demo.job;

import com.eastgate.demo.service.impl.UserServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanupJob {
    private final UserServiceImpl userService;

    public CleanupJob(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Scheduled(fixedRate = 60000) // Chạy mỗi 60 giây
    public void cleanExpiredKeys() {
        userService.deleteExpiredKeys();
    }
}
