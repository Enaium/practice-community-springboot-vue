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
import {h, onMounted, reactive} from "vue";
import http from "@/util/http";
import {normalTime} from "@/util/time";
import {NButton} from "naive-ui";

const columns = [
  {
    title: "ID",
    key: "id",
    sorter: (row1: any, row2: any) => row1.id - row2.id
  },
  {
    title: "Title",
    key: "title"
  },
  {
    title: "Draft",
    key: "draft",
    render(row: any) {
      return h(NButton, {
        type: row.draft ? "error" : "primary"
      }, {default: () => "Draft"})
    }
  },
  {
    title: "View",
    key: "viewCount"
  },
  {
    title: "Comment",
    key: "commentCount"
  },
  {
    title: "Create Time",
    key: "createTime",
    render(row: any) {
      return normalTime(row.createTime)
    }
  },
  {
    title: "Update Time",
    key: "updateTime",
    render(row: any) {
      return normalTime(row.updateTime)
    }
  },
  {
    title: "Delete",
    key: "del",
    render(row: any) {
      return h(NButton, {
        type: row.draft ? "error" : "primary"
      }, {default: () => row.banned ? "Recover" : "Delete"})
    }
  }
]

const data = reactive({
  post: {
    records: [],
    pages: 1,
    current: 1
  }
})

const refresh = () => {
  http.post("/post/posts", {
    current: data.post.current
  }).then(r => {
    data.post = r.data.content
  })
}

onMounted(() => {
  refresh()
})

const page = (page: number) => {
  data.post.current = page
  refresh()
}
</script>

<template>
  <n-data-table
      ref="dataTableInst"
      :columns="columns"
      :data="data.post.records"
  />
  <div style="display: flex;justify-content: center">
    <n-pagination v-model:page="data.post.current" :page-count="data.post.pages" :on-update:page="page"/>
  </div>
</template>

<style scoped>

</style>