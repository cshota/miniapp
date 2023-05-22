<template>
  <page-searchbar
    :city="city"
    @search="reloadCoupon"
    @clickCity="handleCityChoose"
  />
  <spin-loading v-if="swiperLoading" />
  <lite-swiper
    v-else
    :imgUrls="rollingPictures.map((pic) => pic.leftImgUrl)"
    @clickImgItem="handleClickSwiper"
  />
  <tag-bar :listItems="listItems" @clickTagItem="handleClickTagItem" />
  <spin-loading v-if="loading" />
  <template v-else>
    <mall-ticket
      v-for="(coupon, index) in coupons"
      :key="index"
      :leftImgUrl="coupon.leftImgUrl"
      :title="coupon.title"
      :hot="coupon.hot"
      :distance="coupon.distance"
      :imgUrl="coupon.imgUrl"
      @clickTicket="handleClickTicket(coupon.shopId)"
    />
  </template>
  <view style="height: 100px"></view>
  <page-navbar :current="0" @clickBarItem="redirectToByIndex" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import common, { instanceWithoutAuth } from "@/common";

const swiperLoading = ref<boolean>(true);
const loading = ref<boolean>(true);
const city = ref<string>("");
const rollingPictures = ref<{ shopId: number; leftImgUrl: string }[]>([]);
const coupons = ref<unknown[]>([]);

const listItems = [
  {
    text: "お勧め",
    value: 0,
  },
  {
    text: "周辺",
    value: 1,
  },
];
onLoad(() => {
  instanceWithoutAuth
    .get({
      url: "/index/rollingPictures",
    })
    .then((res) => {
      const data = res.data;
      console.log(data.rollingPictures);
      rollingPictures.value = data.rollingPictures;
    })
    .catch(common.defaultRequestCatch)
    .finally(() => {
      swiperLoading.value = false;
    });
  instanceWithoutAuth
    .get({
      url: "/index/coupons",
    })
    .then((res) => {
      const data = res.data;
      console.log(data.coupons);
      coupons.value = data.coupons;
    })
    .catch(common.defaultRequestCatch)
    .finally(() => {
      loading.value = false;
    });
  city.value = uni.getStorageSync("activeAreaPrefecture");
});

const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};
const handleCityChoose = () => {
  uni.navigateTo({
    url: "../home/area",
  });
};
const handleClickSwiper = (index: number) => {
  const pic = rollingPictures.value[index];
  // #ifndef H5
  if (pic) {
    uni.navigateTo({
      url: `../shop/shop?shopId=${pic.shopId}`,
    });
  }
  // #endif
};
const handleClickTicket = (shopId: number) => {
  uni.navigateTo({
    url: `../shop/shop?shopId=${shopId}`,
  });
};
const handleClickTagItem = (index: number) => {
  common.sendLog("CLICK_COUPON_TAB_EVENT", listItems[index].text!);
  reloadCoupon();
};
const reloadCoupon = () => {
  instanceWithoutAuth
    .get({
      url: "/index/coupons",
    })
    .then((res) => {
      const data = res.data;
      console.log(data.coupons);
      // if (searchValue) {
      //   coupons.value = data.coupons.filter(
      //     (coupon) => coupon.title.indexOf(searchValue) !== -1
      //   );
      // } else {
      coupons.value = data.coupons;
      // }
    })
    .catch(common.defaultRequestCatch);
};
</script>
