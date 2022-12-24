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

import http from "@/util/http.js";
import {onMounted, reactive} from "vue";

const data = reactive({
  isLogin: false
})

onMounted(() => {
  http.get('/auth/isLogin').then(r => {
    data.isLogin = r.data.content
  })
})

</script>

<template>
  <n-space vertical>
    <n-card title="Community" size="small">
      <div v-if="data.isLogin">
        <router-link to="/publish">
          <n-button>Publish</n-button>
        </router-link>
      </div>
      <div v-else>
        You can
        <router-link to="/login">
          <n-gradient-text type="success">
            login
          </n-gradient-text>
        </router-link>
        or
        <router-link to="/register">
          <n-gradient-text type="success">
            register
          </n-gradient-text>
        </router-link>
      </div>
    </n-card>
  </n-space>
</template>