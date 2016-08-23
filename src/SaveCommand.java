/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

public class SaveCommand extends Command {
  private String fileName;
  public SaveCommand(String fileName) {
    this.fileName = fileName;
  }
  public void execute() {
    model.save(fileName);
  }
  public  boolean undo() {
    return false;
  }
  public  boolean redo() {
    return false;
  }
}
