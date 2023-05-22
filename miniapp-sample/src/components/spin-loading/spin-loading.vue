<template>
  <view class="spin-loading">
    <view></view>
    <view></view>
    <view></view>
    <view></view>
  </view>
</template>

<style scoped>
.spin-loading {
  margin: 0 auto;
  width: 30px;
  height: 30px;
  position: relative;
  -webkit-animation: spin-loading-spin 1s steps(12, end) infinite;
  animation: spin-loading-spin 1s steps(12, end) infinite;
}
.spin-loading > view {
  position: absolute;
  width: 2px;
  height: 6px;
  border-radius: 2px/3px;
}
.spin-loading > view::before,
.spin-loading > view::after {
  content: "";
  display: block;
  position: absolute;
  width: 2px;
  height: 6px;
  border-radius: 2px/3px;
}
.spin-loading > view:nth-child(1)::before {
  background: #e2e2e2;
  transform: rotate(-30deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(1) {
  background: #e9e9e9;
  transform: translate(14px, 3px);
}
.spin-loading > view:nth-child(1)::after {
  background: #989697;
  transform: rotate(30deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(2)::before {
  background: #9b999a;
  transform: rotate(-30deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(2) {
  background: #a3a1a2;
  transform: translate(14px, 3px) rotate(90deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(2)::after {
  background: #aba9aa;
  transform: rotate(30deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(3)::before {
  background: #b2b2b2;
  transform: rotate(-30deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(3) {
  background: #bab8b9;
  transform: translate(14px, 3px) rotate(180deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(3)::after {
  background: #c2c0c1;
  transform: rotate(30deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(4)::before {
  background: #cbcbcb;
  transform: rotate(-30deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(4) {
  background: #d2d2d2;
  transform: translate(14px, 3px) rotate(-90deg);
  transform-origin: 1px 12px;
}
.spin-loading > view:nth-child(4)::after {
  background: #dadada;
  transform: rotate(30deg);
  transform-origin: 1px 12px;
}

@keyframes spin-loading-spin {
  0% {
    transform: rotate3d(0, 0, 1, 0deg);
  }
  100% {
    transform: rotate3d(0, 0, 1, 360deg);
  }
}
</style>
