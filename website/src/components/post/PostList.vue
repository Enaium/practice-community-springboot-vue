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
import {onMounted, reactive, watch} from "vue";
import http from "@/util/http";
import PostItem from "@/components/post/PostItem.vue";

const props = defineProps({
  category: Number,
  user: Number
})


const data = reactive({
  post: {
    records: [],
    pages: 1,
    current: 1
  }
})

const posts = (categoryId: any, userId?: any) => {
  http.post("/post/posts", {
    categoryId,
    userId,
    current: data.post.current
  }).then(r => {
    data.post = r.data.content
  })
}

const refresh = () => {
  posts(props.category, props.user)
}

watch(() => props.category, (category) => {
  posts(category, props.user)
})

const page = (page: number) => {
  data.post.current = page
  refresh()
}


onMounted(() => {
  refresh()
})

</script>
<template>
  <n-list bordered>
    <n-list-item v-for="post in data.post.records">
      <PostItem :data="post"/>
    </n-list-item>
  </n-list>
  <div style="display: flex;justify-content: center">
    <n-pagination v-model:page="data.post.current" :page-count="data.post.pages" :on-update:page="page"/>
  </div>
</template>
<style scoped>

</style>