/**
 * 
 */
package com.basic.netty.message.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.basic.netty.message.common.MessageType;

/**
 * @author gmc
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "chat_message")
public class IMessage implements Serializable {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String messageId;
	
	/**
	 * 发送人
	 */
	@Column(name = "message_from", nullable = false)
	private Long messageFrom;
	
	/**
	 * 接收人
	 */
	@Column(name = "message_to", nullable = false)
	private Long messageTo;
	
	/**
	 * 消息状态 0-发送成功，1-对方已接受，2-对方已读信息
	 */
	@Column(name = "message_status", nullable = false)
	private Integer messageStatus;
	
	/**
	 * 消息类型
	 */
	@Column(name = "message_type", nullable = false)
	private Integer messageType;
	
	/**
	 * 消息内容
	 */
	@Column(name = "message_context", length = 2000, nullable = false)
	private String messageContext;
	
	/**
	 * 发送时间
	 */
	@Column(name = "message_send_time", nullable = false)
	private Date messageSendTime;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Long getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(Long messageFrom) {
		this.messageFrom = messageFrom;
	}

	public Long getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(Long messageTo) {
		this.messageTo = messageTo;
	}

	public Integer getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getMessageContext() {
		return messageContext;
	}

	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext;
	}

	public Date getMessageSendTime() {
		return messageSendTime;
	}

	public void setMessageSendTime(Date messageSendTime) {
		this.messageSendTime = messageSendTime;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	
}
