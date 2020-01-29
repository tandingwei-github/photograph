package com.tdw.photograph.bean;

import java.util.Date;

/**
 * @author 谭锭伟
 * @date 2020/1/3-15:23
 */
public class Message {
    private Integer messageId;
    private Date messageTime;
    private String messageTitle;
    private String messageContent;
    private String messageWriter;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageWriter() {
        return messageWriter;
    }

    public void setMessageWriter(String messageWriter) {
        this.messageWriter = messageWriter;
    }

    public Message(Integer messageId, Date messageTime,
                   String messageTitle, String messageContent,
                   String messageWriter) {
        this.messageId = messageId;
        this.messageTime = messageTime;
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
        this.messageWriter = messageWriter;
    }

    public Message(Integer messageId) {
        this.messageId = messageId;
    }
}