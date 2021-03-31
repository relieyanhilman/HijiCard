package hiji;

public class Game {
	public static final int NUM_CARD_HAND = 7;
	public static Deck deck;
	public static ArrayList<Player> players;
	public static int nextTurn;
	public static Card prevCard;
	public static boolean forward;
	public static Scanner scan;
	
	public Game(int NPemain) {
		nextTurn = 0;
		forward = true;
		makeDeck();
		makePlayers(playNum);
		prevCard = deck.drawCard();
		while (!(prevCard instanceof NumberCard)) {
			deck.addCard(prevCard);
			prevCard = deck.drawCard();
		}
	}
	public void makePlayers(int p) {
		players = new ArrayList<Player>(p);
		Player c = null;
		c = new Player(0);
		for (int t = 0; t < NUM_CARD_HAND; t++) {
			c.addCard(deck.drawCard());
		}
		players.add(c);
		for (int i = 1; i < p; i++) {
			c = new MedPlayer(i);
			for (int t = 0; t < NUM_CARD_HAND; t++) {
				c.addCard(deck.drawCard());
			}
			players.add(c);
		}
	}
	public Player getCurrentPlayer() {
		return players.get(nextTurn);
	}

	public Card getPrevCard() {
		return prevCard;
	}

	public static void main(String[] args) {
		System.out.println("Berapa banyak Pemain? ");
		scan = new Scanner(System.in);
		Game u = new Game(scan.nextInt());
		for (int i = 1; i < nextInt(); i++) {
			System.out.println("Nama pemain " + i ":");
			String nama = in.nextLine();
			setNama(nama);
		}
		System.out.println("Current Card:" + u.getPrevCard());
	}
	class Deck {

		ArrayList<Card> pile;
		ArrayList<Card> cards;

		public Deck(ArrayList<Card> c) {
			cards = c;
		}

		public Deck() {
			cards = new ArrayList<Card>(108);
		}

		public void addCard(Card c) {
			cards.add(c);
		}

		public Card drawCard() {
			if (cards.size() == 0) {
				Uno.makeDeck();
			}
			return cards.remove(0);
		}
	}
	public static void makeDeck() {
		deck = new Deck();
	}
	public void subtractCard(Card c) {
		for (int i = 0; i < kartu.size(); i++) {
			if (c.equals(kartu.get(i))) {
				kartu.remove(i);
				i = kartu.size();
			}
		}
}
		
