<template>
  <view>
    <view style="height: 42px"></view>
    <spin-loading v-if="loading" />
    <template v-else>
      <coupon-ticket
        v-for="coupon in couponMap[currentName]"
        :key="coupon.id"
        :id="coupon.id"
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
      <coupon-bar
        :discount="couponMap?.discount?.length || 0"
        :cash="couponMap?.cash?.length || 0"
        :expiredSoon="couponMap?.expiredSoon?.length || 0"
        @clickCouponItem="handleClickCouponItem"
        @clickHistory="handleClickHistory"
      />
    </template>
    <coupon-dialog
      v-if="dialogCoupon"
      :coupon="dialogCoupon"
      @swiped="handleDialogSwiped"
      @close="handleDialogClose"
    />
    <view style="height: 100px"></view>
    <page-navbar :current="1" @clickBarItem="redirectToByIndex" />
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import common, { instance } from "@/common";

const loading = ref<boolean>(true);
const currentName = ref<string>("discount");
const dialogCoupon = ref<unknown>(null);
const couponMap = ref<{
  discount: unknown[];
  cash: unknown[];
  expiredSoon: unknown[];
}>({
  discount: [],
  cash: [],
  expiredSoon: [],
});

onLoad(() => {
  instance
    .get({
      url: "/coupon/mine",
    })
    .then((res) => {
      const data = res.data;
      console.log(data.coupons);
      couponMap.value.discount = data.coupons.filter(
        (coupon: any) => !coupon.cashCoupon
      );
      couponMap.value.cash = data.coupons.filter(
        (coupon: any) => coupon.cashCoupon
      );
      couponMap.value.expiredSoon = data.coupons.filter(
        (coupon: any) => coupon.subtitleWarn
      );
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
const handleClickHistory = () => {
  uni.navigateTo({
    url: "../history/history",
  });
};
const handleClickCouponItem = (name: string) => {
  currentName.value = name;
};
const handleClickCoupon = (id: string, coupon: any) => {
  dialogCoupon.value = coupon;
};
const handleDialogSwiped = (id: string) => {
  dialogCoupon.value = null;
  uni.navigateTo({
    url: "../history/history",
  });
};
const handleDialogClose = () => {
  dialogCoupon.value = null;
};
const handleClickInfoLink = (id: string, coupon: any) => {
  console.log(id);
  uni.navigateTo({
    url: `../shop/shop?shopId=${coupon.shopId}`,
  });
};
</script>
