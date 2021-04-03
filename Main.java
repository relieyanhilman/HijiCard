
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Hiji");
        Scanner sc = new Scanner(System.in);
        System.out.println("pilih menu : ");
        String pilihan = sc.nextLine();
        while (!pilihan.equals("EXIT")) {
            if (pilihan.equals("F01")) {
                System.out.println("START GAME");
                Game HIJI = new Game();
                for (int i = 0; i< HIJI.playerIds.length; i++){
                    System.out.println();
                }

            } else if (pilihan.equals("F02")) {
                System.out.println("List Cards");
                ListCards(getCurrentPlayer());
            } else if (pilihan.equals("F03")) {
                System.out.println("Discard");
            } else if (pilihan.equals("F04")) {
                System.out.println("Draw");
            } else if (pilihan.equals("F05")) {
                System.out.println("Declare HIJI");
            } else if (pilihan.equals("F06")) {
                System.out.println("List Players");
            } else if (pilihan.equals("F07")) {
                System.out.println("View Player in Turn");
            } else if (pilihan.equals("F08")) {
                System.out.println("Help");
            }
        }
    }
}
