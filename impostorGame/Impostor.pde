class Impostor {

  PVector position;
  PVector velocity;
  float sizeX;
  float sizeY;
  PImage impostor;
  boolean active;

  Impostor () {
    impostor = loadImage("impostor.png");
    position = new PVector(random(width), random(height));
    velocity = new PVector(random((2+level), (8+level)), random((2+level), (8+level)));
    sizeX = impostor.width/2;
    sizeY = impostor.height/2;
    imageMode(CENTER);
    active = true;
  }

  void draw() 
  {
    image(impostor, position.x, position.y);
  }


  void move() 
  {
    position.x = position.x + velocity.x;
    position.y = position.y + velocity.y;

    if (position.x - sizeX < 0 && velocity.x < 0)
    {
      // Escaping to the left, start moving right.
      velocity.x = -1*velocity.x;
    } else if (position.x + sizeX > width && velocity.x > 0)
    {
      // Escaping to the right, start moving left.
      velocity.x = -1*velocity.x;
    }

    if (position.y - sizeY < 0 && velocity.y < 0)
    {
      // Escaping upwards, start moving down.
      velocity.y = -1*velocity.y;
    } else if (position.y + sizeY > height && velocity.y > 0)
    {
      // Escaping downwards, start moving up.
      velocity.y = -1*velocity.y;
    }
  }

  boolean hit() 
  {
    if (dist(mouseX, 0, position.x, 0)< sizeX && dist(0, mouseY, 0, position.y)< sizeY) 
    {
      if (active==true) {
        impostor.filter(GRAY);
        win.play();
        active = false;
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  void stop() 
  {
    velocity.x = 0;
    velocity.y = 0;
  }

  void intro()
  {
    image(impostor, width/2, width/5.5);
  }
}
