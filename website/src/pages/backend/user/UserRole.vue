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
import http from "@/util/http";

const data = reactive({
  id: 0,
  role: undefined as any,
  roles: [] as any,
})

onMounted(() => {
  http.get("/role/roles").then(r => {
    r.data.content.forEach((element: { name: string; id: number; }) => {
      data.roles.push({
        label: element.name,
        value: element.id
      })
    })
  })
})

const find = () => {
  http.post("role/user", {id: data.id}).then(r => {
    data.role = r.data.content
  })
}

const set = () => {
  http.post("/role/update", {id: data.id, role: data.role.id})
}
</script>

<template>
  <n-form inline>
    <n-form-item label="Find User" path="id">
      <n-input-number v-model:value="data.id" clearable/>
    </n-form-item>

    <n-form-item>
      <n-button attr-type="button" @click="find">
        Find
      </n-button>
    </n-form-item>
  </n-form>

  <div v-if="data.role">
    <h2>{{ data.role.name }}</h2>

    <n-form>
      <n-form-item label="Select Role" path="role">
        <n-select
            v-model:value="data.role.id"
            :options="data.roles"
            placeholder="Select Role"
        />
      </n-form-item>

      <n-form-item>
        <n-button attr-type="button" @click="set">
          Set
        </n-button>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped>

</style>