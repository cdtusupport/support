package com.cdtu.support.service;

import com.cdtu.support.pojo.Policy;

import java.util.List;

public interface  PolicyService {
    List<Policy> queryAll();
    void addAlterPolicy(Policy policy);
    List<Policy> queryByName(String name);
    void delectByName(String name);
}
