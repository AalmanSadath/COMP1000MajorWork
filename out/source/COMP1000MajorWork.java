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

float xcord=250;
float yenemy=-20;
float xenemy=250;
float walkRate=0;

public void setup() {
    
}


public void draw() {
    grass();
    wallysWalk();
    walkRate++;
    if (walkRate>=50) {
        walkRate=0;
    }
    player();
    yenemy=yenemy+5;
    ped();
    if (collision() == true) {
        fill(100,100,100,99);
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
    rect(xcord,675,30,100,20);
    //personarms
    fill(0,150,0);
    strokeWeight(1);
    rectMode(CORNER);
    rect(xcord-4,638,8,15,3);
    rect(xcord-4,698,8,15,3);
    //personbody
    rect(xcord-10,650,20,50,10);
    //head
    circle(xcord-3,675,25);
}

public void ped() {
    strokeWeight(2);
    fill(200,100,100);
    rectMode(CENTER);
    rect(xenemy,yenemy,50,20,10);
    rectMode(CORNER);
    circle(xenemy,yenemy+3,25);
    if (yenemy > 750) {
        yenemy=-100;
    }
}

public void keyPressed() {
    if (keyCode == 37) {
        xcord=constrain(xcord-5,115,385);
    }
    else if (keyCode == 39) {
        xcord=constrain(xcord+5,115,385);
    }
}

public boolean collision() {
    float distX=abs(xcord-xenemy);
    float distY=abs(675-yenemy);
    if (distX <= 40) {
        if (distY <= 60) {    
            return true;
        }
    }
    return false;
}

public void wallysWalk() {
    fill(193,193,171);
    strokeWeight(2);
    for (float rows=0; rows<16; rows++) {
        for (float columns=0; columns<6; columns++) {
            rect(width/5+(columns*50),walkRate+(50*rows)-50,50,50);
        }
    }
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
