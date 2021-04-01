import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class HijiDeck {

    private HijiCard[] cards;
    private int cardsInDeck;

    public HijiDeck() {
        cards = new HijiCard[108];
    }

    public void reset() {
        HijiCard.Color[] colors = HijiCard.Color.values();
        cardsInDeck = 0;

        for (int i = 0; i < colors.length - 1; i++) {
            HijiCard.Color warna = colors[i];
            cards[cardsInDeck++] = new HijiCard(warna, HijiCard.Value.getValue(0));

            for (int j = 0; j < 10; j++) {
                // HijiCard.Value[] values = HijiCard.Value.values();
                // HijiCard.Value nilai = values[i];

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

        HijiCard.Value[] values = new HijiCard.Value[] { HijiCard.Value.Wild, HijiCard.Value.Wild_Four };
        for (HijiCard.Value value : values) {
            for (int i = 0; i < 4; i++) {
                cards[cardsInDeck++] = new HijiCard(HijiCard.Color.Wild, value);
            }

        }

    }

    // public void replaceDeckWith(ArrayList<HijiCard> cards) {
    // this.cards = cards.toArray(new HijiCard(cards.si));
    // }

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
            // Get a random index of the array past the current index
            // ... The argument is an exclusive bound
            // swap the random element with the present element
            int randomValue = i + random.nextInt(n - i);
            HijiCard randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;

        }
    }

    public HijiCard drawCard() throws IllegalArgumentException {
        if (isEmpty()) {
            throw new IllegalArgumentException(" Tidak bisa draw kartu karena tidak ada kartu di deck");
        }
        return cards[--cardsInDeck];
    }

    public ImageIcon drawCardImage() throws IllegalArgumentException {
        if (isEmpty()) {
            throw new IllegalArgumentException("tidak bisa draw kartu karena deck kosong");
        }
        return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
    }


    public HijiCard[] drawCard(int n){
        if (n< 0) {
            throw new IllegalArgumentException("harus menarik kartu sejumlah angka positif tetapi anda mencoba untuk menarik " + n + "kartu");
        }

        if (n > cardsInDeck) {
            throw new IllegalArgumentException("tidak bisa menarik sejumlah " + n + " kartu karena hanya ada " + cardsInDeck + " kartu");
        }
    HijiCard[] ret = new HijiCard[n];

    for(
    int i = 0;i<n;i++)
    {
        ret[i] = cards[--cardsInDeck];
    }return ret;

}}