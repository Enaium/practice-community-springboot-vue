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
import CommentList from "@/components/comment/CommentList.vue";
import LikeButton from "@/components/LikeButton.vue";
import DislikeButton from "@/components/DislikeButton.vue";

const router = useRouter()
const route = useRoute()

const data = reactive({
  post: {} as any,
  comment: ''
})

onMounted(() => {
  http.post("/post/info", {id: route.query.id}).then(r => {
    data.post = r.data.content
  })
})

const publish = () => {
  http.post("/comment/publish", {postId: data.post.id, content: data.comment}).then(r => {
    if (r.data.code == 200) {
      window.$message.success("success")
    }
  })
}

const like = () => {
  http.post("/post/update", {id: data.post.id, voteUp: 1})
}

const dislike = () => {
  http.post("/post/update", {id: data.post.id, voteDown: 1})
}
</script>

<template>
  <n-card>
    <div style="display: flex;justify-content:space-between;align-items: center">
      <div>{{ data.post.title }}</div>
      <div style="display: flex;align-items: center">
        <LikeButton @click="like"/>
        <span>{{ data.post.voteUp }}</span>
        <DislikeButton @click="dislike"/>
        <span>{{ data.post.voteDown }}</span>
      </div>
    </div>

    <md-editor v-model="data.post.content" preview-only/>
    <div style="display: flex;flex-direction: row-reverse">
      <n-button type="primary" @click="router.push({name:'publish', query:{id:route.query.id}})">
        Edit
      </n-button>
    </div>
  </n-card>

  <n-card title="Publish Comment">
    <n-space vertical>
      <md-editor v-model="data.comment"/>
      <n-button type="primary" @click="publish">
        Publish
      </n-button>
    </n-space>
  </n-card>
  <n-card :title="'Comment:'+data.post.commentCount">
    <CommentList :postId="data.post.id" v-if="data.post.id"/>
  </n-card>
</template>

<style scoped>

</style>