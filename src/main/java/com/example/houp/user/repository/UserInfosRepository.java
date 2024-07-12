package com.example.houp.user.repository;

import com.example.houp.user.UserInfos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfosRepository extends JpaRepository<UserInfos, Long> {
}