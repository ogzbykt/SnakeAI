import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
//AI
public class Main {

	public static void main(String[] args) throws InterruptedException {
		StdDraw.setCanvasSize(512, 512);

		StdDraw.enableDoubleBuffering();
		int gridSize = 6;
		StdDraw.setScale(0, gridSize);
		Square [][] grid = new Square[gridSize][gridSize];
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				grid[i][j]=new Square(i,j,0);
			}
		}
		int a = 0;
		grid[a][a].value=Integer.MIN_VALUE;;
		grid[a][a].type=2;
		ArrayList<Square> path = new ArrayList();
		while(true) {
			path = new ArrayList();
			grid[a][a].value=Integer.MIN_VALUE;;
			grid[a][a].type=2;
			Square start = grid[a][a];
			Square current = start;

			while(path.size()!=gridSize*gridSize) {
				drawGrid(grid);
				StdDraw.show();
				path.add(current);
				//if(path.contains(grid[start.x-1][start.y])&&path.contains(grid[start.x+1][start.y])&&path.contains(grid[start.x][start.y-1])&&path.contains(grid[start.x][start.y+1]))
				if(path.contains(grid[start.x+1][start.y])&&path.contains(grid[start.x][start.y+1]))
					break;	
				Square next=null;
				if(current.x==0&&grid[current.x+1][current.y].type!=2)
					next=grid[current.x+1][current.y];
				else if(current.x!=0&&grid[current.x-1][current.y].type!=2)
					next=grid[current.x-1][current.y];
				else if(current.y==0&&grid[current.x][current.y+1].type!=2)
					next=grid[current.x][current.y+1];
				else if(current.y!=0&&grid[current.x][current.y-1].type!=2)
					next=grid[current.x][current.y-1];

				if(next==null) {
					break;
				}

				if(next.x-current.x!=0) {//next is somewhere in the x axis
					if(current.x!=0) {//next is left
						if(current.x!=gridSize-1&&grid[current.x+1][current.y].type!=2) {//right
							if(grid[current.x+1][current.y].value>next.value)
								next=grid[current.x+1][current.y];
						}
						if(current.y!=0&&grid[current.x][current.y-1].type!=2) {//down
							if(grid[current.x][current.y-1].value>next.value)
								next=grid[current.x][current.y-1];
						}
						if(current.y!=gridSize-1&&grid[current.x][current.y+1].type!=2) {//up
							if(grid[current.x][current.y+1].value>next.value)
								next=grid[current.x][current.y+1];
						}
					}else {//next is right and we can't go left
						if(current.y!=0&&grid[current.x][current.y-1].type!=2) {//down
							if(grid[current.x][current.y-1].value>next.value)
								next=grid[current.x][current.y-1];
						}
						if(current.y!=gridSize-1&&grid[current.x][current.y+1].type!=2) {//up
							if(grid[current.x][current.y+1].value>next.value)
								next=grid[current.x][current.y+1];
						}

					}
				}else {//next is on the y axis
					if(current.y!=0) {//next is down
						if(current.x!=gridSize-1&&grid[current.x+1][current.y].type!=2) {//right
							if(grid[current.x+1][current.y].value>next.value)
								next=grid[current.x+1][current.y];
						}
						if(current.x!=0&&grid[current.x-1][current.y].type!=2) {//left
							if(grid[current.x-1][current.y].value>next.value)
								next=grid[current.x][current.y-1];
						}
						if(current.y!=gridSize-1&&grid[current.x][current.y+1].type!=2) {//up
							if(grid[current.x][current.y+1].value>next.value)
								next=grid[current.x][current.y+1];
						}
					}else {//next is up and we can't go down
						if(current.x!=gridSize-1&&grid[current.x+1][current.y].type!=2) {//right
							if(grid[current.x+1][current.y].value>next.value)
								next=grid[current.x+1][current.y];
						}
						if(current.x!=0&&grid[current.x-1][current.y].type!=2) {//left
							if(grid[current.x-1][current.y].value>next.value)
								next=grid[current.x][current.y-1];
						}
					}
				}

				current=next;
				current.type=2;
				int count = 0;
				if(current.x!=0) 
					if(grid[current.x-1][current.y].type != 2)
						count++;
				if(current.x!=gridSize-1)
					if(grid[current.x+1][current.y].type != 2)
						count++;
				if(current.y!=0)
					if(grid[current.x][current.y-1].type != 2)
						count++;
				if(current.y!=gridSize-1)
					if(grid[current.x][current.y+1].type != 2)
						count++;
				current.value+=20-(count*5);
				current.value+=path.size();

			}
			drawGrid(grid);
			StdDraw.show();
			int penalty = ((gridSize*gridSize)-path.size())*10;
			if(path.size()==gridSize*gridSize) {
				if(Math.abs((current.x-start.x)+(current.y-start.y))==1) {
					for(Square x:path) {
						System.out.print(x.number(gridSize)+" ");
					}
					break;
				}else {
					penalty=gridSize*gridSize*10;
					for(int i=path.size();i>=0;i--) {
						path.get(i).value-=penalty;
						path.get(i).type = 0;
						penalty-=10;
					}
					continue;
				}
			}

			
			for(int i=0;i<path.size();i++) {
				path.get(i).value-=penalty;
				path.get(i).type = 0;
				//penalty-=10;
			}
		}
		drawGrid(grid);
		StdDraw.show();
		
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				grid[i][j]=new Square(i,j,0);
			}
		}
		
		Random rand = new Random();
		int startx = rand.nextInt(gridSize);
		int starty = rand.nextInt(gridSize);
		
		Snake snake = new Snake(new Square(startx,starty,0));
		int index = path.indexOf(snake.head);
		StdDraw.clear();
		Square food = spawnFood(snake,grid);
		drawGame(grid);
		StdDraw.show();
		boolean ate = false;
		while(snake.length<gridSize*gridSize) {
			TimeUnit.MILLISECONDS.sleep(500);
			StdDraw.clear();
			drawGame(grid);
			if(!ate)
				snake.move(path);
			else
				ate=false;
			snake.draw();
			StdDraw.show();
			if(snake.head.equals(food)) {
				snake.eat(path);
				food.type=0;
				food=spawnFood(snake,grid);
				ate=true;
			}
			
		}
		snake.move(path);
		snake.draw();
		StdDraw.show();

	}

	public static void drawGrid(Square[][] grid) {
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				grid[i][j].draw(grid[i].length);
			}
		}
	}
	
	public static void drawGame(Square[][] grid) {
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				grid[i][j].gameDraw();
			}
		}
	}
	
	public static Square spawnFood(Snake s,Square[][]grid) {
		Square food = null;
		while(true) {
			Random rand = new Random();
			int x = rand.nextInt(grid[0].length);
			int y = rand.nextInt(grid[0].length);
			food = new Square(x,y,1);
			if(food.equals(s.head)) {
				continue;
			}
			boolean flag = false;
			for(Square a:s.body) {
				if(food.equals(a)){
					flag=true;
				}
			}
			if(flag)
				continue;
			
			grid[x][y]=food;
			break;
		}
		return food;
		
	}
	

}
