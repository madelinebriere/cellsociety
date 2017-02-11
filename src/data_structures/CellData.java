package data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import cellular_level.Cell;
import patch_level.Patch;
import societal_level.CellSociety;
import util.Location;

/**
 * This class is used to limit the amount of control given to a single Cell
 * 
 * A CopySociety is passed to a cell to give it the information it needs about
 * the entire board, but limit access to ONLY NEIGHBORING CELLS
 * 
 * @author maddiebriere
 *
 */

public class CellData {
	private CellSociety mySociety;
	private List<Location> available;
	
	public CellData(CellSociety s){
		mySociety=s;
		available=null;
	}

	public CellData(CellSociety s, List<Location> validSpots) {
		mySociety = s;
		available = validSpots;
	}

	public int getX() {
		return mySociety.getX();
	}
	
	public int getY() {
		return mySociety.getY();
	}

	public int getNumberNeighbors(Cell c) {
		return mySociety.neighbors(c).size();
	}

	/**
	 * Call will be specific to CellSociety --> correct type of neighbors
	 */
	public List<Patch> getNeighbors(Cell c) {
		return mySociety.neighbors(c);
	}

	public Map <CellName, List<Cell>> getCurrentCellsCopy() {
		return copy(mySociety.getCurrentCells());
	}

	public int countSameNeighbors(Cell center) {
		int sameCount = 0;
		for (Patch p : mySociety.neighbors(center)) {
			if (p.getMyCell()!= null && p.getMyCell().getMyState() != null
					&& p.getMyCell().getMyState().equals(center.getMyState())) {
				sameCount++;
			}
		}
		return sameCount;
	}

	public int countDiffNeighbors(Cell center) {
		return getNumberNeighbors(center) - countSameNeighbors(center);
	}

	public int countNonEmptyNeighbors(Cell center) {
		List<Patch> neighbors = mySociety.neighbors(center);
		List<Location> emptyNeighbors = mySociety.getEmptyCells(getPatchLocations(neighbors));
		if (neighbors.size() == 0) {
			return 0;
		}
		if (emptyNeighbors.size() == 0) {
			return neighbors.size();
		}
		return neighbors.size() - emptyNeighbors.size();
	}
	
	private List<Location> getPatchLocations(List<Patch> patches){
		ArrayList <Location> locs = new ArrayList<Location>();
		for(Patch p: patches){
			locs.add(p.getMyLocation());
		}
		return locs;
	}

	public Location getAvailableSpot() {
		if (available.size() == 0) {
			return null;
		}
		Random randy = new Random();
		int emptyIndex = randy.nextInt(available.size());
		ArrayList<Location> openCells = new ArrayList<Location>(available);
		return openCells.get(emptyIndex);
	}

	public Location getAvailableNeighbor(Cell c) {
		if (available.size() == 0) {
			return null;
		}
		Random randy = new Random();
		ArrayList<Location> availableNeighbors = new ArrayList<Location>(available);
		availableNeighbors.retainAll(getPatchLocations(mySociety.neighbors(c)));
		if (availableNeighbors.size() == 0) {
			return null;
		}
		int emptyIndex = randy.nextInt(availableNeighbors.size());
		return availableNeighbors.get(emptyIndex);
	}

	/**
	 * Basic List copy function to limit actual access to items
	 * 
	 * @param list
	 *            List to be copied
	 * @return Copy of List filled with COPIES OF EACH ITEM
	 */
	private <T extends Cell> List<Cell> copy(List<T> list) {
		ArrayList<Cell> copy = new ArrayList<Cell>();
		for (Cell c : list) {
			Cell newCell = c.createCopy();
			copy.add(newCell);
		}
		return copy;
	}
	
	/**
	 * Basic List copy function to limit actual access to items
	 * 
	 * @param toCopy
	 *            List to be copied
	 * @return Copy of List filled with COPIES OF EACH ITEM
	 */
	private Map<CellName,List<Cell>> copy(Map<CellName, List<Cell>> toCopy) {
		TreeMap <CellName,List<Cell>> ret = new TreeMap<CellName, List<Cell>>();
		for(CellName name: toCopy.keySet()){
			ret.put(name, copy(toCopy.get(name)));
		}
		return ret;

	}

}