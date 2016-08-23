/**
* Main Program
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

public class DrawingProgram {
	
	public static void main(String[] args) {
		Model model = new Model();
		UndoManager undoManager = new UndoManager();
		UndoManager.setModel(model);
		View.setUndoManager(undoManager);
		View.setModel(model);
		View view = new View();
		Model.setView(view);
		Command.setUndoManager(undoManager);
		Command.setModel(model);
		view.show();
	}
	
}