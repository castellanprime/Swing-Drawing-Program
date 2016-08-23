/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

import java.awt.Point;
import java.util.ArrayList;

public class Square extends Item {

	private Point point1;
	private ArrayList<Point> controlPoints = new ArrayList<Point>();

	public Square(Point point1) {
		this.point1 = point1;
	}

	public Square() {
	}

	public void render() {
		uiContext.draw(this);
	}

	public void setPoint1(Point point) {
		point1 = point;
	}

	public Point getPoint1() {
		return point1;
	}

	public String toString() {
		return "Square  from " + point1;
	}

	@Override
	public void move(Point point) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean pointClickedOnItem(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Point> addControlPoints() {
		// TODO Auto-generated method stub
		return controlPoints;
	}

	@Override
	public boolean includes(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
