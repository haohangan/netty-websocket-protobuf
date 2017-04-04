package org.eva.netty_websocket.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class JavaConsoleClient2 {
	static final String url = "ws://127.0.0.1/websocket?uname=java&pwd=";
	static final String subprotocol = "zookeeperWS";

	public static void main(String[] args) throws URISyntaxException {
		WebSocketJavaClient client = new WebSocketJavaClient(new URI(url), subprotocol);
		client.init();
		String uid = "java";
		String tid = "jack";
		Scanner scanner = null;

		scanner = new Scanner(System.in);
		for (;;) {
			String line = scanner.nextLine();
			if (line == null) {
				break;
			}
			client.write(uid, tid, line);
			// Sends the received line to the server.
			// If user typed the 'bye' command, wait until the server closes
			// the connection.
			if ("bye".equals(line.toLowerCase())) {
				client.close();
				break;
			}
		}
		System.out.println("挂壁");
		if (scanner != null) {
			scanner.close();
		}
	}
}
