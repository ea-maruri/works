// https://www.codeproject.com/articles/38821/make-a-poker-hand-evalutator-in-java
int MAX_PLAYERS = 8;

void setup(){
  size(displayWidth, displayHeight);
  
  int number_of_players = 3;
  
  if(number_of_players <= MAX_PLAYERS && number_of_players > 1){
    // Creating the deck 
    cDeck deck = new cDeck();
    deck.shuffle_deck();
    deck.printDeck();
    
    // Creating players
    cPlayer[] players = new cPlayer[number_of_players];
    for(int i = 0; i < players.length; i++){
      players[i] = new cPlayer("Player"+i);
      players[i].printCash();
      println();
    }
    
    cTable table = new cTable(width/2, height/2);
    //arc(width/2, height/2-20, width-80, height-60, radians(0), radians(270));
   
    int turn = int(random(0, number_of_players));
    players[turn].inTurn = true;
    
    players = table.putPlayersInOrder(players, turn);
    println("New order by tyrn: " + turn);
    for(int i = 0; i < players.length; i++){
      players[i].printCash();
      print(players[i].inTurn);
      println();
    }
    
    deck.deal_card_to_players(players);
    println("\nDealing Cards...");
    for(int i = 0; i < players.length; i++){
      players[i].printPlayer();
      print(players[i].inTurn);
      println();
    }
    println("Cards in deck: " + deck.card_counter);
  }
  else{
    println("Error: too much players!!!");
  }
}

void draw(){

}
