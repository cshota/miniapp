<template>
  <view>
    <spin-loading v-if="loading" />
    <template v-else>
      <coupon-ticket
        v-for="coupon in historyCoupons"
        :key="coupon.id"
        :id="typeof coupon.id === 'number' ? '0000' + coupon.id : coupon.id"
        :shopId="coupon.shopId"
        :type="coupon.type"
        :cashCoupon="coupon.cashCoupon"
        :leftImgUrl="coupon.leftImgUrl"
        :title="coupon.title"
        :subtitle="coupon.subtitle"
        :subtitleWarn="coupon.subtitleWarn"
        :hasDeadline="coupon.hasDeadline"
        :infos="coupon.infos"
        @buttonClick="handleClickCoupon"
        @infoLinkClick="handleClickInfoLink"
      />
    </template>
    <view style="height: 100px"></view>
    <page-navbar :current="1" @clickBarItem="redirectToByIndex" />
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import common, { instance } from "@/common";

const loading = ref<boolean>(true);
const historyCoupons = ref<unknown[]>([]);

onLoad(() => {
  instance
    .get({
      url: "/coupon/myHistory",
    })
    .then((res) => {
      const data = res.data;
      console.log(data.historyCoupons);
      historyCoupons.value = data.historyCoupons;
    })
    .catch(common.defaultRequestCatch)
    .finally(() => {
      loading.value = false;
    });
});

const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};
const handleClickCoupon = (id: string, coupon: any) => {
  console.log(id);
  uni.navigateTo({
    url: `../shop/shop?shopId=${coupon.shopId}`,
  });
};
const handleClickInfoLink = (id: string, coupon: any) => {
  console.log(id);
  uni.navigateTo({
    url: `../shop/shop?shopId=${coupon.shopId}`,
  });
};
</script>
