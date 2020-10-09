class AimPoint {

  AimPoint() {
  }
  void draw () {

    rectMode(CENTER);

    noFill();
    stroke(247, 255, 0);
    strokeWeight(5);
    rect(mouseX, mouseY, 50, 50);

    noStroke();
    fill(247, 255, 0);
    circle(mouseX, mouseY, 11);
  }
}
