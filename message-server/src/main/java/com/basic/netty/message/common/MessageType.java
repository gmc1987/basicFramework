/**
 * 
 */
package com.basic.netty.message.common;

/**
 * @author gmc
 * @see 消息类型
 */
public enum MessageType {

	/**
	 * 文本信息
	 */
	TEXT_MSG,
	
	/**
	 * 媒体信息（包含了图片、语音、录像）
	 */
	MEDIA_MSG,
	
	/**
	 * 语音通讯
	 */
	VOICE_MSG,
	
	/**
	 * 视频通讯
	 */
	VIDEO_MSG;
	
}
