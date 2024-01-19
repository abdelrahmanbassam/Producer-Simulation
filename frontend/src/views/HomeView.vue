<template>
  <div class="home">
    <taskBar/>
  </div>
</template>

<script>
// @ is an alias to /src
import taskBar from '@/components/taskBar.vue'
export default {
  name: 'HomeView',
  components: {
    taskBar
  }
}
</script>
