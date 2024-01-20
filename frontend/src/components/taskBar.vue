<template>
  <div class="hello">
    <v-btn class="btn" v-for="(but,index) in buttons" :key="index" @click="handleclick(index)" :class="{ 'clicked': clickedButton === index }" :disabled=disablebutton[index] >
      <v-icon v-if="icons[index]!==''" :color="iconcolors[index]">{{ icons[index] }}</v-icon>
      {{ but }}</v-btn>

  </div>
</template>

<script>

export default {
  
  name: 'taskBar',
  data:()=>({
    
    buttons:["play","Stop","Add Machine","Add Queue","Stop input","Replay", "New Simulation",'connect items'],
    icons:["mdi-play","mdi-stop",'mdi-factory','mdi-queue-first-in-last-out','mdi-pause','mdi-replay','','mdi-arrow-right-bold'],
    iconcolors:["green",'red','black','black','red','black','black'],
    disablebutton:Array(8).fill(false),
    clickedButton:null,
    customcolors:[],
    isActive:false,

  }),
  methods:{
    open(){
      this.isActive=true;
    },
    handleclick(index){
      if(index!==6&&index!==4){
      this.clickedButton=index;
      }
      switch(index){
        case 0:
          this.disablebutton[2]=true
          this.disablebutton[3]=true
          this.play();
          break;
        case 1:
        this.disablebutton[2]=false
          this.disablebutton[3]=false
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
        case 7:
          this.addArrow();
          break;
        case 8:
          this.isActive=true;
          break; 
      }

    },
    play(){
      this.$emit('addMachine')
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
      this.$emit('newSimulation');
    },
    addArrow(){
      this.$emit('addArrow');
    },
    addColor(){
      this.customcolors.push()
    }

  },
 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello{
  display: flex;
  flex-wrap: wrap; 
  justify-content: center;  
  height: 9vh;
  width: auto;
  /* background-color: grey; */
  background-color: #365486;
  gap: 7px;
  align-items: center;
  border-radius: 10px;
}
.btn:hover{
  background-color: rgb(150, 202, 255);
  background-color:#7FC7D9;
}
.btn.clicked{
  background-color: dodgerblue;
  color: black;
}
input::placeholder{
  text-align: center;
}
.input-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
