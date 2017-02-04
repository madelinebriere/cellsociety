package cellular_level;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.paint.Color;
import util.CellData;

public class EmptyCell extends Cell{
	private static Color EMPTY_COLOR = null;
	
	public EmptyCell(){
		this(0,0);
	}
	
	public EmptyCell(int row, int col){
		super(row, col,  EMPTY_COLOR);
	}
	
	public EmptyCell(Cell c){
		super(c.getMyRow(), c.getMyCol(), EMPTY_COLOR);
	}
	
	@Override
	public Cell createCopy(){
		EmptyCell copy = new EmptyCell();
		copy.basicCopy(this);
		return copy;
	}
	
	@Override
	public Collection<Cell> update(CellData data) {
		ArrayList<Cell> empty = new ArrayList<Cell>();
		return empty;
	}
	

}
