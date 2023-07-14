<template>
<div class="navtop"><!--上部のナビゲーションバー-->
    <button @click="link1()" 
    style="display: inline-block;
            background-color: rgb(0, 0, 100);
            color: white;
            width: 50%;
            ;
            ">
            災害
    </button>
    <button @click="link2()" 
    style="display: inline-block;
            background-color: red;
            color: white;
            width: 50%
            ">
            防災
    </button>
    <div class="contents"><!--投稿ジャンル高くする-->
    <button @click="link11()" 
        style="display: block;
            height: 15vw;
            background-color: antiquewhite;
            ">
        飲料
    </button>
    <button @click="link12()" 
        style="display: block;
            height: 15vw;
            background-color: antiquewhite;
        ">
        食料
    </button>
    <button @click="link13()" 
        style="display: block;
            height: 15vw;
            background-color: antiquewhite;
        ">
        持ち物
    </button>
    <button @click="link14()" 
        style="display: block;
            height: 15vw;
            background-color: antiquewhite;
        ">
        その他
    </button>
    <div class="navbot">
        <button @click="link111()" 
        style="display: inline-block;
                background-color: red;
                width: 33.3vw;
                height: 15vw;
                vertical-align: bottom;
                ">
        <img src="../../static/home.png" style="margin: auto; width: 30%;"/>
        </button>
        <button @click="link112()" 
        style="display: inline-block;
                background-color: red;
                width: 33.3vw;
                height: 15vw;
                vertical-align: bottom;
                ">
        <img src="../../static/plus.png" style="margin: auto; width: 30%;"/>
        </button>
        <button @click="link113()" 
        style="display: inline-block;
            background-color: red;
            width: 33.3vw;
            height: 15vw;
            vertical-align: bottom;
            ">
        <img src="../../static/search.png" style="margin: auto; width: 30%;"/>
        </button>
    </div>
    </div>
</div>
</template>

<script setup lang="ts">

//災害ホームへ
const link1 = () => {
uni.navigateTo({
    url: "./home",
})
}
//防災ホームへ
const link2 = () => {
uni.navigateTo({
    url: "./home2",
})
}
//ジャンル1へ
const link11 = () => {
uni.navigateTo({
    url: "../genre/drink2",
})
}
//ジャンル2へ
const link12 = () => {
uni.navigateTo({
    url: "../genre/foods2",
})
}
//ジャンル3へ
const link13 = () => {
uni.navigateTo({
    url: "../genre/goods2",
})
}
//ジャンル4へ
const link14 = () => {
uni.navigateTo({
    url: "../genre/other2",
})
}
//災害ホームへ
const link111 = () => {
uni.navigateTo({
    url: "./home",
})
}
//投稿へ
const link112 = () => {
uni.navigateTo({
    url: "./plus",
})
}
//検索へ
const link113 = () => {
uni.navigateTo({
    url: "./search",
})
}
</script>

<style>
.navbot {
position:absolute;
bottom: 0;
}
</style>