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
import {normalTime} from "@/util/time";
import {h, onMounted, reactive} from "vue";
import {NSwitch} from "naive-ui";
import http from "@/util/http";

const columns = [
  {
    title: "ID",
    key: "id",
    sorter: (row1: any, row2: any) => row1.id - row2.id
  },
  {
    title: "Content",
    key: "content"
  },
  {
    title: "Like",
    key: "voteUp"
  },
  {
    title: "Dislike",
    key: "voteDown"
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
          http.post("/comment/update", {id: row.id, del: row.del}).then(r => {
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
  comment: {
    records: [],
    pages: 1,
    current: 1
  },
  form: {
    content: undefined,
    voteUp: undefined,
    voteDown: undefined,
    delete: undefined,
    createTime: undefined,
    updateTime: undefined,
  },
})

const refresh = () => {
  http.post("/comment/comments", {
    current: data.comment.current,
    ...data.form
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

const filter = () => {
  refresh()
}
</script>

<template>
  <n-card title="Filter">
    <n-form>
      <n-form-item label="Title">
        <n-input v-model:value="data.form.content"/>
      </n-form-item>

      <n-form-item label="Vote Up">
        <n-input-number v-model:value="data.form.voteUp" clearable/>
      </n-form-item>

      <n-form-item label="Vote Down">
        <n-input-number v-model:value="data.form.voteDown" clearable/>
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
      :data="data.comment.records"
  />
  <div style="display: flex;justify-content: center">
    <n-pagination v-model:page="data.comment.current" :page-count="data.comment.pages" :on-update:page="page"/>
  </div>
</template>

<style scoped>

</style>