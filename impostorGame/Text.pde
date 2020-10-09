class Text {

  color yellow = color(247, 255, 0);
  int h1 = 65;
  int h2 = 30;
  int h3 = 20;

  Text() {
    textAlign(CENTER, CENTER);
    fill(247, 255, 0);
    textSize(h1);
  }

  void intro() {                 //Intro text, instructions
    fill(247, 255, 0);
    textSize(h1);

    String introMessage = "One Impostor Remains";
    String starter = "Press ENTER to start";
    String instructions = "Click to shoot \n Press 'r' to restart the game \n Press 'm' to toggle sound";
    text(introMessage, width/2, height/2.1);

    fill(255);
    textSize(h2);
    text(starter, width/2, height/1.5);
    
    textSize(h3);
    text(instructions,width/2, height/1.1);
  }

  void gameOver() {      //Game over text
    fill(yellow);
    textSize(h1);

    String overMessage = "Game over";
    text(overMessage, width/2, height/2.5);

    fill(255);
    textSize(h1);
    text(level, width/2, height/1.8);
  }

  void nextLevel() {      //Displays next level
    fill(255);
    textSize(h1);
    String message = "Level " + level;

    text(message, width/2, height/2.2);
  }

  void level() {
    textSize(20);
    fill(240);
    String levelDisplay = "Level: " + level;
    text(levelDisplay, 50, 20);
  }
}
