/**
 * 
 * Because we will have different cells of interest, we need 
 * to pass the entire grid!!
 * 
 * @author maddiebriere
 */






package cellular_level;
import util.Location;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.paint.Color;

public abstract class Cell {
	private Location myLocation;
	private Color myState;
	
	public Cell(){
		myLocation = new Location(0,0);
		myState = Color.WHITE;
	}
	
	public Cell(int row, int col, Color state){
		myLocation = new Location(row, col);
		myState=state;
	}
	
	public boolean positionEquals(Object o){
		if(o == this){
			return true;
		}
		if(!(o instanceof Cell)){
			return false;
		}
		return getMyLocation().equals(((Cell)o).getMyLocation());
	}
	
	public abstract Cell createCopy();
	
	public abstract ArrayList<Cell> update(ArrayList<Cell>currentCells, ArrayList<EmptyCell>available, int size);

	public ArrayList<Cell> getFirstNeighbors(ArrayList<Cell> currentCells){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		for(Cell possible: currentCells){
			if(isAdjacent(possible)){
				neighbors.add(possible);
			}
		}
		return neighbors;
	}
	
	public ArrayList<Cell> getWrappedFirstNeighbors(ArrayList<Cell> currentCells, int size){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		for(Cell possible: currentCells){
			if(isAnyAdjacent(possible, size)){
				neighbors.add(possible);
			}
			
		}
		return neighbors;
	}


	public ArrayList<Cell>getSecondNeighbors(ArrayList<Cell> currentCells){
		HashSet <Cell> neighborhood = new HashSet<Cell>();
		ArrayList<Cell> firstOrderNeighbors = getFirstNeighbors(currentCells);
		neighborhood.addAll(firstOrderNeighbors);
		for(Cell n: firstOrderNeighbors){
			ArrayList<Cell> secondOrderNeighbors = n.getFirstNeighbors(currentCells);
			neighborhood.addAll(secondOrderNeighbors);
		}
		ArrayList<Cell> toRet = new ArrayList<Cell>(neighborhood);
		return toRet;
	}
	
	public ArrayList<Cell>getSecondWrappedNeighbors(ArrayList<Cell> currentCells, int size){
		HashSet <Cell> neighborhood = new HashSet<Cell>();
		ArrayList<Cell> firstOrderNeighbors = getWrappedFirstNeighbors(currentCells, size);
		neighborhood.addAll(firstOrderNeighbors);
		for(Cell n: firstOrderNeighbors){
			ArrayList<Cell> secondOrderNeighbors = n.getWrappedFirstNeighbors(currentCells, size);
			neighborhood.addAll(secondOrderNeighbors);
		}
		ArrayList<Cell> toRet = new ArrayList<Cell>(neighborhood);
		return toRet;
	}
	
	
	protected int countSameNeighbors(ArrayList<Cell>neighbors){
		int sameCount = 0;
		for(Cell c: neighbors){
			if(c.getMyState()!=null && c.getMyState().equals(this.getMyState())){
				sameCount++;
			}
		}
		return sameCount;
	}
	
	protected int countDiffNeighbors(ArrayList<Cell>neighbors){
		int diffCount = 0;
		for(Cell c: neighbors){
			if(!(c.getClass() == this.getClass())){
				diffCount++;
			}
		}
		return diffCount;
	}
	
	public void copyLocation(Cell copyFrom){
		this.setMyCol(copyFrom.getMyCol());
		this.setMyRow(copyFrom.getMyRow());
	}
	
	/**
	 * Adjacency in common sense
	 * @param c target cell
	 * @return true if adjacent, false otherwise
	 */
	public boolean isAdjacent(Cell c){
		return isAdjacent(c.getMyLocation());
	}
	
	protected boolean isAdjacent(Location l){
		return (sameColumn(l) && oneAwayVertical(l))||(sameRow(l) && oneAwayHorizontal(l))
				||(oneAwayVertical(l) && oneAwayHorizontal(l));
	}
	
	/**
	 * Adjacency EVEN ACROSS BOARD (wrapped sides)
	 * @param c target cell
	 * @param size size of the board
	 * @return true if wrapped adjacent, false otherwise
	 */
	protected boolean isWrappedAdjacent(Cell c, int size){
		return isWrappedAdjacent(c.getMyLocation(), size);
	}
	
	protected boolean isWrappedAdjacent(Location l, int size){
		return (sameColumn(l) && inRowAcrossBoard(l,size)) || 
				(sameRow(l) && inColAcrossBoard(l,size));
	}
	
	/**
	 * Includes both common adjacency and wrapped adjacency
	 * @param l target cell
	 * @param size size of the board
	 * @return true if any type of adjacent, false otherwise
	 */
	protected boolean isAnyAdjacent(Location l, int size){
		return isWrappedAdjacent(l,size)|| isAdjacent(l);
	}
	
	public boolean isAnyAdjacent(Cell c, int size){
		return isWrappedAdjacent(c.getMyLocation(),size)|| isAdjacent(c.getMyLocation());
	}
	
	private boolean inColAcrossBoard(Location l, int size){
		return (l.getMyCol()==0 && getMyCol() == size-1) ||
			   (getMyCol()==0 && l.getMyCol() == size-1);
	}
	
	private boolean inRowAcrossBoard(Location l, int size){
		return (l.getMyRow()==0 && getMyRow() == size-1) ||
				   (getMyRow()==0 && l.getMyRow() == size-1);
	}
	
	private boolean sameColumn(Location l){
		return l.getMyCol() == getMyCol();
	}
	private boolean sameRow(Location l){
		return l.getMyRow() == getMyRow();
	}
	private boolean oneAwayVertical(Location l){
		return ((this.getMyRow()+1) == l.getMyRow()) || ((this.getMyRow()-1) == l.getMyRow());
	}
	
	private boolean oneAwayHorizontal(Location l){
		return ((this.getMyCol()+1) == l.getMyCol()) || ((this.getMyCol()-1) == l.getMyCol());
	}
	
	public Location getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(Location myLocation) {
		this.myLocation = myLocation;
	}

	public int getMyRow() {
		return myLocation.getMyRow();
	}

	public void setMyRow(int row) {
		myLocation.setMyRow(row);
	}

	public int getMyCol() {
		return myLocation.getMyCol();
	}

	public void setMyCol(int col) {
		myLocation.setMyCol(col);
	}

	public Color getMyState() {
		return myState;
	}

	public void setMyState(Color myState) {
		this.myState = myState;
	}
	
}
