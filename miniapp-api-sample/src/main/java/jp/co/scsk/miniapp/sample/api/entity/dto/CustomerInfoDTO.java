package jp.co.scsk.miniapp.sample.api.entity.dto;

import lombok.Data;

/**
 * 顧客情報DTO
 */
@Data
public class CustomerInfoDTO {
    /** 顧客Open ID */
    private String openId;
    /** 顧客情報 */
    private String customerInfo;

}
