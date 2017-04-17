package org.eva.netty_cloud.handler;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eva.common.WaitUtil;

import io.netty.channel.Channel;

public class AsynWaitUtil {

	public static void waitInput(Channel channel) throws IOException, InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					WaitUtil.waitInput(channel);
					executor.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
