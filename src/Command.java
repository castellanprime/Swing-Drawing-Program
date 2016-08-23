/**
* Command abstract class
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

public abstract class Command {
  protected static UndoManager manager;
  protected static Model model;
  public static void setModel(Model model) {
    Command.model = model;
  }
  public static void setUndoManager(UndoManager undoManager) {
    Command.manager = undoManager;
  }
  public abstract boolean undo();
  public abstract boolean redo();
  public abstract void execute();
  public boolean end() {
    return true;
  }
}
