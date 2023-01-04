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
import http from "@/util/http";
import CommentItem from "@/components/comment/CommentItem.vue";

const props = defineProps<{
  postId: Number
}>()

const data = reactive({
  comment: {
    records: [],
    pages: 1,
    current: 1
  }
})

const refresh = () => {
  http.post("/comment/comments", {
    postId: props.postId,
    current: data.comment.current
  }).then(r => {
    data.comment = r.data.content
  })
}

onMounted(() => {
  refresh()
})

const page = (page: number) => {
  data.comment.current = page
  refresh()
}
</script>

<template>
  <n-list bordered>
    <n-list-item v-for="comment in data.comment.records">
      <CommentItem :data="comment"/>
    </n-list-item>
  </n-list>
  <div style="display: flex;justify-content: center" v-if="data.comment.records.length !== 0">
    <n-pagination v-model:page="data.comment.current" :page-count="data.comment.pages" :on-update:page="page"/>
  </div>
</template>

<style scoped>

</style>