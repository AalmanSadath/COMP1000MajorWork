float xCord=250;
float yEnemy=-20;
float xEnemy=250;
float walkRate=0;
float yTree=0;

void setup() {
    size(500,750);
}


void draw() {
    //grass();
    genTree();
    wallysWalk();
    yTree++;
    if (yTree>=height/3) {
        yTree=0;
    }
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

void grass() {
    fill(0,155,0);
    rect(0,0,width/5,height);
    rect(width*4/5,0,width/5,height);
}

void player() {
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

void ped() {
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

void keyPressed() {
    if (keyCode == 37) {
        xCord=constrain(xCord-5,115,385);
    }
    else if (keyCode == 39) {
        xCord=constrain(xCord+5,115,385);
    }
}

boolean collisionPed() {
    float distX=abs(xCord-xEnemy);
    float distY=abs(675-yEnemy);
    if (distX <= 40 && distY <= 60) {
        return true;
    }
    return false;
}

void wallysWalk() {
    fill(193,193,171);
    strokeWeight(2);
    for (float rows=0; rows<16; rows++) {
        for (float columns=0; columns<6; columns++) {
            rect(width/5+(columns*50),walkRate+(50*rows)-50,50,50);
        }
    }
}

void genTree() {
    rectMode(CENTER);
    fill(0,155,0);
    strokeWeight(2);
    rect(width/10,yTree-width/6,width/5,height/3);
    rect(width/10,yTree+width/6,width/5,height/3);
    rect(width/10,yTree+height/2,width/5,height/3);
    rect(width/10,yTree+(height*5/6),width/5,height/3);
}