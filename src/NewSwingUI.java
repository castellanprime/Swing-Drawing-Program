import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;


public class NewSwingUI implements UIContext {
	private Graphics graphics;
	private static NewSwingUI swingUI;

	private NewSwingUI() {
	}

	public static NewSwingUI getInstance() {
		if (swingUI == null) {
			swingUI = new NewSwingUI();
		}
		return swingUI;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void draw(Label label) {
		if (label.getStartingPoint() != null) {
			if (label.getText() != null) {
				graphics.drawString(label.getText(), (int) label
						.getStartingPoint().getX(), (int) label
						.getStartingPoint().getY());
			}
		}
		int length = graphics.getFontMetrics().stringWidth(label.getText());
		graphics.drawString("_",
				(int) label.getStartingPoint().getX() + length, (int) label
						.getStartingPoint().getY());
	}

	public void draw(Line line) {
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		if (line.getPoint1() != null) {
			i1 = Math.round((float) (line.getPoint1().getX()));
			i2 = Math.round((float) (line.getPoint1().getY()));
			if (line.getPoint2() != null) {
				i3 = Math.round((float) (line.getPoint2().getX()));
				i4 = Math.round((float) (line.getPoint2().getY()));
			} else {
				i3 = i1;
				i4 = i2;
			}
			graphics.drawLine(i1, i2, i3, i4);
		}
	}

	public void draw(Ellipse ellipse) {
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		if (ellipse.getPoint1() != null) {
			i1 = Math.round((float) (ellipse.getPoint1().getX()));
			i2 = Math.round((float) (ellipse.getPoint1().getY()));
			if (ellipse.getPoint2() != null) {
				i3 = Math.round((float) (ellipse.getPoint2().getX()));
				i4 = Math.round((float) (ellipse.getPoint2().getY()));
			} else {
				i3 = i1;
				i4 = i2;
			}

			Point p3 = new Point(i3, i2);
			Point p4 = new Point(i1, i4);

			// graphics.drawLine(i1, i2, i1, i4);
			// graphics.drawLine(i1, i2, i3, i2);
			// graphics.drawLine(i3, i2, i3, i4);
			// graphics.drawLine(i1, i4, i3, i4);

			int width = 0;
			int height = 0;

			if (ellipse.getPoint1() != null && ellipse.getPoint2() != null) {

				width = (int) getDistance(p3, ellipse.getPoint2());

				height = (int) getDistance(ellipse.getPoint1(), p3);

				System.out.println("drawing ellipse with new points:"
						+ ellipse.getPoint1().getX() + "   "
						+ ellipse.getPoint1().getY());
				graphics.drawOval((int) ellipse.getPoint1().getX(),
						(int) ellipse.getPoint1().getY(), height, width);

				System.out.println(width);
				System.out.println(height);

			}

		}
	}

	public void draw(Polygon polygon) {
		Iterator<Line> iter = polygon.getAllLinesDrawn();
		while(iter.hasNext())
			draw(iter.next());
		
		if (polygon.getLastPoint() == polygon.getOrigin() || polygon.isDrawingComplete() == false) {
			Color previousColor = graphics.getColor();
			graphics.setColor(Color.black);
			Point point = polygon.getOrigin();
			//graphics.fillRect((int) point.getX(), (int) point.getY(), MARKER_SQUARE_PIXELS, MARKER_SQUARE_PIXELS);

			Point lastPolyPoint = polygon.getLastPoint();
			graphics.setColor(Color.RED);
			graphics.drawLine((int) lastPolyPoint.getX(), (int) lastPolyPoint.getY(),
					(int) polygon.getCurrentlyClicked().getX(), (int) polygon.getCurrentlyClicked().getY());

			graphics.setColor(previousColor);

		}
		
	}

	public double getDistance(Point p1, Point p2) {

		System.out.println(p2.getX());

		int dx = (int) (p1.getX() - p2.getX());
		int dy = (int) (p1.getY() - p2.getY());
		return Math.sqrt(dx * dx + dy * dy);

	}

	public void draw(Item item) {
		System.out.println("Cant draw unknown Item \n");
	}

	@Override
	public void draw(Square sq) {

		//System.out.println("rendering square" + sq.getPoint1());
		Point p1 = sq.getPoint1();
		graphics.fillRect((int) p1.getX(), (int) p1.getY(), 7, 7);

	}

}