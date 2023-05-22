<template>
  <view v-if="!noSearch" class="prefecture-search-input-view">
    <image
      class="prefecture-search-input-icon"
      src="/static/prefecture_search_icon.png"
      @tap="handleSearch()"
    />
    <input
      class="prefecture-search-input"
      confirm-type="search"
      placeholder="検索都道府県"
      placeholderStyle="font-size:14px;font-weight:500;color:#bbb"
      v-model="searchInputText"
      @confirm="handleSearch()"
    />
  </view>
  <view class="prefecture-search-view">
    <view class="prefecture-search-collect">
      <view class="prefecture-search-collect-title">{{
        noActive ? "すべて" : "位置決め/最近の訪問"
      }}</view>
      <view
        class="prefecture-search-collect-text"
        @tap="handlePrefectureChoose(activePrefecture)"
        v-if="!noActive && activePrefecture.indexOf(search) !== -1"
      >
        <image
          class="prefecture-search-active-icon"
          src="/static/prefecture_search_active.png"
        />
        {{ activePrefecture || "すべて" }}
      </view>
      <view
        v-for="(collectPrefecture, index) in collectPrefectures.filter(
          (collectPrefecture) => collectPrefecture.indexOf(search) !== -1
        )"
        :key="index"
        class="prefecture-search-collect-text"
        @tap="handlePrefectureChoose(collectPrefecture)"
      >
        {{ collectPrefecture || "すべて" }}
      </view>
    </view>
    <view
      v-for="item in list"
      :key="item.region"
      class="prefecture-search-region"
    >
      <view class="prefecture-search-title">{{ item.region }}</view>
      <view
        v-for="prefecture in item.prefectures.filter(
          (prefecture) => prefecture.indexOf(search) !== -1
        )"
        :key="prefecture"
        class="prefecture-search-text"
        :class="{ mark: withActiveMark && prefecture === activePrefecture }"
        @tap="handlePrefectureChoose(prefecture)"
      >
        {{ prefecture }}
        {{
          withActiveMark && prefecture === activePrefecture ? "(居住地)" : ""
        }}
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";

const props = defineProps<{
  noSearch?: boolean;
  noActive?: boolean;
  withActiveMark?: boolean;
  activePrefecture?: string;
  collectPrefectures: string[];
}>();

const searchInputText = ref<string>("");
const search = ref<string>("");

const list = [
  {
    region: "北海道地方",
    prefectures: ["北海道"],
  },
  {
    region: "東北地方",
    prefectures: ["青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県"],
  },
  {
    region: "関東地方",
    prefectures: [
      "茨城県",
      "栃木県",
      "群馬県",
      "埼玉県",
      "千葉県",
      "神奈川県",
      "東京都",
    ],
  },
  {
    region: "中部地方",
    prefectures: [
      "新潟県",
      "富山県",
      "石川県",
      "福井県",
      "山梨県",
      "長野県",
      "岐阜県",
      "静岡県",
      "愛知県",
      "三重県",
    ],
  },
];

const emit = defineEmits<{
  (
    e: "prefectureChoose",
    prefecture: string,
    collectPrefectures: string[]
  ): void;
}>();

const handleSearch = () => {
  search.value = searchInputText.value;
};
const handlePrefectureChoose = (prefecture: string) => {
  if (prefecture === props.activePrefecture) {
    emit("prefectureChoose", prefecture, props.collectPrefectures);
  } else {
    emit("prefectureChoose", prefecture, [
      props.activePrefecture || "",
      ...props.collectPrefectures.filter(
        (collectPrefecture) => collectPrefecture !== prefecture
      ),
    ]);
  }
};
</script>

<style scoped>
.prefecture-search-input-view {
  padding: 23px 0 20px;
}
.prefecture-search-input-icon {
  position: absolute;
  margin-top: 10px;
  right: 46px;
  width: 17px;
  height: 17px;
}
.prefecture-search-input {
  margin: 0 32px;
  padding: 10px 34px 8px 20px;
  background: #ffffff;
  border: 1px solid #dedede;
  font-size: 12px;
  color: #373737;
  border-radius: 20px;
}
.prefecture-search-view {
  padding: 0 15px;
  background: #ffffff;
  font-size: 0;
}
.prefecture-search-collect {
  padding: 10px 0 6px;
}
.prefecture-search-collect-title {
  margin-left: 5px;
  font-size: 13px;
  font-weight: 400;
  color: #686868;
}
.prefecture-search-active-icon {
  margin-right: 2px;
  width: 11px;
  height: 16px;
  vertical-align: sub;
}
.prefecture-search-collect-text {
  display: inline-block;
  margin: 5px;
  padding: 6px 0;
  min-width: calc(33.33% - 10px);
  background: #ecefc3;
  font-size: 13px;
  font-weight: 400;
  color: #503159;
  text-align: center;
  border-radius: 3px;
}
.prefecture-search-region {
  padding-top: 7px;
}
.prefecture-search-title {
  margin-left: 5px;
  font-size: 13px;
  font-weight: 400;
  color: #686868;
}
.prefecture-search-text {
  display: inline-block;
  margin: 5px;
  padding: 6px 0;
  min-width: calc(33.33% - 10px);
  background: #fadd6c;
  font-size: 13px;
  font-weight: 400;
  color: #a039af;
  text-align: center;
  border-radius: 3px;
}
.prefecture-search-text.mark {
  background-color: #bf242a;
  color: #fadd6c;
}
</style>
