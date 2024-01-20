<template>
  <div class="hello">
    <v-btn class="btn" v-for="(but,index) in buttons" :key="index" @click="handleclick(index)" :class="{ 'clicked': clickedButton === index }">
      <v-icon v-if="icons[index]!==''" :color="iconcolors[index]">{{ icons[index] }}</v-icon>
      {{ but }}</v-btn>

    <v-dialog v-model="props">
      <template v-slot:activator="{ props }">
        <v-btn
        color="primary"
        v-bind="props"
        prepend-icon="mdi-pen"
        >
        Compose
    </v-btn>
</template>
          <v-card width="600px" height="720px" title="Custom color input">
            <v-card-actions>
              <v-text-field
                clearable
                hide-details="auto"
                label="Enter color"
                class="mr-3"
              ></v-text-field>
            </v-card-actions>

            <!-- List below the input field -->
            <v-card-actions>
              <v-list>
                <v-list-item
                  v-for="(co,index) in customcolors"
                  :key="index"
                  :title="co"
                ></v-list-item>
              </v-list>
            </v-card-actions>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn text="Save" @click="addColor(writtencolor)"></v-btn>
              <v-btn text="Close" @click="isActive=false"></v-btn>
            </v-card-actions>
          </v-card>
        
      </v-dialog>
  </div>
</template>

<script>

export default {
  
  name: 'taskBar',
  data(){
    return{
    buttons:["play","Stop","Add Machine","Add Queue","Stop input","Replay", "New Simulation",'connect items','custom Input'],
    icons:["mdi-play","mdi-stop",'mdi-factory','mdi-queue-first-in-last-out','mdi-pause','mdi-replay','','mdi-arrow-right-bold',''],
    iconcolors:["green",'red','black','black','red','black','black','black'],
    clickedButton:null,
    customcolors:[],
    isActive:false,
    writtencolor:'red'

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
        case 7:
          this.addArrow();
          break;
        case 8:
          this.isActive=true;
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
    },
    addArrow(){
      console.log('arrow pew pew');
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
  color: black;
}
</style>
