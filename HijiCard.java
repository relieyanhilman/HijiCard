// package hiji;

public class HijiCard{
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

	public HijiCard(final Color color, final Value value){
		this.color = color;
		this.value = value;
	}

	public Color getColors(){
		return this.color;
	}

	public Value getValues(){
		return this.value;
	}

	public String toString(){
		return color + "_" + value;
	}
}

class SpecialCard {
	public void cardPower() {
	  System.out.println("Tiap power card memiliki kekuatannya masing-masing");
	}
  }
  
  class Skip extends SpecialCard {
	public void cardPower() {
	  System.out.println("Kartu ini dapat men-skip giliran pemain selanjutnya");
	}
  }
  
  class Reverse extends SpecialCard {
	public void cardPower() {
	  System.out.println("Kartu ini dapat memutarbalikkan urutan permainan");
	}
  }