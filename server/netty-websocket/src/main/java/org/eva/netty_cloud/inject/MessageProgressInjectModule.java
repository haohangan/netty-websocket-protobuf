package org.eva.netty_cloud.inject;

import org.eva.netty_cloud.message.protobuf.CloudProtobufMessageProgress;
import org.eva.netty_websocket.message.MessageProgress;

import com.google.inject.AbstractModule;

public class MessageProgressInjectModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MessageProgress.class).to(CloudProtobufMessageProgress.class);
	}

}
