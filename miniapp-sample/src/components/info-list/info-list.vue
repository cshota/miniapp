<template>
  <view>
    <view v-if="title" class="info-list-title">{{ title }}</view>
    <view v-if="list.length" class="info-list">
      <view v-for="(item, index) in list" class="info-list-item" :key="index">
        <view
          class="info-list-item-label"
          :style="`width:${labelWidth || 49}px`"
        >
          {{ item.label }}
        </view>
        <view class="info-list-item-label-after">:</view>
        <view class="info-list-item-content">{{ item.content }}</view>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import "vue";

defineProps<{
  labelWidth?: number;
  title?: string;
  list?: {
    label: string;
    content: string;
  }[];
}>();
</script>

<style scoped>
.info-list-title {
  margin-top: 12px;
  padding: 0 12px;
  font-size: 20px;
  font-weight: 800;
  line-height: 28px;
  color: #5d5d5d;
}
.info-list {
  margin: 13px 21px 0 0;
  padding: 0 12px 18px;
}
.info-list-item {
  display: flex;
  margin-top: 4px;
}
.info-list-item-label {
  font-size: 17px;
  font-weight: 800;
  line-height: 34px;
  color: #5d5d5d;
  text-align: justify;
  text-align-last: justify;
}
.info-list-item-label-after {
  width: 27px;
  font-size: 17px;
  font-weight: 800;
  line-height: 34px;
  color: #5d5d5d;
}
.info-list-item-content {
  flex: 1;
  font-size: 17px;
  font-weight: 400;
  line-height: 34px;
  color: #5d5d5d;
  word-break: break-all;
}
</style>
