import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class COMP1000MajorWork extends PApplet {

float xCord=250;
float yEnemy=-20;
float xEnemy=250;
float walkRate=0;
float yTree=0;

float[] scale= new float[8];
float[] radius= new float[8];
int[] bulbs=new int[8];


public void setup() {
    
}


public void draw() {
    //grass();
    genGrass();
    wallysWalk();
    
    if (yTree>=height/3) {
        //treeTransfer();
        yTree=0;
    }
    yTree++;
    walkRate++;
    if (walkRate>=50) { 
        walkRate=0;
    }
    player();
    yEnemy=yEnemy+3;
    ped();
    if (collisionPed() == true) {
        fill(100,100,100,98);
        rect(0,0,width,height);
        textAlign(CENTER);
        fill(0);
        textSize(70);
        text("Game Over",250,100);
        noLoop();
    }
}

public void grass() {
    fill(0,155,0);
    rect(0,0,width/5,height);
    rect(width*4/5,0,width/5,height);
}

public void player() {
    //skateboard
    strokeWeight(3);
    fill(100);
    rectMode(CENTER);
    rect(xCord,675,30,100,20);
    //personarms
    fill(0,150,0);
    strokeWeight(1);
    rectMode(CORNER);
    rect(xCord-4,638,8,15,3);
    rect(xCord-4,698,8,15,3);
    //personbody
    rect(xCord-10,650,20,50,10);
    //head
    circle(xCord-3,675,25);
}

public void ped() {
    strokeWeight(2);
    fill(200,100,100);
    rectMode(CENTER);
    rect(xEnemy,yEnemy,50,20,10);
    rectMode(CORNER);
    fill(200,100,100,99);
    circle(xEnemy,yEnemy+3,25);
    if (yEnemy > 750) {
        yEnemy=-100;
    }
}

public void keyPressed() {
    if (keyCode == 37) {
        xCord=constrain(xCord-5,115,385);
    }
    else if (keyCode == 39) {
        xCord=constrain(xCord+5,115,385);
    }
}

public boolean collisionPed() {
    float distX=abs(xCord-xEnemy);
    float distY=abs(675-yEnemy);
    if (distX <= 40 && distY <= 60) {
        return true;
    }
    return false;
}

public void wallysWalk() {
    rectMode(CORNER);
    fill(193,193,171);
    strokeWeight(2);
    for (float rows=0; rows<16; rows++) {
        for (float columns=0; columns<6; columns++) {
            rect(width/5+(columns*50),walkRate+(50*rows)-50,50,50);
        }
    }
}

public void genGrass() {
    rectMode(CENTER);
    fill(0,155,0);
    strokeWeight(2);
    //for(int i=-1;i<=5;i+=2){
    //   rect(width/10,yTree+(height*i/6),width/5,height/3);
    //    //drawTree(width/10,yTree+(height*i/6),scale[0],bulbs[0],radius[0]);
    //    rect(width*9/10,yTree+(height*i/6),width/5,height/3);
        //drawTree(width/10,yTree+(height*i/6),scale[0],bulbs[0],radius[0]);
    //}
    rect(width/10,yTree-height/6,width/5,height/3);
    //drawTree(width/10,yTree-height/6,scale[0],bulbs[0],radius[0]);
    rect(width/10,yTree+height/6,width/5,height/3);
    //drawTree(width/10,yTree+height/6,scale[1],bulbs[1],radius[1]);
    rect(width/10,yTree+(height*3/6),width/5,height/3);
    //drawTree(width/10,yTree+(height*3/6),scale[2],bulbs[2],radius[2]);
    rect(width/10,yTree+(height*5/6),width/5,height/3);
    //drawTree(width/10,yTree+(height*5/6),scale[3],bulbs[3],radius[3]);
    rect(width*9/10,yTree-height/6,width/5,height/3);
    //drawTree(width*9/10,yTree-height/6,scale[4],bulbs[4],radius[4]);
    rect(width*9/10,yTree+height/6,width/5,height/3);
    //drawTree(width*9/10,yTree+height/6,scale[5],bulbs[5],radius[5]);
    rect(width*9/10,yTree+(height*3/6),width/5,height/3);
    //drawTree(width*9/10,yTree+(height*3/6),scale[6],bulbs[6],radius[6]);
    rect(width*9/10,yTree+(height*5/6),width/5,height/3);
    //drawTree(width*9/10,yTree+(height*5/6),scale[7],bulbs[7],radius[7]);
}

public void drawTree(int x, int y, float scale, int bulbs, float radius){

}
  public void settings() {  size(500,750); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "COMP1000MajorWork" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
