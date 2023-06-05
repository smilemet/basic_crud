<template>
  <div>
    <h2>회원 목록</h2>
    <table class="mt-10 w-full text-center">
      <thead>
        <tr>
          <th>ID</th>
          <th>이메일</th>
          <th>패스워드</th>
          <th>조작</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="member in members" :key="member.id">
          <td>{{ member.id }}</td>
          <td>{{ member.email }}</td>
          <td>{{ member.password }}</td>
          <td>
            <button class="mx-1 px-2 bg-gray-100" @click="editMember(member)">수정</button>
            <button class="mx-1 px-2 bg-gray-100" @click="deleteMember(member.id)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
    <button class="mt-10 px-5 py-1 bg-red-50" @click="addMember">회원 추가</button>
  </div>
</template>

<script>
import axios from "axios";
const url = "http://localhost:8080/api";

export default {
  data() {
    return {
      members: [],
    };
  },
  created() {
    axios
      .get(url + "/member")
      .then((response) => {
        this.members = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    addMember() {
      const id = this.members[this.members.length - 1].id + 1;
      const email = prompt("이메일");
      const password = prompt("비밀번호");
      this.members.push({ id, email, password });

      axios
        .post(url + "/member", { email, password })
        .then((response) => {
          console.log(response);
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    editMember(member) {
      const newEmail = prompt("새 이메일", member.email);
      const newPassword = prompt("새 패스워드", member.password);
      member.email = newEmail;
      member.password = newPassword;

      axios
        .post(url + "/member/" + member.id, member)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    deleteMember(id) {
      axios
        .delete(url + "/member/" + id)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
