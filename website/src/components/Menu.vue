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
import {h, onMounted, reactive} from 'vue'
import type {MenuOption} from 'naive-ui'
import Logo from '@/assets/vue.svg'
import {RouterLink} from "vue-router";
import {isLogin} from "@/util/request";

const data = reactive({
  menu: [] as MenuOption[]
})

onMounted(() => {
  isLogin().then(isLogin => {
    if (isLogin) {
      data.menu = [
        {
          label: () =>
              h(
                  RouterLink,
                  {
                    to: {
                      name: 'home'
                    }
                  },
                  {default: () => 'Home'}
              ),
          key: "home"
        },
        {
          label: () =>
              h(
                  RouterLink,
                  {
                    to: {
                      name: 'search'
                    }
                  },
                  {default: () => 'Search'}
              ),
          key: "search"
        },
        {
          label: "Me",
          children: [
            {
              label: () =>
                  h(
                      RouterLink,
                      {
                        to: {
                          name: 'profile'
                        }
                      },
                      {default: () => 'Profile'}
                  ),
              key: "me-profile"
            },
            {
              label: () =>
                  h(
                      RouterLink,
                      {
                        to: {
                          name: 'space'
                        }
                      },
                      {default: () => 'Space'}
                  ),
              key: "me-space"
            }
          ],
          key: "me"
        },

        {
          label: () =>
              h(
                  RouterLink,
                  {
                    to: {
                      name: 'logout'
                    }
                  },
                  {default: () => 'Logout'}
              ),
          key: "logout"
        }
      ]
    } else {
      data.menu = [
        {
          label: () =>
              h(
                  RouterLink,
                  {
                    to: {
                      name: 'register'
                    }
                  },
                  {default: () => 'Register'}
              ),
          key: "register"
        },
        {
          label: () =>
              h(
                  RouterLink,
                  {
                    to: {
                      name: 'login'
                    }
                  },
                  {default: () => 'Login'}
              ),
          key: "login"
        }
      ]
    }
  })
})
</script>

<template>
  <div style="display: flex;justify-content: space-between;align-items: center">
    <div style="display: flex;justify-content: center">
      <img
          :src="Logo"
          @click="this.$router.push({path:'/'})"
      />
      <h3 @click="this.$router.push({path:'/'})">Community</h3>
    </div>

    <n-menu :options="data.menu" mode="horizontal"/>
  </div>
</template>

<style scoped>
.n-space {
  background: darkgrey;
}
</style>