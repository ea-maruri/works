class cPosition{
  float posx, posy;
  
  cPosition(float posx, float posy){
    this.posx = posx;
    this.posy = posy;
  }
  
  void printPosition(){
    print("("+posx+","+posy+")");
  }
}
