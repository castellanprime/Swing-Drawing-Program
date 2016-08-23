import java.text.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;


import java.util.*;

public class Model {
	private Vector itemList;
	private Vector selectedList;
	private Vector controlPointsList;
	public static Deque<Point> moveStack = new LinkedList<Point>();
	
	// list of "currently selected" items
	private static UIContext uiContext;
	private static View view;

	// private static Item itemBeforeMove;
	private static Item itemAfterMove;

	public Model() {
		itemList = new Vector();
		selectedList = new Vector();
		controlPointsList = new Vector();
	}

	public static void setUI(UIContext uiContext) {
		Model.uiContext = uiContext;
		Item.setUIContext(uiContext);
	}

	public static void setView(View view) {
		Model.view = view;
	}

	public void undoMove(Item item) {
		System.out.println("In the Undo Method of Model");
		itemList.remove(itemAfterMove);
		itemList.add(item);
		itemAfterMove = item;
		view.refresh();
	}

	public void redoMove() {
		System.out.println("In the redo method of model");
		// removeItem(itemBeforeMove);
		// addItem(itemAfterMove);

	}

	public void markSelected(Item item) {
		// marks an item as selected by moving it to the
		// selected list.
		if (itemList.contains(item)) {
			itemList.remove(item);
			selectedList.add(item);
			view.refresh();
		}
	}

	public void unSelect(Item item) {
		if (selectedList.contains(item)) {
			selectedList.remove(item);
			itemList.add(item);
			view.refresh();
		}
	}

	public void move(Item item, Point point) {

		itemAfterMove = item;
		item.move(point);
		view.refresh();
	}

	public void addControlPoints() {

		Enumeration enumeration = getItems();
		int count = 0;
		while (enumeration.hasMoreElements()) {
			Item item = (Item) (enumeration.nextElement());
			Iterator controlPoints = item.addControlPoints().iterator();
			while (controlPoints.hasNext()) {
				count++;
				Point pt = (Point) controlPoints.next();
				Square sq = new Square(pt);
				addControlPointItem(sq);
				System.out.println("added a square and counter is" + count);
			}
		}
	}

	/*
	 * public void removeControlPoints() {
	 * 
	 * Enumeration enumeration1 = getItems(); int count=0; while
	 * (enumeration1.hasMoreElements()) { Item item = (Item)
	 * (enumeration1.nextElement());
	 * System.out.println(item.getClass().getName()); if
	 * (item.getClass().getName().equals("Square")) { count++;
	 * System.out.println("count of squares is:"+count);
	 * 
	 * } } view.refresh(); }
	 */

	public void deleteSelectedItems() {
		selectedList.removeAllElements();
		view.refresh();
	}

	public void deleteControlPoints() {
		controlPointsList.removeAllElements();
		view.refresh();
	}

	public void addItem(Item item) {
		itemList.add(item);
		view.refresh();
	}

	public void addControlPointItem(Item item) {
		controlPointsList.add(item);
		view.refresh();
	}

	public void removeItem(Item item) {
		itemList.remove(item);
		view.refresh();
	}

	public Enumeration getItems() {
		return itemList.elements();
	}

	public void setChanged() {
		view.refresh();
	}

	public Enumeration getSelectedItems() {
		return selectedList.elements();
	}

	public Enumeration getControlPoints() {
		return controlPointsList.elements();
	}

	// other fields, methods and classes
	public void save(String fileName) {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(itemList);
			output.writeObject(selectedList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void retrieve(String fileName) {
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream input = new ObjectInputStream(file);
			itemList = (Vector) input.readObject();
			selectedList = (Vector) input.readObject();
			Item.setUIContext(uiContext);
			view.refresh();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
}
