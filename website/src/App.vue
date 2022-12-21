<script setup>

import Message from "@/components/Message.vue";
</script>
<template>
  <n-message-provider>
    <Message/>
  </n-message-provider>
  <router-view/>
</template>
