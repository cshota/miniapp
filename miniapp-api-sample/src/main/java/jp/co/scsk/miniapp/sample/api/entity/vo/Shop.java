package jp.co.scsk.miniapp.sample.api.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(name = "Shop")
@Data
@Accessors(chain = true)
public class Shop {

    @Schema(name = "id", description = "id")
    private Integer id;

    @Schema(name = "title", description = "title")
    private String title;

    @Schema(name = "hot", description = "popularity")
    private Integer hot;

    @Schema(name = "subtitle", description = "sub title")
    private String subtitle;

    @Schema(name = "imgUrl", description = "img http url")
    private String imgUrl;

    @Schema(name = "distance", description = "distance from here /m")
    private String distance;

    @Schema(name = "products", description = "shop products")
    private List<String> products;

    @Schema(name = "coupons", description = "shop coupons")
    private List<Coupon> coupons;

    @Schema(name = "shop", description = "shop info")
    private List<String> shop;

    @Schema(name = "contact", description = "contact details")
    private Contact contact;

    @Data
    @Schema(name = "Coupon")
    @Accessors(chain = true)
    public static class Coupon {

        @Schema(name = "leftImgUrl", description = "coupon image on the left")
        private String leftImgUrl;

        @Schema(name = "title", description = "title")
        private String title;

        @Schema(name = "cashCoupon", description = "is cash coupon")
        private Boolean cashCoupon;

        @Schema(name = "subtitle", description = "sub title")
        private String subtitle;

        @Schema(name = "hasDeadline", description = "is there a deadline")
        private boolean hasDeadline;

        @Schema(name = "infos", description = "description")
        private List<String> infos;

        @Schema(name = "type", description = "type")
        private String type;

    }

    @Data
    @Accessors(chain = true)
    @Schema(name = "ContactDetails")
    private static class Contact {
        @Schema(name = "location", description = "latitude and longitude")
        private List<Double> location;

        @Schema(name = "address", description = "address")
        private String address;

        @Schema(name = "phone", description = "phone number")
        private String phone;

        @Schema(name = "url", description = "url")
        private String url;
    }

}
