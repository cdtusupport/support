package com.cdtu.support.service;


import com.cdtu.support.pojo.NeedJoin;

import java.util.List;

public interface NeedJoinService {

	Integer addNeedJoin(NeedJoin needJoin);
	List<NeedJoin> queryAll();
}
