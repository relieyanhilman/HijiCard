import java.util.Scanner;

public class PlayerRound {
    private Player [] players;


    PlayerRound(int jumlahPemain){
    Scanner scan = new Scanner(System.in);
    for (int i = 0;i<(jumlahPemain-1);i++)
    {
        System.out.print("Masukkan nama pemain : ");
        players[i]=scan.nextLine();
    }
    }

    
    
}
