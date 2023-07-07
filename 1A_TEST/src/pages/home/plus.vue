<template>
  <form @submit.prevent="handleSubmit">
    <label for="type">投稿タイプ:</label>
    <select id="type" v-model="type">
      <option value="Post">災害</option>
      <option value="Comment">防災</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="genre">ジャンル:</label>
    <select id="genre" v-model="genre">
      <option value="General">飲料</option>
      <option value="Discussion">食料</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="prefecture">都道府県:</label>
    <select id="prefecture" v-model="prefecture">
      <option value="Tokyo">東京</option>
      <option value="Osaka">大阪</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="name">名前:</label>
    <input type="text" id="name" v-model="name" style="background-color: white;" />

    <label for="content">内容:</label>
    <textarea id="content" v-model="content" style="background-color: white;"></textarea>

    <button type="submit">Submit</button>
  </form>
</template>

<script lang="ts">
import { createObjectCsvWriter } from 'csv-writer';
import fs from 'fs';

export default {
  data() {
    return {
      type: 'Post',
      genre: 'General',
      prefecture: 'Tokyo',
      name: '',
      content: '',
      posts: [] as { type: string; genre: string; prefecture: string; name: string; date: string; content: string }[],
    };
  },
  methods: {
    writeCSVFile(data: { type: string; genre: string; prefecture: string; name: string; date: string; content: string }[]) {
      const csvWriter = createObjectCsvWriter({
        path: '../../data/post.csv',
        header: [
          { id: 'type', title: 'Type' },
          { id: 'genre', title: 'Genre' },
          { id: 'prefecture', title: 'Prefecture' },
          { id: 'name', title: 'Name' },
          { id: 'date', title: 'Date' },
          { id: 'content', title: 'Content' },
        ],
      });

      return csvWriter.writeRecords(data);
    },
    handleSubmit() {
      const date = new Date().toLocaleDateString();

      // 投稿データを追加
      this.posts.push({
        type: this.type,
        genre: this.genre,
        prefecture: this.prefecture,
        name: this.name.slice(0, 10),
        date: date,
        content: this.content.slice(0, 200),
      });

      // CSVファイルにデータを書き込む
      this.writeCSVFile(this.posts)
        .then(() => {
          console.log('CSV file written successfully.');
          this.type = 'Post';
          this.genre = 'General';
          this.prefecture = 'Tokyo';
          this.name = '';
          this.content = '';
        })
        .catch((error: Error) => {
          console.error('Error:', error);
        });
    },
  },
};
</script>
