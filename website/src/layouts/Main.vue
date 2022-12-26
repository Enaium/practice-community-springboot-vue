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
import Menu from "@/components/Menu.vue";
import Side from "@/components/Side.vue";
import Footer from "@/components/Footer.vue";</script>

<template>
  <div style="display: flex;flex-direction: column;min-height: 100vh">
    <Menu/>
    <n-layout has-sider>
      <n-layout-content content-style="padding: 24px;">
        <n-card>
          <router-view/>
        </n-card>
      </n-layout-content>
      <n-layout-sider content-style="padding: 24px;">
        <Side/>
      </n-layout-sider>
    </n-layout>
    <Footer/>
  </div>
</template>

<style scoped>
.n-layout {
  padding: 0 !important;
  margin: 0 !important;
}
</style>