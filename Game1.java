 
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game1 implements player {
    Scanner sc = new Scanner(System.in);
    GenericPrint gp2 = new GenericPrint();
    int jumlahKartuSubmit = 0; 
    // Untuk keperluan multiple discard, sebagai penghitung seberapa banyak kartu yang telah disubmit dalam 1 waktu
    private int currentPlayer;
    int pemainSelanjutnya;
    private String[] playerIds; 
    private HijiDeck deck; 
    private ArrayList<ArrayList<HijiCard>> playerHand;
    private ArrayList<HijiCard> stockPile;
    private HijiCard.Color validColor;
    private HijiCard.Value validValue;
    boolean gameDirection;

    public Game1(String[] pids) {
        deck = new HijiDeck();
        deck.reset();
        deck.shuffle();
        stockPile = new ArrayList<HijiCard>();

        playerIds = pids;
        currentPlayer = 0;
        randomPemain();
        gameDirection = false;

        playerHand = new ArrayList<ArrayList<HijiCard>>();

        for (int i = 0; i < pids.length; i++) {
            ArrayList<HijiCard> hand = new ArrayList<HijiCard>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }
    }
    
    // Shuffle urutan pemain pada awal permainan
    public void randomPemain(){  
        Random random = new Random();
        int randomValue = random.nextInt(playerIds.length);
        currentPlayer = randomValue;
    }

    // F02
    public void ListCards(String pid){
        int nomor = 1;
        System.out.println("Kartu yang dimiliki :");
         for (int i=0;i<=(getPlayerHandSize(pid)-1);i++){
             System.out.println(nomor+". "+getPlayerCard(pid, i));
             nomor++;
         }
    }

    // F06
    public void ListPlayers() {
        int nomor = 1;
        for (int i=0 ; i<=(getPlayers().length-1) ;i++){
            System.out.println("Pemain "+nomor+": "+playerIds[i]);
            System.out.println("Jumlah kartu : "+getPlayerHandSize(playerIds[i]));
            if (playerIds[i] == getCurrentPlayer()){
            System.out.println("Sedang  giliran");
            }
            else if (playerIds[i] != getCurrentPlayer()){
            System.out.println("Tidak sedang dalam giliran");
            }
            System.out.println();
            nomor++;        
        }
    }

    // F07
    public void ViewPlayerInTurn() {
        System.out.println("Sekarang giliran : "+getCurrentPlayer());     
        if (gameDirection == false) {
            pemainSelanjutnya = ((currentPlayer + 1) % playerIds.length);
        }
        else if(gameDirection == true) {
            pemainSelanjutnya = (currentPlayer - 1) % playerIds.length;
            if (currentPlayer == -1) {
                pemainSelanjutnya = playerIds.length - 1;
            }
        }
        System.out.println("Selanjutnya giliran: " + playerIds[pemainSelanjutnya]);
       
    }
    

	public void start(Game1 game) {
        HijiCard card = deck.drawCard();
        validColor = card.getColors();
        validValue = card.getValues();

        if (card.getValues() == HijiCard.Value.Wild) {
            start(game);
        }

        if (card.getValues() == HijiCard.Value.WildFour || card.getValues() == HijiCard.Value.DrawTwo) {
            start(game);
        }

        if (card.getValues() == HijiCard.Value.Skip) {
           
            if (gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }

            else if (gameDirection == true) {
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if (currentPlayer == -1){
                    currentPlayer = playerIds.length -1;
                }
            }

        }
        if (card.getValues() == HijiCard.Value.Reverse) {
            gameDirection ^= true;
            currentPlayer = playerIds.length - 1;
        }

        stockPile.add(card);
    }

    public HijiCard getTopCard() {
        return new HijiCard(validColor, validValue);
    }

    public boolean isGameOver() {
        for (String player : this.playerIds){
            if(hasEmptyHand(player)){
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlayer() {
        return this.playerIds[this.currentPlayer];
    }

    public String getPreviousPlayer(int i){
        int Index = this.currentPlayer - i;
        if (Index == -1){
            Index = playerIds.length - 1;
        }
        return this.playerIds[Index];
    }
    public String[] getPlayers(){
        return playerIds;
    }

    public ArrayList<HijiCard> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid);
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size();
    }

    public HijiCard getPlayerCard(String pid, int choice) {
        ArrayList<HijiCard> hand = getPlayerHand(pid);
        return hand.get(choice);
    }

    public boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public boolean cardLeftOne(String pid){
        return (getPlayerHandSize(getCurrentPlayer())==1);
    }

    public boolean validCardPlay(HijiCard card){
        return card.getColors() == validColor || card.getValues() == validValue;
    }


    public void submitDraw(String pid){      
        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());
    }

    public void lanjutMain(){
        if(gameDirection == false) {
            currentPlayer = (currentPlayer +1) % playerIds.length;
        }

        else if(gameDirection == true){
            currentPlayer = (currentPlayer -1) % playerIds.length;
            if (currentPlayer == -1) {
                currentPlayer = playerIds.length - 1;
            }
        }
    }

    public void setCardColor (HijiCard.Color color) {
        validColor = color;
    }
 
    public void submitPlayerCard(String pid, HijiCard card, HijiCard.Color declaredColor) 
        throws InvalidCardSubmissionException {
            ArrayList<HijiCard> pHand = getPlayerHand(pid);    
            if(!validCardPlay(card)) {
                if (card.getColors() == HijiCard.Color.Wild) {
                    validColor = card.getColors();
                    validValue = card.getValues();
                }
    
                HijiCard.Value actual;
                HijiCard.Value expected;
                
                if ((card.getColors() != validColor)) {
                        String message = "Invalid player move, expected color: " + validColor + " but got color " + card.getColors();                   
                        throw new InvalidCardSubmissionException(message);   
                    }
                  
                else if (card.getValues() != validValue){
                    String message2 = "Invalid player move, expected color: " + validValue + " but got color " + card.getValues();                 
                    throw new InvalidCardSubmissionException(message2);
                }
                
            }

        pHand.remove(card);

        // Pengondisian Declare HIJI
        long startTime;
        String inputhiji;
        if (getPlayerHandSize(this.playerIds[currentPlayer]) == 1){

            System.out.println("Waktunya declare HIJI!");
            
            startTime = System.currentTimeMillis();
            System.out.println("Ketik HIJI dalam 3 detik");
            inputhiji = sc.next().toUpperCase();

            if ((inputhiji == "HIJI") && (System.currentTimeMillis() - startTime > 1000)){
              
                System.out.println("Declare HIJI sukses!");      
                } 

            if (System.currentTimeMillis() - startTime > 3000){
                System.out.println("Kamu telat declare HIJI! Kamu dapat tambahan 2 kartu!");
                getPlayerHand(pid).add(deck.drawCard());
                getPlayerHand(pid).add(deck.drawCard());
                }
            }

        if (hasEmptyHand(this.playerIds[currentPlayer])) {
            String message2 = this.playerIds[currentPlayer] + ("Selamat kamu menang! Sampai jumpa lagi di dunia HIJI!");
            gp2.printAnything(message2);
            
            System.exit(0);
        }

        
        validColor = card.getColors();
        validValue = card.getValues();

        stockPile.add(card);
        
        System.out.println("Apakah ingin melakukan submit lagi? Ya / Tidak");
        String decision = sc.next();
        
        
        while (!decision.equals("Tidak")){
            
                    System.out.println("Multiple Discard");
                    System.out.println("Silahkan pilih nomor kartu di list kartu: ");
                    ListCards(getCurrentPlayer());
                    int pilihanKartu = sc.nextInt();
                            
                    HijiCard.Color colorChoosen = HijiCard.Color.Red;
                    HijiCard cardChoosen = getPlayerCard(getCurrentPlayer(), pilihanKartu - 1);
                    if ((cardChoosen.getColors() == validColor) && (cardChoosen.getValues() == validValue)){
                        
                        jumlahKartuSubmit = jumlahKartuSubmit + 1;
                        pHand.remove(cardChoosen);

                        if (hasEmptyHand(this.playerIds[currentPlayer])) {
                            String message2 = this.playerIds[currentPlayer] + ("Selamat kamu menang! Sampai jumpa lagi di dunia HIJI!");
                            
                            System.exit(0);
                        }
                        
                        stockPile.add(card);
                        
                    }
                    else{
                        System.out.println("Kartu kamu tidak valid. Game akan dilanjutkan");
                    }

                    System.out.println("Apakah kamu ingin melakukan submit lagi? Ya / Tidak");
                    decision = sc.next();
        } 

        jumlahKartuSubmit = jumlahKartuSubmit +1;
    
        //melanjutkan permainan ke pemain selanjutnya
        if (gameDirection == false) {
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        }

        else if(gameDirection == true) {
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if (currentPlayer == -1) {
                currentPlayer = playerIds.length - 1;
            }
        }

        
        if (card.getColors() == HijiCard.Color.Wild) {
            validColor = declaredColor;
        }

        if (card.getValues() == HijiCard.Value.DrawTwo) {
            pid = playerIds[currentPlayer];
            for(int i = 0; i< (jumlahKartuSubmit); i++){
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            if (gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }
    
            else if(gameDirection == true) {
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if (currentPlayer == -1) {
                    currentPlayer = playerIds.length - 1;
                }
            }
        }
            
        }
        
        if (card.getValues() == HijiCard.Value.WildFour) {
            pid = playerIds[currentPlayer];
            for(int i = 0; i< (jumlahKartuSubmit); i++){
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            if (gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }
    
            else if(gameDirection == true) {
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if (currentPlayer == -1) {
                    currentPlayer = playerIds.length - 1;
                }
            }
            }
        }

        if (card.getValues() == HijiCard.Value.Skip) {
            
            for(int i = 0; i< (jumlahKartuSubmit); i++){
            if (gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }

            else if(gameDirection == true){
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if (currentPlayer == -1){
                    currentPlayer = playerIds.length -1 ;
                }
            }
        }
        }

        if (card.getValues() == HijiCard.Value.Reverse) {

            if (jumlahKartuSubmit % 2 == 1){
                gameDirection ^= true;
                if (gameDirection == true) {
                    currentPlayer = (currentPlayer -2) % playerIds.length;
                    if (currentPlayer == -1){
                        currentPlayer = playerIds.length -1 ;
                    }
    
                    if (currentPlayer == -2) {
                        currentPlayer = playerIds.length -2;
                    }
                }
                else if (gameDirection == false) {
                    currentPlayer = (currentPlayer + 2) % playerIds.length;
                }
                
            }
            
    }
    
    jumlahKartuSubmit = 0;

}

class InvalidPlayerTurnException extends Exception {

    String playerId;

    public InvalidPlayerTurnException(String message, String pid) {
        super(message);
        playerId= pid;
    }

    public String getPid() {
        return playerId;
    }
}
}

class InvalidCardSubmissionException extends Exception {
    private HijiCard.Color expected;
    private HijiCard.Color actual;
    public InvalidCardSubmissionException(String message){
        System.out.println(message);
    }
}


