package jp.co.scsk.miniapp.sample.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private long id;
    private long userId;
    private String title;
    private String message;
    private Date createAt;
    private String clickPagePath;
}
