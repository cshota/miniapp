package jp.co.scsk.miniapp.sample.api.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "SignInfo")
public class SignDTO {
    @Schema(name = "jwt", description = "jwt token")
    private String jwt;
    @Schema(name = "customerId", description = "customer open id")
    private String customId;
}
