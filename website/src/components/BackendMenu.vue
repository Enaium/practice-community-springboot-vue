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
import {MenuOption} from "naive-ui";
import {h} from "vue";
import {RouterLink} from "vue-router";

const menuOptions: MenuOption[] = [
  {
    label: "User",
    children: [
      {
        label: () =>
            h(
                RouterLink,
                {
                  to: {
                    name: 'user-list'
                  }
                },
                {default: () => 'List'}
            ),
        key: "user-list"
      },
      {
        label: () =>
            h(
                RouterLink,
                {
                  to: {
                    name: 'user-create'
                  }
                },
                {default: () => 'Create'}
            ),
        key: "user-create"
      },
      {
        label: () =>
            h(
                RouterLink,
                {
                  to: {
                    name: 'user-post'
                  }
                },
                {default: () => 'Role'}
            ),
        key: "user-post"
      }
    ],
    key: "user"
  },
  {
    label: "Post",
    children: [
      {
        label: () =>
            h(
                RouterLink,
                {
                  to: {
                    name: 'post-list'
                  }
                },
                {default: () => 'PostList'}
            ),
        key: 'post-list'
      },
      {
        label: () =>
            h(
                RouterLink,
                {
                  to: {
                    name: 'comment-list'
                  }
                },
                {default: () => 'CommentList'}
            ),
        key: 'comment-list'
      }
    ],
    key: "post"
  }
]

</script>

<template>
  <n-menu :options="menuOptions"/>
</template>

<style scoped>

</style>