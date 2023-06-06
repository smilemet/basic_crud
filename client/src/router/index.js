import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import MemberView from "../views/MemberView.vue";
import MemberDetailView from "../views/MemberDetailView.vue";

const routes = [
  { path: "/", name: "Home", component: HomeView },
  { path: "/member", name: "Member", component: MemberView },
  { path: "/member/:id", name: "MemberDetail", component: MemberDetailView },
];

const router = createRouter({
  history: createWebHistory(process.env.VUE_APP_URL),
  routes,
});

export default router;
