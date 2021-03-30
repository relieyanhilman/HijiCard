package hiji;

public class Card{
	enum Color{
		Red, Blue, Green, Yellow, Wild;

		private static final Color[] colors = Color.values();
		public static Color getColor(int i){
			return Color.colors[i];
		}
	}

	enum Value{
		Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, WildFour, Wild, Skip, Reverse;

		private static final Value[] values = Value.values();
		public static Value getValue(int i){
			return Value.values[i];
		}
	}

	private final Color color;
	private final Value value;

	public Card(final Color color, final Value value){
		this.color = color;
		this.value = value;
	}

	public Color getColor(){
		return this.color;
	}

	public value getValue(){
		return this.value;
	}

	public String toString(){
		return color + "_" + value;
	}
}