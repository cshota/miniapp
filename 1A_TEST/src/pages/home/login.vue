<template>
    <div>
        <h2>ログインフォーム</h2>
        <form @submit.prevent="login">
            <label for="email">Email:</label>
            <input type="email" id="email" v-model="email">

            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password">

            <button type="submit">ログイン</button>
        </form>
        <p v-if="errorMessage">{{ errorMessage }}</p>
    </div>
</template>

<script lang="ts">
import { ref } from 'vue';
import { getAuth, signInWithEmailAndPassword } from 'firebase/auth';

export default {
    setup() {
        const auth = getAuth();
        const email = ref('');
        const password = ref('');
        const errorMessage = ref('');

        const login = async () => {
            try {
                await signInWithEmailAndPassword(auth, email.value, password.value);
                // ログイン成功時の処理（例えば、ホーム画面にリダイレクトするなど）
            } catch (error) {
                // エラーハンドリング
                errorMessage.value = error.message;
            }
        }

        return { email, password, errorMessage, login };
    }
}
</script>
