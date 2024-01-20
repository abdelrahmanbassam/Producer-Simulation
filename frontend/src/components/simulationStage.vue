<template>
    
    <div class="page">
     <taskBar @addMachine="addMachine" @addQueue="addQueue" @addArrow="addArrow" @startSimulation="startSimulation" @newSimulation="newSimulation"/>

     <div class="konva-holder"></div>
    </div>
</template>

<script>
import Konva from 'konva';
import taskBar from './taskBar.vue'

export default {
    components: {
     taskBar,
    },
   data() {
     return {
      allMachines:[],  
      allQueues:[],  
      allArrows:[],  
      allShapes:[],
      layer:null,
      newQueueId:0,
      newMachineId:0,
      arrowSrc:null,
      arrowDest:null,
      isDrawingQueue:false,
      isDrawingMachine:false,
      isDrawingArrow:false,
      isStartedArrow:false,
      
     }
   },

   mounted() {
    this.createKonvaStage();
   },

   methods:{

    addMachine(){
     this.clear();
     this.isDrawingMachine=true;
    }, 

    addQueue(){
     this.clear();
     this.isDrawingQueue=true;
    },

    addArrow(){
     this.clear();
     this.isDrawingArrow=true;
    },

    clear(){
      this.isDrawingQueue=false;
      this.isDrawingMachine=false;
      this.isDrawingArrow=false;
      this.isStartedArrow=false;
    },

    async createKonvaStage() {//creating stage with events (if i click a shape then i clicked the screen it will create a shape)create line in this(special case ) 
     const viewportWidth = Math.max(document.documentElement.clientWidth || 0, window.innerWidth || 0);
     const viewportHeight = Math.max(document.documentElement.clientHeight || 0, window.innerHeight || 0);

     const stageWidth = viewportWidth * (98.8 / 100); // Replace percentageWidth with your desired percentage
     const stageHeight = viewportHeight * (85 /100); // Replace percentageHeight with your desired percentage

     this.stage = new Konva.Stage({
        container: '.konva-holder',
        width: stageWidth,
        height: stageHeight,
     });

     this.layer = new Konva.Layer();
     this.stage.add(this.layer);

     let newShape={};

     this.stage.on('click', (e) => {
        // console.log(this.stage.getPointerPosition().x)
        // console.log(this.stage.getPointerPosition().y)
        if(this.isDrawingQueue||this.isDrawingMachine){
            newShape.x = this.stage.getPointerPosition().x;
            newShape.y = this.stage.getPointerPosition().y;
        
          if(this.isDrawingQueue){
         
            newShape.type='queue'
            newShape.fill='red'
            newShape.text='Q'+this.newQueueId.toString();
            this.newQueueId++;
            this.sendQueueToBack(newShape);

          }
          else if(this.isDrawingMachine){
            newShape.type='machine'
            newShape.fill='blue'
            newShape.text='M'+this.newMachineId.toString();
            this.newMachineId++;
            this.sendMachineToBack(newShape);
          }
          
        }
        
       
     });
      
    },

    async  clearAndDraw() {//clear the layer and draw the array elements again using this.drawShape 
        this.layer.destroyChildren();
        this.allArrows.forEach((shape) => {
            this.drawKonvaShape(shape,'arrow');
        });
        this.allQueues.forEach((shape) => {

            this.drawKonvaShape(shape,'queue');
        });
        this.allMachines.forEach((shape) => {
            this.drawKonvaShape(shape,'machine');
        });
    },

    drawKonvaShape(shape,type) {// (print the array ) take a shape object and convert it to element of Konva and have the events of every shape(methods)
       let newShape=null;
        if (type=== 'arrow') {
        newShape =  new Konva.Arrow({
                points: shape.points, // Define the start and end points of the arrow
                pointerLength: 7.5,  // Length of the pointer head
                pointerWidth: 7.5,   // Width of the pointer head
                fill: 'black',      // Fill color
                stroke: 'black',    // Stroke color
                strokeWidth: 2      // Stroke width
            });
        } 
        else if (type === 'machine') {
            newShape = new Konva.Circle(shape);
            newShape.attrs.stroke='black';
            newShape.attrs.radius=40;
            
        }
        else if (type === 'queue') {
          newShape=new Konva.Rect(shape);
          newShape.attrs.stroke='black';
          newShape.attrs.width=80;
          newShape.attrs.height=50;
          newShape.attrs.offsetX=40, // Half of the width
          newShape.attrs.offsetY=25  // Half of the height
        }
        
        let text = new Konva.Text({
            x: newShape.attrs.x-10,
            y: newShape.attrs.y-10,
            text: shape.text,
            fontSize: 15,
            fontFamily: 'Calibri',
            fill: 'white',
            align: 'center',
            verticalAlign: 'middle'
        });  
        
        let group = new Konva.Group();
        group.on('click', () => {
            if(this.isDrawingArrow && !this.isStartedArrow){
                this.isStartedArrow = true;
                this.arrowSrc = newShape;
            }
            else if(this.isStartedArrow){
                if((newShape.attrs.x != this.arrowSrc.attrs.x || newShape.attrs.y != this.arrowSrc.attrs.y) && newShape.className != this.arrowSrc.className){
                        
                    this.arrowDest = newShape;
                    let arrow = {type:'arrow',points: this.adjustPoints(this.arrowSrc,this.arrowDest)} // Define the start and end points of the arrow
                    this.conectShapes(arrow,this.arrowSrc,this.arrowDest);

                
                }
                
                this.isStartedArrow=false;
            }
      });

        group.add(newShape);
        group.add(text);

        this.layer.add(group);
                console.log(JSON.stringify(newShape, null, 2));

    },
    
    addShape(shape,type){//send shape to backend
        const newShape = {...shape}; // Create a new objec(Copy)
        this.allShapes.push(newShape);
        // console.log(JSON.stringify( this.allShapes, null, 2));
        this.clearAndDraw();
      //send it to backand return array of shapes 
    },

    sendQueueToBack(newQueue){
        console.log(JSON.stringify( newQueue, null, 2));
        const newQueue2 = {...newQueue}; // Create a new objec(Copy)
        this.allQueues.push(newQueue2);
        this.clearAndDraw();
        // fetch('http://localhost:8081/addQueue'
        //     , {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json'
        //         },
        //         body: JSON.stringify({
        //             params:{
        //                 queueId: newQueue.id,
        //                 queueX: newQueue.x,
        //                 queueY: newQueue.y,
        //                 queueColor: newQueue.Color,
        //             }
        //         })
        //     }
        //     )
        //     .then(response => response.json())
        //     .then(data => {
        //         this.allQueues = data;
        //     })
        //     .catch(error => console.error('Error changing list:', error));
    },
    sendMachineToBack(newMachine){
        const newMachine2 = {...newMachine}; // Create a new objec(Copy)
        console.log(2);
        this.allMachines.push(newMachine2);
        this.clearAndDraw();
        // fetch('http://localhost:8081/addMachine'
        //     , {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json'
        //         },
        //         body: JSON.stringify({
        //             params:{
        //                 MachineId: newMachine.id,
        //                 MachineX: newMachine.x,
        //                 MachineY: newMachine.y,
        //                 MachineColor: newMachine.Color,
        //             }
        //         })
        //     }
        //     )
        //     .then(response => response.json())
        //     .then(data => {
        //         this.allMachines = data;
        //     })
        //     .catch(error => console.error('Error changing list:', error));
    },

    conectShapes(arrow,srcShape,destShape){
        const newArrow = {...arrow}; // Create a new objec(Copy)
        this.allArrows.push(newArrow);
        this.clearAndDraw();
        let source='machine';
        let destination='queue';
        if(srcShape.attrs.class=='Rectangle'){
            let temp=source
            source=destination
            destination=temp;
        }
        // fetch('http://localhost:8081/'+'connect'+source+destination
        //     , {
        //         method: 'PUT',
        //         headers: {
        //             'Content-Type': 'application/json'
        //         },
        //         body: JSON.stringify({
        //             params:{
        //                 MachineId: newMachine.id,
        //                 MachineX: newMachine.x,
        //                 MachineY: newMachine.y,
        //                 MachineColor: newMachine.Color,
        //             }
        //         })
        //     }
        //     )
        //     .then(response => response.json())
        //     .then(data => {
        //         this.allMachines = data;
        //     })
        //     .catch(error => console.error('Error changing list:', error));
    },

    adjustPoints(arrowSrc,arrowDest){
        let points = [arrowSrc.attrs.x , arrowSrc.attrs.y , arrowDest.attrs.x , arrowDest.attrs.y];
        let hDiff = arrowSrc.attrs.x - arrowDest.attrs.x;
        let vDiff = arrowSrc.attrs.y - arrowDest.attrs.y;
        let dir=null;
       if(Math.abs(hDiff) > Math.abs(vDiff)){
         if(hDiff > 0)
          dir = 'left';
         else
          dir = 'right';
       } 
       else{
         if(vDiff > 0)
          dir = 'up';
         else
          dir = 'down';
       }
       switch(dir){
        case 'left':
          points[0] -= this.getDimentions(arrowSrc,dir);
          points[2] += this.getDimentions(arrowDest,dir);
        break;

        case 'right':
          points[0] += this.getDimentions(arrowSrc,dir);
          points[2] -= this.getDimentions(arrowDest,dir);
        break;
        case 'up':
          points[1] -= this.getDimentions(arrowSrc,dir);
          points[3] += this.getDimentions(arrowDest,dir);
        break;

        case 'down':
          points[1] += this.getDimentions(arrowSrc,dir);
          points[3] -= this.getDimentions(arrowDest,dir);
        break;
       }
       return points;
    },

    getDimentions(shape,dir){
        if(shape.attrs.radius == null){

          if(dir == 'right' || dir == 'left')
             return shape.attrs.width/2;
          else 
             return shape.attrs.height/2;
        }
     return shape.attrs.radius;
    },

    newSimulation(){
      this.clear();
      this.newQueueId=0;
      this.newMachineId=0;
      this.allArrows=[];
      this.allMachines=[];
      this.allQueues=[];
      this.clearAndDraw();
    },
    
    startSimulation() {
      this.clear();

      // Initialize WebSocket connection
      this.webSocket = new WebSocket("ws://spring-backend-websocket-url");

      // WebSocket open event
      this.webSocket.onopen = () => {
        console.log("WebSocket connection established");
        // You can send a message to the server if required
        this.webSocket.send(JSON.stringify({ action: "startSimulation" }));
      };

      // WebSocket message event
      this.webSocket.onmessage = (event) => {
        const data = JSON.parse(event.data);
        if (data.type === "allShapes") {
          this.allShapes = data.shapes;
        }
      };

      // WebSocket error event
      this.webSocket.onerror = (error) => {
        console.error("WebSocket error", error);
      };

      // WebSocket close event
      this.webSocket.onclose = () => {
        console.log("WebSocket connection closed");
      };
    
    }
    
   }
}
</script>

<style>
.konva-holder {
  height: 84vh;
  width:100%;
  /* border: 1px solid black; */
  background-color: #DCF2F1;
  /* padding-right:-1px  ; */
}
.page {
  border: 1px solid black;
  border-top-left-radius:10px ;
  border-top-right-radius:10px ;
}
</style>