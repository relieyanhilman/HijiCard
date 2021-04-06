import java.util.ArrayList;

public interface player {
    // Method untuk memilih pemain yang main pada  pertama secara  random
    public void randomPemain();
    // Method untuk mendapatkan list pemain, jumlah kartu yang dimiliki, dan status giliran main
    public void ListPlayers();
    // Method untuk mendapatkan nama pemain yang sedang dalam giliran main
    public String getCurrentPlayer();
    // Method untuk mendapatkan i pemain sebelum pemain yang sedang dalam giliran main
    public String getPreviousPlayer(int i);
    // Method untuk mendapatkan pemain sebelumnya
    public String[] getPlayers();
    // Method untuk mendapatkan list pemain yang sedang memainkan game
    public ArrayList<HijiCard> getPlayerHand(String pid);
    // Method untuk mendapatkan list kartu yang dimiliki oleh pemain
    public int getPlayerHandSize(String pid);
    // Method untuk mengecek jumlah kartu yang sedang ada pada pemain
    public HijiCard getPlayerCard(String pid, int choice);
    // Method untuk mendapatkan kartu yang dipilih pemain
    public boolean hasEmptyHand(String pid);
    // Method untuk mencari pemenang dengan mengecek pemain yang sudah tidak memiliki kartu
    
}
