class cGame{
  cPlayer[] players_in_game;
  cTable table;
  cDeck deck_in_game;
  
  String currentTurn;
 
  cGame(cPlayer[] players_in_game, cTable table, cDeck deck_in_game, String currentTurn){
    this.players_in_game = players_in_game;
    this.table = table;
    this.deck_in_game = deck_in_game;
    this.currentTurn = currentTurn;
  }
}
