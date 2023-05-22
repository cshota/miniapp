<template>
  <view class="coupon-bar-view">
    <view class="coupon-bar-flex-view">
      <view
        class="coupon-bar-item"
        :class="{ active: currentName === 'discount' }"
      >
        <view @tap="handleCouponItemClick('discount')">割引券</view>
        <view class="coupon-bar-badge">{{ discount }}</view>
      </view>
      <view class="coupon-bar-item" :class="{ active: currentName === 'cash' }">
        <view @tap="handleCouponItemClick('cash')">現金券</view>
        <view class="coupon-bar-badge">{{ cash }}</view>
      </view>
      <view
        class="coupon-bar-item"
        :class="{ active: currentName === 'expiredSoon' }"
      >
        <view @tap="handleCouponItemClick('expiredSoon')">
          まもなく期限切れ
        </view>
        <view class="coupon-bar-badge">{{ expiredSoon }}</view>
      </view>
    </view>
    <view class="coupon-bar-after" @tap="handleHistoryClick">履歴</view>
  </view>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";

const props = withDefaults(
  defineProps<{
    current?: string;
    discount?: number;
    cash?: number;
    expiredSoon?: number;
  }>(),
  {
    current: "discount",
    discount: 0,
    cash: 0,
    expiredSoon: 0,
  }
);

const currentName = ref<string>(props.current || "discount");

const emit = defineEmits<{
  (e: "clickCouponItem", name: string): void;
  (e: "clickHistory"): void;
}>();
const handleCouponItemClick = (name: string) => {
  if (currentName.value !== name) {
    currentName.value = name;
    emit("clickCouponItem", name);
  }
};

const handleHistoryClick = () => emit("clickHistory");

watch(
  () => props.current,
  (val) => {
    if (val !== currentName.value) {
      currentName.value = val;
    }
  }
);
</script>

<style scoped>
.coupon-bar-view {
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: space-between;
  /* #ifdef H5 */
  top: 44px;
  /* #endif */
  /* #ifndef H5 */
  top: 0;
  /* #endif */
  width: calc(100% - 12px);
  padding: 0 7px 0 6px;
  background: #ffffff;
  font-size: 0;
  box-shadow: 0px 2px 3.3px 0.3px rgba(0, 0, 0, 0.16);
}
.coupon-bar-flex-view {
  flex: 75%;
  display: flex;
}
.coupon-bar-item {
  flex: 1;
  position: relative;
  margin: 13px 3px 11px;
  padding: 4px 7px 3px;
  background: rgba(255, 255, 255, 0.5);
  font-size: 11px;
  font-weight: 500;
  color: #8b8b8b;
  border: 1px solid #efefef;
  border-radius: 33px;
  text-align: center;
  white-space: nowrap;
  /* #ifdef H5 */
  cursor: pointer;
  /* #endif */
}
.coupon-bar-item.active {
  border: 1px solid #bf242a;
  color: #bf242a;
}
.coupon-bar-badge {
  position: absolute;
  top: -6px;
  right: 0;
  padding: 0 3px 0 3px;
  background: #bf242a;
  font-size: 8px;
  font-weight: 500;
  color: #ffffff;
  text-align: center;
  border-radius: 67px;
}
.coupon-bar-after {
  flex: 1 0 15%;
  font-size: 12px;
  font-weight: 500;
  color: #bf242a;
  text-align: right;
}
</style>
