package com.example.buysell.validator.user;

import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserValidatorImpl implements UserValidator {
    private final UserRepository userRepository;

    @Override
    public User existsById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
    }
}
