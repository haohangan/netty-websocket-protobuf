package org.eva.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.channel.Channel;

public class WaitUtil {

	public static void waitInput(Channel channel) throws IOException, InterruptedException {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			boolean done = false;
			while (!done) {
				System.out.print("> ");

				String line = in.readLine();
				if (line == null) {
					break;
				}

				String command = line.trim();
				String[] parts = command.split("\\s");
				if (parts.length == 0) {
					continue;
				}
				String operation = parts[0];

				if (operation.equalsIgnoreCase("q") || operation.equalsIgnoreCase("quit")) {
					done = true;
				}
				Thread.sleep(1000); // just to allow the console output to catch
									// up
			}
		} finally {
			channel.close();
		}
	}
}
