<template>
  <form>
    <div>
      <label for="type">投稿タイプ:</label>
        <select id="type" v-model="type">
      <option value="null">選択してください</option>
      <option value="災害">災害</option>
      <option value="防災">防災</option>
      </select>
    </div>

    <div>
      <label for="genre">ジャンル:</label>
        <select id="genre" v-model="genre">
      <option value="null">選択してください</option>
      <option value="飲料">飲料</option>
      <option value="食料">食料</option>
      <option value="持ち物">持ち物</option>
      <option value="その他">その他</option>
      </select>
    </div>

    <div>
      <label for="prefecture">都道府県:</label>
        <select id="prefecture" v-model="prefecture">
      <option value="非公開">選択してください</option>
      <option value="北海道">北海道</option>
      <option value="青森県">青森県</option>
      <option value="岩手県">岩手県</option>
      <option value="宮城県">宮城県</option>
      <option value="秋田県">秋田県</option>
      <option value="山形県">山形県</option>
      <option value="福島県">福島県</option>
      <option value="茨城県">茨城県</option>
      <option value="栃木県">栃木県</option>
      <option value="群馬県">群馬県</option>
      <option value="埼玉県">埼玉県</option>
      <option value="千葉県">千葉県</option>
      <option value="東京都">東京都</option>
      <option value="神奈川県">神奈川県</option>
      <option value="新潟県">新潟県</option>
      <option value="富山県">富山県</option>
      <option value="石川県">石川県</option>
      <option value="福井県">福井県</option>
      <option value="山梨県">山梨県</option>
      <option value="長野県">長野県</option>
      <option value="岐阜県">岐阜県</option>
      <option value="静岡県">静岡県</option>
      <option value="愛知県">愛知県</option>
      <option value="三重県">三重県</option>
      <option value="滋賀県">滋賀県</option>
      <option value="京都府">京都府</option>
      <option value="大阪府">大阪府</option>
      <option value="兵庫県">兵庫県</option>
      <option value="奈良県">奈良県</option>
      <option value="和歌山県">和歌山県</option>
      <option value="鳥取県">鳥取県</option>
      <option value="島根県">島根県</option>
      <option value="岡山県">岡山県</option>
      <option value="広島県">広島県</option>
      <option value="山口県">山口県</option>
      <option value="徳島県">徳島県</option>
      <option value="香川県">香川県</option>
      <option value="愛媛県">愛媛県</option>
      <option value="高知県">高知県</option>
      <option value="福岡県">福岡県</option>
      <option value="佐賀県">佐賀県</option>
      <option value="長崎県">長崎県</option>
      <option value="熊本県">熊本県</option>
      <option value="大分県">大分県</option>
      <option value="宮崎県">宮崎県</option>
      <option value="鹿児島県">鹿児島県</option>
      <option value="沖縄県">沖縄県</option>
      </select>
    </div>

    <div>
      <label for="name"></label>
        <input type="text" id="name" v-model="name" style="background-color: white; border-bottom:solid 2px black" placeholder="名前を入力" pattern=".{1,10}"/>
        <p v-if="nameError" style="color: red;">名前は1文字以上10文字以下にしてください</p>
    </div>

    <div>
      <label for="content"></label>
        <textarea id="content" v-model="content" style="background-color: white; width: 100vw;" placeholder="投稿内容を入力" pattern=".{1,200}"></textarea>
        <p v-if="contentError" style="color: red;">投稿内容は1文字以上200文字以下にしてください</p>
    </div>

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
      prefecture: '非公開',
      name: '',
      content: '',
      nameError: false,
      contentError:false,
      posts: [] as { type: string; genre: string; prefecture: string; name: string; date: Timestamp; content: string }[],
    };
  },
  watch: {
    // nameプロパティのウォッチャーを追加
    name(newVal) {
      if (newVal.length < 1 || newVal.length > 10) {
        this.nameError = true;
      } else {
        this.nameError = false;
      }
    },
    content(newVal) {
      if (newVal.length < 1 || newVal.length > 200) {
        this.contentError = true;
      } else {
        this.contentError = false;
      }
    },
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
