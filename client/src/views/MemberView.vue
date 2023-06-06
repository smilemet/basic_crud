<template>
  <div>
    <h2 class="text-xl font-bold">회원 목록</h2>
    <div class="mt-5 py-3 border-y-2">
      <table class="w-full text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>이메일</th>
            <th>패스워드</th>
            <th>메뉴</th>
          </tr>
        </thead>
        <tbody v-if="members.length !== 0">
          <tr v-for="member in members" :key="member.id">
            <td>{{ member.id }}</td>
            <td>{{ member.email }}</td>
            <td>{{ member.password }}</td>
            <td>
              <button class="mx-1 px-2 bg-gray-100" @click="editMember(member)">수정</button>
              <button class="mx-1 px-2 bg-gray-100" @click="deleteMember(member.id)">삭제</button>
              <router-link class="mx-1 px-2 bg-gray-100" :to="`/member/${member.id}`">상세</router-link>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td colspan="4">회원이 없습니다.</td>
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

const url = process.env.VUE_APP_API;

let members = ref([]);

const addMember = () => {
  const id = members.value[members.value.length - 1].id + 1;
  const email = prompt("이메일");
  const password = prompt("비밀번호");
  members.value.push({ id, email, password });

  axios
    .post(url + "/member", { email, password })
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.error(error);
    });
};

const editMember = (member) => {
  const newEmail = prompt("새 이메일", member.email);
  const newPassword = prompt("새 패스워드", member.password);
  member.email = newEmail;
  member.password = newPassword;

  axios
    .post(url + `/member/${member.id}`, member)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.error(error);
    });
};

const deleteMember = (id) => {
  axios
    .delete(url + `/member/${id}`)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.error(error);
    });
};

onMounted(() => {
  axios
    .get(url + "/member")
    .then((response) => {
      members.value = response.data;
    })
    .catch((error) => {
      console.error(error);
    });
});
</script>
