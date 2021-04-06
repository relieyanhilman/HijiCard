import java.util.ArrayList;
import java.util.Random;



public class HijiDeck {

    private HijiCard[] cards;
    /* private*/ int cardsInDeck;

    public HijiDeck() {
        cards = new HijiCard[108];
    }

    public void reset() {
        HijiCard.Color[] colors = HijiCard.Color.values();
        cardsInDeck = 0;

        for (int i = 0; i < colors.length - 1; i++) {
            HijiCard.Color warna = colors[i];
            cards[cardsInDeck++] = new HijiCard(warna, HijiCard.Value.getValue(0));

            for (int j = 1; j < 10; j++) {

                cards[cardsInDeck++] = new HijiCard(warna, HijiCard.Value.getValue(j));
                cards[cardsInDeck++] = new HijiCard(warna, HijiCard.Value.getValue(j));
            }

            HijiCard.Value[] values = new HijiCard.Value[] { HijiCard.Value.DrawTwo, HijiCard.Value.Reverse,
                    HijiCard.Value.Skip };

            for (HijiCard.Value value : values) {
                cards[cardsInDeck++] = new HijiCard(warna, value);
                cards[cardsInDeck++] = new HijiCard(warna, value);
            }

        }

        HijiCard.Value[] values = new HijiCard.Value[] { HijiCard.Value.Wild, HijiCard.Value.WildFour };
        for (HijiCard.Value value : values) {
            for (int i = 0; i < 4; i++) {
                cards[cardsInDeck++] = new HijiCard(HijiCard.Color.Wild, value);
            }

        }

    }

    public void replaceDeckWith(ArrayList<HijiCard> cards) {
        this.cards = cards.toArray(new HijiCard[cards.size()]);
        this.cardsInDeck = this.cards.length;
    }

    public boolean isEmpty() {
        return cardsInDeck == 0;
    }

    public void shuffle() {
        int n = cardsInDeck;
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            HijiCard randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;

        }
    }

    public HijiCard drawCard() throws IllegalArgumentException {
        if (isEmpty()) {
            throw new IllegalArgumentException(" Tidak bisa draw kartu karena tidak ada kartu pada deck");
        }
        return cards[--cardsInDeck];
    }

    public HijiCard[] drawCard(int n){
        if (n< 0) {
            throw new IllegalArgumentException("Kartu harus ditarik sejumlah angka positif tetapi kamu mencoba untuk menarik " + n + "kartu!");
        }

        if (n > cardsInDeck) {
            throw new IllegalArgumentException("Tidak dapat menarik " + n + " kartu karena hanya ada " + cardsInDeck + " kartu");
        }
    HijiCard[] ret = new HijiCard[n];

    for(
    int i = 0;i<n;i++)
    {
        ret[i] = cards[--cardsInDeck];
    }return ret;

}}