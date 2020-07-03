class cTable{
  cCard[] cards_in_table;
  int number_of_cards;
  cPosition centerPosition;
  
  cTable(float posx, float posy){
    cards_in_table = new cCard[5];
    number_of_cards = 0;
    centerPosition = new cPosition(posx, posy);
  }
  
  void printTable(){
    println(number_of_cards + " Cards in table:");
    print("\t{ ");    
    for(int i = 0; i < number_of_cards; i++){
      print(cards_in_table[i].name);
      if(i < number_of_cards-1){
        print(" - ");
      }
      else{
        print(" }");
      }
    }
  }
  
  void drawArc(float mwidth, float mheight, float begin, float end){
    arc(centerPosition.posx, centerPosition.posy, mwidth, mheight, begin, end);
  }
  
  cPlayer[] putPlayersInOrder(cPlayer[] players, int better){
    cPlayer[] playersInOrder = new cPlayer[players.length];
  
    println();
    for(int i = 0; i < players.length; i = i + 1){
      playersInOrder[i] = players[better];
      better = better + 1;
    
      if(better == players.length){
        better = 0;
      }
    }
    
    return playersInOrder;
  }
}
