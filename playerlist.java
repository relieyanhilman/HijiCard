package Game;

    public class PlayerList{

        private String playerIds[];
        private int cardAmount[];
        private boolean playing;

        public PlayerList (String playerIds[]){
            cardAmount = new int[playerIds.length];
            for (int i=0; i<playerIds.length; i++){
                cardAmount[i] = 0;
            }
            this.playerIds = playerIds;
        }
        public void printPlayerIds(int i){
            System.out.println("Pemain " + (i+1) + ": " + playerIds[i]);
        }
        public void printcardAmount(int i){
            System.out.println("Jumlah kartu " + (i+1) + ": " + cardAmount[i]);
        }   
    }

    // public String getcurrentPlayer(){
    //     return playerIds[currentPlayer];
    // }

    // public void printcurrentRole(int i){
    //     if ((playerIds[currentPlayer]) == playerIds[i]) {
    //         playing = true;
    //     }
    //     else{
    //         playing = false;
    //     }
    // }

    //for (int i=0; i<playerIds.length; i++){
    //   printPlayerIds(i);
    //   printcardAmount(i);
        // if (playing = true){
        //     System.out.println("Sedang giliran");
        // }
        // else{
        //     System.out.println("Tidak sedang giliran");
        // }
    //}

