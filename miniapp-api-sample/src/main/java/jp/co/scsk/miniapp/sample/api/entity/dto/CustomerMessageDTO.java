package jp.co.scsk.miniapp.sample.api.entity.dto;

import lombok.Data;

/**
 * 顧客メッセージDTO
 */
@Data
public class CustomerMessageDTO {
    /** 顧客アクセストークン */
    private String accessToken;
    /** メッセージタイトル */
    private String messageTitle;
    /** メッセージ内容 */
    private String messageBody;
    /** クリックして開けたページパス */
    private String clickPagePath;

}
