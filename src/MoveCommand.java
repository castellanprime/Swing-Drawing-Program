import java.awt.Point;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class MoveCommand extends Command {
	private Item item;
	private Vector itemList;
	private Point point;

	/*
	 * class StackItems { //private Item item; private Point point;
	 * 
	 * StackItems(Point point) { this.point = point; //this.item = item; }
	 * 
	 * public Item getItem() { return item; }
	 * 
	 * public Point getPoint() { return point; } }
	 */

	public MoveCommand() {

		/*
		 * itemList = new Vector(); Enumeration enumeration =
		 * model.getSelectedItems(); while (enumeration.hasMoreElements()) {
		 * Item item = (Item) (enumeration.nextElement()); itemList.add(item); }
		 */
	}

	public void setPoint(Point point) {
		this.point = point;
		Model.moveStack.addLast(point);
	}

	public boolean undo() {

		System.out.println("in the undo method of move command");
		// StackItems it = (StackItems) moveStack.addFirst(point,);
		Point point = Model.moveStack.getFirst();
		model.move(item, point);
		return true;
	}

	public boolean redo() {
		// model.redoMove();
		Point point = Model.moveStack.getLast();
		model.move(item, point);
		return true;
	}

	public void execute() {
		Enumeration enumeration = model.getSelectedItems();
		while (enumeration.hasMoreElements()) {
			item = (Item) (enumeration.nextElement());
			// moveStack.addLast(new StackItems(point));
			model.move(item, point);
		}
	}

	public boolean end() {
		model.unSelect(item);
		return true;
	}

}
