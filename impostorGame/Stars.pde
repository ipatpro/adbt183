class Stars {
  int starCount = 111;
  float [] xps = new float [starCount];
  float [] yps = new float [starCount];
  float [] sizes = new float [starCount];

  Stars () {
    randValues();
  }

  void draw() {
    noStroke();
    for (int i=0; i<xps.length; i++) {
      fill(240);
      circle(xps[i], yps[i], sizes[i]);
    }
  }

  void randValues() {
    for (int i=0; i<xps.length; i++) {    //Random star pos. and sizes
      sizes[i] = random(0, 3);
      xps[i] = random(sizes[i], width);
      yps[i] = random(sizes[i], height);
    }
  }
}
