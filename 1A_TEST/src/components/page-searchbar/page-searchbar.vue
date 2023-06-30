<!--検索バーコンポーネント完成-->
<template>
  <div class="search-bar-container">
    <input
      ref="searchInput"
      type="text"
      @click="focusSearch"
      placeholder="検索"
      style="background-color: white; color: black;"
      class="search-input"
    />
    <button @click="performSearch" class="search-button">検索</button>
  </div>
</template>

<style scoped>
.search-bar-container {
  position:absolute;top:0;
  display: flex;
  display: -webkit-flex;
  -webkit-justigy-content:center;
  align-items: center;
  justify-content: center;
  padding: 0 10%;
}

.search-input {
  flex: 1;
  padding: 10px;
  border-radius: 20px;
}

.search-button {
  margin-left: 10px;
  padding: 1px 5px;
  border-radius: 20px;
}
</style>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  methods: {
    focusSearch() {
      (this.$refs.searchInput as HTMLInputElement).focus();
    },
    performSearch() {
      // 検索の実行
      const searchTerm = (this.$refs.searchInput as HTMLInputElement).value;
      // 検索結果の処理などを行う
      console.log("検索実行:", searchTerm);
    }
  }
});
</script>
