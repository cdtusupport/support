package com.cdtu.support.service;

import com.cdtu.support.pojo.User;

public interface LoginService {

	User queryByName(String name);

	User queryById(String id);
}
