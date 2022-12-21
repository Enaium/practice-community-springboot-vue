import {createApp} from 'vue'
import App from '@/App.vue'
import naive from '@/util/naive'
import "@/style.css"
import router from "@/router";

createApp(App).use(router).use(naive).mount('#app')
