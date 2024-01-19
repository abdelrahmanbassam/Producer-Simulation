<template>
  <div class="hello">
    <v-btn class="btn" v-for="(but,index) in buttons" :key="index" @click="handleclick(index)" :class="{ 'clicked': clickedButton === index }">
      {{ but }}</v-btn>
  </div>
</template>

<script>

export default {
  
  name: 'taskBar',
  data(){
    return{
    buttons:["play","Stop","Add Machine","Add Queue","Stop input","Replay", "New Simulation"],
    clickedButton:null
    }
  },
  methods:{
    handleclick(index){
      this.clickedButton=index;
      switch(index){
        case 0:
          this.play();
          break;
        case 1:
          this.stop();
          break
        case 2:
          this.addMachine();
          break;
        case 3:
          this.addQueue();
          break;
        case 4:
          this.stopInput();
          break;
        case 5:
          this.replay();
          break;
        case 6:
          this.newSim();
          break;  
      }

    },
    play(){
      this.$emit('addArrow');
      console.log("play");
    },
    stop(){
      console.log("stop");
    },
    addMachine(){
      this.$emit('addMachine');
    },
    addQueue(){
      this.$emit('addQueue');
    },
    stopInput(){
      console.log("stop input");
    },
    replay(){
      console.log("replay");
    },
    newSim(){
      console.log("newsim")
    }

  },
 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello{
  display: flex;
  flex-wrap: wrap; /* Allow buttons to wrap to the next line if necessary */
  justify-content: center;  
  height: 7vh;
  width: auto;
  background-color: grey;
  gap: 7px;
  align-items: center;
  border-radius: 10px;
}
.btn:hover{
  background-color: rgb(150, 202, 255);
}
.btn.clicked{
  background-color: dodgerblue;
  color: white;
}
</style>
