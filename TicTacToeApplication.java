package ticTacToe;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;




public class TicTacToeApplication extends Application{
    
   
    
    public static void main(String[] args) {
        
        launch(TicTacToeApplication.class);
    }
    
    
    
    @Override
    public void start(Stage window) throws Exception{
        Game game = new Game();
        
        Font font = new Font("Monospaced", 40);
        
        VBox welcomeView = new VBox();
        HBox welcome = new HBox();
        HBox size = new HBox();
        HBox button = new HBox();
        //Create welcome Window
        //BorderPane welcomeView = new BorderPane();
        
        Button startButton = new Button("Start Game");
        startButton.setFont(font);
        
        TextField boardSize = new TextField("3");
        boardSize.setFont(font);
        boardSize.setMaxWidth(100);
        
        Label welcomeMessage = new Label("Welcome!  Enter New Board Width Or Press Start Game!");
        welcomeMessage.setFont(Font.font("Monospaced", 20));
        welcomeMessage.wrapTextProperty();
        welcomeMessage.setAlignment(Pos.CENTER);
        boardSize.setAlignment(Pos.CENTER);
        startButton.setAlignment(Pos.CENTER);
        
        
        welcome.getChildren().add(welcomeMessage);
        size.getChildren().add(boardSize);
        button.getChildren().add(startButton);
        welcomeView.getChildren().addAll(welcome, size, button);
        
        
        size.setAlignment(Pos.CENTER);
        button.setAlignment(Pos.CENTER);
        welcomeView.setSpacing(50);
        welcomeView.setPadding(new Insets(30, 30, 30, 30));
        
        

        //Create game Window
        BorderPane fullView = new BorderPane();
        HBox messageView = new HBox();
        Label message = new Label("Turn: X");
        message.setFont(font);
        message.setAlignment(Pos.CENTER);
        messageView.getChildren().add(message);
        messageView.setAlignment(Pos.CENTER);
        
        GridPane buttonView = new GridPane();
        Label bottomPlaceHolder = new Label("");
        buttonView.setPadding(new Insets(20, 20, 20, 20));
        buttonView.setAlignment(Pos.CENTER);
        buttonView.setHgap(20);
        buttonView.setVgap(20);
        
        HBox gameOverMenu = new HBox();
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setFont(font);
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setFont(font);
        gameOverMenu.getChildren().addAll(playAgainButton, mainMenuButton);
        gameOverMenu.setSpacing(50);
        gameOverMenu.setAlignment(Pos.CENTER);    
        fullView.setTop(messageView);
        fullView.setCenter(buttonView);
        fullView.setBottom(bottomPlaceHolder);
        fullView.setPadding(new Insets(20, 20, 20, 20));
        
        
        
        
        
        Scene gameScene = new Scene(fullView);
        Scene welcomeScene = new Scene(welcomeView);
        
        startButton.setOnAction((event)-> {
            int boardWidth = Integer.parseInt(boardSize.getText());
            game.setBoardWidth(boardWidth);
            game.createGameBoard();
            fullView.setPrefSize(game.getBoardWidth()*200, game.getBoardWidth()*175);
            
            
            for(int x = 0; x < boardWidth; x++){
            for(int y = 0; y < boardWidth; y++){
                Button btn = new Button("  ");
                btn.setFont(font);
                buttonView.add(btn, y, x);
                int xPos = x;
                int yPos = y;
                
                btn.setOnAction((event1) -> {
                    String symbol = game.playerTurn().getSymbol();
                    if(game.playTurn(xPos, yPos)){
                        btn.setText(symbol);
                        if(game.gameOver()){
                            message.setText("The end! " + game.getWinner());
                            fullView.setBottom(gameOverMenu);
                            
                        }else{
                            message.setText("Turn: "+ game.playerTurn().getSymbol());
                        }
                    }
                });
                
            }
        }
        
            window.setScene(gameScene);
        });
        
        playAgainButton.setOnAction((event)->{
            game.clear();
            buttonView.getChildren().clear();
            fullView.setBottom(bottomPlaceHolder);
            message.setText("Turn: X");
            for(int x = 0; x < game.getBoardWidth(); x++){
                for(int y = 0; y < game.getBoardWidth(); y++){
                    
                    Button btn1 = new Button("  ");
                    btn1.setFont(font);
                    buttonView.add(btn1, y, x);
                    
                    int xPos = x;
                    int yPos = y;
                
                    btn1.setOnAction((event1) -> {
                    String symbol = game.playerTurn().getSymbol();
                    if(game.playTurn(xPos, yPos)){
                        btn1.setText(symbol);
                        if(game.gameOver()){
                            message.setText("The end! " + game.getWinner());
                            fullView.setBottom(gameOverMenu);
                            
                        }else{
                            message.setText("Turn: "+ game.playerTurn().getSymbol());
                        }
                    }
                });
                    
                    
                }    
            }
        });
        
        
        mainMenuButton.setOnAction((event)->{
            
            game.clear();
            buttonView.getChildren().clear();
            fullView.setBottom(bottomPlaceHolder);
            message.setText("Turn: X");
            window.setScene(welcomeScene);        
        });
        
        
        
        window.setScene(welcomeScene);
        window.show();
        
    }
    
    

}
