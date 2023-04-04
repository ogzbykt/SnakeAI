import java.awt.Font;

//AI
public class Square {
	int x;
	int y;
	int type; //0 means block, 1 means food, 2 means snake
	int value;
	public Square(int x,int y,int type) {
		this.x=x;
		this.y=y;
		this.type=type;
		value = 1000000;
	}

	public boolean isOccupied() {
		if(type==0)
			return false;
		else
			return true;
	}

	public boolean equals(int x,int y) {
		return this.x == x && this.y == y;
	}

	public int number(int gridSize) {
		return y*gridSize+(x)+1;
	}

	public void draw(int gridSize) {
		if(type == 0)
			StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		else
			StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(x+0.5,y+0.5,0.5);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.square(x+0.5,y+0.5,0.5);
		StdDraw.setFont(new Font( "SansSerif", Font.PLAIN, 12));
		StdDraw.text(x+0.5,y+0.5,""+value);
		StdDraw.setFont(new Font( "SansSerif", Font.PLAIN, 10));
		int number = y*gridSize+(x)+1;
		StdDraw.text(x+0.85,y+0.85,""+number);
	}

	public void gameDraw() {
		if(type!=2) {
			if(type == 0)
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			else if(type == 1)
				StdDraw.setPenColor(StdDraw.PINK);

			StdDraw.filledSquare(x+0.5,y+0.5,0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square(x+0.5,y+0.5,0.5);
		}else {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledSquare(x+0.5,y+0.5,0.45);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square(x+0.5,y+0.5,0.45);
		}
	}

	public boolean equals(Square s) {
		return x==s.x&&y==s.y;
	}
}
