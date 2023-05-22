<template>
  <view>
    <view class="notice-card-view">
      <image mode="aspectFill" class="notice-card-left" :src="picUrl" />
      <view class="notice-card-content-view">
        <view class="notice-card-title">{{ title }}</view>
        <view class="notice-card-space"></view>
        <view class="notice-card-content">
          <view class="notice-card-content-bottom">
            <view class="notice-card-date">
              {{ date }}
            </view>
            <view class="notice-card-button" @tap="handleNoticeButtonClick">
              クリックして見る
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import "vue";

defineProps<{
  picUrl: string;
  date: string;
  title: string;
}>();

const emit = defineEmits<{
  (e: "clickNoticeButton", index: number): void;
}>();
const handleNoticeButtonClick = (index: number) =>
  emit("clickNoticeButton", index);
</script>

<style scoped>
.notice-card-view {
  display: flex;
  margin-bottom: 10px;
  padding: 8px 11px 8px 10px;
  background-color: #fff;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.03);
}
.notice-card-left {
  width: 153px;
  height: 108px;
  border-radius: 9px;
  align-self: center;
}
.notice-card-content-view {
  display: flex;
  flex-direction: column;
  -webkit-flex: 1;
  flex: 1;
  padding-left: 17px;
  -webkit-justify-content: space-between;
  justify-content: space-between;
}
.notice-card-space {
  flex: auto;
}
.notice-card-title {
  padding-top: 3px;
  font-size: 15px;
  font-weight: bold;
  line-height: 21px;
  color: #373737;
  overflow: hidden;
}
.notice-card-content-bottom {
  display: flex;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  align-items: center;
}
.notice-card-date {
  font-size: 12px;
  font-weight: 400;
  color: #d4d4d4;
  white-space: nowrap;
}
.notice-card-button {
  padding: 2px 12px 3px 12px;
  background: #ffefe5;
  font-size: 12px;
  font-weight: 500;
  color: #bf242a;
  border-radius: 4px;
  white-space: nowrap;
}
</style>
