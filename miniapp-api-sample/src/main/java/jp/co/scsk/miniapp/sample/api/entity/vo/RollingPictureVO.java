package jp.co.scsk.miniapp.sample.api.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(name = "RollingAdvertising")
@Data
@Accessors(chain = true)
public class RollingPictureVO {

    @Schema(name = "rollingPictures", description = "rolling advertising list")
    List<PictureInfo> rollingPictures;

    @Data
    @Accessors(chain = true)
    @Schema(name = "AdvertisingInfo")
    public static class PictureInfo {
        @Schema(name = "shopId", description = "shop id")
        private String shopId;

        @Schema(name = "leftImgUrl", description = "shop image on the left")
        private String leftImgUrl;
    }
}
