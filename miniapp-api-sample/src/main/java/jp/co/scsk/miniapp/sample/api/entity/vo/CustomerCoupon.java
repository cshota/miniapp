package jp.co.scsk.miniapp.sample.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Schema(name = "CustomerCoupon")
@Accessors(chain = true)
public class CustomerCoupon {
    @JsonIgnore
    private long userId;
    @Schema(name = "coupons", description = "coupon list")
    private List<CouponInfo> coupons;

    @Data
    @Accessors(chain = true)
    @Schema(name = "CouponInfo")
    private static class CouponInfo {

        @Schema(name = "shopId", description = "shop id")
        private Integer shopId;

        @Schema(name = "id", description = "coupon id")
        private String id;

        @Schema(name = "type", description = "coupon type")
        private String type;

        @Schema(name = "cashCoupon", description = "is cash coupon")
        private Boolean cashCoupon;

        @Schema(name = "leftImgUrl", description = "coupon image on the left")
        private String leftImgUrl;

        @Schema(name = "title", description = "title")
        private String title;

        @Schema(name = "subtitle", description = "subtitle")
        private String subtitle;

        @Schema(name = "subtitleWarn", description = "sub title warn")
        private Boolean subtitleWarn;

        @Schema(name = "hasDeadline", description = "is there a deadline")
        private Boolean hasDeadline;

        @Schema(name = "infos", description = "description")
        private List<String> infos;
    }
}
