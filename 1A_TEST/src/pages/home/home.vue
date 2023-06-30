<template>
  <!-- ナビゲーションバー -->
  <view style="height:100px"></view>
  <router-link to="./search.vue" active-class="selected-link">災害</router-link>
  <router-view></router-view>
  <router-link to="./plus.vue" active-class="selected-link">防災</router-link>
  <page-navbar />
  <page-searchbar />
</template>

<script setup lang="ts">
import { ref } from "vue";
import PageSearchbar from '../../components/page-searchbar/page-searchbar.vue';
import PageNavbar from '../../components/page-navbar/page-navbar.vue'


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
