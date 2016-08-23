import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EllipseButton extends JButton implements ActionListener {
	
	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private EllipseCommand ellipseCommand;
	private UndoManager undoManager;

	public EllipseButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Ellipse");
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

	private class MouseHandler extends MouseAdapter {
		private int pointCount = 0;

		public void mouseClicked(MouseEvent event) {
			if (++pointCount == 1) {
				ellipseCommand = new EllipseCommand(View.mapPoint(event.getPoint()));
				undoManager.beginCommand(ellipseCommand);
			} else if (pointCount == 2) {
				pointCount = 0;
				ellipseCommand.setEllipsePoint(View.mapPoint(event.getPoint()));
				drawingPanel.removeMouseListener(this);
				view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				undoManager.endCommand(ellipseCommand);
			}
		}
	}

}
