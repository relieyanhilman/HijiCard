import java.awt.Font;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game1 {
    private int currentPlayer;
    private String[] playerIds;

    HijiDeck deck;
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
        gameDirection = false;

        playerHand = new ArrayList<ArrayList<HijiCard>>();

        for (int i = 0; i < pids.length; i++) {
            ArrayList<HijiCard> hand = new ArrayList<HijiCard>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }

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

    

	public void start(Game1 game) {
        HijiCard card = deck.drawCard();
        System.out.println(card);
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
        
    public ImageIcon getTopCardImage() {
        return new ImageIcon(validColor + "-"+ validValue+ ".png");
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

    public boolean validCardPlay(HijiCard card){
        return card.getColors() == validColor || card.getValues() == validValue;
    }

    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        if (this.playerIds[this.currentPlayer] != pid) {
            throw new InvalidPlayerTurnException("it is not " + pid + " 's turn", pid);
        }
        
    }

    public void submitDraw(String pid) throws InvalidPlayerTurnException {
        checkPlayerTurn(pid);

        if (deck.isEmpty()){
            deck.replaceDeckWith(stockPile);
            deck.shuffle();

        }

        getPlayerHand(pid).add(deck.drawCard());
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
        throws InvalidColorSubmissionException, InvalidValueSubmissionException, InvalidPlayerTurnException {
            checkPlayerTurn(pid);

            ArrayList<HijiCard> pHand = getPlayerHand(pid);

            if(!validCardPlay(card)) {
                if (card.getColors() == HijiCard.Color.Wild) {
                    validColor = card.getColors();
                    validValue = card.getValues();
                }

                
                HijiCard.Value actual;
                HijiCard.Value expected;
                if (card.getColors() != validColor) {
                    String message = "Invalid player move, expected color: " + validColor + " but got color " + card.getColors();
                    // message.setFont(new Font("Arial", Font.BOLD, 48));
                    // JOptionPane.showMessageDialog(null, message);
                    throw new InvalidColorSubmissionException(message, card.getColors(), validColor);
                }

                else if (card.getValues() != validValue){
                    String message2 = "Invalid player move, expected color: " + validValue + " but got color " + card.getValues();
                    // message2.setFont(new Font("Arial", Font.BOLD, 48));
                    // JOptionPane.showMessageDialog(null, message2);
                    throw new InvalidValueSubmissionException(message2, card.getValues(), validValue);
                }
                
            }

        pHand.remove(card);

        if (hasEmptyHand(this.playerIds[currentPlayer])) {
            String message2 = this.playerIds[currentPlayer] + ("won the ! thank you for playing!");
            // message2.setFont(new Font("Arial", Font.BOLD, 48));
            // JOptionPane.showMessageDialog(null, message2);
            System.exit(0);
        }

        validColor = card.getColors();
        validValue = card.getValues();

        stockPile.add(card);
        
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
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            // JLabel message = new JLabel(pid + " drew 2 cards!");
        }
        
        if (card.getValues() == HijiCard.Value.WildFour) {
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            // JLabel message = new JLabel(pid + " drew 4 cards!");
        }

        if (card.getValues() == HijiCard.Value.Skip) {
            // JLabel message = new JLabel(playerIds[currentPlayer] + (" was skipped!"));
            // message.setFont(new Font("Arial", Font.BOLD, 48));
            // JOptionPane.showMessageDialog(null, message);
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
        }

        
    }
    

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

class InvalidColorSubmissionException extends Exception {
    private HijiCard.Color expected;
    private HijiCard.Color actual;
    public InvalidColorSubmissionException(String message, HijiCard.Color actual, HijiCard.Color expected) {
        this.actual = actual;
        this.expected = expected;
    }
}

class InvalidValueSubmissionException extends Exception {
    private HijiCard.Value expected;
    private HijiCard.Value actual;

    public InvalidValueSubmissionException(String message, HijiCard.Value actual, HijiCard.Value expected) {
        this.actual = actual;
        this.expected = expected;
    }
}
