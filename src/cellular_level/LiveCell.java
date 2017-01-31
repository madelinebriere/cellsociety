/**
 * Class representing live cells in the Game of Life Simulation
 * Capable of active updates based on surroundings
 * --> Checks for overpopulation
 * --> Checks for underpopulation
 * 
 * @author maddiebriere
 */

package cellular_level;

import javafx.scene.paint.Color;
import java.util.ArrayList;


import util.Location;

public class LiveCell extends Cell {
	private static int underpopulation = 2;
	private static int overpopulation = 3;
	private static Color liveColor = Color.BLACK;
	
	public LiveCell(){
		super();
		setMyState(liveColor);
	}
	
	public LiveCell(int row, int col){
		super(row,col, liveColor);
	}
	
	/**
	 * @param neighbors Cell neighbors
	 * @param nullCells Cells with no current occupants, stored as nulls
	 * @return ArrayList of Cells for the next generation. Will contain either the current
	 * live cell or a new dead cell in the same location. Never empty.
	 */
	@Override
	protected ArrayList<Cell> update(ArrayList<Cell> neighbors, ArrayList<Location> nullCells) {
		ArrayList<Cell> newGen = new ArrayList<Cell>();
		int numLive = countSameNeighbors(neighbors);
		if(isOverpopulated(numLive)||isUnderpopulated(numLive)){
			DeadCell child = new DeadCell();
			child.copyLocation(this);
			newGen.add(child);
		}
		else{
			newGen.add(this);
		}
		return newGen;
		
	}
	
	private boolean isOverpopulated(int numLive){
		return numLive>overpopulation;
	}
	
	private boolean isUnderpopulated(int numLive){
		return numLive<underpopulation;
	}

	public static int getUnderpopulation() {
		return underpopulation;
	}

	public static void setUnderpopulation(int underpopulation) {
		LiveCell.underpopulation = underpopulation;
	}

	public static int getOverpopulation() {
		return overpopulation;
	}

	public static void setOverpopulation(int overpopulation) {
		LiveCell.overpopulation = overpopulation;
	}

	public static Color getLiveColor() {
		return liveColor;
	}

	public static void setLiveColor(Color liveColor) {
		LiveCell.liveColor = liveColor;
	}
	
	
	

}
