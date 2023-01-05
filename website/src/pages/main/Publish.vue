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
import {onMounted, reactive} from 'vue';
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import http from "@/util/http";
import {useRoute, useRouter} from "vue-router";

const router = useRouter()
const route = useRoute()

const data = reactive({
  categories: [] as any,
  post: {
    categoryId: 1,
    title: '',
    content: '# Hello Markdown Editor',
    draft: true
  }
})

onMounted(() => {
  http.get("/category/categories").then(r => {
    r.data.content.forEach((element: { title: string; id: number; }) => {
      data.categories.push({
        label: element.title,
        value: element.id
      })
    })
  })

  if (route.query.id) {
    http.post("/post/info", {id: route.query.id}).then(r => {
      data.post = r.data.content
    })
  }
})

const publish = () => {
  http.post("/post/publish", data.post).then(r => {
    if (r.data.code == 200) {
      window.$message.success(r.data.message)
      router.back()
    }
  })
}

</script>

<template>
  <n-form>

    <n-form-item label="Category" path="category">
      <n-select
          v-model:value="data.post.categoryId"
          :options="data.categories"
          placeholder="Select Category"
      />
    </n-form-item>

    <n-form-item label="Title" path="title">
      <n-input v-model:value="data.post.title"/>
    </n-form-item>

    <n-form-item label="Content" path="content">
      <md-editor v-model="data.post.content"/>
    </n-form-item>

    <n-form-item label="Draft" path="draft">
      <n-switch v-model:value="data.post.draft"/>
    </n-form-item>

    <div style="display: flex;flex-direction: row-reverse">
      <n-button round type="primary" @click="publish">
        Publish
      </n-button>
    </div>
  </n-form>
</template>