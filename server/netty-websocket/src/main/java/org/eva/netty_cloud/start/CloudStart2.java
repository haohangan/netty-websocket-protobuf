package org.eva.netty_cloud.start;

import org.eva.netty_cloud.handler.WSCloudServer;

/**
 * 测试用
 * @author 976175665
 * @date 2017年4月8日 下午5:02:45
 */
public class CloudStart2 {
   public static void main(String[] args) {
	   
	try {
		new WSCloudServer().run(82,"app2","192.168.0.108:2181");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
