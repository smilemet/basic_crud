<template>
  <div class="content">
    <nav class="mb-10">
      <router-link class="mx-3 underline" v-for="item in menu" :to="item.path" :key="item.name">
        {{ item.name }}
      </router-link>
    </nav>
  </div>
  <router-view />
</template>

<script setup>
import { ref } from "vue";

const menu = ref([
  { name: "Home", path: "/" },
  { name: "Member", path: "/member" },
]);
</script>
