<template>
  <view class="mine-view">
    <image
      mode="widthFix"
      class="header-image"
      src="/static/header_image.png"
    />
    <menu-list :list="list" @listItemClick="handleListItemClick" />
    <view style="height: 100px"></view>
    <page-navbar :current="2" @clickBarItem="redirectToByIndex" />
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import common, { instance } from "@/common";

const messageList = ref<unknown[]>([]);

const list = [
  {
    pagePath: "../coupon/coupon",
    iconPath: "/static/menuicon01.png",
    iconWidth: 21,
    iconHeight: 14,
    text: "獲得済みクーポン",
  },
  {
    pagePath: "../mine/favorite",
    iconPath: "/static/menuicon02.png",
    iconWidth: 19,
    iconHeight: 15,
    text: "お気に入り",
  },
  {
    pagePath: "../mine/notice",
    iconPath: "/static/menuicon03.png",
    iconWidth: 18,
    iconHeight: 15,
    text: "お知らせ",
    notice: 6,
  },
  {
    pagePath: "../mine/address",
    iconPath: "/static/menuicon04.png",
    iconWidth: 18,
    iconHeight: 17,
    text: "居住地設定",
  },
];

onLoad(() => {
  instance
    .get({
      url: "/message/list",
    })
    .then((res) => {
      messageList.value = res.data?.messageList || [];
      console.log(messageList.value.length);
    })
    .catch(common.defaultRequestCatch);
});

const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};
const handleListItemClick = (url: string) => {
  uni.navigateTo({
    url,
  });
};
</script>

<style scoped>
.mine-view {
  font-size: 0;
}
.header-image {
  width: 100%;
}
</style>
