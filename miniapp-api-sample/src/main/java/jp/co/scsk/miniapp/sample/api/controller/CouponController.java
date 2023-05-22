package jp.co.scsk.miniapp.sample.api.controller;

import cn.hutool.json.JSONArray;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.co.scsk.miniapp.sample.api.entity.vo.CustomerCoupon;
import jp.co.scsk.miniapp.sample.api.entity.vo.CustomerCouponHistory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Tag(name = "CouponManager")
@RestController
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    /**
     * mock data
     */
    @Resource
    Map<String, Object> cache;

    List<CustomerCoupon> couponCache;

    List<CustomerCouponHistory> couponHistoryList;


    @PostConstruct
    private void initCouponCache() {
        JSONArray coupons = (JSONArray) cache.get("userCoupons");
        couponCache = coupons.toList(CustomerCoupon.class);
        couponHistoryList = coupons.toList(CustomerCouponHistory.class);
    }

    @Operation(summary = "MyCoupon")
    @GetMapping(value = "/mine")
    public CustomerCoupon queryMyCoupon() {
        Optional<CustomerCoupon> userCouponInfo =
                couponCache.stream().findFirst();
        return userCouponInfo.orElse(new CustomerCoupon().setUserId(getUserId()));
    }

    @Operation(summary = "MyCouponHistory")
    @GetMapping(value = "/myHistory")
    public CustomerCouponHistory queryMyCouponHistory() {
        Optional<CustomerCouponHistory> userCouponInfo =
                couponHistoryList.stream().findFirst();
        return userCouponInfo.orElse(new CustomerCouponHistory().setUserId(getUserId()));
    }
}
