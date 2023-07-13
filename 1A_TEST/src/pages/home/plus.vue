<template>
  <form>
    <label for="type">投稿タイプ:</label>
    <select id="type" v-model="type">
      <option value="null">選択してください</option>
      <option value="災害">災害</option>
      <option value="防災">防災</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="genre">ジャンル:</label>
    <select id="genre" v-model="genre">
      <option value="null">選択してください</option>
      <option value="飲料">飲料</option>
      <option value="食料">食料</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="prefecture">都道府県:</label>
    <select id="prefecture" v-model="prefecture">
      <option value="null">選択してください</option>
      <option value="東京">東京</option>
      <option value="大阪">大阪</option>
      <!-- 他のオプションをここに追加 -->
    </select>

    <label for="name">名前:</label>
    <input type="text" id="name" v-model="name" style="background-color: white;" placeholder="名前を入力"/>

    <label for="content">内容:</label>
    <textarea id="content" v-model="content" style="background-color: white;" placeholder="投稿内容を入力"></textarea>

    <!-- ボタンがクリックされたときにhandleClickメソッドを実行する -->
    <button type="button" @click="handleClick">送信</button>
  </form>
</template>

<script lang="ts">
// ページ下部に自分の投稿を削除する機能。実装するにはユーザーごとのユニークなvalueが必要
import { collection, addDoc, Timestamp } from 'firebase/firestore';
import { db } from '../../main'; // main.tsからdbをインポートします

export default {
  data() {
    return {
      type: 'null',
      genre: 'null',
      prefecture: 'null',
      name: '',
      content: '',
      posts: [] as { type: string; genre: string; prefecture: string; name: string; date: Timestamp; content: string }[],
    };
  },
  methods: {
    handleClick() {
      // 日時を取
      //const date = Timestamp.fromDate(new Date());
      const date = new Date().toLocaleString();

      // 投稿データを追加
      const newPost = {
        type: this.type,
        genre: this.genre,
        prefecture: this.prefecture,
        name: this.name.slice(0, 10),
        date: date,
        content: this.content.slice(0, 200),
        likes: 0  // 追加: いいねの初期値は0
      };

      this.posts.push(newPost);

      // 送信前にアラートを表示
      if (window.confirm('内容を送信します。よろしいですか？')) {
        addDoc(collection(db, "posts"), newPost)
          .then(() => {
            console.log('Data written successfully.');
            this.type = 'Post';
            this.genre = 'General';
            this.prefecture = 'Tokyo';
            this.name = '';
            this.content = '';
          })
          .catch((error: Error) => {
            console.error('Error:', error);
          });
      }
    },
  },
};
</script>
