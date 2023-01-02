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
import PostList from "@/components/post/PostList.vue";
import {useRoute} from "vue-router";


const data = reactive({
  category: 1,
  categories: []
})

onMounted(() => {
  http.get("/post/categories").then(r => {
    data.categories = r.data.content
  })
})

</script>
<template>
  <n-button-group>
    <n-tooltip v-for="category in data.categories" placement="top-start" trigger="hover">
      <template #trigger>
        <n-button ghost @click="data.category = category.id">{{ category.title }}
        </n-button>
      </template>
      {{ category.description }}
    </n-tooltip>
  </n-button-group>
  <PostList :category="data.category"/>
</template>


<style scoped>

</style>