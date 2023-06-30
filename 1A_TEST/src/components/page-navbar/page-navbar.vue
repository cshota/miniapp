<template>
  <nav class="navigation-bar">
    <ul>
      <li v-for="(item, index) in navigationItems" :key="index" @click="navigate(item.path)">
        {{ item.label }}
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  data() {
    return {
      navigationItems: [
        { label: 'home', path: '/' },
        { label: 'SOS', path: '../../pages/home/plus.vue' },
        { label: 'search', path: '../../pages/home/search.vue' }
      ]
    };
  },
  methods: {
    navigate(path: string) {
      // ページ遷移の処理を行う
      // 例えば、Vue Router を使用してページを遷移させる場合は以下のようにします
      // this.$router.push(path);
      this.$router.push("../../pages/home/home.vue")
      console.log('Navigate to:', path);
      

    }
  }
});
</script>


<style scoped>
.navigation-bar {
  background-color: #f42828;
  padding: 10px;
  position: fixed;
  width: 100%;
  bottom: 0px;
  font-size: 10;
  opacity: 0.9;
  z-index: 99;
}

.navigation-bar ul {
  list-style-type: none;
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin: 10;
  padding: 0;
}

.navigation-bar li {
  cursor: pointer;
  font-weight: bold;
}
</style>
