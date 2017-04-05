package org.eva.netty_cloud.group;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public enum Group {
	INSTANCE;

	private final Set<String> COMMON_GROUP = new HashSet<>();
	
	private final Set<String> OTHER_GROUP = new HashSet<>();

	public void initGroups() {
		Properties prop = new Properties();
		try {
			prop.load(Group.class.getResourceAsStream("groups.properties"));
			prop.forEach((k, v) -> {
				COMMON_GROUP.add(k.toString().trim());
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addSpecial(String groupName){
		if(COMMON_GROUP.contains(groupName)){
			return;
		}
		OTHER_GROUP.add(groupName);
	}

	public Set<String> getAllGroup() {
		Set<String> set = new HashSet<>();
		set.addAll(COMMON_GROUP);
		set.addAll(OTHER_GROUP);
		return set;
	}

	public Set<String> getCommonGroup() {
		return COMMON_GROUP;
	}
	
}
