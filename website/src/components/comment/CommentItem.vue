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
import {userInfo} from "@/util/request";
import {useRouter} from "vue-router";
import Avatar from "@/components/Avatar.vue";
import {normalTime} from "@/util/time";
import LikeButton from "@/components/LikeButton.vue";
import DislikeButton from "@/components/DislikeButton.vue";
import http from "@/util/http";

const router = useRouter();

const props = defineProps<{
  data: {
    id: Number,
    userId: Number,
    content: String,
    voteUp: Number,
    voteDown: Number,
    updateTime: Date
  }
}>()

const data = reactive({
  user: {} as any
})

onMounted(() => {
  userInfo(props.data.userId).then(r => {
    data.user = r
  })
})

const like = () => {
  http.post("/comment/update", {id: props.data.id, voteUp: 1})
}

const dislike = () => {
  http.post("/comment/update", {id: props.data.id, voteDown: 1})
}
</script>

<template>
  <div style="display: flex;align-items: center">
    <Avatar :size="48" :avatar="data.user.avatar" @click="router.push({name:'space', query:{id:props.data.userId}})"/>

    <div style="display: flex;flex-direction:column;justify-content: center;width: 100%">
      <div style="display: flex;justify-content:space-between;align-items: center">
        <div>{{ data.user.username }} {{ normalTime(props.data.updateTime) }}</div>
        <div style="display: flex;align-items: center">
          <LikeButton @click="like"/>
          <span>{{ props.data.voteUp }}</span>
          <DislikeButton @click="dislike"/>
          <span>{{ props.data.voteDown }}</span>
        </div>
      </div>
      <div>{{ props.data.content }}</div>
    </div>
  </div>
</template>

<style scoped>

</style>