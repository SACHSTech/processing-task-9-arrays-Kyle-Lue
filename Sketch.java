import processing.core.PApplet;

public class Sketch extends PApplet {
	
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  //  Variables
  float[] circleY = new float[35];
  float[] circleX = new float[35];
  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean slowDown = false;
  boolean speedUp = false;
  boolean[] circleHidden = new boolean[35];
  boolean mouseClicked = false;
  int intLives = 3;

  int PlayerY = 300;
  int PlayerX = 300;

  float circleRadius = (float)12.5;
  float playerRadius = (float)7.5;

  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
    // Changes the height of each circle in the array
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
    }
    for (int i = 0; i < circleX.length; i++) {
      circleX[i] = random(width);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  // Background
    background(50);
    // Creating the circles that fall down
    for (int i = 0; i < circleY.length; i++) {
      if(circleHidden[i] == false){
      fill(255,255,255);
      ellipse(circleX[i], circleY[i], 25, 25);
      }
    // Collision detection
    if(dist((float)PlayerX,(float)PlayerY,circleX[i],circleY[i]) < circleRadius + playerRadius){
      intLives = intLives - 1;
      circleY[i] = 0;
    }
      // Controls the speed of the circles falling
      circleY[i]+=2;
      // Speeds up the speed of the circles when down pressed
      if(speedUp){
        circleY[i]++ ;
      }
      // Slows down the speed of the circles when up pressed
      if(slowDown){
        circleY[i]--;
      }
      // Resets the position of the circles when they hit the bottom 
      if (circleY[i] > height) {
        circleY[i] = 0;
      }
      if(circleX[i] > width){
        circleX[i] = 0;
      }
    }
    // Controls the movement of the player's circle
    if (upPressed) {
      PlayerY--;
    }
    if (downPressed) {
      PlayerY++;
    }
    if (leftPressed) {
      PlayerX--;
    }
    if (rightPressed) {
      PlayerX++;
    }
    
    // Changes the colour and creates the player's circle
    fill(0,204,204);
    ellipse(PlayerX,PlayerY, 15, 15);
    // Collision detection to make sure the player doesn't go out of bounds
    if(PlayerX < 0){
      PlayerX = 0;
    }else if(PlayerX > width){
      PlayerX = width;
    }else if(PlayerY < 0){
      PlayerY = 0;
    }else if(PlayerY > height){
      PlayerY = height;
    }
    // Live Removal
    if(intLives == 3){
      fill(255,0,0);
      rect(385,5,10 , 10);
      rect(370, 5, 10, 10);
      rect(355,5,10,10);
    }else if(intLives == 2){
      fill(255,0,0);
      rect(385,5,10 , 10);
      rect(370, 5, 10, 10);
    }else if(intLives ==  1){
      fill(255,0,0);
      rect(385,5,10 , 10);
    }else if(intLives == 0){
      background(255,255,255);
      noLoop();
    }
  }
  // define other methods down here.
  /*
   * This method determines if the key is pressed and uses boolean to determine if the key is pressed or not
  */
  public void keyPressed() {
    if (key == 'w') {
      upPressed = true;
    }
    else if (key == 's') {
      downPressed = true;
    }
    else if (key == 'a') {
      leftPressed = true;
    }
    else if (key == 'd') {
      rightPressed = true;
    }else if(keyCode == UP){
      slowDown = true;
    }else if(keyCode == DOWN){
      speedUp = true;
    }
  }
  /*
  * This method determines if the key is pressed and uses boolean to determine if the key is pressed or not
  */
  public void keyReleased() {
    if (key == 'w') {
      upPressed = false;
    }
    else if (key == 's') {
      downPressed = false;
    }
    else if (key == 'a') {
      leftPressed = false;
    }
    else if (key == 'd') {
      rightPressed = false;
    }else if(keyCode == UP){
      slowDown = false;
    }else if(keyCode == DOWN){
      speedUp = false;
    }
  }
  public void mouseClicked(){
    for(int i = 0; i < circleY.length; i++){
      if(dist(mouseX,mouseY, circleX[i], circleY[i]) < 25){
        circleHidden[i] = true;
      }
    }
  }
}