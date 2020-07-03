import ddf.minim.*;

/*For game*/
int SCORE, myScore, numberOfPillars = 4; // Score of player and number of pillars in screen
boolean intro, playing, lost, counter; // To know state of game
float centerX, centerY; // To know the center of window
Bird bird = new Bird(); // the character
Pillar[] pillars; // An array of Pillar objects

/*MEDIA*/
PImage back, calabaza, salida, principal;

Minim soundengine;
AudioSample sonidojuego;
AudioSample sonidofinal;
AudioSample sonidopantalla;
AudioSample sonidoPillar;

long initialTime, currentTime, beginGameTime, endGameTime;
String[] arrayOfData;


void setup(){
  size(1000, 700); // size of window
  
  // SOUNDS
  println("Initilizing sounds...");
  soundengine = new Minim(this);
  sonidojuego = soundengine.loadSample("Audio\\musicafondo.mp3", 1024);
  sonidofinal = soundengine.loadSample("Audio\\lost.mp3", 1024);
  sonidopantalla = soundengine.loadSample("Audio\\SonidoPantalla.mp3", 1024);
  sonidoPillar = soundengine.loadSample("Audio\\mario-coin.mp3", 1024);
  
  playCoin();
  sonidopantalla.setVolume(5);
  sonidopantalla.trigger();
  
  // IMAGES
  println("Initializing images...");
  back = loadImage("Images\\entrada.png");
  calabaza = loadImage("Images\\haa.png");
  salida = loadImage("Images\\pru.png");
  principal = loadImage("Images\\pagina principal.png");
  
  // Initializing the game
  centerX = width/2;
  centerY = height/2;
  SCORE = 0;
  
  playing = false;
  intro = true;
  lost = false;
  counter = false;
  bird = new Bird();
  
  pillars = new Pillar[numberOfPillars];
  for(int i = 0; i < numberOfPillars; i++)
    pillars[i] = new Pillar(i);
   
  showScoreAndPosY();
  beginGameTime = millis();
  arrayOfData = new String[2];
}

void draw(){
  background(back);   
  showScoreAndPosY();  
  
  if(intro) showIntro();
  if(counter) showCounter();
  if(lost) showLost();
  if(playing) showGame();
 // text(posY, 200, height/2);
}


class Pillar {
  float xPos, opening = 200, lengthUp;
  boolean cashed = false;
  
  Pillar(int i) {
    xPos = 400 + (i*300);
    lengthUp = random(height/2) + random(50);
  }
  
  void drawPillar() {
   strokeWeight(15);  
   stroke(255);
   line(xPos, 0, xPos, lengthUp);
   line(xPos, lengthUp+opening, xPos, height);
  }
   
  void move(){
    xPos -= 2;
  }
  
  void checkPosition() {
    if (xPos < 0) {
      xPos += 300 * numberOfPillars;
      lengthUp = random(height/2) + random(50);
      cashed = false;
    } 
    if (xPos < bird.xPos && cashed == false) {
      cashed = true;
      SCORE++;
      playCoin();
    }
  }
}

class Bird{
  float xPos, yPos,  ySpeed;
  
  Bird(){
    xPos = width/7;
    yPos = height/2;
  }
  
  void drawBird() {
    /*stroke(255);
    noFill();
    strokeWeight(3);*/
    image(calabaza, xPos, yPos, 70, 70);
    drag();
  }
  
  void jump() {
    ySpeed =- 10;
  }
  
  void drag() {
    ySpeed += 0.4;
  }
  
  // Bird moves in Y and pillars in X
  void move() {
    yPos += ySpeed; 
  }
  
  void checkCollisions() {
    //sonidoPillar.trigger();
    if (yPos >= height - 70){
      println("Te chocaste abajo");
      yPos = height - 10;
      playing = false;
      lost = true;
      playLost();
    }
    if(yPos <= 10){
      println("Te chocaste arriba");
      yPos = 15;
      playing = false;
      lost = true;
      playLost();
    }
    
    for(int i = 0; i < numberOfPillars; i++) {
      if(xPos+70 == pillars[i].xPos && (yPos < pillars[i].lengthUp-2)){
        println("Te chocaste arriba con ", i, " en", xPos, " y ", yPos);
        playing = false;
        lost = true;
        playLost();
      }
      if(xPos+70 == pillars[i].xPos && (yPos > (pillars[i].lengthUp + pillars[i].opening - 75))){
        println("Te chocaste abajo con ", i, " en", xPos, " y ", yPos);
        playing = false;
        lost = true;
        playLost();
      }
    }
  }
}

/*Metodos para las etapas del juego*/
void showTabKey(int x, int y){
  textSize(65);
  textAlign(CENTER);
  
  endGameTime = millis();
  if(endGameTime - beginGameTime >= 1000)
    fill(0);
  if(endGameTime - beginGameTime >= 2000){
    fill(255);
    beginGameTime = millis();
  }
 
  text("Press TAB", x, y);
}

void showIntro(){
  background(principal);
  showTabKey(width / 2 + 100, height/5);
}

void showCounter(){
  background(0);
  fill(255);
  textSize(100);
  textAlign(CENTER);
  
  currentTime = millis(); // tiempo actual
  if(currentTime - initialTime >= 500){
    background(0);
    text("READY?", width/2, height/2);
  }
  
  int a_second = 1000;
  int time_counter = 5;
  for(int i = 0; i <= time_counter; i++){
    if((currentTime - initialTime) >= (500 + a_second*(i+1))){
       background(0);
       //int text = (500 + a_second*i);
       text(time_counter, width/2, height/2);
       time_counter = time_counter - 1;
    }
  }
  /*if(currentTime - initialTime >= 1500){
    background(0);
    text(3, width/2, height/2);
  }
  if(currentTime - initialTime >= 2500){
    background(0);   
    text(2, width/2, height/2);
  }
  if(currentTime - initialTime >= 3500){
    background(0);
    text(1, width/2, height/2);
  }
  if(currentTime - initialTime >= 4500){
    background(0);
    text(0, width/2, height/2);
  }*/
  if(currentTime - initialTime >= 6000){
    playing = true;
    counter = false;
  }
  
  textSize(70);
  text("PLAY WITH \"UP\" KEY!!", width/2, height/2 + 200);
}

void beginGame(){
  intro = false; // No more intro
  counter = true; //
  sonidopantalla.stop();
  sonidojuego.trigger();
  
  bird = new Bird();
  
  SCORE = 0;
  lost = false;
  for (int i = 0; i < numberOfPillars; i++) {
    pillars[i].xPos += 300;
    pillars[i].cashed = false;
  }
  
  initialTime = millis();
}

/*boolean close = false; 
void closePillars(){
  if((SCORE % 2 + 1) == 1){
    close = true;
    if(SCORE > 1 && SCORE < 100 && SCORE % 2 == 0 && close){
      for(int i = 0; i < numberOfPillars; i++) {
        pillars[i].opening -= 5;
      }
     close = false;
    }
  }
}*/

void showGame(){
  bird.move();
  bird.drawBird();
  bird.checkCollisions();
  
  //closePillars();
  
  for(int i = 0; i < numberOfPillars; i++) {
    pillars[i].drawPillar();
    pillars[i].move();
    pillars[i].checkPosition();
  }
}

void playCoin(){
  sonidoPillar.trigger();  
}

void playLost(){
  sonidojuego.stop();
  sonidofinal.trigger();
  delay(2000);
  sonidofinal.stop();
  sonidopantalla.trigger();
}

void showLost(){  
  background(salida);
  fill(#E74C3C);  
  textSize(50);
  text("GAME OVER", width/2 + 200, 350);   
  fill(#F7DC6F);  
  text("SCORE", centerX+100, centerY-60);
  text(SCORE, centerX+250, centerY-60);
  
  textSize(65);
  textAlign(CENTER);
  
  endGameTime = millis();
  if(endGameTime - beginGameTime >= 1000)
    fill(0);
  if(endGameTime - beginGameTime >= 2000){
    fill(255);
    beginGameTime = millis();
  }
 
  text("Press TAB", width/2+200, height/2+150); 
}

void showScoreAndPosY(){
  fill(0, 150);
  stroke(0);
  rect(20, 20, 100, 50);
  fill(255);
  textSize(45);
  text(SCORE, 25, 60);  
  textSize(20);
  text(bird.xPos, 25, height-15);
}

void keyPressed() {
  if(key == TAB)
    if (playing == false) 
      beginGame();
      
  if(key == CODED){
    if(keyCode == UP && playing){
      bird.jump();
    }
  }
}
