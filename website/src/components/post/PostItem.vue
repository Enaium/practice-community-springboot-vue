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
import Avatar from "@/components/Avatar.vue";
import {onMounted, reactive} from "vue";
import http from "@/util/http";
import {useRouter} from "vue-router";
import {normalTime} from "@/util/time";

const router = useRouter();

const props = defineProps<{
  data: {
    id: Number,
    draft: Boolean,
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
  <div style="display: flex;justify-content:space-between;align-items: center">
    <div style="display: flex;align-items: center;gap: 10px">
      <Avatar :size="48" :avatar="data.avatar" @click="router.push({name:'space', query:{id:props.data.userId}})"/>
      <div>{{ props.data.commentCount }}/{{ props.data.viewCount }}</div>
      <div @click="router.push({name:'post', query:{id:props.data.id}})">
        {{ props.data.title }}
      </div>
    </div>

    <div style="display: flex;align-items: center;gap: 10px">
      <n-tag type="warning" v-if="props.data.draft">
        Draft
      </n-tag>
      {{ normalTime(props.data.updateTime) }}
    </div>
  </div>
</template>