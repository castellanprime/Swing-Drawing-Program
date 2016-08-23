/**
* 
* @author: Okusanya David
* @date: August 22, 2016
* @version: 0.1
*/

import javax.swing.*;
import java.awt.event.*;
public class RedoButton  extends JButton implements ActionListener {
  private UndoManager undoManager;
  public RedoButton(UndoManager undoManager) {
    super("redo");
    this.undoManager = undoManager;
    addActionListener(this);
  }
  public void actionPerformed(ActionEvent event) {
    undoManager.redo();
  }
}
