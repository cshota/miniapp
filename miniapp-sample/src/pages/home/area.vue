<template>
  <prefecture-search
    :activePrefecture="activePrefecture"
    noSearch
    noActive
    withActiveMark
    :collectPrefectures="['']"
    @prefectureChoose="handlePrefectureChoose"
  />
  <view style="height: 100px"></view>
  <page-navbar :current="0" @clickBarItem="redirectToByIndex" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";

const activePrefecture = ref<string>("");

onLoad(() => {
  activePrefecture.value = uni.getStorageSync("activePrefecture");
});

const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};
const handlePrefectureChoose = (activePrefecture: string) => {
  uni.setStorageSync("activeAreaPrefecture", activePrefecture);
  uni.reLaunch({
    url: "/pages/home/home",
  });
};
</script>
