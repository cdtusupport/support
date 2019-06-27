package com.cdtu.support.service;

import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();
    List<User> queryByName(String name);
    void deleteByPrimaryKey(String id);
}
