<template>
  <view>
    <view class="page-navbar-view">
      <view
        v-for="(item, index) in list"
        class="page-navbar-item"
        :key="index"
        @tap="onTabbarClick(index)"
      >
        <view class="page-navbar-item__icon">
          <image
            v-if="index === currentIndex"
            class="page-navbar-item__image"
            :src="item.selectedIconPath"
          />
          <image v-else class="page-navbar-item__image" :src="item.iconPath" />
        </view>
        <text class="page-navbar-item__text">{{ item.text }}</text>
      </view>
    </view>
  </view>
</template>


<script setup lang="ts">
import { ref, watch } from "vue";
import navs from "./navs.json";

const props = defineProps<{
  items?: {
    pagePath: string;
    selectedIconPath: string;
    iconPath: string;
    text: string;
  }[];
  current: number;
}>();

const list = props.items || navs;

const currentIndex = ref<number>(props.current || 0);

const emit = defineEmits<{
  (e: "clickBarItem", path: string, index: number): void;
}>();

const onTabbarClick = (index: number) => {
  if (currentIndex.value !== index) {
    currentIndex.value = index;
    emit("clickBarItem", list[index].pagePath, index);
  }
};

watch(
  () => props.current,
  (val) => {
    if (val !== currentIndex.value) {
      currentIndex.value = val;
    }
  }
);
</script>

<style scoped>
.page-navbar-view {
  position: fixed;
  left: 0;
  right: 0;
  height: 60px;
  height: calc(60px + env(safe-area-inset-bottom));
  height: calc(60px + constant(safe-area-inset-bottom));
  bottom: 0;
  padding-bottom: 0;
  padding-bottom: env(safe-area-inset-bottom);
  padding-bottom: constant(safe-area-inset-bottom);
  background: #fff;
  box-shadow: 0px -0.6px 3.3px 0.3px rgba(0, 0, 0, 0.1);
}
.page-navbar-view {
  /* #ifndef APP-NVUE */
  display: flex;
  box-sizing: border-box;
  /* #endif */
  flex-direction: row;
  height: 60px;
  height: calc(60px + env(safe-area-inset-bottom));
  height: calc(60px + constant(safe-area-inset-bottom));
  overflow: hidden;
  /* #ifdef H5 */
  cursor: pointer;
  /* #endif */
}
.page-navbar-item {
  /* #ifndef APP-NVUE */
  display: inline-flex;
  box-sizing: border-box;
  /* #endif */
  position: relative;
  flex: 1;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.page-navbar-item__icon {
  display: inline-block;
  position: relative;
  margin-top: 9px;
  width: 38px;
  height: 38px;
}
.page-navbar-item__image {
  width: 38px;
  height: 38px;
}
.page-navbar-item__text {
  margin-bottom: 6px;
  flex: 1;
  font-size: 8px;
  line-height: normal;
  text-align: center;
}
</style>
