import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PolygonButton extends JButton implements ActionListener {

	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private PolygonCommand polygonCommand;
	private UndoManager undoManager;
	private static Point initialPoint = null;
	

	public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Polygon");
		this.undoManager = undoManager;
		addActionListener(this);
		view = jFrame;
		drawingPanel = jPanel;
		mouseHandler = new MouseHandler();
	}

	public void actionPerformed(ActionEvent event) {
		view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		// Change cursor when button is clicked
		drawingPanel.addMouseListener(mouseHandler);
		// Start listening for mouseclicks on the drawing panel
	}
	
	private boolean pointsAreClose(Point point1, Point point2) {
		double x_difference = point1.getX() - point2.getX();
		double y_difference = point1.getY() - point2.getY();

		if (x_difference < 0)
			x_difference *= -1;
		if (y_difference < 0)
			y_difference *= -1;

		return x_difference <= 10 && y_difference <= 10;
	}

	private class MouseMotionHandler implements MouseMotionListener {

		public void mouseDragged(MouseEvent event) {
		}

		public void mouseMoved(MouseEvent event) {
			if (initialPoint == null)
				return;

			polygonCommand.setMouse(View.mapPoint(event.getPoint()));
		}
	}
	
	private class MouseHandler extends MouseAdapter {

		private int pointCount = 0;

		public void mouseClicked(MouseEvent event) {

			Point currentPoint = View.mapPoint(event
					.getPoint());
			
			if (++pointCount == 1) {
				polygonCommand = new PolygonCommand(currentPoint);
				initialPoint = currentPoint;
				undoManager.beginCommand(polygonCommand);
				undoManager.endCommand(polygonCommand);
				polygonCommand.setMouse(currentPoint);
				view.refresh();
			} else if (pointsAreClose(initialPoint, currentPoint)){
				pointCount = 0;

				undoManager.beginCommand(polygonCommand);
				polygonCommand.addPoint(currentPoint);
				drawingPanel.removeMouseListener(this);
				drawingPanel.removeMouseMotionListener(this);
				view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				undoManager.endCommand(polygonCommand);

				view.refresh();
			} else {
				undoManager.beginCommand(polygonCommand);
				polygonCommand.addPoint(currentPoint);
				undoManager.endCommand(polygonCommand);
				view.refresh();
				
			}
		}
	}

}
