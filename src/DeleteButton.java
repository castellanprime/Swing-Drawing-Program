/**
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

import javax.swing.*;
import java.awt.event.*;
public class DeleteButton  extends JButton implements ActionListener {
  private DeleteCommand deleteCommand;
  private UndoManager undoManager;
  public DeleteButton(UndoManager undoManager) {
    super("Delete");
    this.undoManager = undoManager;
    addActionListener(this);
  }
  public void actionPerformed(ActionEvent event) {
    deleteCommand = new DeleteCommand();
    undoManager.beginCommand(deleteCommand);
    undoManager.endCommand(deleteCommand);
  }
}
