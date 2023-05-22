<template>
  <prefecture-search
    :activePrefecture="activePrefecture"
    :collectPrefectures="collectPrefectures"
    @prefectureChoose="handlePrefectureChoose"
  />
  <view style="height: 100px"></view>
  <page-navbar :current="2" @clickBarItem="redirectToByIndex" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";

const activePrefecture = ref<string>("");
const collectPrefectures = ref<string[]>([]);

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
  activePrefecture.value = uni.getStorageSync("activePrefecture");
  collectPrefectures.value = JSON.parse(
    uni.getStorageSync("collectPrefectures") || "[]"
  );
});

const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};
const handlePrefectureChoose = (
  newActivePrefecture: string,
  newCollectPrefectures: string[]
) => {
  uni.setStorageSync("activePrefecture", newActivePrefecture);
  uni.setStorageSync("collectPrefectures", JSON.stringify(newCollectPrefectures));
  activePrefecture.value = newActivePrefecture;
  collectPrefectures.value = newCollectPrefectures;
  uni.showToast({
    title: "設定完了",
    icon: "success",
    duration: 1000,
  });
};
</script>

<style scoped>
</style>
