<template>
  <view class="demo-home">
    <notice-card
      v-for="(notice, index) in notices"
      :key="index"
      :picUrl="notice.picUrl"
      :title="notice.title"
      :date="notice.date"
      @clickNoticeButton="handleClickNotice"
    />
    <view style="height: 100px"></view>
    <page-navbar :current="2" @clickBarItem="redirectToByIndex" />
  </view>
</template>

<script setup lang="ts">
import "vue";

const notices = [
  {
    noticeId: 1,
    picUrl: "/static/noticepic01.png",
    title: "徳島新店 新しいクーポンがあるから見てみよう",
    date: "2022/05/24",
  },
  {
    noticeId: 2,
    picUrl: "/static/noticepic02.png",
    title: "ペットコーナー 新しいクーポンがあるから見てみよう",
    date: "2022/05/24",
  },
  {
    noticeId: 3,
    picUrl: "/static/noticepic03.png",
    title: "食品新店 新しいクーポンがあるから見てみよう",
    date: "2022/05/24",
  },
  {
    noticeId: 4,
    picUrl: "/static/noticepic04.png",
    title: "徳島新店 新しいクーポンがあるから見てみよう",
    date: "2022/05/24",
  },
  {
    noticeId: 5,
    picUrl: "/static/noticepic05.png",
    title: "徳島新店 新しいクーポンがあるから見てみよう",
    date: "2022/05/24",
  },
  {
    noticeId: 6,
    picUrl: "/static/noticepic06.png",
    title: "徳島新店 新しいクーポンがあるから見てみよう",
    date: "2022/05/24",
  },
];
const redirectToByIndex = (path: string) => {
  uni.reLaunch({
    url: path,
  });
};
const handleClickNotice = () => {
  uni.navigateTo({
    url: `../shop/shop?shopId=1`,
  });
};
</script>

<style scoped>
.header-image {
  width: 100%;
}
</style>
