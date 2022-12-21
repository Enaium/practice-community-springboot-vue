<!--
  - Copyright (c) 2022 Enaium
  -
  - Permission is hereby granted, free of charge, to any person obtaining a copy
  - of this software and associated documentation files (the "Software"), to deal
  - in the Software without restriction, including without limitation the rights
  - to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  - copies of the Software, and to permit persons to whom the Software is
  - furnished to do so, subject to the following conditions:
  -
  - The above copyright notice and this permission notice shall be included in all
  - copies or substantial portions of the Software.
  -
  - THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  - IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  - FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  - AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  - LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  - OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  -->
<script setup lang="ts">
import {reactive} from "vue";
import http from "@/util/http";

const data = reactive({
  form: {
    username: '',
    password: ''
  }
})

const login = () => {
  http.post("/auth/login", data.form).then(result => {
    if (result.data.code == 200) {
      localStorage.setItem("token", result.data.content)
      window.$message.success(result.data.message)
    }
  })
}
</script>
<template>
  <n-form ref="formRef" :model="data.form" style="margin:0 280px">
    <n-form-item path="username" label="username">
      <n-input v-model:value="data.form.username"/>
    </n-form-item>

    <n-form-item path="password" label="password">
      <n-input v-model:value="data.form.password"/>
    </n-form-item>

    <n-button
        round
        type="primary"
        @click="login"
        style="width: 100%"
    >
      Login
    </n-button>
  </n-form>
</template>

<style scoped>
</style>