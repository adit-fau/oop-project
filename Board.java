package mineSweeper;

import java.util.Random;

public class Board {
	private final int MAXSIZE=25;
	
	
	public Board()
	{
		gameBoard=new MineButton[MAXSIZE][MAXSIZE];
		for(int i=0;i<MAXSIZE;i++)
		{
			for(int j=0;j<MAXSIZE;j++)
			{
				gameBoard[i][j]=new MineButton();
			}
		}		
		difficulty="easy";
		redrawBoard();
	}
	
	public void redrawBoard()
	{

		if(difficulty=="easy")
		{
			helpDrawBoard(10,10);
		}
		else if (difficulty=="medium")
		{
			helpDrawBoard(25,15);
		}
		else if (difficulty=="hard")
		{
			helpDrawBoard(50,25);
		}
		else
		{
			//don't know how we got here
		}
	}
	private void helpDrawBoard(int mineCount,int activeSize)
	{
		Random r = new Random();
		
		numMines=0;
		for (int i=0;i<MAXSIZE;i++)
		{
			for (int j=0;j<MAXSIZE;j++)
			{
				if (i<activeSize && j<activeSize)//active area
				{
					gameBoard[i][j].changeActive(true);
					gameBoard[i][j].changeFlag(false);
					gameBoard[i][j].changeClicked(false);
					if(numMines<mineCount)//need more mines
					{
						if (r.nextInt(activeSize)==1)//1 in activeSize chance of getting a mine
						{
							numMines++;
							gameBoard[i][j].changeMine(true);
						}
						else//not getting a mine
							{
							gameBoard[i][j].changeMine(false);
							}
					}
					else//got max mines in this step
						{
						gameBoard[i][j].changeMine(false);
						}
					
				}
				else//inactive area
				{
					gameBoard[i][j].changeActive(false);
					gameBoard[i][j].changeFlag(false);
					gameBoard[i][j].changeMine(false);
					gameBoard[i][j].changeClicked(false);
				}
					
			}
		}
		while(numMines<mineCount)//might not have enough mines
		{
			int temp1=r.nextInt(activeSize);
			int temp2=r.nextInt(activeSize);
			if (!gameBoard[temp1][temp2].getMine())
			{
				numMines++;
				gameBoard[temp1][temp2].changeMine(true);
			}
		}
	}
	
	public void changeDifficulty(String newState)
	{
		difficulty=newState;
		redrawBoard();
	}
	
	public String getDifficulty()
	{
		return difficulty;
	}
	
	public int getMines()
	{
		return numMines;
	}
	
	public void changeNumMines(Integer newNumMines)
	{
		numMines=newNumMines;
	}
	
	public MineButton giveIndex(int row,int collumn)
	{
		return gameBoard[row][collumn];
	}
	
	public int getMax()
	{
		return MAXSIZE;
	}
	
	public int getBoardSize()
	{
		if (difficulty=="easy")
			return 10;
		else if (difficulty =="medium")
			return 15;
		else
			return 25;
	}
	
	public void printBoard()
	{
		String row="";
		for(int i=0;i<MAXSIZE;i++)
		{
			row="";
			for(int j=0;j<MAXSIZE;j++)
			{
				if (gameBoard[i][j].getActive())
				{
					if(gameBoard[i][j].getMine())
						row=row +" X ";
					else
						row=row +" O ";
				}
			}
			if(row!="")
			{
				System.out.println(row);
			}
		}		
	}
	
	private String difficulty;
	private MineButton [][] gameBoard;
	private int numMines;
}
