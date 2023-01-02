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
import {onMounted, reactive} from "vue";
import {useRoute, useRouter} from "vue-router";
import http from "@/util/http";
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {userInfo} from "@/util/request";

const router = useRouter()
const route = useRoute()

const data = reactive({
  post: {} as any,
  me: false
})

onMounted(() => {
  http.post("/post/info", {id: route.query.id}).then(r => {
    data.post = r.data.content
    userInfo().then((info: any) => {
      if (info.id === data.post.userId) {
        data.me = true
      }
    })
  })
})
</script>

<template>
  <div style="display: flex;justify-content: space-between">
    <div>
      {{ data.post.title }}
    </div>
    <div v-if="data.me">
      <n-button type="primary" @click="router.push({name:'publish', query:{id:route.query.id}})">
        Edit
      </n-button>
    </div>
  </div>
  <md-editor v-model="data.post.content" preview-only/>
</template>

<style scoped>

</style>