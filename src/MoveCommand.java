/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

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


	public MoveCommand() {

	}

	public void setPoint(Point point) {
		this.point = point;
		Model.moveStack.addLast(point);
	}

	public boolean undo() {

		System.out.println("in the undo method of move command");
		Point point = Model.moveStack.getFirst();
		model.move(item, point);
		return true;
	}

	public boolean redo() {
		Point point = Model.moveStack.getLast();
		model.move(item, point);
		return true;
	}

	public void execute() {
		Enumeration enumeration = model.getSelectedItems();
		while (enumeration.hasMoreElements()) {
			item = (Item) (enumeration.nextElement());
			model.move(item, point);
		}
	}

	public boolean end() {
		model.unSelect(item);
		return true;
	}

}
