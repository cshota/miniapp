<template>
<div>
    <label for="genre">ジャンルを選択してください</label>
    <select name="genre" v-model="selectedGenre">
    <option>選択してください</option>
    <option value="drink">飲料</option>
    </select>
    <div v-if="selectedGenre !== ''">
    <button @click="link2">次へ</button>
    </div>
</div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
name: "GenreSelect",
data() {
    return {
    selectedGenre: "",
    };
},
methods: {
    link2() {
    // ここで遷移先のコンポーネントにデータを渡す処理を追加できます
    uni.navigateTo({
        url:"./plus",
    })
    this.$emit("genre-selected", this.selectedGenre);
    },
},
});
</script>
