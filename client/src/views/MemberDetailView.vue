<template>
  <div>
    <h2 class="text-xl font-bold">회원 상세정보</h2>
    <div class="mt-5 py-3 border-y-2">
      <table class="w-full text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>이메일</th>
            <th>패스워드</th>
          </tr>
        </thead>
        <tbody v-if="member !== null">
          <tr>
            <td>{{ member.id }}</td>
            <td>{{ member.email }}</td>
            <td>{{ member.password }}</td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td>hello?</td>
          </tr>
        </tbody>
      </table>
    </div>

    <button class="mt-10 px-5 py-1 bg-red-50" @click="addMember">회원 추가</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";

const url = process.env.VUE_APP_API;

const id = ref(null);
const member = ref(null);
const route = useRoute();

onMounted(() => {
  id.value = route.params.id;

  axios
    .get(url + `/member/${id.value}`)
    .then((response) => {
      member.value = response.data;
    })
    .catch((error) => {
      console.error(error);
    });
});
</script>
