package jp.co.scsk.miniapp.sample.api.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(name = "HomePageCoupon")
@Data
@Accessors(chain = true)
public class IndexCouponVO {
    @Schema(name = "coupons", description = "coupon list")
    List<IndexCouponInfo> coupons;

    @Data
    @Schema(name = "HomePageCouponDetail")
    public static class IndexCouponInfo {
        @Schema(name = "shopId", description = "shop id")
        private int shopId;

        @Schema(name = "leftImgUrl", description = "shop image on the left")
        private String leftImgUrl;

        @Schema(name = "title", description = "shop title")
        private String title;

        @Schema(name = "hot", description = "shop popularity")
        private int hot;

        @Schema(name = "distance", description = "shop distance from here")
        private String distance;

        @Schema(name = "imgUrl", description = "shop image url")
        private String imgUrl;
    }
}
