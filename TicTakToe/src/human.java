import java.util.Scanner;

public class human {
    public int[] humanTurn() {

        System.out.println("Your turn...");
        Scanner scan = new Scanner(System.in);
        int currMove_x = scan.nextInt();
        int currMove_y = scan.nextInt();
        scan.nextLine();
        int[] move = new int[2];
        move[0] = currMove_x;
        move[1] = currMove_y;
        return move;
    }

    public human() {}
}
