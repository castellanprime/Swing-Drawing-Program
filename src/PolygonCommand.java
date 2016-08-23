import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

public class PolygonCommand extends Command {

	private Polygon polygon;
	private Point currentClicked;
	
	private Stack<Point> lastPoints = new Stack<Point>();

	/**
	public PolygonCommand() {
		this(null, null);
		pointCount = 0;
	}*/

	
	public PolygonCommand(Point point) {
		polygon =  new Polygon(point);
	}

	public void addPoint(Point point){
		polygon.addPoint(point);
		model.setChanged();
	}
	

	public void execute() {
		model.addItem(polygon);
	}

	public boolean isEmpty(){
		return lastPoints.isEmpty();
	}
	
	public boolean undo() {
		model.removeItem(polygon);
		
		if (polygon.isPolygonEmpty()){
			
		}else if (polygon.getLastPoint() == polygon.getOrigin()){
			lastPoints.push(polygon.getLastLineDrawn().getPoint1());
			polygon.removeLastLineDrawn();
			model.addItem(polygon);
		} else {
			lastPoints.push(polygon.getLastLineDrawn().getPoint2());
			polygon.removeLastLineDrawn();
			model.addItem(polygon);
		}
		return true;
	}

	public void setMouse(Point currentClicked){
		polygon.setCurrentlyClicked(currentClicked);
		
		model.setChanged();
	}
	
	public boolean redo() {
		if (!isEmpty())
			polygon.addPoint(lastPoints.pop());
		model.setChanged();
		return true;
	}

	public boolean end() {
		/**
		if (polygon.getPoint1() == null) {
			undo();
			return false;
		}
		if (polygon.getPoint2() == null) {
			polygon.setPoint2(polygon.getPoint1());
		}*/
		return true;
	}
}
