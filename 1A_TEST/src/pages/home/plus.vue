<template>
  <div>
    <label for="name">名前:</label>
    <input type="text" id="name" v-model="name" />

    <label for="message">投稿内容:</label>
    <textarea id="message" v-model="message"></textarea>

    <button @click="showText">送信</button>

    <div>
      <h2>入力結果:</h2>
      <p>名前: {{ name }}</p>
      <p>投稿内容: {{ message }}</p>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  data() {
    return {
      name: "",
      message: ""
    };
  },
  methods: {
    showText() {
      alert(`名前: ${this.name}\n投稿内容: ${this.message}`);
    }
  }
});
</script>