<template>
    <div>
        <!-- 都道府県フィルター -->
        <label for="prefecture">都道府県:</label>
        <select id="prefecture" v-model="selectedPrefecture">
            <option value="">選択してください</option>
            <option value="東京">東京</option>
            <option value="大阪">大阪</option>
            <!-- 他のオプションをここに追加 -->
        </select>
            <button @click="applyFilter">適用</button>
        <div v-for="post in filteredPosts" :key="post.id" style="background-color: antiquewhite; border-bottom:solid 2px blue">
            <p style="">{{ post.prefecture }}</p>
            <!-- 日付の表示を変更（TimestampからDateへの変換） -->
            <p>{{ post.date }}</p>
            <!-- <p><strong>日付:</strong> {{ post.date.toDate() }}</p> -->
            <p><strong>名前:</strong> {{ post.name }}</p>
            <p><strong>内容:</strong> {{ post.content }}</p>
            <p><img src="../../static/heart.png"> {{ post.likes }}</p>  <!-- 追加: いいねの数を表示 -->
            <button @click="likePost(post.id)"><img src="../../static/heart.png"></button>  <!-- 追加: いいねボタン -->
        </div>
    </div>
</template>

<script lang="ts">
import { onMounted, ref } from 'vue';
//import { collection, query, getDocs, orderBy, doc, updateDoc, increment} from 'firebase/firestore';
import { collection, query, getDocs, orderBy, doc, updateDoc, increment, onSnapshot } from 'firebase/firestore';
import { db } from '../../main';

export default {
setup() {
    const posts = ref([]);
    const filteredPosts = ref([]);
    const selectedType = ref('災害');
    const selectedGenre = ref('飲料');
    const selectedPrefecture = ref('');
    
    const likePost = async (postId) => {
        const postRef = doc(db, 'posts', postId);
        await updateDoc(postRef, { likes: increment(1) });
    }
    
    const applyFilter = async () => {
        if (selectedPrefecture.value === "") {
    filteredPosts.value = posts.value.filter(post => post.type === selectedType.value && post.genre === selectedGenre.value);
        } else {
    filteredPosts.value = posts.value.filter(post => post.type === selectedType.value && post.genre === selectedGenre.value && post.prefecture === selectedPrefecture.value);
        };
    };
    
    onMounted(async () => {
    //await applyFilter(); // 適用ボタンの機能を実行
    const postsRef = collection(db, 'posts');
    const q = query(postsRef, orderBy('date', 'desc'));
    await applyFilter(); // 適用ボタンの機能を実行


    const unsubscribe = onSnapshot(q, (snapshot) => {
        posts.value = [];
    //querySnapshot.forEach((doc) => {
        snapshot.forEach((doc) => {
        const data = doc.data();
        //data.date = data.date.toDate();  // TimestampをDateに変換
        posts.value.push({ id: doc.id, ...doc.data() });
    });

    // filteredPosts.value = posts.value.filter(post => post.type === selectedType.value && post.genre === selectedGenre.value && post.prefecture === selectedPrefecture.value);
    // });
    //filteredPosts.value = posts.value.filter(post => post.type === selectedType.value && post.genre === selectedGenre.value && post.prefecture === selectedPrefecture.value);

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
