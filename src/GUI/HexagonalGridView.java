//author Talha Koc

package GUI;

import data_structures.Dimensions;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonalGridView extends GridView {
	
	double unit;
	double starting_y;
	double starting_x;
	
	public HexagonalGridView(Frame bounds, Dimensions dimensions, Color[][] colors){
		super(bounds, dimensions, colors);
		this.setBackgroundColor(Color.DARKSLATEGRAY);
		double numberOfUnits = (dimensions.getX()+1)*3 - 1;
		unit = bounds.getWidth()/numberOfUnits;
		
		//TODO grid inset
		double gridWidth;
		double gridHeight;
		
		starting_x = unit*1.25;
		starting_y = unit/2;
		
		setupGrid(colors);
	}
	
	@Override
	public void updateGrid(Color[][] newColors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setupGrid(Color[][] newColors) {
		for(int i=0; i<this._dimensions.getX(); i++){
			for(int j =0; j<_dimensions.getY(); j++){
				Polygon hex;
				if(j%2==0){
					hex = plainHexagon(starting_x + i*unit*3, starting_y+j*unit);
				}else{
					hex = plainHexagon(starting_x + i*unit*3 + 1.5*unit, starting_y+j*unit);
				}
				hex.setFill(Color.WHITE);
				this.getChildren().add(hex);
			}
		}
	}
	
	private Polygon plainHexagon(double x, double y){
		Polygon p = new Polygon();
		double center = getH(unit) + 1.0;
		double side = unit - .5;
		Double[] points = {
				x, y,
				x + side, y,
				x + side + side/2, y + center,
				x + side, y + center*2,
				x, y+center*2,
				x - side/2, y + center
		};
		p.getPoints().addAll(points);
		return p;
	}
	private double getH(double side) {
	      return ((Math.sqrt(3)/2)*side);
	 }


}