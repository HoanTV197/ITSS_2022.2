package main.java.login;

import java.util.HashMap;

public class Credentials {
	 
	HashMap<String, String> loginInfo = new HashMap<String, String>();
	
	protected Credentials() {
		loginInfo.put("admin", "123");
		loginInfo.put("workerLeader", "123");
		loginInfo.put("worker", "123");
		loginInfo.put("officerLeader", "123");
		loginInfo.put("officer", "123");
	}
	
	protected HashMap<String, String> getLoginInfo() {
		return loginInfo;
	}
}
