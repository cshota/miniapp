<template>
  <view class="tag-bar-view" :style="`margin-top:${marginTop || 0}px`">
    <view
      v-for="item in listItems"
      :key="item.value"
      class="tag-bar-item"
      :class="{ active: item.value === current }"
      @click="onTagClick(item.value)"
    >
      {{ item.text }}
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";

const props = defineProps<{
  marginTop?: number;
  listItems: { text: string; value: number }[];
}>();

const current = ref<number>(props.listItems[0].value);

const emit = defineEmits<{
  (e: "clickTagItem", value: number): void;
}>();
const onTagClick = (value: number) => {
  current.value = value;
  emit("clickTagItem", value);
};
</script>

<style scoped>
.tag-bar-view {
  padding: 11px 8px;
  background: #ffffff;
  box-shadow: 0px 2px 3.3px 0.3px rgba(0, 0, 0, 0.16);
}
.tag-bar-item {
  display: inline-block;
  margin: 0 7px;
  padding: 3px 0;
  min-width: 57px;
  background: rgba(255, 255, 255, 0.5);
  font-size: 12px;
  font-weight: 500;
  color: #8b8b8b;
  border: 1px solid #efefef;
  text-align: center;
  border-radius: 33px;
}
.tag-bar-item.active {
  background: #ffffff;
  border: 1px solid #bf242a;
  color: #bf242a;
}
</style>
