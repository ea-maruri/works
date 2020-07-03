class cPlayer{
  cCard[] hand;
  String name;
  float cash;
  boolean inTurn;
  
  cPlayer(String name){
    hand = new cCard[2];
    this.name = name;
    this.cash = 12000;
    this.inTurn = false;
  }
  
  void bet(float value){
    this.cash = this.cash - value;
  }
  
  void fold(){
    print(this.name + " Fold");
  }
  
  void call(float value){
    bet(value);
  }
  
  void printHand(){
    print(this.name + "(" + hand[0].name + " - " + hand[1].name + ")");
  }
  
  void printCash(){
    print("\t" + this.name + " has $" + this.cash);
  }
  
  void printPlayer(){
    printHand();
    printCash();
  }
}
