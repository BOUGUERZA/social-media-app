package com.socialmedia.app.repository;

import com.socialmedia.app.model.LoginRecord;
import com.socialmedia.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {
    List<LoginRecord> findByUserOrderByLoginTimeDesc(User user);
}
