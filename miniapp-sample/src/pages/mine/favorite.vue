<template>
  <view class="demo-home">
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
        @clickTicket="handleClickTicket"
      />
    </template>
    <view style="height: 100px"></view>
    <page-navbar :current="2" @clickBarItem="redirectToByIndex" />
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import common, { instanceWithoutAuth } from "@/common";

const loading = ref<boolean>(true);
const coupons = ref<unknown[]>([]);

onLoad(() => {
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
});

const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};
const handleClickTicket = () => {
  uni.navigateTo({
    url: `../shop/shop?shopId=1`,
  });
};
</script>

<style scoped>
.header-image {
  width: 100%;
}
</style>
