package mineSweeper;


public class Game {
	
	public Game()
	{
		gameOver=false;
		board=new Board();
		openSpaces=90;
		firstMove=true;
		minesLeft=board.getMines();
	}
	
	public Board giveBoard()
	{
		return board;
	}
	
	public void startGame()
	{

		openSpaces=board.getBoardSize()*board.getBoardSize()-board.getMines();
		gameOver=false;
		minesLeft=board.getMines();
	}
	
	public int makeMove(int row,int collumn)
	{
		if (gameOver)
		{
			System.out.println("Game Already Over, You had a time of Blah");
			return -1;
		}
		if(firstMove)
		{
			//time.start();
			firstMove=false;
		}

		int surMines=-1;
		MineButton temp=board.giveIndex(row, collumn);
		if(temp.getClicked())
		{
			return 0;
		}
		if(!temp.getFlagged()  && temp.getActive() )
		{
			temp.changeClicked(true);
			if(!temp.getMine())
			{
				surMines=0;
				surMines=surMines+checkIndex(row-1,collumn-1);
				surMines=surMines+checkIndex(row-1,collumn);
				surMines=surMines+checkIndex(row-1,collumn+1);
				surMines=surMines+checkIndex(row,collumn-1);
				surMines=surMines+checkIndex(row,collumn+1);
				surMines=surMines+checkIndex(row+1,collumn-1);
				surMines=surMines+checkIndex(row+1,collumn);
				surMines=surMines+checkIndex(row+1,collumn+1);
				System.out.println("I FAIL AT LIFE");

				openSpaces--;
			}
			else
			{
				gameOver=true;//you hit a mine
				//time.stopTime();
				System.out.println("You Lost");
			}
		}
		gameWon();
		return surMines;
	}
	
	public int getTimer()
	{
		//if(!gameOver)
			//return (int)(time.giveTime()/1000);
		//return (int)(time.giveStopTime()/1000); 
		return 0;
	}
	
	private int checkIndex(int row,int collumn)
	{
		if(row<0||collumn<0||row>board.getBoardSize()||collumn>board.getBoardSize())
		{
			return 0;
		}
		
		MineButton temp=board.giveIndex(row, collumn);
		if (temp.getMine())
			return 1;
		return 0;
	}
	
	public void placeFlag(int row, int collumn)
	{
		MineButton temp=board.giveIndex(row, collumn);
		if (!temp.getFlagged() && temp.getActive() && !temp.getClicked())
		{
			temp.changeFlag(true);
			minesLeft--;
		}
	}
	
	public void removeFlag(MineButton flagged)
	{
		flagged.changeFlag(false);
		minesLeft++;
	}
	
	public int getMines()
	{
		return minesLeft;
	}
	
	public void printGame()
	{
		board.printBoard();
	}
	
	public void gameWon()
	{
		if (openSpaces==0)
		{
			System.out.println("You Won");
			gameOver=true;
			//time.stopTime();
		}
	}
	
	private int minesLeft;
	private int openSpaces;
	private Board board;
	//private GameTimer time;
	private Boolean firstMove;
	private Boolean gameOver;

}
