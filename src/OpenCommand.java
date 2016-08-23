/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

public class OpenCommand extends Command {
  private String fileName;
  public OpenCommand(String fileName) {
    this.fileName = fileName;
  }
  public void execute() {
    model.retrieve(fileName);
  }
  public  boolean undo() {
    return false;
  }
  public  boolean redo() {
    return false;
  }
}
