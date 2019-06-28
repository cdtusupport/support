package com.cdtu.support.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SupportUtil {

	public static String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String getUUID(){
		return UUID.randomUUID().toString().substring(0,5);
	}
}
