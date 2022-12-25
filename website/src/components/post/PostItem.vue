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
import {normalTime} from "@/util/time";
import Avatar from "@/components/Avatar.vue";
import {onMounted, reactive} from "vue";
import http from "@/util/http";
import {useRouter} from "vue-router";
const router = useRouter();

const props = defineProps<{
  data: {
    id: Number,
    title: String,
    userId: Number,
    commentCount: Number,
    viewCount: Number,
    updateTime: Date
  }
}>()

const data = reactive({
  avatar: undefined
})

onMounted(() => {
  http.post("/user/info", {id: props.data.userId}).then(r => {
    data.avatar = r.data.content.avatar
  })
})

</script>

<template>
  <n-space justify="space-between" align="center">
    <n-space align="center">
      <Avatar :size="48" :avatar="data.avatar"/>
      <div>{{ props.data.commentCount }}/{{ props.data.viewCount }}</div>
      <div @click="router.push({name:'post',query:{id:props.data.id}})">
        {{ props.data.title }}
      </div>
    </n-space>

    <div>
      {{ normalTime(props.data.updateTime) }}
    </div>
  </n-space>
</template>