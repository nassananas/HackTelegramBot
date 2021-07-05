package com.hack.telegrambot.service;

import com.hack.telegrambot.model.User;
import com.hack.telegrambot.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User findByChatId(long id) { return  userRepository.findByChatId(id); }

    @Transactional
    public List<User> findAll() { return userRepository.findAll(); }

    @Transactional
    public void addUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public List searchByStateLike(String state) {return userRepository.searchByStateLike(state);}


}
