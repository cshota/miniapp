<template>
  <view class="store-card-view">
    <view class="store-card-info-view">
      <image class="store-card-info-image" :src="imgUrl" />
      <view class="store-card-info">
        <view class="store-card-info-title">{{ title }}</view>
        <view class="store-card-info-sub">
          <view class="store-card-info-hot-view">
            <image
              v-for="index in hot"
              :key="index"
              class="store-card-info-hot-active"
              src="/static/big_hot_icon_active.png"
            />
            <image
              v-for="index in 5 - hot"
              :key="index + hot"
              class="store-card-info-hot"
              src="/static/big_hot_icon.png"
            />
          </view>
          <view class="store-card-info-subtitle">{{ subtitle }}</view>
        </view>
      </view>
      <image
        class="store-card-collect"
        :class="{ active: collected }"
        src="/static/shop_collect_icon.png"
        @tap="collected = !collected"
      />
    </view>
    <view class="store-card-more">
      <image
        class="store-card-icon-location"
        src="/static/big_location_icon.png"
        @tap="handleLocationClick"
      />
      <view class="store-card-more-content" @tap="handleLocationClick">
        <view class="store-card-location">{{ location }}</view>
        <view class="store-card-distance">距離 {{ distance }}</view>
      </view>
      <image
        class="store-card-icon-phone"
        src="/static/big_phone_icon.png"
        @tap="handlePhoneClick(phone)"
      />
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";

const props = defineProps<{
  title: string;
  hot: number;
  subtitle: string;
  imgUrl: string;
  location: string;
  distance: string;
  phone?: string;
}>();

const collected = ref<boolean>(false);
const currentTag = ref<number>(1);

const emit = defineEmits<{
  (e: "clickPhone", phone: string): void;
  (e: "clickLocation"): void;
}>();
const handlePhoneClick = (phone: string) => emit("clickPhone", phone);
const handleLocationClick = () => emit("clickLocation");
</script>

<style scoped>
.store-card-view {
  margin: 8px 6px 0 7px;
  padding: 7px 12px 7px 7px;
  background: #ffffff;
  box-shadow: 0px 1px 2px 0.3px rgba(0, 0, 0, 0.14);
  font-size: 0;
  border-radius: 7px;
}
.store-card-info-view {
  display: flex;
}
.store-card-info-image {
  flex: none;
  margin-right: 7px;
  width: 144px;
  height: 92px;
  box-shadow: 0px 1px 2px 0.3px rgba(0, 0, 0, 0.06);
  border-radius: 3px;
}
.store-card-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  padding-bottom: 2px;
  justify-content: space-between;
}
.store-card-info-title {
  font-size: 15px;
  font-weight: 800;
  line-height: 19px;
  color: #373737;
}
.store-card-info-hot-view {
  margin: 3px 0 8px;
}
.store-card-info-hot-active {
  margin-right: 4px;
  width: 10px;
  height: 13px;
}
.store-card-info-hot {
  margin-right: 4px;
  width: 10px;
  height: 13px;
}
.store-card-info-subtitle {
  font-size: 12px;
  font-weight: 400;
  color: #717171;
}
.store-card-collect {
  flex: none;
  width: 19px;
  height: 18px;
  filter: grayscale(1);
}
.store-card-collect.active {
  filter: grayscale(0);
}
.store-card-more {
  display: flex;
  margin-top: 15px;
  font-size: 0;
}
.store-card-icon-location {
  flex: none;
  margin-right: 5px;
  width: 16px;
  height: 21px;
}
.store-card-more-content {
  flex: 1;
}
.store-card-location {
  font-size: 12px;
  font-weight: 400;
  line-height: 21px;
  color: #373737;
}
.store-card-distance {
  flex: none;
  margin-top: 2px;
  font-size: 12px;
  font-weight: 400;
  line-height: 18px;
  color: #d4d4d4;
}
.store-card-icon-phone {
  width: 21px;
  height: 21px;
}
</style>
