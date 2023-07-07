<template>
  <form @submit.prevent="handleSubmit">
    <label for="type">Type:</label>
    <select id="type" v-model="type">
      <option value="Post">Post</option>
      <option value="Comment">Comment</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="genre">Genre:</label>
    <select id="genre" v-model="genre">
      <option value="General">General</option>
      <option value="Discussion">Discussion</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="prefecture">Prefecture:</label>
    <select id="prefecture" v-model="prefecture">
      <option value="Tokyo">Tokyo</option>
      <option value="Osaka">Osaka</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="name">Name:</label>
    <input type="text" id="name" v-model="name" />

    <label for="content">Content:</label>
    <textarea id="content" v-model="content"></textarea>

    <button type="submit">Submit</button>
  </form>
</template>

<script>
import { createObjectCsvWriter } from 'csv-writer';

export default {
  data() {
    return {
      type: 'Post',
      genre: 'General',
      prefecture: 'Tokyo',
      name: '',
      content: '',
      posts: [],
    };
  },
  methods: {
    writeCSVFile(filename, data) {
      const csvWriter = createObjectCsvWriter({
        path: '../../data/post',
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
      this.writeCSVFile('posts.csv', this.posts)
        .then(() => {
          console.log('CSV file written successfully.');
          this.type = 'Post';
          this.genre = 'General';
          this.prefecture = 'Tokyo';
          this.name = '';
          this.content = '';
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    },
  },
};
</script>
