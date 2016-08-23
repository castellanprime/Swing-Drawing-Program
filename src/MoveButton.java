/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MoveButton extends JButton implements ActionListener {

	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private MouseHandler mouseHandler1;
	// private MouseDragged mouseDragged;
	private MoveCommand moveCommand;
	private SquareCommand squareCommand;
	private UndoManager undoManager;

	public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Move");
		addActionListener(this);
		view = jFrame;
		drawingPanel = jPanel;
		this.undoManager = undoManager;
		mouseHandler = new MouseHandler();
		// mouseDragged = new MouseDragged();
		mouseHandler1 = new MouseHandler();

	}

	public void actionPerformed(ActionEvent event) {
		squareCommand = new SquareCommand();
		view.setCursor(new Cursor(Cursor.HAND_CURSOR));
		drawingPanel.addMouseListener(mouseHandler);
		// drawingPanel.addMouseMotionListener(mouseDragged);
		// drawingPanel.addMouseListener(mouseHandler1);
		undoManager.beginCommand(squareCommand);

	}

	private class MouseHandler extends MouseAdapter {

		int pointCount = 0;

		public void mouseClicked(MouseEvent event) {
			System.out.println("point count is:" + pointCount);

			Point currentPoint = View.mapPoint(event.getPoint());

			if (pointCount == 0) {

				squareCommand.setPoint(currentPoint);
				undoManager.endCommand(squareCommand);

			} else if (pointCount == 1) {

				moveCommand = new MoveCommand();
				moveCommand.setPoint(currentPoint);
				undoManager.beginCommand(moveCommand);
				undoManager.endCommand(moveCommand);

				view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				drawingPanel.removeMouseListener(this);
			} else {

				squareCommand.setPoint(currentPoint);
				undoManager.endCommand(squareCommand);
				pointCount = 0;
			}
			pointCount++;
		}

		// pointCount = 0;
	}

	/*
	 * private class MouseHandler1 extends MouseAdapter {
	 * 
	 * public void mouseReleased(MouseEvent e) {
	 * 
	 * drawingPanel.removeMouseListener(this);
	 * undoManager.endCommand(moveCommand);
	 * 
	 * } }
	 * 
	 * private class MouseDragged extends MouseMotionAdapter { public void
	 * mouseDragged(MouseEvent e) {
	 * 
	 * System.out.println("mouse is being dragged");
	 * undoManager.beginCommand(moveCommand);
	 * moveCommand.setPoint(View.mapPoint(e.getPoint()));
	 * undoManager.endCommand(moveCommand);
	 * 
	 * }
	 * 
	 * }
	 */

}
