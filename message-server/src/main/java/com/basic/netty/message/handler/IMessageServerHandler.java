/**
 * 
 */
package com.basic.netty.message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author gmc
 * @see 消息服务器消息处理器
 */
public class IMessageServerHandler extends SimpleChannelInboundHandler<String> {
	
	private final Logger logger = LoggerFactory.getLogger(IMessageServerHandler.class);

	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	/**
	 * 服务端收到客户端接入时调用
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		channels.add(ctx.channel());
	}
	
	/**
	 * 服务端收到客户端断开时调用
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		channels.remove(ctx.channel());
	}
	
	/**
	 * 服务端读取到客户端写入信息时调用
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
		logger.info("接收到来自客户端的信息：" + msg);
		
		for(Channel channel : channels) {
			if(channel == ctx.channel()) {
				channel.writeAndFlush(msg);
			}
		}
	}
	
	/**
	 * 服务端监听到客户端活动时调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}
	
	/**
	 * 服务端监听到客户端不活动时调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}
	
	/**
	 * 事件处理方法出现异常时调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
		ctx.close();
	}

}
