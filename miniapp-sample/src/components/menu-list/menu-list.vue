<template>
  <view
    v-for="(item, index) in list"
    :key="index"
    class="menu-list-item"
    @click="handleListItemClick(item.pagePath, item)"
  >
    <view>
      <view class="menu-list-item-icon-view">
        <image
          class="menu-list-item-icon"
          :style="`width:${item.iconWidth}px;height:${item.iconHeight}px`"
          :src="item.iconPath"
        />
      </view>
      <view class="menu-list-item-text">{{ item.text }}</view>
      <view v-if="item.notice" class="menu-list-item-badge">{{
        item.notice
      }}</view>
    </view>
    <image
      mode="aspectFit"
      class="menu-list-item-arrow"
      src="/static/menuicon_right.png"
    />
  </view>
</template>

<script setup lang="ts">
import "vue";

type ListItem = {
  pagePath: string;
  iconWidth: number;
  iconHeight: number;
  iconPath: string;
  text: string;
  notice: string;
};

defineProps<{
  list: ListItem[];
}>();

const emit = defineEmits<{
  (e: "listItemClick", url: string, item: ListItem[]): void;
}>();
const handleListItemClick = (url: string, item: ListItem[]) =>
  emit("listItemClick", url, item);
</script>

<style scoped>
.menu-list-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 7px;
  padding: 17px 16px;
  background: #fff;
}
.menu-list-item-icon-view {
  display: inline-block;
  width: 24px;
  text-align: right;
}
.menu-list-item-icon {
  width: 100%;
  height: 15px;
  display: inline-block;
  vertical-align: middle;
}
.menu-list-item-text {
  display: inline-block;
  margin-left: 9px;
  font-size: 15px;
  font-weight: 400;
  color: #292929;
  vertical-align: middle;
}
.menu-list-item-badge {
  display: inline-block;
  margin-left: 11px;
  padding: 0 8px;
  background: #df3232;
  font-size: 10px;
  font-weight: 400;
  line-height: 12px;
  color: #ffffff;
  border-radius: 17px;
  vertical-align: middle;
}
.menu-list-item-arrow {
  display: inline-block;
  width: 9px;
  height: 17px;
  vertical-align: middle;
}
</style>
