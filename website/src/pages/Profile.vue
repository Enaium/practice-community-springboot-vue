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
import http from "@/util/http";
import {diffDay, normalTime} from "@/util/time";
import Avatar from "@/components/Avatar.vue";

const data = reactive({
  info: {
    username: undefined,
    avatar: null,
    post_count: 0,
    comment_count: 0,
    banned: 0,
    createTime: new Date(),
    updateTime: new Date()
  }
})

onMounted(() => {
  http.post("/user/info").then(r => {
    data.info = r.data.content
  })
})

</script>
<template>
  <div style="display: flex">
    <Avatar :avatar="data.info.avatar" :size="128"/>
    <div style="display: flex;flex-direction: column;justify-content: space-between;font-size: 2em">
      <div>Username:{{ data.info.username }}</div>
      <div>Create Time:{{ normalTime(data.info.createTime) }}</div>
      <div>Last Time:{{ normalTime(data.info.updateTime) }}({{ diffDay(data.info.updateTime, new Date()) }} Days)</div>
    </div>
  </div>

</template>