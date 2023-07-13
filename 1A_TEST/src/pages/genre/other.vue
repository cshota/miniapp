<template>
    <div>
        <label for="prefecture">都道府県:</label><!-- 都道府県フィルター -->
        <select id="prefecture" v-model="selectedPrefecture">
            <option value="">全国</option>
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
            <!-- 他のオプションをここに追加 -->
        </select>
        <button @click="applyFilter" class="filterbutton">適用</button>
        <div v-for="post in filteredPosts" :key="post.id" style="top: 2em; background-color: antiquewhite; border-bottom:solid 2px blue">
            <p style="">{{ post.prefecture }}</p> <!--都道府県-->
            <p>{{ post.date }}</p> <!--日時-->
            <p><strong>名前:</strong> {{ post.name }}</p> <!--名前-->
            <p><strong>内容:</strong> {{ post.content }}</p> <!--投稿内容-->
            <p><img src="../../static/heart.png"> {{ post.likes }}</p>  <!-- いいねの数を表示 -->
            <button @click="likePost(post.id)" class="heartbutton" style="border: none;">
                <img src="../../static/heart.png"> <!--♥ボタン-->
            </button> 
        </div>
    </div>
</template>

<script lang="ts">
import { onMounted, ref } from 'vue';
import { collection, query, getDocs, orderBy, doc, updateDoc, increment, onSnapshot } from 'firebase/firestore';
import { db } from '../../main';

export default {
setup() {
    const posts = ref([]);
    const filteredPosts = ref([]);
    const selectedType = ref('災害');//【変更】災害のトピックのみ表示
    const selectedGenre = ref('その他');//【変更】飲料のトピックのみ表示
    const selectedPrefecture = ref('');

    let likedPosts = ref(JSON.parse(localStorage.getItem('likedPosts') || '[]'));
    
    const likePost = async (postId) => {//いいねボタンの処理
        const postRef = doc(db, 'posts', postId);
        let likedPosts = JSON.parse(localStorage.getItem('likedPosts') || '[]');
        if (likedPosts.includes(postId)) {
            // If post is already liked, unlike it and decrement the count
            await updateDoc(postRef, { likes: increment(-1) });
            likedPosts = likedPosts.filter(id => id !== postId);
        } else {
            // If post is not liked, like it and increment the count
            await updateDoc(postRef, { likes: increment(1) });
            likedPosts.push(postId);
        }
        localStorage.setItem('likedPosts', JSON.stringify(likedPosts));
        }
    
    const applyFilter = async () => {//都道府県フィルター処理
        if (selectedPrefecture.value === "") {
    filteredPosts.value = posts.value.filter(post => post.type === selectedType.value && post.genre === selectedGenre.value);
        } else {
    filteredPosts.value = posts.value.filter(post => post.type === selectedType.value && post.genre === selectedGenre.value && post.prefecture === selectedPrefecture.value);
        };
    };
    
    onMounted(async () => {
    const postsRef = collection(db, 'posts');
    const q = query(postsRef, orderBy('date', 'desc'));//日時の降順表示設定
    await applyFilter(); // 適用ボタンの機能を実行


    const unsubscribe = onSnapshot(q, (snapshot) => {
        posts.value = [];
        //querySnapshot.forEach((doc) => {
        snapshot.forEach((doc) => {
        const data = doc.data();
        //data.date = data.date.toDate();  // TimestampをDateに変換
        posts.value.push({ id: doc.id, ...doc.data() });
    });
    applyFilter();
});
    return () => {
        // Stop listening to changes when the component is unmounted
        unsubscribe();
    }
    });
    return { filteredPosts, selectedType, selectedGenre, selectedPrefecture, applyFilter, likePost};   
},
};
</script>

<style scoped>
.filterbutton {
    width: 4em;
    height: 2em;
    text-align: center;
    right: 0;
    position: absolute;
    top: 0;
    display: block;
    color: white;
    background-color: rgb(0, 98, 255);
    font-size: 80%;
    padding: 0;
}
.heartbutton {
    width: 20%;
    height: auto;
    text-align: center;
    margin: 0 0 0 auto;
    padding: 0;
    padding-right: 1em;
    background-color: antiquewhite;
    display: flex;
    justify-content: flex-end;
    border: antiquewhite;
    outline: antiquewhite;
}
</style>