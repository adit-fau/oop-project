package mineSweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class UI {
	public UI()//setting up the UI frame
	{

		frame=new JFrame("World's Best Minesweeper Rip");
		frame.setLayout(new BorderLayout());
		game=new Game();
		
		frame.add(BorderLayout.SOUTH,buildGrid());
		frame.add(BorderLayout.WEST,diffMenu());
		frame.add(BorderLayout.EAST,minesLeft());
		frame.add(BorderLayout.CENTER,restart());
	    frame.setSize(1000, 1000);
	    frame.setVisible(true);

		
	}
	
	//creates the panel with the mines left
	private JPanel minesLeft()
	{
		JPanel mines=new JPanel();
		mineLeft=new JTextField(((Integer)(game.getMines())).toString());
		mineLeft.setEditable(false);
		mines.add(mineLeft);
		JLabel temp=new JLabel("Mines Left");
		mines.add(temp);
		return mines;
	}
	
	//updates the minesLeft text field
	private void updateMines()
	{
		mineLeft.setText(((Integer)(game.getMines())).toString());
	}
	
	//sets up the jbuttons that work as the minefield
	private JPanel buildGrid()
	{
		int row=0;
		int collumn=0;
		Board temp=game.giveBoard();
		JPanel mineField=new JPanel();
		mineField.setLayout(new GridLayout(0,temp.getMax()));
		mineGrid=new JButton[temp.getMax()][temp.getMax()];
		for (row=0;row<temp.getMax();row++)
		{
			for (collumn=0;collumn<temp.getMax();collumn++)
			{
				mineGrid[row][collumn]=new JButton();
            	mineGrid[row][collumn].setText(" ");
            	mineGrid[row][collumn].setVisible(false);
            	mineGrid[row][collumn].setActionCommand(((Integer)(row)).toString()+ "/"+((Integer)(collumn)).toString() );
            	if(row<temp.getBoardSize()&&collumn<temp.getBoardSize())
            	{
            		mineGrid[row][collumn].setVisible(true);
            	}
            	mineField.add(mineGrid[row][collumn]);
            	
            	mineGrid[row][collumn].addActionListener(new ActionListener() {
      		      public void actionPerformed(ActionEvent e) {//this is action listener for left click
      		    	  
      		    	  if(((JButton)(e.getSource())).getText()==" ")
      		    	  {
      		    
      		    	  	int temp1=0;
      		    	  	int temp2=0;
      		    	  	String temp=((JButton) (e.getSource())).getActionCommand();
        		    	  System.out.println(temp);

      		    	  	temp2=Integer.parseInt(temp.substring(temp.lastIndexOf("/") + 1));
      		    	  	temp1=Integer.parseInt(temp.substring(0,temp.lastIndexOf("/") ));

      							mineGrid[temp1][temp2].setText(((Integer)(game.makeMove(temp1,temp2))).toString() );
      							//take the button at location [temp1][temp2], change its text to the number of mines surrounding the location at [temp1][temp2] in the board


      						
      						
      						
      						
      						
      						
      		    	  }

      		      }//this is the action listener for left click
            	});
			}
		}

		return mineField;
	}
	//updates the minefield
	private void rebuildGrid()
	{
		int row=0;
		int collumn=0;
		game.startGame();
		updateMines();
		Board temp=game.giveBoard();
		for (row=0;row<temp.getMax();row++)
		{
			for (collumn=0;collumn<temp.getMax();collumn++)
			{
            	mineGrid[row][collumn].setText(" ");
            	mineGrid[row][collumn].setVisible(false);
            	if(row<temp.getBoardSize()&&collumn<temp.getBoardSize())
            	{
            		mineGrid[row][collumn].setVisible(true);
            	}
			}
		}
	}
	//builds the restart button and makes it work
	private JPanel restart()
	{
		JButton temp=new JButton("Restart Game");
		 temp.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		  		rebuildGrid();
		      }
		 });
		 JPanel plzNoJumboBtn=new JPanel();//before I put it into a JPanel, the button was HUGE
		 plzNoJumboBtn.add(temp);
		return plzNoJumboBtn;
	}

	//holds the panel that changes the difficulty and restarts a game if you hit the change difficulty button
	private JPanel diffMenu()
	{
		JPanel temp=new JPanel();
		final JComboBox<String>  c = new JComboBox<String>();
		JButton b=new JButton("Change Difficulty");
		String [] difficulties={"easy","medium","hard"};
		for (int i=0;i<difficulties.length;i++)
		{
			c.addItem(difficulties[i]);
		}
		temp.add(c);
		 b.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  game.giveBoard().changeDifficulty((String)(c.getSelectedItem()));
		  		rebuildGrid();
		      }
		 });
		temp.add(b);
		return temp;
	}

	
	private Game game;
	
	private JTextField mineLeft;
	private JButton [][] mineGrid;
	private JFrame frame;

}
