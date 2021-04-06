public class SkipCard extends HijiCard {
    
    public SkipCard(HijiCard.Color color, HijiCard.Value value) {
        super(color, value);
        //TODO Auto-generated constructor stub
    }


    boolean gameDirection;
    int currentPlayer;
    private String[] playerIds;

    public void NgeSkip(){
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

    
