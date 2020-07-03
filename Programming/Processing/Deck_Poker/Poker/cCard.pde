class cCard{
  int value;
  int suit;
  String name;
  
  String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "Ace"};
  String[] suits = {"clubs", "diamonds", "hearts", "spades"};
  
  cCard(int value, int suit){
    this.value = value;
    this.suit = suit;
    this.name = '"' + values[value] + " of " + suits[suit] + '"';
  }
  
  String getValue(){
    return this.values[value];
  }
  
  String getSuit(){
    return this.suits[suit];
  }
}
