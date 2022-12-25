/*
 * Copyright (c) 2022 Enaium
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 */

import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";
import Login from '@/pages/Login.vue';
import Home from "@/pages/Home.vue";
import Register from "@/pages/Register.vue";
import Publish from "@/pages/Publish.vue";
import Profile from "@/pages/Profile.vue";
import Main from "@/layouts/Main.vue";
import Logout from "@/pages/Logout.vue";
import Post from "@/pages/Post.vue";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/",
        name: "main",
        component: Main,
        children: [
            {
                path: "/",
                name: "home",
                component: Home,
            },
            {
                path: "/profile",
                name: "profile",
                component: Profile,
            },
            {
                path: "/publish",
                name: "publish",
                component: Publish,
            },
            {
                path: "/logout",
                name: "logout",
                component: Logout
            },
            {
                path: "/post",
                name: "post",
                component: Post
            }
        ]
    },
    {
        path: "/register",
        name: "register",
        component: Register,
    },
    {
        path: "/login",
        name: "login",
        component: Login,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router