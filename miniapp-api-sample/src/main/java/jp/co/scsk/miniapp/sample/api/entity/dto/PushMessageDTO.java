package jp.co.scsk.miniapp.sample.api.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PushMessageDTO {
    @Schema(name = "messageTitle", description = "message title")
    private String messageTitle;
    @Schema(name = "messageBody", description = "message body")
    private String messageBody;
    @Schema(name = "clickPagePath", description = "path when click")
    private String clickPagePath;
}
