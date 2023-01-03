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
import {useRoute, useRouter} from "vue-router";
import {onMounted, reactive} from "vue";
import {id, userInfo} from "@/util/request";
import {diffDay, normalTime} from "@/util/time";
import Avatar from "@/components/Avatar.vue";
import PostList from "@/components/post/PostList.vue";

const route = useRoute()
const router = useRouter()

const data = reactive({
  info: {} as any
})

onMounted(() => {
  if (route.query.id) {
    userInfo(route.query.id).then(info => {
      data.info = info
    })
  } else {
    id().then(id => {
      userInfo(id).then(info => {
        data.info = info
      })
    })
  }
})

</script>

<template>
  <div style="display: flex">
    <Avatar :avatar="data.info.avatar" :size="128"/>
    <div style="display: flex;flex-direction: column;justify-content: space-between;font-size: 2em">
      <div>Username:{{ data.info.username }}</div>
      <div>Create Time:{{ normalTime(data.info.createTime) }}</div>
      <div>Update Time:{{ normalTime(data.info.updateTime) }}({{ diffDay(data.info.updateTime, new Date()) }} Days)
      </div>
    </div>
  </div>
  <n-divider/>
  <div style="display: flex;align-content: center;gap: 5px">
    <n-tag type="success">Post:{{ data.info.postCount }}</n-tag>
    <n-tag type="success">Comment:{{ data.info.commentCount }}</n-tag>
    <n-tag type="error" v-if="data.info.banned">
      Banned
    </n-tag>
  </div>
  <n-divider></n-divider>
  <PostList :post="data.info.id" v-if="data.info.id"/>
</template>

<style scoped>

</style>