package org.eva.netty_websocket.inject;

import org.eva.netty_websocket.message.MessageProgress;
import org.eva.netty_websocket.message.protobuf.ProtebufMessageProgress;

import com.google.inject.AbstractModule;

public class SingleServerProgressModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MessageProgress.class).to(ProtebufMessageProgress.class);
	}

}
