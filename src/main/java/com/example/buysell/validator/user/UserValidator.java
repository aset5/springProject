package com.example.buysell.validator.user;

import com.example.buysell.models.User;
import org.springframework.stereotype.Component;

@Component
public interface UserValidator {

    User existsById(long userId);
}
