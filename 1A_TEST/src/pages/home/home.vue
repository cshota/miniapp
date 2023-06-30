<template>
  <div class="container">
    <!-- ナビゲーションバー -->
    <div class="navbar">
      <div class="navbar-link">
        <router-link to="./search.vue" active-class="selected-link">災害</router-link>
      </div>
      <div class="divider"></div>
      <div class="navbar-link">
        <router-link to="./plus.vue" active-class="selected-link">防災</router-link>
      </div>
    </div>
    <router-view></router-view>
    <page-navbar />
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import PageNavbar from '../../components/page-navbar/page-navbar.vue'

const redirectToByIndex = () => {
  alert();
}

</script>

<style>
.container {
  padding-top: 0;
  padding-bottom: 0;
}

.navbar {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f2f2f2;
  padding: 10px;
}

.navbar-link {
  display: flex;
  align-items: center;
  margin: 0 100px;
}

.navbar-link a {
  text-decoration: none;
  color: #333;
  padding: 5px;
}

.divider {
  width: 1px;
  height: 100%;
  background-color: #333;
  margin: 0 100px;
}

.selected-link {
  font-weight: bold;
}

</style>