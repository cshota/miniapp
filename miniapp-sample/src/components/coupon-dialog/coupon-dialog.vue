<template>
  <view class="coupon-dialog-view" @touchmove.stop.prevent="moveHandle">
    <view class="coupon-dialog">
      <image
        class="coupon-dialog-close-icon"
        src="/static/dialog_close.png"
        @tap="onClose"
      />
      <view
        class="coupon-dialog-ticket-view"
        :class="{ 'coupon-dialog-ticket-view-discount': !coupon.cashCoupon }"
      >
        <view class="coupon-dialog-ticket-left-view">
          <image
            v-if="coupon.cashCoupon"
            class="coupon-dialog-ticket-left-cash"
            :src="coupon.leftImgUrl"
          />
          <image
            v-else
            class="coupon-dialog-ticket-left-normal"
            :src="coupon.leftImgUrl"
          />
        </view>
        <view class="coupon-dialog-ticket-content-view">
          <view class="coupon-dialog-ticket-title">{{ coupon.title }}</view>
          <view v-if="coupon.id" class="coupon-dialog-ticket-id"
            >ID：{{ coupon.id }}</view
          >
        </view>
      </view>
      <view class="coupon-dialog-notice">
        <view v-if="flag" class="coupon-dialog-title">店員確認済み！</view>
        <view v-else class="coupon-dialog-title">本当に使いますか?</view>
        <view v-if="flag" class="coupon-dialog-content">
          クーポンが使用されました。
        </view>
        <view v-else class="coupon-dialog-content">
          必ずスタッフに見せたうえでこ利用ください。
        </view>
      </view>
      <view v-if="flag" class="coupon-dialog-movable-area-active">
        店員確認済み
        <view class="coupon-dialog-movable-view-active">
          <image
            class="coupon-dialog-movable-icon-active"
            src="/static/dialog_movable_icon_active.png"
          />
        </view>
      </view>
      <movable-area v-show="!flag" class="coupon-dialog-movable-area">
        <view class="coupon-dialog-movable-view">
          <view
            class="coupon-dialog-movable-area-inside"
            :style="{ width: `${movableWidth}px` }"
          >
          </view>
        </view>
        右にスライド店員確認
        <movable-view
          :x="x"
          direction="horizontal"
          @mouseup="onMovableUp"
          @touchend="onMovableUp"
          @change="onMovableChange"
          :out-of-bounds="true"
        >
          <image
            class="coupon-dialog-movable-icon"
            src="/static/dialog_movable_icon.png"
          />
        </movable-view>
      </movable-area>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, nextTick } from "vue";

defineProps<{
  coupon: {};
}>();

const movableWidth = ref(0);
const flag = ref(false);
const x = ref(0);
const o = ref({
  x: 0,
});

const emit = defineEmits<{
  (e: "swiped"): void;
  (e: "close"): void;
}>();

const moveHandle = () => {};
const onMovableUp = () => {
  x.value = o.value.x;
  nextTick(() => {
    x.value = 0;
  });
};
const onMovableChange = ({
  detail,
}: {
  detail: { x: number; source: string };
}) => {
  movableWidth.value = detail.x;
  o.value.x = detail.x;
  if (
    detail.source === "touch-out-of-bounds" &&
    detail.x > 100 &&
    !flag.value
  ) {
    flag.value = true;
    setTimeout(() => {
      emit("swiped");
    }, 1000);
  }
};
const onClose = () => emit("close");
</script>

<style scoped>
.coupon-dialog-view {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: rgba(16, 16, 16, 0.56);
  z-index: 4;
}
.coupon-dialog {
  margin: 0 auto;
  position: fixed;
  top: calc(50% - 266px);
  left: 0;
  right: 0;
  width: calc(100vw - 100px);
  min-width: 267px;
  background: #ffffff;
  font-size: 12px;
  border: 1px solid #707070;
  text-align: center;
  border-radius: 7px;
  z-index: 5;
}
.coupon-dialog-ticket-view {
  display: flex;
  margin: 56px 8px 0;
  height: 88px;
  background: #bf242a;
  border-radius: 4px;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.03);
}
.coupon-dialog-ticket-view-discount {
  background: linear-gradient(
    233deg,
    #ff4324 11%,
    #fe812d 47%,
    rgba(255, 100, 209, 0.97) 88%
  );
}
.coupon-dialog-ticket-left-view {
  display: flex;
  flex-direction: column;
  width: 90px;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  border-radius: 4px 0 0 4px;
}
.coupon-dialog-ticket-left-cash {
  margin-bottom: -24px;
  padding: 9px 0 0 15px;
  width: 80px;
  height: 64px;
}
.coupon-dialog-ticket-left-normal {
  margin-bottom: -24px;
  padding: 5px 0 0 5px;
  width: 89px;
  height: 69px;
}
.coupon-dialog-ticket-content-view {
  display: flex;
  flex-direction: column;
  -webkit-flex: 1;
  flex: 1;
  padding: 0 4px;
  align-items: center;
  -webkit-justify-content: center;
  justify-content: center;
}
.coupon-dialog-ticket-title {
  padding: 4px 0 2px;
  font-size: 16px;
  font-weight: 800;
  line-height: 19px;
  color: #ffffff;
  text-shadow: 0px 2px 3px rgba(0, 0, 0, 0.16);
}
.coupon-dialog-ticket-id {
  padding: 0;
  font-size: 10px;
  font-weight: 400;
  line-height: 23px;
  color: #ffffff;
  white-space: nowrap;
}
.coupon-dialog-close-icon {
  top: 15px;
  right: 16px;
  position: absolute;
  width: 19px;
  height: 19px;
}
.coupon-dialog-swipe-image {
  margin-top: 21px;
  width: 122px;
  height: 120px;
}
.coupon-dialog-notice {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 11px;
  height: 118px;
}
.coupon-dialog-title {
  font-size: 20px;
  font-weight: 800;
  color: #373737;
  line-height: 24px;
}
.coupon-dialog-content {
  margin: 6px 30px 0;
  font-size: 15px;
  font-weight: 400;
  color: #716d6d;
  line-height: 20px;
}
.coupon-dialog-movable-area-active {
  position: relative;
  margin: 35px 10px 46px;
  padding: 13px 41px 13px 0;
  width: calc(100% - 61px);
  height: 20px;
  background-color: #76cf51;
  font-size: 15px;
  font-weight: 400;
  color: #ffffff;
  border-radius: 3px;
  box-shadow: 0 1px 2px 0.3px rgba(0, 0, 0, 0.16);
  text-shadow: 0 1px 2px 0.3px rgba(0, 0, 0, 0.16);
}
.coupon-dialog-movable-area {
  margin: 35px 10px 46px;
  padding: 13px 0 13px 41px;
  width: calc(100% - 61px);
  height: 20px;
  background-color: #efefef;
  font-size: 15px;
  font-weight: 400;
  color: #8b8b8b;
  border-radius: 3px;
  box-shadow: 0 1px 2px 0.3px rgba(0, 0, 0, 0.16);
}
.coupon-dialog-movable-area-inside {
  padding: 13px 20px 13px 21px;
  width: 0px;
  height: 20px;
  background-color: #76cf51;
  font-size: 15px;
  font-weight: 400;
  color: #ffffff;
  border-radius: 3px;
  word-break: keep-all;
  overflow: hidden;
  box-shadow: 0 1px 2px 0.3px rgba(0, 0, 0, 0.16);
  text-shadow: 0 1px 2px 0.3px rgba(0, 0, 0, 0.16);
}
.coupon-dialog-movable-view {
  position: absolute;
  width: calc(100% - 41px);
  top: 0;
  left: 0;
}
.coupon-dialog-movable-view-active {
  position: absolute;
  top: 0;
  right: 0;
}
.coupon-dialog-movable-icon-active {
  margin: -3px;
  width: 54px;
  height: 54px;
}
.coupon-dialog-movable-icon {
  margin: -3px;
  width: 54px;
  height: 54px;
}
</style>
