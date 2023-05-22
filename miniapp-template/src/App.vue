<script setup lang="ts">
import { onLaunch, onShow, onHide } from "@dcloudio/uni-app";
import mini from "@/mini";

onLaunch(() => {
  console.log("App Launch");
  mini.init();
  mini.sendGuestLog();
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
