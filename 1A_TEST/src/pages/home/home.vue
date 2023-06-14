<template>
  <view class="content">
    <image class="logo" src="/static/soslogo.png" />
    <hello-world :title="title" />
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";
const title = ref("Hello Vue");
</script>

<style>
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.logo {
  height: 290rpx;
  width: 290rpx;
  margin-top: 200rpx;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 50rpx;
}
</style>
