<template>
  <div class="hello">
    <v-dialog v-model="isActive" persistent width="1024">
        <template v-slot:activator="{ props }">
    <v-btn class="btn" v-for="(but,index) in buttons" :key="index" @click="handleclick(index)" :class="{ 'clicked': clickedButton === index }" v-bind="props">
      <v-icon v-if="icons[index]!==''" :color="iconcolors[index]">{{ icons[index] }}</v-icon>
      {{ but }}</v-btn>

      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Custom colors</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  label="Enter the colors"
                  required
                  v-model="writtencolor"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-list lines="one">
                <v-list-item
                  v-for="(n,index) in customcolors"
                  :key="index"
                  :title="n"
                ></v-list-item>
              </v-list>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue-darken-1" variant="text" @click="isActive = false">
            Close
          </v-btn>
          <v-btn
            color="blue-darken-1"
            variant="text"
            @click="addColor(writtencolor)"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>

export default {
  
  name: 'taskBar',
  data:()=>({
    
    buttons:["play","Stop","Add Machine","Add Queue","Stop input","Replay", "New Simulation",'connect items','custom Input'],
    icons:["mdi-play","mdi-stop",'mdi-factory','mdi-queue-first-in-last-out','mdi-pause','mdi-replay','','mdi-arrow-right-bold',''],
    iconcolors:["green",'red','black','black','red','black','black','black'],
    clickedButton:null,
    customcolors:[],
    isActive:false,
    writtencolor:'red'
  }),
  methods:{
    open(){
      this.isActive=true;
    },
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
  height: 7vh;
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
</style>
