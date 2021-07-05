package com.hack.telegrambot.repo;

import com.hack.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u")
    User findByChatId(long id);

    @Query("SELECT u FROM User u WHERE u.state LIKE %:state%")
    List searchByStateLike(@Param("state") String state);

    List<User> findAll();


}
