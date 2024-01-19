<template>
    
    <div>
     <taskBar @addMachine="addMachine" @addQueue="addQueue" @addArrow="addArrow"/>

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
      
    },
    // async startUp() {
    //  await this.createKonvaStage();
    // },

    async createKonvaStage() {//creating stage with events (if i click a shape then i clicked the screen it will create a shape)create line in this(special case ) 
     const viewportWidth = Math.max(document.documentElement.clientWidth || 0, window.innerWidth || 0);
     const viewportHeight = Math.max(document.documentElement.clientHeight || 0, window.innerHeight || 0);

     const stageWidth = viewportWidth * (98.8 / 100); // Replace percentageWidth with your desired percentage
     const stageHeight = viewportHeight * (80 /100); // Replace percentageHeight with your desired percentage

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
            //   newShape.x-=50/2;
            //   newShape.y-=25/2;
            newShape.type='queue'
            newShape.fill='red'
            newShape.text='Q'+this.newQueueId.toString();;
            this.newQueueId++;
          }
          else if(this.isDrawingMachine){
            newShape.type='machine'
            newShape.fill='blue'
            newShape.text='M'+this.newMachineId.toString();;
            this.newMachineId++;
          }
          this.addShape(newShape);
          
        }
        
       
     });
      
    },

   async  clearAndDraw() {//clear the layer and draw the array elements again using this.drawShape 
        this.layer.destroyChildren();
        this.allShapes.forEach((shape) => {
            this.drawKonvaShape(shape);
        });
    },
    drawKonvaShape(shape) {// (print the array ) take a shape object and convert it to element of Konva and have the events of every shape(methods)
      let newShape=null;
    //   console.log(JSON.stringify(shape, null, 2));
      if (shape.type === 'arrow') {
        newShape =  new Konva.Arrow({
                points: shape.points, // Define the start and end points of the arrow
                pointerLength: 7.5,  // Length of the pointer head
                pointerWidth: 7.5,   // Width of the pointer head
                fill: 'black',      // Fill color
                stroke: 'black',    // Stroke color
                strokeWidth: 2      // Stroke width
            });
        } 
        else if (shape.type === 'machine') {
            newShape = new Konva.Circle(shape);
            newShape.attrs.stroke='black';
            newShape.attrs.radius=40;
            
      }
      else if (shape.type === 'queue') {
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
        newShape.on('click', () => {
        if(this.isDrawingArrow&&!this.isStartedArrow){
            this.isStartedArrow=true;
            this.arrowSrc=newShape;
        }
        else if(this.isStartedArrow){
                    console.log(JSON.stringify(newShape, null, 2));
                    console.log(JSON.stringify(this.arrowSrc, null, 2));

            if(newShape.attrs.x != this.arrowSrc.attrs.x || newShape.attrs.y != this.arrowSrc.attrs.y){
                
             this.arrowDest=newShape;
             let arrow = {type:'arrow',points: this.adjustPoints(this.arrowSrc,this.arrowDest)} // Define the start and end points of the arrow
             this.addShape(arrow);
            
            }
             
            this.isStartedArrow=false;
        }
      });

      group.add(newShape);
      group.add(text);

      this.layer.add(group);
    },
    //send shape to backend
    addShape(shape){
        const newShape = {...shape}; // Create a new objec
        this.allShapes.push(newShape);
        // console.log(JSON.stringify( this.allShapes, null, 2));
        this.clearAndDraw();
      //send it to backand return array of shapes 
    },
    adjustPoints(arrowSrc,arrowDest){
        let points=[arrowSrc.attrs.x,arrowSrc.attrs.y,arrowDest.attrs.x,arrowDest.attrs.y];
        let hDiff=arrowSrc.attrs.x-arrowDest.attrs.x;
        let vDiff=arrowSrc.attrs.y-arrowDest.attrs.y;
        let dir=null;
       if(Math.abs(hDiff)>Math.abs(vDiff)){
         if(hDiff>0)
          dir='left';
         else
          dir='right';
       } 
       else{
         if(vDiff>0)
          dir='up';
         else
          dir='down';
       }
       switch(dir){
        case 'left':
          points[0]-=this.getDimentions(arrowSrc,dir);
          points[2]+=this.getDimentions(arrowDest,dir);
        break;

        case 'right':
          points[0]+=this.getDimentions(arrowSrc,dir);
          points[2]-=this.getDimentions(arrowDest,dir);
        break;
        case 'up':
          points[1]-=this.getDimentions(arrowSrc,dir);
          points[3]+=this.getDimentions(arrowDest,dir);
        break;

        case 'down':
          points[1]+=this.getDimentions(arrowSrc,dir);
          points[3]-=this.getDimentions(arrowDest,dir);
        break;
       }
      
       return points;
    },
    getDimentions(shape,dir){
    if(shape.attrs.radius==null){
      if(dir=='right'||dir=='left')
       return shape.attrs.width/2;
      else 
      return shape.attrs.height/2;
    }
     return shape.attrs.radius;
    }
    
   }
    
 

}
</script>

<style>
.konva-holder {
  height: 80vh;
  width:100%;
  border: 1px solid black;
  /* padding-right:-1px  ; */
}
</style>