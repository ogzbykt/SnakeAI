import java.util.ArrayList;

public class Snake {

	int length;
	ArrayList<Square> body;
	Square head;

	public Snake(Square head) {
		this.head = head;
		head.type = 2;
		body = new ArrayList<>();
		length = 1;
	}

	public void move(ArrayList<Square> path) {
		int s = 0;
		for(int i=0;i<path.size();i++)
			if(path.get(i).equals(head))
				s=i;
		int p = (s+1)%path.size();
		int oldx = head.x;
		int oldy = head.y;
		head.x=path.get(p).x;
		head.y=path.get(p).y;
		if(body.size()>0) {
			for(int i=body.size()-1;i>0;i--) {
				body.get(i).x=body.get(i-1).x;
				body.get(i).y=body.get(i-1).y;
			}
			body.get(0).x=oldx;
			body.get(0).y=oldy;
		}

	}

	public void draw() {
		head.gameDraw();
		StdDraw.square(head.x+0.33,head.y+0.5,0.05);
		StdDraw.square(head.x+0.66,head.y+0.5,0.05);
		StdDraw.filledCircle(head.x+0.66,head.y+0.5,0.025);
		StdDraw.filledCircle(head.x+0.33,head.y+0.5,0.025);
		for(Square x: body)
			x.gameDraw();
	}

	public void eat(ArrayList<Square> path) {
		Square temp = null;
		if(body.size()>0)
			temp = new Square(body.get(body.size()-1).x,body.get(body.size()-1).y,2);
		else
			temp = new Square(head.x,head.y,2);
		int s = 0;
		for(int i=0;i<path.size();i++)
			if(path.get(i).equals(head))
				s=i;
		int p = (s+1)%path.size();
		int oldx = head.x;
		int oldy = head.y;
		head.x=path.get(p).x;
		head.y=path.get(p).y;
		if(body.size()>0) {
			for(int i=body.size()-1;i>0;i--) {
				body.get(i).x=body.get(i-1).x;
				body.get(i).y=body.get(i-1).y;
			}
			body.get(0).x=oldx;
			body.get(0).y=oldy;
		}
		body.add(temp);
		length++;
	}


}
