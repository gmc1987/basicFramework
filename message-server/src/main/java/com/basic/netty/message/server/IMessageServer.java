/**
 * 
 */
package com.basic.netty.message.server;

import org.springframework.beans.factory.annotation.Value;

import com.basic.netty.message.common.ServerChannelInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author gmc
 *
 */
public class IMessageServer {

	@Value("message.server.port")
	private int port;
	
	/**
	 * 服务端启动方法
	 * @throws Exception
	 */
	public void messageServerStart() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootStrap = new ServerBootstrap();
			bootStrap.group(bossGroup, workGroup);
			bootStrap.channel(NioServerSocketChannel.class);
			bootStrap.childHandler(new ServerChannelInitializer());
			bootStrap.option(ChannelOption.SO_BACKLOG, 128);
			bootStrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootStrap.bind(this.port).sync();
			
			if(future.isCancellable()) {
				future.channel().closeFuture().sync();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
