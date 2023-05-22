<template>
  <view class="mall-ticket-view" @tap="handleTicketClick">
    <image class="mall-ticket-left-image" :src="leftImgUrl" />
    <view class="mall-ticket-content-view">
      <view class="mall-ticket-title">{{ title }}</view>
      <view class="mall-ticket-content-bottom">
        <view class="mall-ticket-hot-view">
          <image
            v-for="index in hot || 0"
            :key="index"
            class="mall-ticket-hot-icon-active"
            src="/static/ticket_hot_icon_active.png"
          />
          <image
            v-for="index in 5 - (hot || 0)"
            :key="index + hot"
            class="mall-ticket-hot-icon"
            src="/static/ticket_hot_icon.png"
          />
        </view>
        <view class="mall-ticket-location-view">
          <image
            v-if="distance"
            class="mall-ticket-location-icon"
            src="/static/ticket_location_icon.png"
          />{{ distance }}
        </view>
      </view>
    </view>
    <image class="mall-ticket-right-image" :src="imgUrl" />
  </view>
</template>

<script setup lang="ts">
import "vue";

defineProps<{
  leftImgUrl: string;
  hot: number;
  title: string;
  distance?: string;
  imgUrl: string;
}>();

const emit = defineEmits<{
  (e: "clickTicket"): void;
}>();
const handleTicketClick = () => emit("clickTicket");
</script>

<style scoped>
.mall-ticket-view {
  display: flex;
  height: 72px;
  margin: 11px 7px 0;
  box-shadow: 0px 1px 2px 0.3px rgba(0, 0, 0, 0.1);
  border-radius: 7px;
}
.mall-ticket-left-image {
  flex: none;
  width: 67px;
  height: 72px;
}
.mall-ticket-content-view {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 14px 3px 6px 8px;
  background: #fff;
}
.mall-ticket-title {
  flex: 1;
  font-size: 10px;
  font-weight: 400;
  line-height: 15px;
  color: #373737;
  overflow: hidden;
}
.mall-ticket-content-bottom {
  flex: none;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}
.mall-ticket-hot-icon-active {
  margin-right: 3px;
  width: 9px;
  height: 11px;
}
.mall-ticket-hot-icon {
  margin-right: 3px;
  width: 9px;
  height: 11px;
}
.mall-ticket-location-view {
  font-size: 10px;
  font-weight: 400;
  line-height: 15px;
  color: #d4d4d4;
  white-space: nowrap;
}
.mall-ticket-location-icon {
  width: 8px;
  height: 11px;
}
.mall-ticket-right-image {
  flex: none;
  width: 94px;
  height: 72px;
}
</style>
