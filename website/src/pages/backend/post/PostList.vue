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
import {NSwitch} from "naive-ui";

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
      return h(NSwitch, {
        value: row.draft,
        onClick: () => {
          row.draft = !row.draft
          http.post("/post/publish", {id: row.id, draft: row.draft}).then(r => {
            if (r.data.code == 200) {
              window.$message.success("Success")
            }
          })
        }
      })
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
      return h(NSwitch, {
        value: row.del,
        onClick: () => {
          row.del = !row.del
          http.post("/post/publish", {id: row.id, del: row.del}).then(r => {
            if (r.data.code == 200) {
              window.$message.success("Success")
            }
          })
        }
      })
    }
  }
]

const data = reactive({
  post: {
    records: [],
    pages: 1,
    current: 1
  },
  form: {
    title: undefined,
    categoryId: undefined,
    viewCount: undefined,
    commentCount: undefined,
    voteUp: undefined,
    voteDown: undefined,
    draft: undefined,
    delete: undefined,
    createTime: undefined,
    updateTime: undefined,
  },
  categories: [] as any,
})

const refresh = () => {
  http.post("/post/posts", {
    current: data.post.current,
    ...data.form
  }).then(r => {
    data.post = r.data.content
  })
}

onMounted(() => {
  refresh()
  http.get("/category/categories").then(r => {
    r.data.content.forEach((element: { title: string; id: number; }) => {
      data.categories.push({
        label: element.title,
        value: element.id
      })
    })
  })
})

const page = (page: number) => {
  data.post.current = page
  refresh()
}

const filter = () => {
  refresh()
}
</script>

<template>
  <n-card title="Filter">
    <n-form>
      <n-form-item label="Title">
        <n-input v-model:value="data.form.title"/>
      </n-form-item>

      <n-form-item label="Category">
        <n-select
            v-model:value="data.form.categoryId"
            :options="data.categories"
            placeholder="Select Category"
        />
      </n-form-item>

      <n-form-item label="View Count">
        <n-input-number v-model:value="data.form.viewCount" clearable/>
      </n-form-item>

      <n-form-item label="Comment Count">
        <n-input-number v-model:value="data.form.commentCount" clearable/>
      </n-form-item>

      <n-form-item label="Vote Up">
        <n-input-number v-model:value="data.form.voteUp" clearable/>
      </n-form-item>

      <n-form-item label="Vote Down">
        <n-input-number v-model:value="data.form.voteDown" clearable/>
      </n-form-item>

      <n-form-item label="Draft">
        <n-switch v-model:value="data.form.draft"/>
      </n-form-item>

      <n-form-item label="Delete">
        <n-switch v-model:value="data.form.delete"/>
      </n-form-item>

      <n-form-item label="Create Time">
        <n-date-picker v-model:value="data.form.createTime" type="datetime" clearable/>
      </n-form-item>

      <n-form-item label="Update Time">
        <n-date-picker v-model:value="data.form.updateTime" type="datetime" clearable/>
      </n-form-item>

      <n-form-item>
        <n-button type="primary" @click="filter">
          Filter
        </n-button>
      </n-form-item>
    </n-form>

  </n-card>
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