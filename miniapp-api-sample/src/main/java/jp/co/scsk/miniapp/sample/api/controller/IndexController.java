package jp.co.scsk.miniapp.sample.api.controller;

import cn.hutool.json.JSONArray;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.co.scsk.miniapp.sample.api.entity.vo.CandidatesVO;
import jp.co.scsk.miniapp.sample.api.entity.vo.IndexCouponVO;
import jp.co.scsk.miniapp.sample.api.entity.vo.RollingPictureVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Tag(name = "HomePageController")
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    /**
     * mock data
     */
    @Resource
    Map<String, Object> cache;

    Map<String, Object> indexCache;

    @PostConstruct
    private void initIndexCache() {
        indexCache = (Map<String, Object>) cache.get("index");
    }

    @Operation(summary = "Candidates")
    @RequestMapping(value = "/candidates", method = RequestMethod.GET)
    public CandidatesVO candidates() {
        JSONArray candidates = (JSONArray) indexCache.get("candidates");
        CandidatesVO candidatesVO = new CandidatesVO();
        candidatesVO.setCandidates(candidates.toBean(ArrayList.class));
        return candidatesVO;
    }

    @Operation(summary = "RollingPicturesAdvertising")
    @RequestMapping(value = "/rollingPictures", method = RequestMethod.GET)
    public RollingPictureVO rollingPictures() {
        JSONArray rollingPictures = (JSONArray) indexCache.get("rollingPictures");
        RollingPictureVO rollingPictureVO = new RollingPictureVO();
        List<RollingPictureVO.PictureInfo> pictureInfos = rollingPictures.toList(RollingPictureVO.PictureInfo.class);
        rollingPictureVO.setRollingPictures(pictureInfos);
        return rollingPictureVO;
    }

    @Operation(summary = "CouponList")
    @RequestMapping(value = "/coupons", method = RequestMethod.GET)
    public IndexCouponVO coupons() {
        JSONArray couponList = (JSONArray) indexCache.get("couponList");
        IndexCouponVO couponVO = new IndexCouponVO();
        List<IndexCouponVO.IndexCouponInfo> indexCouponInfos =
                couponList.toList(IndexCouponVO.IndexCouponInfo.class);
        Collections.shuffle(indexCouponInfos);
        couponVO.setCoupons(indexCouponInfos);
        return couponVO;
    }

}
