<template>
  <div class="div1">
    <!-- 投稿タイプを選択 -->
    <label for="type">投稿：</label>
    <select name="type">
      <option>選択してください</option>
      <option value="saigai">災害</option>
      <option value="bousai">防災</option>
    </select>
    <!-- 都道府県を選択 -->
    <label for="area">エリア：</label>
      <select name="pref">
        <option>選択してください</option>
        <option value="Hokkaido">北海道</option>
        <option value="Aomori">青森県</option>
  
      </select>
    <!-- 投稿ジャンルを選択 -->
    <label for="genre">ジャンル：</label>
      <select name="genre">
        <option>選択してください</option>
        <option value="drink">飲料</option>
  
      </select>
    <!-- 投稿者の名前 -->
    <label for="name">名前:</label>
    <input type="text" id="name" v-model="name" />
    <!-- 投稿内容 -->
    <label for="message">投稿内容:</label>
    <textarea id="message" v-model="message"></textarea>
    <!-- 送信ボタン -->
    <button class="button" @click="showText" style="background-color: rgb(104, 104, 255);">送信</button>
    <butoon class="button" @click="">キャンセル</butoon>
  </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref, watch } from "vue";
  import {createObjectCsvWriter} from 'csv-writer';

  const fs =require('fs');
  const csv = require('csv');
  
  const type = typeInput.value;
  const genre = genreInput.value;
  const prefecture = prefectureInput.value;
  const name = nameInput.value.slice(0,10);

  const csvWriter = createCsvWriter({
    path: '../../data/post',
    header: ['id','title','view'],
    fieldDelimiter: ","
  });
  
  </script>
  
  <style scoped>
  .div1{
  background-color: antiquewhite;
  
  }
  
  .button{
  background-color: white;
  margin-left: auto;
  margin-right: auto;
  width: 10em;
  height: 3em;
  display: inline;
  justify-content: center;
  }
  </style>