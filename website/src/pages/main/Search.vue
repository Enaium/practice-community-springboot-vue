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
import PostItem from "@/components/post/PostItem.vue";

const data = reactive({
  title: '',
  post: {
    records: [],
    pages: 1,
    current: 1
  }
})

const search = () => {
  http.post("/post/posts", {
    title: data.title,
    current: data.post.current
  }).then(r => {
    data.post = r.data.content
  })
}

const page = (page: number) => {
  data.post.current = page
  search()
}
</script>

<template>
  <div style="display: flex;gap: 5px">
    <n-input v-model:value="data.title"/>
    <n-button type="primary" @click="search">
      Search
      <template #icon>
        <n-icon>
          <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512">
            <path
                d="M456.69 421.39L362.6 327.3a173.81 173.81 0 0 0 34.84-104.58C397.44 126.38 319.06 48 222.72 48S48 126.38 48 222.72s78.38 174.72 174.72 174.72A173.81 173.81 0 0 0 327.3 362.6l94.09 94.09a25 25 0 0 0 35.3-35.3zM97.92 222.72a124.8 124.8 0 1 1 124.8 124.8a124.95 124.95 0 0 1-124.8-124.8z"
                fill="currentColor"></path>
          </svg>
        </n-icon>
      </template>
    </n-button>
  </div>
  <div v-if="data.post.records.length !== 0">
    <n-list bordered>
      <n-list-item v-for="post in data.post.records">
        <PostItem :data="post"/>
      </n-list-item>
    </n-list>

    <div style="display: flex;justify-content: center">
      <n-pagination v-model:page="data.post.current" :page-count="data.post.pages" :on-update:page="page"/>
    </div>
  </div>
</template>

<style scoped>

</style>