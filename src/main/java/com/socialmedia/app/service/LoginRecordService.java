package com.socialmedia.app.service;

import com.socialmedia.app.model.LoginRecord;
import com.socialmedia.app.model.User;
import com.socialmedia.app.repository.LoginRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginRecordService {

    private final LoginRecordRepository loginRecordRepository;

    @Autowired
    public LoginRecordService(LoginRecordRepository loginRecordRepository) {
        this.loginRecordRepository = loginRecordRepository;
    }

    public LoginRecord recordLogin(User user, String ipAddress, String userAgent) {
        LoginRecord loginRecord = new LoginRecord(user, ipAddress, userAgent);
        return loginRecordRepository.save(loginRecord);
    }

    public List<LoginRecord> getLoginHistoryForUser(User user) {
        return loginRecordRepository.findByUserOrderByLoginTimeDesc(user);
    }
}
