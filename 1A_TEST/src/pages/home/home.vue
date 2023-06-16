<template>
  <!-- ナビゲーションバー -->
  <page-navbar :current="0" @clickBarItem = "redirectToByIndex"/>

</template>

<script setup lang="ts">
import { ref } from "vue";
const title = ref("Hello Vue");

const redirectToByIndex = () => {
  alert();
}

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
  margin-top: 250rpx;
  margin-left: 0;
  margin-right: 0;
  margin-bottom: 50rpx;
}
</style>
