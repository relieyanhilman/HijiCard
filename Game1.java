import java.awt.Font;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Scanner;

public class Game1 {
    Scanner sc = new Scanner(System.in);
    int jumlahKartuSubmit = 0;
    private int currentPlayer;
    int pemainSelanjutnya;
    String[] playerIds; //nnti diubah lagi jadi private

    HijiDeck deck; /* nanti diubah lagi jadi private */
    private ArrayList<ArrayList<HijiCard>> playerHand;
    private ArrayList<HijiCard> stockPile;

    private HijiCard.Color validColor;
    private HijiCard.Value validValue;

    boolean gameDirection;

    public Game1(String[] pids) {
        deck = new HijiDeck();
        

        // deck.shuffle();
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
    
    //shuffle urutan pemain pada awal permainan
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
        // System.out.println(card);
        validColor = card.getColors();
        validValue = card.getValues();

        if (card.getValues() == HijiCard.Value.Wild) {
            start(game);
        }

        if (card.getValues() == HijiCard.Value.WildFour || card.getValues() == HijiCard.Value.DrawTwo) {
            start(game);
        }

        if (card.getValues() == HijiCard.Value.Skip) {
            // JLabel message = new JLabel(playerIds[currentPlayer] + "was skipped!");
            // message.setFont(new Font("Arial", Font.BOLD, 48));
            // JOptionPane.showMessageDialog(null, message);

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
            // JLabel message = new JLabel(playerIds[currentPlayer] + " The game direction changed! ");
            // message.setFont(new Font("Arial", Font.BOLD, 48));
            // JOptionPane.showMessageDialog(null, message);
            gameDirection ^= true;
            currentPlayer = playerIds.length - 1;


        }

        stockPile.add(card);
    }

    public HijiCard getTopCard() {
        return new HijiCard(validColor, validValue);
    }
        
    // public ImageIcon getTopCardImage() {
    //     return new ImageIcon(validColor + "-"+ validValue+ ".png");
    // }

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

    // public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
    //     if (this.playerIds[this.currentPlayer] != pid) {
    //         throw new InvalidPlayerTurnException("it is not " + pid + " 's turn", pid);
    //     }
        
    // }

    public void submitDraw(String pid){ //throws InvalidPlayerTurnException 
        // checkPlayerTurn(pid);

        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();

        }

        getPlayerHand(pid).add(deck.drawCard());
        // if(gameDirection == false){
        //     currentPlayer = (currentPlayer +1) % playerIds.length;
        // }

        // else if(gameDirection == true){
        //     currentPlayer = (currentPlayer -1) % playerIds.length;
        //     if (currentPlayer == -1) {
        //         currentPlayer = playerIds.length - 1;
        //     }
        // } karena abis draw masih bisa nge submit card
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
            // checkPlayerTurn(pid);

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
                        
                        // message.setFont(new Font("Arial", Font.BOLD, 48));
                        // JOptionPane.showMessageDialog(null, message);
                        throw new InvalidCardSubmissionException(message);
                        
                        // message2.setFont(new Font("Arial", Font.BOLD, 48));
                        // JOptionPane.showMessageDialog(null, message2);
                        // throw new InvalidValueSubmissionException(message2, card.getValues(), validValue);
                        
                    }
                  
                

                else if (card.getValues() != validValue){
                    String message2 = "Invalid player move, expected color: " + validValue + " but got color " + card.getValues();
                    // message2.setFont(new Font("Arial", Font.BOLD, 48));
                //     // JOptionPane.showMessageDialog(null, message2);
                    throw new InvalidCardSubmissionException(message2);
                }
                
            }

        pHand.remove(card);

        if (hasEmptyHand(this.playerIds[currentPlayer])) {
            String message2 = this.playerIds[currentPlayer] + ("Congratulation you won the game! Thank you for playing!");
            // message2.setFont(new Font("Arial", Font.BOLD, 48));
            // JOptionPane.showMessageDialog(null, message2);
            System.exit(0);
        }

        // if (cardLeftOne(getCurrentPlayer())){
            
            
        // }

        validColor = card.getColors();
        validValue = card.getValues();

        stockPile.add(card);
        
        System.out.println("apakah ingin melakukan submit lagi? ya / tidak");
        String decision = sc.next();
        if (decision.equals("ya")){
                    System.out.println("Multiple Discard");
                    System.out.println("silakan pilih nomor kartu di list kartu: ");
                    ListCards(getCurrentPlayer());
                    int pilihanKartu = sc.nextInt();
                            
                    HijiCard.Color colorChoosen = HijiCard.Color.Red;
                    HijiCard cardChoosen = getPlayerCard(getCurrentPlayer(), pilihanKartu);
                    if ((cardChoosen.getColors() == validColor) && (cardChoosen.getValues() == validValue)){
                        jumlahKartuSubmit = jumlahKartuSubmit + 1;
                        submitPlayerCard(getCurrentPlayer(), cardChoosen, colorChoosen);
                    }
                    else{
                        System.out.println("kartu anda tidak valid. game akan dilanjutkan");
                    }
        } 

        jumlahKartuSubmit = jumlahKartuSubmit +1;
        
        

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
        }
            // JLabel message = new JLabel(pid + " drew 2 cards!");
        }
        
        if (card.getValues() == HijiCard.Value.WildFour) {
            pid = playerIds[currentPlayer];
            for(int i = 0; i< (jumlahKartuSubmit); i++){
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            }
            // JLabel message = new JLabel(pid + " drew 4 cards!");
        }

        if (card.getValues() == HijiCard.Value.Skip) {
            // JLabel message = new JLabel(playerIds[currentPlayer] + (" was skipped!"));
            // message.setFont(new Font("Arial", Font.BOLD, 48));
            // JOptionPane.showMessageDialog(null, message);
            
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
            // JLabel message = new JLabel(pid + (" changed the game direction"));
            // message.setFont(new Font("Arial", Font.BOLD, 48));
            // JOptionPane.showMessageDialog(null, message);

            
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
            
            for(int i = 0; i< (jumlahKartuSubmit-1); i++){
                gameDirection ^= true;
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
    jumlahKartuSubmit = 0;

}

class InvalidPlayerTurnException extends Exception {
    /**
     *
     */
    // private static final long serialVersionUID = 1L;
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
// class InvalidColorSubmissionException extends Exception {
//     private HijiCard.Color expected;
//     private HijiCard.Color actual;
//     public InvalidColorSubmissionException(String message, HijiCard.Color actual, HijiCard.Color expected) {
//         this.actual = actual;
//         this.expected = expected;
//     }
// }

// class InvalidValueSubmissionException extends Exception {
//     private HijiCard.Value expected;
//     private HijiCard.Value actual;

//     public InvalidValueSubmissionException(String message, HijiCard.Value actual, HijiCard.Value expected) {
//         this.actual = actual;
//         this.expected = expected;
//     }
// }

class InvalidCardSubmissionException extends Exception {
    private HijiCard.Color expected;
    private HijiCard.Color actual;
    public InvalidCardSubmissionException(String message){
        System.out.println(message);
    }
}
