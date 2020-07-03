class cDeck{
 
  int card_counter = 0;
  int current_card = 0;
  
  cCard[] deck;
  
  cDeck(){
    deck = new cCard[52];
    
    for(int i = 0; i < 13; i++){
      for(int j = 0; j < 4; j++){
        deck[card_counter] = new cCard(i, j);
        card_counter++;
      }
    }
  }
  
  void shuffle_deck(){
    cCard previous;
    
    int random_from_position, random_to_position;
    
    for(int i = 0; i < deck.length; i++){
      random_from_position = int(random(0, deck.length));
      random_to_position = int(random(0, deck.length));
      
      previous = deck[random_to_position];
      deck[random_to_position] = deck[random_from_position];
      deck[random_from_position] = previous;
    }
  }
  
  void deal_card_to_players(cPlayer[] players){
    for(int i = 0; i < players.length; i++){
      players[i].hand[0] = deck[current_card++];
      players[i].hand[1] = deck[current_card + players.length - 1];
      card_counter -= 2;
    }
  }
  
  void deal_card_to_table(cTable table){
    table.cards_in_table[table.number_of_cards] = deck[current_card++];
    card_counter -= 1;
    table.number_of_cards++;
  }
  
  void printDeck(){
    print("\nThere are " + card_counter + " cards.\n");
    
    for(int i = current_card; i < deck.length; i++){
      print(deck[i].name + ", ");
    }
  }
}
