<template>
  <div>
    <page-searchbar />
  </div>
</template>

<script setup lang="ts">
import { defineComponent } from 'vue';
import PageSearchbar from '../../components/page-searchbar/page-searchbar.vue';

export default defineComponent({
  components: {
    PageSearchbar
  }
});

</script>