/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

/**
 *
 * @author Josh and Jessica
 */
public class Game {
    
    
    private Player player1;
    private Player player2;
    private int turnNumber;
    private String[][] gameBoard;
    private Player winner;
    private int boardWidth;
    
    
    
    public Game(){
        this.player1 = new Player("X");
        this.player2 = new Player("O");
        winner = null;
        
        
        turnNumber = 1;
    }
    
    public void setBoardWidth(int value){
        this.boardWidth = value;
    }
    public int getBoardWidth(){
        return this.boardWidth;
    }
    
    public void createGameBoard(){
        gameBoard = new String[this.boardWidth][this.boardWidth];
        for(int x = 0; x < this.boardWidth; x++){
            for(int y = 0; y < this.boardWidth; y++){
                gameBoard[x][y] = "";
            }
        }
    }
    public Player playerTurn(){
        if(turnNumber % 2 != 0){
            return this.player1;
        }
        return this.player2;
    }
    
    public boolean playTurn(int row, int column){
        Player player = this.playerTurn();
        if(gameBoard[row][column].equals("") && winner == null){
            gameBoard[row][column] = player.getSymbol();
            turnNumber++;
            if(columnWin(player) || rowWin(player) || diagonalWin(player)){
                winner = player;
            }
            
            return true;
        }
        return false;
    }
    
    public boolean gameOver(){
        if(turnNumber > this.boardWidth*this.boardWidth){
            return true;
        }
        if(winner != null){
            return true;
        }
        return false;
    }
    
    public String getWinner(){
           if(!(winner == null)){
            return winner.getSymbol()+ " Wins!";
        }
           return "It's a Tie!";
    }
    
    public void clear(){
        for(int x = 0; x < this.boardWidth; x++){
            for(int y = 0; y < this.boardWidth; y++){
                gameBoard[x][y] = "";
            }
        }
        winner = null;
        turnNumber = 1;
    }
    
    public boolean rowWin(Player player){
        String symbol = player.getSymbol();
        boolean win = false;
        for(int row = 0; row < this.boardWidth; row++){
            win = true;
            for(int column = 0; column < this.boardWidth; column++){
                if(!gameBoard[row][column].equals(symbol)){
                    win = false;
                }
            }
            if(win == true){
                return win;
            }
        }
        return win;
    }
    
    public boolean columnWin(Player player){
        String symbol = player.getSymbol();
        boolean win = false;
        for(int row = 0; row < this.boardWidth; row++){
            win = true;
            for(int column = 0; column < this.boardWidth; column++){
                if(!gameBoard[column][row].equals(symbol)){
                    win = false;
                }
            }
            if(win == true){
                return win;
            }
        }
        return win;
    }
    
    public boolean diagonalWin(Player player){
        String symbol = player.getSymbol();
        boolean win = true;
        int row = 0;
        int column  =0;
        while(row < boardWidth){
            if(!gameBoard[row][column].equals(symbol)){
                win = false;
            }
            row++;
            column++;
        }
        if(win == true){
            return win;
        }
        row = 0;
        column--;
        win = true;
        while(row < boardWidth){
            if(!gameBoard[row][column].equals(symbol)){
                win = false;
            }
            row++;
            column--;
        }
        return win;
    }
}
