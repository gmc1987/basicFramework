/**
 * 
 */
package com.basic.netty.message.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.netty.message.handler.IMessageServerHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author gmc
 * @see 服务器初始化
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final Logger logger = LoggerFactory.getLogger(ServerChannelInitializer.class);
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new IMessageServerHandler());

        logger.info("IM-Server初始化...");
	}

}
