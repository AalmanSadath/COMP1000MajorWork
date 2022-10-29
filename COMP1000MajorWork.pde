float xCord=250;
float yEnemy=-20;
float xEnemy=250;
float walkRate=0;
float yTree=0;

float[] scaleArr= new float[8];
float[] radiusArr= new float[8];
int[] bulbsArr=new int[8];
int[] treeHeights={-1,1,3,5};
int[][] pedArr=new int[6][3];

void setup() {
    size(500,750);
    for(int i=0;i<=7;i++){
        scaleArr[i]=random(10, 50);
        radiusArr[i]=random(10, 40);
        bulbsArr[i]=int(random(3,10));
    }
    for(int i=0;i<=5;i++){
        pedArr[i][0]=int(random(width/5+25,(width*4/5)-25));
        pedArr[i][1]=-20;
        pedArr[i][2]=int(random(2,5));
    }
}


void draw() {
    wallysWalk();
    genGrass();
    if (yTree>=height/3) {
        treeTransfer();
        yTree=0;
    }
    yTree++;
    walkRate++;
    if (walkRate>=50) { 
        walkRate=0;
    }
    player();
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

void player() {
    //skateboard
    stroke(0);
    strokeWeight(3);
    fill(100);
    rectMode(CENTER);
    rect(xCord,675,30,100);
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
    for(int i=0;i<=5;i++){
        pedArr[i][1]+=pedArr[i][2];
        strokeWeight(2);
        fill(200,100,100);
        rectMode(CENTER);
        rect(pedArr[i][0],pedArr[i][1],50,20,10);
        rectMode(CORNER);
        fill(200,100,100);
        circle(pedArr[i][0],pedArr[i][1]+3,25);
        if (pedArr[i][1] > height+10) {
            pedArr[i][0]=int(random(width/5+25,(width*4/5)-25));
            pedArr[i][1]=-20;
            pedArr[i][2]=int(random(2,5));
        }
    }
}

void keyPressed() {
    if (keyCode == 37 | keyCode == 65) {
        xCord=constrain(xCord-5,115,385);
    }
    else if (keyCode == 39 | keyCode == 68) {
        xCord=constrain(xCord+5,115,385);
    }
}

boolean collisionPed() {
    for(int i=0;i<=5;i++){
        float distX=abs(xCord-pedArr[i][0]);
        float distY=abs(675-pedArr[i][1]);
        if (distX <= 40 && distY <= 60) {
            return true;
        }
    }
    return false;
}

void wallysWalk() {
    rectMode(CORNER);
    fill(193,193,171);
    strokeWeight(2);
    stroke(0);
    for (float rows=0; rows<16; rows++) {
        for (float columns=0; columns<6; columns++) {
            rect(width/5+(columns*50),walkRate+(50*rows)-50,50,50);
        }
    }
}

void genGrass() {
    rectMode(CENTER);
    for(int i=0;i<=3;i++){
        for(int j=1; j<=9;j+=8){

            if(j==1){
                fill(0,155,0);
                noStroke();
                rect(width*j/10,yTree+(height*treeHeights[i]/6),width/5,height/3);
                drawTree(width*j/10,int(yTree+(height*treeHeights[i]/6)),scaleArr[i],bulbsArr[i],radiusArr[i]);
            }

            else if(j==9){
                fill(0,155,0);
                noStroke();
                rect(width*j/10,yTree+(height*treeHeights[i]/6),width/5,height/3);
                drawTree(width*j/10,int(yTree+(height*treeHeights[i]/6)),scaleArr[i+4],bulbsArr[i+4],radiusArr[i+4]);
            }
        }
    }
}

void drawTree(int x, int y, float scale, int bulbs, float radius){
    for(int i=1;i<=bulbs;i++){
        int n=i-1;
        float xCordBranch=radius*cos(2*n*3.14/bulbs)+x;
        float yCordBranch=radius*sin(2*n*3.14/bulbs)+y;
        strokeWeight(5);
        stroke(79, 57, 9);
        line(x,y,xCordBranch,yCordBranch);
        strokeWeight(0);
        fill(22, 51, 11);
        circle(xCordBranch,yCordBranch,scale);
    }
}

void treeTransfer(){
    for(int i=3;i>0;i--){
        scaleArr[i]=scaleArr[i-1];
        scaleArr[i+4]=scaleArr[i+3];
        radiusArr[i]=radiusArr[i-1];
        radiusArr[i+4]=radiusArr[i+3];
        bulbsArr[i]=bulbsArr[i-1];
        bulbsArr[i+4]=bulbsArr[i+3];
    }
    for(int i=0;i<=4;i+=4){
        scaleArr[i]=random(10, 50);
        radiusArr[i]=random(10, 40);
        bulbsArr[i]=int(random(3,10));
    }
}