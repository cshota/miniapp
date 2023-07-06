import Home from "@/pages/home/home.vue";
import Plus from "@/pages/home/plus.vue";
import Search from "@/pages/home/search.vue";

import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    routes: [
      {
        path: "../pages/home/home.vue",
        name: "Home",
        component: Home
      },
      {
        path: "../pages/home/plus.vue",
        name: "Plus",
        component: Plus
      },
      {
        path: "../pages/home/search.vue",
        name: "Search",
        component: Search
      }
    ]
  });

  export default router;
