
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Hiji");
        Scanner sc = new Scanner(System.in);
        System.out.println("pilih menu : ");
        String pilihan = sc.nextLine();
        
        String[] Pemain = new String[] { "beyblade1", "beyblade2", "beyblade3", "beyblade4" };
        Game1 HIJI = new Game1(Pemain);
        
        while (!HIJI.isGameOver()) {
            while (!pilihan.equals("EXIT")) {
                if (pilihan.equals("F01")) {
                    System.out.println("START GAME");
                    HIJI.start(HIJI);
                    System.out.println(HIJI.deck.cardsInDeck);

                    System.out.println("yang bermain sekarang adalah: " + HIJI.getCurrentPlayer());
                    System.out.println("kartu yang lu punya sekarang " + HIJI.getPlayerHand(HIJI.getCurrentPlayer()));
                    

                    // pilihan = sc.nextLine();
                    System.out.println("pilih menu: ");
                    String pilihanFungsi = sc.nextLine();
                    // if (pilihanFungsi.equals("F04")){
                    //     HIJI.
                    // }
                    if (pilihanFungsi.equals("F02")){
                        //System.out.println(HIJI.getPlayerHand(HIJI.getCurrentPlayer()));
                        HIJI.ListCards(HIJI.getCurrentPlayer());
                    }else if (pilihanFungsi.equals("F03")){
                        System.out.println("submit player card: ");
                        System.out.println("nomor list kartu yang ingin dikeluarkan: ");
                        int pilihanKartuList = sc.nextInt();
                        HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), HIJI.getPlayerCard(HIJI.getCurrentPlayer(), (pilihanKartuList - 1) ), HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartuList - 1 ).getColors());
                
                    }
                    else if (pilihanFungsi.equals("F04")) {
                        System.out.println("Draw Kartu");
                        
                    }
                  

                    pilihan = sc.nextLine();

                    

                } else if (pilihan.equals("F02")) {
                    System.out.println("List Cards");
                    pilihan = sc.nextLine();
                } else if (pilihan.equals("F03")) {
                    System.out.println("Discard");
                    pilihan = sc.nextLine();
                } else if (pilihan.equals("F04")) {
                    System.out.println("Draw");
                    pilihan = sc.nextLine();
                } else if (pilihan.equals("F05")) {
                    System.out.println("Declare HIJI");
                    pilihan = sc.nextLine();
                } else if (pilihan.equals("F06")) {
                    System.out.println("List Players");
                    pilihan = sc.nextLine();
                } else if (pilihan.equals("F07")) {
                    System.out.println("View Player in Turn");
                    pilihan = sc.nextLine();
                } else if (pilihan.equals("F08")) {
                    System.out.println("Help");
                    pilihan = sc.nextLine();

                }
            } System.exit(0);
        } 
    }
}
