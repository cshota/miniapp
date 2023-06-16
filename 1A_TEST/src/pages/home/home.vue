<template>
<<<<<<< Updated upstream
  <view class="content">
    <image class="logo" src="/static/soslogo.png" />
    <hello-world :title="title" />
  </view>
=======
  
  <page-navbar :current="0" @clickBarItem="redirectToByIndex" />
  
  
  
>>>>>>> Stashed changes
</template>

<script setup lang="ts">
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
const title = ref("sosアプリ");

const swiperLoading = ref<boolean>(false);
const loading = ref<boolean>(false);

const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};

</script>

<style>
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.soslogo {
  height: 270rpx;
  width: 270rpx;
  margin-top: 100rpx;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 50rpx;
}
</style>

