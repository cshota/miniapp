<template>
  <view :class="{ 'coupon-ticket-gray': type === 'used' || type === 'got' }">
    <view class="coupon-ticket" @tap="handleCouponClick">
      <image
        class="coupon-ticket-right-image"
        :src="
          hasDeadline
            ? '/static/ticket_right01.png'
            : '/static/ticket_right00.png'
        "
      />
      <view v-if="infos.length" class="coupon-ticket-info-button">
        使い方
        <view class="coupon-ticket-arrowdown-icon" :class="{ open }"> > </view>
      </view>
    </view>
    <view
      class="coupon-ticket-view"
      :class="{ 'coupon-ticket-view-discount': !cashCoupon }"
      @tap="handleCouponClick"
    >
      <view class="coupon-ticket-left-view">
        <image
          v-if="cashCoupon"
          class="coupon-ticket-left-cash"
          :src="leftImgUrl"
        />
        <image v-else class="coupon-ticket-left-normal" :src="leftImgUrl" />
        <view v-if="id" class="coupon-ticket-left-id">ID：{{ id }}</view>
      </view>
      <view class="coupon-ticket-content-view">
        <view class="coupon-ticket-space"></view>
        <view class="coupon-ticket-title">{{ title }}</view>
        <view
          class="coupon-ticket-subtitle"
          :class="{ 'coupon-ticket-subtitle-warn': subtitleWarn }"
        >
          {{ subtitle || "" }}
        </view>
        <view class="coupon-ticket-content">
          <button
            v-if="type === 'unused'"
            class="coupon-ticket-content-unused"
            hover-class="coupon-ticket-content-unused-hover"
            @tap.stop="handleButtonClick(id, coupon)"
          >
            今すぐつかう
          </button>
          <button
            v-else-if="type === 'used'"
            class="coupon-ticket-content-used"
            type="button"
            disabled
          >
            使用済み
          </button>
          <button
            v-else-if="type === 'expired'"
            class="coupon-ticket-content-expired"
            type="button"
            disabled
          >
            期限切れ
          </button>
          <button
            v-else-if="type === 'got'"
            class="coupon-ticket-content-got"
            type="button"
            disabled
          >
            取得済み
          </button>
          <button
            v-else
            class="coupon-ticket-content-normal"
            hover-class="coupon-ticket-content-normal-hover"
            @tap.stop="handleButtonClick()"
          >
            クーポンを獲得
          </button>
        </view>
        <view class="coupon-ticket-space"></view>
      </view>
      <view class="coupon-ticket-right-view"></view>
    </view>
    <view
      class="coupon-ticket-info-transition"
      :class="{ active: open && infos.length }"
    >
      <view class="coupon-ticket-info-view">
        <view
          class="coupon-ticket-info-text"
          v-for="(info, index) in infos"
          :key="index"
        >
          <rich-text :nodes="info"></rich-text>
        </view>
        <view
          v-if="id && !hideInfoLink"
          class="coupon-ticket-info-link"
          @tap="handleClickInfoLink(id)"
        >
          お店ページ>>
        </view>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from "vue";

const props = withDefaults(
  defineProps<{
    id?: string;
    shopId?: string;
    type?: string;
    cashCoupon?: boolean;
    leftImgUrl: string;
    title: string;
    subtitle?: string;
    subtitleWarn?: boolean;
    hasDeadline?: boolean;
    infos: string[];
    hideInfoLink?: boolean;
  }>(),
  {
    id: "",
    type: "normal",
    cashCoupon: false,
    subtitle: "",
    subtitleWarn: false,
    hasDeadline: false,
    hideInfoLink: false,
  }
);
const open = ref<boolean>(false);

const emit = defineEmits<{
  (e: "buttonClick", id: number, coupon: any): void;
  (e: "infoLinkClick", id: number, coupon: any): void;
}>();
const handleCouponClick = () => {
  open.value = !open.value;
};
const handleButtonClick = (id: number) => emit("buttonClick", id, props);
const handleClickInfoLink = (id: number) => emit("infoLinkClick", id, props);
</script>

<style scoped>
.coupon-ticket-gray {
  filter: grayscale(1);
}
.coupon-ticket-view {
  display: flex;
  margin: 14px 8px 0;
  height: 106px;
  background: #bf242a;
  border-radius: 4px;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.03);
}
.coupon-ticket-view-discount {
  background: linear-gradient(
    233deg,
    #ff4324 11%,
    #fe812d 47%,
    rgba(255, 100, 209, 0.97) 88%
  );
}
.coupon-ticket-left-view {
  display: flex;
  flex-direction: column;
  width: 128px;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  border-radius: 4px 0 0 4px;
}
.coupon-ticket-left-cash {
  margin-bottom: -24px;
  padding: 10px 0 0 22px;
  width: 106px;
  height: 85px;
}
.coupon-ticket-left-normal {
  margin-bottom: -24px;
  padding: 5px 0 0 10px;
  width: 118px;
  height: 91px;
}
.coupon-ticket-left-id {
  margin-bottom: 3px;
  padding-left: 21px;
  font-size: 11px;
  font-weight: 400;
  line-height: 23px;
  color: #ffffff;
}
.coupon-ticket-content-unused {
  width: 130px;
  background: #ffffff;
  box-shadow: 0px 2px 3px rgb(0 0 0 / 3%);
  border-radius: 20px;
  font-size: 14px;
  font-weight: 400;
  line-height: 25px;
  color: #bf242a;
  text-shadow: 0px 2px 3px rgb(0 0 0 / 16%);
}
.coupon-ticket-content-unused-hover,
.coupon-ticket-content-unused:hover {
  background: #ffe713;
}
.coupon-ticket-content-used {
  width: 135px;
  background: rgba(55, 55, 55, 0.15);
  border: 1px solid #8b8b8b;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.03);
  border-radius: 4px;
  font-size: 12px;
  font-weight: 400;
  line-height: 23px;
  color: #373737;
}
.coupon-ticket-content-expired {
  width: 135px;
  background: rgba(191, 36, 42, 0.11);
  border: 1px solid #ffe713;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.03);
  border-radius: 4px;
  font-size: 12px;
  font-weight: 400;
  line-height: 23px;
  color: #ffe713;
}
.coupon-ticket-content-got {
  width: 130px;
  background: #ffffff;
  box-shadow: 0px 2px 3px rgb(0 0 0 / 3%);
  border-radius: 20px;
  font-size: 14px;
  font-weight: 400;
  line-height: 25px;
  color: #373737;
  text-shadow: 0px 2px 3px rgb(0 0 0 / 7%);
}
.coupon-ticket-content-normal {
  width: 130px;
  background: #ffffff;
  box-shadow: 0px 2px 3px rgb(0 0 0 / 3%);
  border-radius: 20px;
  font-size: 14px;
  font-weight: 400;
  line-height: 25px;
  color: #bf242a;
  text-shadow: 0px 2px 3px rgb(0 0 0 / 7%);
}
.coupon-ticket-content-normal-hover,
.coupon-ticket-content-normal:hover {
  background: #ffe713;
}
.coupon-ticket-content-view {
  display: flex;
  flex-direction: column;
  -webkit-flex: 1;
  flex: 1;
  padding: 0 11px;
  align-items: center;
  -webkit-justify-content: space-between;
  justify-content: space-between;
}
.coupon-ticket-space {
  flex: auto;
}
.coupon-ticket-title {
  font-size: 17px;
  font-weight: 800;
  line-height: 19px;
  color: #ffffff;
  text-shadow: 0px 2px 3px rgba(0, 0, 0, 0.16);
}
.coupon-ticket-subtitle {
  min-height: 13px;
  font-size: 11px;
  font-weight: 400;
  line-height: 23px;
  color: #ffffff;
  text-shadow: 0px 2px 3px rgba(0, 0, 0, 0.16);
  white-space: nowrap;
}
.coupon-ticket-subtitle-warn {
  color: #ffe713;
}
.coupon-ticket-right-view {
  width: 66px;
  text-align: end;
  border-radius: 0 4px 4px 0;
  overflow: hidden;
}
.coupon-ticket {
  position: absolute;
  right: 8px;
  font-size: 0;
}
.coupon-ticket-right-image {
  width: 76px;
  height: 78px;
}
.coupon-ticket-info-button {
  margin: 3px 2px 0 0;
  text-align: right;
  font-size: 11px;
  font-weight: 400;
  line-height: 23px;
  color: #ffe713;
}
.coupon-ticket-arrowdown-icon {
  display: inline-block;
  font-size: 16px;
  font-weight: 400;
  color: #ffe713;
  vertical-align: middle;
  transform: scaleY(0.6) rotate(90deg);
  transition: 0.2s ease;
}
.coupon-ticket-arrowdown-icon.open {
  transform: scaleY(0.6) rotate(-90deg);
}
.coupon-ticket-info-transition {
  transition: max-height 0.2s ease;
  max-height: 0;
  overflow: hidden;
}
.coupon-ticket-info-transition.active {
  max-height: 300px;
}
.coupon-ticket-info-view {
  margin: 3px 8px 0;
  padding: 17px 11px 9px 7px;
  min-height: 56px;
  background: #ffffff;
  border-radius: 8px;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.03);
}
.coupon-ticket-info-text {
  padding-left: 86px;
  font-size: 14px;
  font-weight: 400;
  line-height: 22px;
  color: #706e6e;
  white-space: pre-wrap;
  text-indent: -86px;
}
.coupon-ticket-info-link {
  font-size: 14px;
  font-weight: 400;
  line-height: 22px;
  text-align: right;
  color: #fe702b;
  /* #ifdef H5 */
  cursor: pointer;
  /* #endif */
}
</style>
