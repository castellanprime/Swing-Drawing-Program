import java.awt.Point;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class SquareCommand extends Command {
	private Item item;
	private Vector itemList;

	public SquareCommand() {

	}

	public void setPoint(Point point) {

		System.out.println("am in the selection");
		Enumeration enumeration = model.getItems();
		while (enumeration.hasMoreElements()) {
			item = (Item) (enumeration.nextElement());
			if (item.includes(point)) {
				model.markSelected(item);

				Model.moveStack.addFirst(point);

				model.deleteControlPoints();
				break;
			}
		}

		// removeControlPoints();
	}

	public boolean end() {
		// model.removeControlPoints();
		return true;
	}

	public void execute() {
		model.addControlPoints();
	}

	@Override
	public boolean undo() {
		return true;
	}

	@Override
	public boolean redo() {
		return true;
	}

}
