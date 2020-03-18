import java.lang.String;
import java.util.Scanner;
public class ticTakToe {
    private boardSymbols[][] board;
    private int turn;

    turnRunner turnRunner = new turnRunner();

    //constructor:
    public ticTakToe() {
         this.board = new boardSymbols[][]{{boardSymbols.empty, boardSymbols.empty, boardSymbols.empty},
                 {boardSymbols.empty, boardSymbols.empty, boardSymbols.empty},
                 {boardSymbols.empty, boardSymbols.empty, boardSymbols.empty}};
    }




    /////////////////////////////////////////////Methods:

    //pre-Process
    private void pick() {
        whoPlaysFirst();
        pickSymbol();
    }

    private void whoPlaysFirst() {
        System.out.println("Do you Wanna play first? \t(Y\\N)");
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while(!validInput) {
            String pick = scanner.nextLine();
            if(pick.equals("Y") || pick.equals("y") || pick.equals("yes") || pick.equals("Yes") || pick.equals("YES")) {
                boardSymbols.firstPlayer.setSome(true, "You");
                boardSymbols.secondPlayer.setSome(false, "Computer");
                validInput = true;
            }
            else if(pick.equals("N") || pick.equals("n") || pick.equals("no") || pick.equals("No") || pick.equals("NO")) {
                boardSymbols.firstPlayer.setSome(false, "Computer");
                boardSymbols.secondPlayer.setSome(true, "You");
                validInput = true;
            }
            else {
                System.out.println("Invalid input, select again.");
            }
        }
    }
    private void pickSymbol() {
        System.out.println("Which symbol do you want? \t(X\\O)");
        String symbol = "";
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while(!validInput){
            symbol = scanner.nextLine();
            if(symbol.equals("X") || symbol.equals("x")){
                if(boardSymbols.firstPlayer.isHuman) {
                    boardSymbols.firstPlayer.setSymbol("X");
                    boardSymbols.secondPlayer.setSymbol("O");
                }
                else {
                    boardSymbols.secondPlayer.setSymbol("X");
                    boardSymbols.firstPlayer.setSymbol("O");
                }
                validInput = true;
            }
            else if(symbol.equals("O") || symbol.equals("o")) {
                if(boardSymbols.firstPlayer.isHuman) {
                    boardSymbols.firstPlayer.setSymbol("O");
                    boardSymbols.secondPlayer.setSymbol("X");
                }
                else {
                    boardSymbols.secondPlayer.setSymbol("O");
                    boardSymbols.firstPlayer.setSymbol("X");
                }
                validInput = true;
            }
            else {
                System.out.println("Invalid input, select again.");
            }
        }
    }



    public void startGame() {


        while(true) {

            showBoard();
            //FirstPlayer turn:
            checkAndMake(boardSymbols.firstPlayer);

            if(gameEnded()) break;

            showBoard();
            //SecondPlayer turn:
            checkAndMake(boardSymbols.secondPlayer);

            if(gameEnded()) break;
            turn++;
        }

    }


    public void checkAndMake (boardSymbols player){
        int[] currMove;

        int[][] newBoard = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j].symbol.equals(" ")) {
                    newBoard[i][j] = 0;
                }
                else {
                    if(board[i][j].isHuman) {
                        newBoard[i][j] = -1;
                    }
                    else {
                        newBoard[i][j] = 1;
                    }
                }
            }
        }
        do{
            currMove = turnRunner.getTurn(player.isHuman, newBoard, turn);
            if(!legalMove(currMove)) {
                System.out.println("invalid input(the house is full)");
            }

        }while (!legalMove(currMove));
        makeMove(currMove, player);

    }
    public void makeMove(int[] currMove, boardSymbols currPlayer) {
        this.board[currMove[0]][currMove[1]] = currPlayer;
    }
    public boolean legalMove(int[] currMove) {
        return board[currMove[0]][currMove[1]] == boardSymbols.empty;
    }
    public boolean gameEnded() {

        boardSymbols winner = Win();
        if(winner != boardSymbols.empty) {
            //current boardSymbol won;
            System.out.println("Winner is " + winner.name);
            System.out.println("Symbol of winner: " + winner.symbol);
            return true;
        }
        if(Tie()) {
            System.out.println("Game is Tie!");
            return true;
        }
        return false;
    }
    public boolean Tie() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j].symbol.equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
    public boardSymbols Win() {
        int i, j;
        boolean flag = true;
        //ofoghi:
        for(i = 0; i < 3; i++) {
            flag = true;
            for(j = 1; j < 3; j++) {
                if(!board[i][j].symbol.equals(board[i][j-1].symbol)) {
                    flag = false;
                    break;
                }
            }
            if(flag && board[i][j-1] != boardSymbols.empty) {
                //SomeOneWon
                return board[i][j-1];
            }
        }

        //Amoodi:
        for(i = 0; i < 3; i++) {
            flag = true;
            for(j = 1; j < 3; j++) {
                if(!board[j-1][i].symbol.equals(board[j][i].symbol)) {
                    flag = false;
                    break;
                }
            }
            if(flag && board[j-1][i] != boardSymbols.empty) {
                //someOne Won
                return board[j-1][i];
            }
        }
        //Ghotri:
        flag = true;
        for(i = 1; i < 3; i++) {
            if(!board[i][i].symbol.equals(board[i-1][i-1].symbol)) {
                flag = false;
                break;
            }
        }
        if(flag && board[i-1][i-1] != boardSymbols.empty) {
            //SomeOnw Won
            return board[i-1][i-1];
        }

        //ghotri baraks:
        flag = true;
        for(i = 0; i < 2; i++) {
            if(!board[i][2-i].symbol.equals(board[i+1][2-(i+1)].symbol)) {
                flag = false;
                break;
            }
        }
        if(flag && board[0][2] != boardSymbols.empty) {
            //someOne Won
            return board[0][2];
        }
        return boardSymbols.empty;
    }
    public void showBoard() {
        System.out.println("¦---+---+---+---¦");
        System.out.println("¦   ¦ 0 ¦ 1 ¦ 2 ¦");
        for(int i = 0; i < 3; i++) {
            System.out.println("¦---+---+---+---¦");
            System.out.print("¦ " + i + " ¦ ");
            for(int j = 0; j < 3; j++) {
                System.out.print(this.board[i][j].symbol + " ¦ ");
            }
            System.out.println();
        }
        System.out.println("¦---+---+---+---¦");
    }
    public static void main(String[] args) {
        ticTakToe ticTakToe = new ticTakToe();
        ticTakToe.pick();
        ticTakToe.startGame();
        ticTakToe.showBoard();
    }
}







enum boardSymbols {
    empty(false, " ", "empty"),
    firstPlayer(false, " ", "empty"),
    secondPlayer(false, " ", "empty");

    public boolean isHuman;
    public String symbol;
    public String name;
    //int playerId; //1 plays first

    boardSymbols() {}

    boardSymbols(boolean isHuman, String symbol, String name) {
        this.isHuman = isHuman;
        this.symbol = symbol;
        this.name = name;
    }

    public void setSome(boolean isHuman, String name) {
        //this.playerId = playerId;
        this.isHuman = isHuman;
        this.name = name;
    }
    public void setSymbol(String string) {
        this.symbol = string;
    }


}
