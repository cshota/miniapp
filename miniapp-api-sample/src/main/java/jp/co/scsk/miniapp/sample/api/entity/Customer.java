package jp.co.scsk.miniapp.sample.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private long userId;
    private String customerInfo;
    private Date createAt;
    private Date updateAt;
    private String accessToken;
}
