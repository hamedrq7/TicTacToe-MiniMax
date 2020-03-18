/*
 function miniMax(node, depth, maximizingPlayer) is
 if depth = 0 or node is a terminal node then
     return the heuristic value of node
 if maximizingPlayer then
     value := −∞
     for each child of node do
         value := max(value, miniMax(child, depth − 1, FALSE))
     return value
 else (* minimizing player *)
     value := +∞
     for each child of node do
         value := min(value, miniMax(child, depth − 1, TRUE))
     return value
*/

public class miniMax {
    int turn;

    public int[] miniMaxTurn(int[][] board, int turn) {
        this.turn = turn;
        int currMove_x = 2, currMove_y = 2;
        System.out.println("Computer turn...");
        int move[] = new int[2];

        int value = -100;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == 0) {

                    //make move
                    board[i][j] = 1;
                    int moveEval = mm(board, 0, false);

                    //undo the move:
                    board[i][j] = 0;

                    if(moveEval > value) {
                        value = moveEval;
                        currMove_x = i;
                        currMove_y = j;
                    }
                }
            }
        }


        move[0] = currMove_x;
        move[1] = currMove_y;
        return move;
    }
    public miniMax(){}

    public int mm(int[][] board, int depth, boolean maximizingPlayer) {
        if(humanWon(board)) {
            return -1;
        }
        if(compWon(board)) {
            return 1;
        }
        if(isTie(board)) {
           return 0;
        }
        if(maximizingPlayer) {
            int value = -100;

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(board[i][j] == 0) {

                        //make move:
                        board[i][j] = 1;
                        int moveEval = mm(board, depth+1, false);

                        //undo the move:
                        board[i][j] = 0;

                        if(value < moveEval) {
                            value = moveEval;
                        }
                    }
                }
            }
            return value;
        }
        else {
            int value = +100;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(board[i][j] == 0) {

                        //make move
                        board[i][j] = -1;
                        int moveEval = mm(board, depth+1, true);

                        //undo :
                        board[i][j] = 0;

                        if(value > moveEval) {
                            value = moveEval;
                        }
                    }
                }
            }
            return value;
        }

    }




    public boolean humanWon(int [][] board) {
        return(checkWin(board, -1));
    }
    public boolean compWon(int [][] board) {
        return(checkWin(board, 1));
    }
    public boolean checkWin(int[][] board, int id) {
        boolean flag;
        for(int i = 0; i < 3; i++) {
            flag = true;
            for(int j = 0; j < 3; j++) {
                if(board[i][j] != id){
                    flag = false;
                }
            }
            if(flag) {
                return true;
            }
            flag = true;
            for(int j = 0; j < 3; j++) {
                if(board[j][i] != id){
                    flag = false;
                }
            }
            if(flag) {
                return true;
            }
        }
        if(board[0][0] == id && board[1][1] == id && board[2][2] == id) {
            return true;
        }
        if(board[0][2] == id && board[1][1] == id && board[2][0] == id) {
            return true;
        }
        return false;
    }
    public boolean isTie(int[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
