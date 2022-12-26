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

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import Avatar from "@/components/Avatar.vue";
import {userInfo} from "@/util/request";
import http from "@/util/http";

const data = reactive({
  info: {
    username: undefined,
    avatar: null
  } as any
})

onMounted(() => {
  userInfo().then(info => {
    data.info = info
  })
})

const update = () => {
  http.post("/user/update", data.info).then(r => {
    if (r.data.code == 200) {
      window.$message.success("Update successful")
    }
  })
}

</script>
<template>
  <div style="display:flex;flex-direction:column;gap: 10px">
    <div style="display: flex;justify-content: center">
      <Avatar :avatar="data.info.avatar" :size="128"/>
    </div>
    <n-form ref="formRef" :model="data.info" label-placement="left" label-width="auto">
      <n-form-item label="Username" path="username">
        <n-input v-model:value="data.info.username"/>
      </n-form-item>

      <n-form-item label="Avtar" path="avatar">
        <n-input v-model:value="data.info.avatar"/>
      </n-form-item>

      <n-button type="primary" style="width: 100%" @click="update">
        Confirm
      </n-button>
    </n-form>
  </div>
</template>