package mineSweeper;

public class MineButton {
	public MineButton()
	{
		isFlagged=false;
		isMine=false;
		isActive=false;
		isClicked=false;
	}
	
	public Boolean getFlagged()
	{
		return isFlagged;
	}
	
	public Boolean getMine()
	{
		return isMine;
	}
	
	public Boolean getActive()
	{
		return isActive;
	}
	
	public void changeFlag(Boolean newState)
	{
		isFlagged=newState;
	}
	
	
	public Boolean getClicked()
	{
		return isClicked;
	}
	
	public void changeClicked(Boolean newState)
	{
		isClicked=newState;
	}
	
	public void changeActive(Boolean newState)
	{
		isActive=newState;
	}
	
	public void changeMine(Boolean newState)
	{
		isMine=newState;
	}
	
	private Boolean isFlagged;
	private Boolean isMine;
	private Boolean isActive;
	private Boolean isClicked;
}
