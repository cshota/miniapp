<script setup lang="ts">
import { onLaunch, onShow, onHide } from "@dcloudio/uni-app";
import mini from "@/mini";

onLaunch(() => {
  console.log("App Launch");
  mini.init();
  mini
    .getToken()
    .then(() => mini.sendUserLog(uni.getStorageSync("CUSTOMER_COUPON_OPENID")))
    .catch(() => {
      mini.sendGuestLog();
    });
});
onShow(() => {
  console.log("App Show");
});
onHide(() => {
  console.log("App Hide");
});
</script>

<style>
page {
  background: #f8f8f8;
}
</style>
