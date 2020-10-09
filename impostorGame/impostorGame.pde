import processing.sound.*;

Ghost[] ghosts;
Impostor[] impostors;
Stars stars = new Stars();
AimPoint aimpoint;
Text text;
SoundFile lose;
SoundFile win;
SoundFile theme;

int level;
boolean started;
boolean over;
boolean loading;
int score;
int timer;
boolean music = true;

void setup() {
  size(1200, 750);

  level = 1;
  started = false;
  over = false;
  loading = false;
  score = 0;
  timer = 0;

  setSounds();

  loadArrays();

  aimpoint = new AimPoint();
  stars = new Stars();
  text = new Text();
}

void draw() {

  background(0, 0, 22);

  stars.draw();            //Display stars

  if (started==false) 
  {                           //Displays intro before game started
    impostors[0].intro();
    text.intro();
  } else                     //Setup when game is running
  {
    text.level();            //Displays level in corner

    drawCharacters();

    if (over==true) {        //Stops movement for all classes when game over

      stopAll();

      text.gameOver();      //Display Game Over message
    } else                  //Only draws AimPoint if game is running, otherwise not visible
    {
      aimpoint.draw();
    }
    if (loading==true) {
      loadLevel();          //Runs delay and refreshes arrays
    }
  }
}


void keyPressed() 
{ 
  if (key == '\n')     //Close intro & start game on ENTER
  {         
    if (started == false) //Only reacts before game begins
    {
      started = true;
    }
  }

  if (key=='r') {      //Restars the game
    setup();
  }

  if (key=='m') {
    if (music==true) {
      theme.pause();
      music=false;
    } else {
      theme.play();
      music=true;
    }
  }
}

  void mouseClicked() 
  {
    for (Ghost ghost : ghosts)     //Checking for collision with ghosts
    {
      ghost.hit();
    }
    if (over==false) { 
      for (Impostor impostor : impostors)     //Checking for hit of impostor
      {
        if (impostor.hit() == true) {
          stopAll();
          level ++;
          loading = true;
          timer = 0;
          println(level);
        }
      }
    }
  }

  void stopAll() {                    //Stops movement of all characters
    for (Ghost ghost : ghosts) 
    {
      ghost.stop();
    }

    for (Impostor impostor : impostors) 
    {
      impostor.stop();
    }
  }

  void loadArrays() {                  //Loads arrays for updated variables
    ghosts = new Ghost[(2 + level)];
    for (int i=0; i<ghosts.length; i++) {
      ghosts[i] = new Ghost();
    }
    impostors = new Impostor[1];
    for (int i=0; i<impostors.length; i++) {
      impostors[i] = new Impostor();
    }
  }

  void loadLevel() {      //Prepares new characters for new level and creates delay
    if (timer<180) {
      text.nextLevel();
      timer++;
    } else if (timer == 180) {
      loadArrays();
      loading = false;
    }
  }

  void drawCharacters() {          //Draws and moves all characters
    for (Ghost ghost : ghosts) {
      ghost.draw();
      ghost.move();
    }
    for (Impostor impostor : impostors) {
      impostor.draw();
      impostor.move();
    }
  }

  void setSounds() {        //Imports sounds and stars theme music
    lose = new SoundFile(this, "Emergency.wav");
    win = new SoundFile(this, "win.wav");
    theme = new SoundFile(this, "theme.wav");

    theme.loop();
  }
