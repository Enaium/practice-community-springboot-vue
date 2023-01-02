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
import {ref} from "vue";
import http from "@/util/http";

const user = ref(0)
const ban = () => {
  http.post("/user/ban", {id: user.value, ban: true})
}

const unban = () => {
  http.post("/user/ban", {id: user.value, ban: false})
}
</script>

<template>
  <n-form>
    <n-form-item label="Ban ID" path="banId">
      <n-input-number v-model:value="user" clearable/>
    </n-form-item>
    <n-button
        round
        type="error"
        @click="ban"
    >
      Ban
    </n-button>
    <n-button
        round
        type="success"
        @click="unban"
    >
      Unban
    </n-button>
  </n-form>
</template>

<style scoped>

</style>