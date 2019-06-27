package com.cdtu.support.service.impl;


import com.cdtu.support.mapper.PolicyMapper;
import com.cdtu.support.pojo.Policy;
import com.cdtu.support.pojo.PolicyExample;
import com.cdtu.support.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    PolicyMapper policyMapper;


    @Override
    public List<Policy> queryAll() {
        List<Policy> policyList = policyMapper.selectByExample(null);
        return policyList;
    }

    @Override
    public void addAlterPolicy(Policy policy) {

        policyMapper.insert(policy);
        return ;
    }

    @Override
    public List<Policy> queryByName(String name) {
        //封装条件
        PolicyExample policyExample = new PolicyExample();
        PolicyExample.Criteria criteria = policyExample.createCriteria();
        //将信息注入
        criteria.andNameEqualTo(name);
        //此处的因为是查询所以是用select，如果是删除就要delectExample
        List<Policy> policyList = policyMapper.selectByExample(policyExample);
        return policyList;
    }

    @Override
    public void delectByName(String name) {
        PolicyExample policyExample = new PolicyExample();
        PolicyExample.Criteria criteria = policyExample.createCriteria();
        criteria.andNameEqualTo(name);
        policyMapper.deleteByExample(policyExample);
    }


}
