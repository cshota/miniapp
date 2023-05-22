<template>
  <spin-loading v-if="loading" />
  <template v-else>
    <store-card
      :title="title"
      :hot="hot"
      :subtitle="subtitle"
      :imgUrl="imgUrl"
      :location="contact.address"
      :distance="typeof distance === 'string' ? distance : distance + 'km'"
      :phone="contact.phone"
      @clickLocation="openMap"
      @clickPhone="handlePhoneCall"
    />
    <tag-bar
      :marginTop="10"
      :listItems="listItems"
      @clickTagItem="handleClickTagItem"
    />
    <template v-if="currentIndex === 0">
      <detail-card v-for="(product, index) in products" :key="index">
        <rich-text :nodes="product"></rich-text>
      </detail-card>
    </template>
    <template v-else-if="currentIndex === 1">
      <coupon-ticket
        v-for="coupon in coupons"
        :key="coupon.id"
        :id="coupon.id"
        :type="coupon.type"
        :cashCoupon="coupon.cashCoupon"
        :leftImgUrl="coupon.leftImgUrl"
        :title="coupon.title"
        :subtitle="coupon.subtitle"
        :subtitleWarn="coupon.subtitleWarn"
        :hasDeadline="coupon.hasDeadline"
        :infos="coupon.infos"
        @buttonClick="handleClickCoupon"
      />
    </template>
    <template v-else>
      <detail-card v-for="(shopDetail, index) in shopDetails" :key="index">
        <rich-text :nodes="shopDetail"></rich-text>
      </detail-card>
      <detail-card>
        <!-- <image
          class="shop-info-image"
          mode="widthFix"
          src="/static/staticmap.png"
          @tap="openMap"
        /> -->
        <image
          class="shop-info-image"
          mode="widthFix"
          :src="`https://maps.googleapis.com/maps/api/staticmap?center=${
            contact.location[1]
          },${
            contact.location[0]
          }&size=${400}x${300}&markers=color:red%7C${
            contact.location[1]
          },${
            contact.location[0]
          }&key=${mapKey}`"
          @tap="openMap"
        />
        <info-list
          :labelWidth="104"
          :list="[
            { label: '住所', content: contact.address },
            { label: 'お問い合わせ', content: contact.phone },
            { label: 'ホームページ', content: contact.url },
          ]"
        />
      </detail-card>
    </template>
  </template>
  <view style="height: 100px"></view>
  <page-navbar :current="0" @clickBarItem="redirectToByIndex" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import mini from "@/mini";
import common, { instanceWithoutAuth } from "@/common";

const mapKey = import.meta.env.VITE_APP_MAP_KEY
const loading = ref<boolean>(true);
const id = ref<number>(-1);
const currentIndex = ref<number>(0);
const title = ref<string>("Loading...");
const hot = ref<number>(3);
const imgUrl = ref<string>("");
const contact = ref<{
  location?: number[];
}>({});
const distance = ref<string>("...");
const subtitle = ref<string>("おまかせコース 5,500円");
const products = ref<string[]>([]);
const shopDetails = ref<string[]>([]);
const coupons = ref<unknown[]>([]);

const listItems = [
  {
    text: "イベント",
    value: 0,
  },
  {
    text: "クーポン",
    value: 1,
  },
  {
    text: "店舗情報",
    value: 2,
  },
];

onLoad(({ shopId }) => {
  instanceWithoutAuth
    .get({
      url: `/shop/${shopId}`,
    })
    .then((res) => {
      const data = res.data;
      id.value = data.id;
      products.value = data.products;
      coupons.value = data.coupons;
      title.value = data.title;
      hot.value = +data.hot;
      subtitle.value = data.subtitle;
      imgUrl.value = data.imgUrl;
      contact.value = data.contact;
      distance.value = data.distance;
      shopDetails.value = data.shop;
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
const handleClickTagItem = (index: number) => {
  currentIndex.value = index;
};
const handleClickCoupon = (id: string) => {
  console.log(id);
};
const handlePhoneCall = (phone: string) => {
  console.log(phone);
  uni.makePhoneCall({
    phoneNumber: phone,
    success: () => {
      console.log("call success");
    },
  });
};
const openMap = () => {
  if (contact.value.location) {
    mini.openMap(
      encodeURIComponent(title.value),
      contact.value.location[0],
      contact.value.location[1]
    );
  }
};
</script>

<style scoped>
.shop-info-image {
  padding: 2px 0 0;
  width: 100%;
}
</style>
