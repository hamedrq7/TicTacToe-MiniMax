import java.util.Scanner;

public class turnRunner {
    // 0 is for human, 1 is for computer

    human human = new human();
    miniMax miniMax = new miniMax();

    public int[] getTurn(boolean isHuman, int[][] board, int turn) {

        int[] currMove = new int[2];

        if(isHuman) {
            currMove = human.humanTurn();
        }
        else {
            currMove = miniMax.miniMaxTurn(board, turn);
        }

        return currMove;
    }

    public turnRunner() {}
}
