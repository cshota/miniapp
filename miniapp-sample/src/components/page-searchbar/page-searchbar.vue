<template>
  <view class="page-searchbar-view">
    <view class="page-searchbar-search-view">
      <view class="page-searchbar-search-city" @click="onCityClick">{{
        city || "地域"
      }}</view>
      <image
        class="page-searchbar-search-arrow"
        src="/static/search_arrow.png"
        @click="onCityClick"
      />
      <input
        class="page-searchbar-search-input"
        v-model="searchText"
        confirm-type="search"
        placeholder="お店、キーワード"
        placeholder-style="font-size:13px;font-weight:500;color:#bbb"
        @confirm="onSearch"
      />
      <image
        class="page-searchbar-search-icon"
        src="/static/search_icon.png"
        @click="onSearch"
      />
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";

const searchText = ref<string>("");

defineProps<{
  city: string;
}>();

const emit = defineEmits<{
  (e: "search", value: string): void;
  (e: "clickCity"): void;
}>();
const onSearch = () => emit("search", searchText.value);
const onCityClick = () => emit("clickCity");
</script>

<style scoped>
.page-searchbar-view {
  padding: 7px 23px 7px;
  background-color: #bf242a;
}
.page-searchbar-search-view {
  display: flex;
  position: relative;
  font-size: 0;
  border-radius: 5px;
  background: #fff;
  border: 1px solid #707070;
  border-radius: 18px;
}
.page-searchbar-search-city {
  flex: none;
  min-width: 57px;
  padding: 9px 0 7px 19px;
  font-size: 13px;
  font-weight: 500;
  color: #8b8b8b;
}
.page-searchbar-search-arrow {
  flex: none;
  padding: 14px 4px 11px 0;
  width: 10px;
  height: 9px;
}
.page-searchbar-search-input {
  flex: 1;
  margin: 7px 0 7px 3px;
  padding: 1px 0 1px 7px;
  font-size: 13px;
  font-weight: 500;
  color: #373737;
  border-left: 1px solid #d4d4d4;
}
.page-searchbar-search-icon {
  flex: none;
  padding: 7px 11px 7px 3px;
  width: 20px;
  height: 20px;
}
</style>
