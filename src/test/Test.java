package test;

public class Test {
	private int[][] matrice = new int[4][4];
	
	public void createMatrice() {	
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				this.matrice[i][j] = 2;
			}
		}
	}
	
	public String printMatrice() {
		String text = "";
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				text += this.matrice[i][j] + " ";
			}
			text += "\n";
		}
		return text;
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		t.createMatrice();
		System.out.println(t.printMatrice());
	}
}
