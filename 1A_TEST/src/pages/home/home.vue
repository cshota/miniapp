<template>
  <!-- ナビゲーションバー -->
  <page-navbar :current="0" @clickBarItem = "redirectToByIndex"/>

  <router-link to="./search.vue" active-class="selected-link">飲料</router-link>
  <router-view></router-view>
  <router-link to="./search.vue" active-class="selected-link">食料</router-link>
  <router-link to="./search.vue" active-class="selected-link">あ</router-link>
  <router-link to="./search.vue" active-class="selected-link">い</router-link>
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
  margin-top: 200rpx;
  margin-left: 0;
  margin-right: 0;
  margin-bottom: 50rpx;
}
</style>
