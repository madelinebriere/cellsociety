//author Talha Koc

package GUI;

import data_structures.Dimensions;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleGridView extends GridView{

	double unit;
	double starting_y;
	double starting_x;
	
	public TriangleGridView(Frame bounds, Dimensions dimensions, Color[][] colors){
		super(bounds, dimensions, colors);
		this.setBackgroundColor(Color.BLACK);
		setupGrid(colors);
	}
	@Override
	public void updateGrid(Color[][] newColors) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setupGrid(Color[][] colors) {
		// TODO Auto-generated method stub
		for(int i=0; i<this._dimensions.getX(); i++){
			for(int j =0; j<_dimensions.getY(); j++){
				Polygon p;
				if(i%2==0){
					p = bottomTriangle(starting_x + i*unit, starting_y + j*unit);
				}else{
					p = topTriangle(starting_x + i*unit, starting_y + j*unit);
				}
			}
		}
	}
	
	private Polygon bottomTriangle(double x, double y){
		double side = unit;
		Polygon t = new Polygon(new double[] {
				x,y,
				x, y+side,
				x+side, y+side
				});
		
		return t;
	}
	private Polygon topTriangle(double x, double y){
		double side = unit;
		Polygon t = new Polygon(new double[] {
				x,y,
				x+side, y,
				x+side, y+side
				});
		return t;
	}



}
