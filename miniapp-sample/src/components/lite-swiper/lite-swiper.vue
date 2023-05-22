<template>
  <view>
    <swiper class="lite-swiper" indicator-dots="true" autoplay>
      <swiper-item
        class="lite-swiper-item"
        v-for="(imgUrl, index) in imgUrls"
        :key="index"
      >
        <image
          class="lite-swiper-image"
          :src="imgUrl"
          @tap="onImgClick(index)"
        />
      </swiper-item>
    </swiper>
  </view>
</template>

<script setup lang="ts">
import "vue";

defineProps<{
  imgUrls: string[];
}>();

const emit = defineEmits<{
  (e: "clickImgItem", index: number): void;
}>();
const onImgClick = (index: number) => emit("clickImgItem", index);
</script>

<style scoped>
.lite-swiper {
  height: 382rpx;
  width: 100%;
  overflow: hidden;
}
.lite-swiper ::v-deep .uni-swiper-wrapper {
  overflow: inherit;
}
.lite-swiper-item {
  text-align: center;
  box-shadow: 0px 1.5px 3px rgba(0, 0, 0, 0.03);
}
.lite-swiper-image {
  width: 100%;
  height: 100%;
}
.lite-swiper ::v-deep .uni-swiper-dot {
  background-color: rgba(191, 36, 42, 0.3);
}
.lite-swiper ::v-deep .uni-swiper-dot-active {
  background-color: #bf242a;
}
</style>
