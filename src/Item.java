/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

import java.io.*;
import java.util.ArrayList;
import java.awt.*;

public abstract class Item implements Serializable {

	protected static UIContext uiContext;

	public static void setUIContext(UIContext uiContext) {
		Item.uiContext = uiContext;
	}

	public abstract boolean includes(Point point);

	public abstract ArrayList<Point> addControlPoints();

	protected double distance(Point point1, Point point2) {
		double xDifference = point1.getX() - point2.getX();
		double yDifference = point1.getY() - point2.getY();
		return ((double) (Math.sqrt(xDifference * xDifference + yDifference
				* yDifference)));
	}

	public abstract boolean pointClickedOnItem(Point point);

	public void render() {
		uiContext.draw(this);
	}

	public abstract void move(Point point);

}