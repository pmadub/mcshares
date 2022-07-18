package com.mcb.mcsharesproject.services.user;

import com.mcb.mcsharesproject.entities.user.User;
import com.mcb.mcsharesproject.vo.UserVO;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User saveUser(UserVO userVO);
    User getUser(String username);
    List<User> getUsers();
}
