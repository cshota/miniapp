package jp.co.scsk.miniapp.sample.api.controller;

import cn.hutool.json.JSONArray;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.co.scsk.miniapp.sample.api.entity.vo.Shop;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "ShopController")
@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {

    /**
     * mock data
     */
    @Resource
    Map<String, Object> cache;

    List<Shop> shopCache;

    @PostConstruct
    private void initShopCache() {
        JSONArray shop = (JSONArray) cache.get("shop");
        shopCache = shop.toList(Shop.class);
    }

    @Operation(summary = "ShopDetail")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Shop queryShop(@PathVariable int id) {
        Optional<Shop> obj = shopCache.stream().filter(shop -> shop.getId() == id).findFirst();
        return obj.orElse(new Shop());
    }


}
