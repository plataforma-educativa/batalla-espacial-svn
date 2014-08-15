/*BEGIN_COPYRIGHT_BLOCK
 *
 * This file is a part of DrJava. Current versions of this project are available
 * at http://sourceforge.net/projects/drjava
 *
 * Copyright (C) 2001-2002 JavaPLT group at Rice University (javaplt@rice.edu)
 *
 * DrJava is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * DrJava is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * or see http://www.gnu.org/licenses/gpl.html
 *
 * In addition, as a special exception, the JavaPLT group at Rice University
 * (javaplt@rice.edu) gives permission to link the code of DrJava with
 * the classes in the gj.util package, even if they are provided in binary-only
 * form, and distribute linked combinations including the DrJava and the
 * gj.util package. You must obey the GNU General Public License in all
 * respects for all of the code used other than these classes in the gj.util
 * package: Dictionary, HashtableEntry, ValueEnumerator, Enumeration,
 * KeyEnumerator, Vector, Hashtable, Stack, VectorEnumerator.
 *
 * If you modify this file, you may extend this exception to your version of the
 * file, but you are not obligated to do so. If you do not wish to
 * do so, delete this exception statement from your version. (However, the
 * present version of DrJava depends on these classes, so you'd want to
 * remove the dependency first!)
 *
END_COPYRIGHT_BLOCK*/

package edu.rice.cs.drjava.plugins.eclipse.views;

import org.eclipse.ui.IActionBars;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.ui.part.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;

import edu.rice.cs.drjava.plugins.eclipse.DrJavaConstants;
import edu.rice.cs.drjava.plugins.eclipse.repl.EclipseInteractionsModel;
import edu.rice.cs.util.text.SWTDocumentAdapter;

/**
 * This class is the main view component for the Interactions Pane
 * Eclipse plugin.
 * @version $Id: InteractionsView.java 3510 2006-01-24 17:00:59Z camus546 $
 */
public class InteractionsView extends ViewPart {
  
  /**
   * Glue code between the model and view.
   * We have to have a reference to it so we can dispose it.
   */
  protected InteractionsController _controller;

  /**
   * The widget displaying the text.  Equivalent of a JTextPane.
   */
  protected StyledText _styledText;

  /**
   * An arrow cursor to display when the pane is not busy.
   */
  protected Cursor _arrowCursor;

  /**
   * An hourglass cursor to display when the pane is busy.
   */
  protected Cursor _waitCursor;

  /**
   * A runnable command to sound a beep as an alert.
   */
  protected Runnable _beep;

  /**
   * Toolbar menu for this view.
   */
  protected IMenuManager _toolbarMenu;

  /**
   * Context menu for this view.
   */
  protected MenuManager _contextMenu;

  protected IActionBars _bars;

  /**
   * Constructor.
   */
  public InteractionsView() {
    _beep = new Runnable() {
      public void run() {
        _styledText.getDisplay().beep();
      }
    };
  }

  /**
   * Cleans up any resources this view created.
   */
  public void dispose() {
    _arrowCursor.dispose();
    _waitCursor.dispose();
    _controller.dispose();
    _styledText.dispose();
    super.dispose();
  }

  /**
   * Accessor method for the text widget.
   */
  public StyledText getTextPane() {
    return _styledText;
  }

  /**
   * Returns a command that creates a beep when run.
   */
  public Runnable getBeep() {
    return _beep;
  }

  /**
   * Sets the command that creates a beep when run.
   * @param beep Command to create a beep
   */
  public void setBeep(Runnable beep) {
    _beep = beep;
  }

  /**
   * Sets the font of the Interactions Pane to be the value
   * stored in JFace's FontRegistry under the key corresponding
   * to DrJava's font preference.
   */
  public void updateFont() {
    Font font = JFaceResources.getFont(DrJavaConstants.FONT_MAIN);
    _styledText.setFont(font);
  }


  /**
   * Callback method that creates and initializes the view.
   */
  public void createPartControl(Composite parent) {
    // NOTE: Do not let anything instantiate the DrJava config framework here...
    setTextPane(new StyledText(parent, SWT.WRAP | SWT.V_SCROLL));

    // Get menus

    _bars = getViewSite().getActionBars();
    /*
    bars.setGlobalActionHandler(
         IWorkbenchActionConstants.CUT, 
	 new CutAction(_styledText, _clipboard));
    */

    _toolbarMenu = _bars.getMenuManager();
    _contextMenu = new MenuManager("#PopupMenu");
    Menu menu = _contextMenu.createContextMenu(_styledText);
    _styledText.setMenu(menu);

    SWTDocumentAdapter adapter = new SWTDocumentAdapter(_styledText);
    EclipseInteractionsModel model = new EclipseInteractionsModel(adapter);
    setController(new InteractionsController(model, adapter, this));

  }

  /**
   * Sets which text pane this view uses.  Package-private; for tests only.
   * NOTE: Should only be called once!
   * @param text A StyledText pane to use for the text
   */
  void setTextPane(StyledText text) {
    _styledText = text;
    _arrowCursor = new Cursor(_styledText.getDisplay(), SWT.CURSOR_ARROW);
    _waitCursor = new Cursor(_styledText.getDisplay(), SWT.CURSOR_WAIT);
  }

  /**
   * Sets which controller is managing this view, so we can dispose it.
   * Package-private; for tests only.
   * @param controller An InteractionsController managing the view
   */
  void setController(InteractionsController controller) {
    _controller = controller;
  }

  /**
   * Returns any text that is after the current prompt.
   */
  public String getCurrentInteraction(int promptPos) {
    int length = _styledText.getText().length();
    return _styledText.getText(promptPos, length - 1);
  }

  /**
   * Sets whether the StyledText widget is editable.
   * @param editable whether the widget should be editable
   */
  public void setEditable(final boolean editable) {
    _styledText.getDisplay().syncExec(new Runnable() {
      public void run() {
        _styledText.setEditable(editable);
      }
    });
  }

  /**
   * Sets whether a busy (hourglass) cursor is currently shown.
   * @param busy Whether to show a busy cursor
   */
  public void setBusyCursorShown(final boolean busy) {
    _styledText.getDisplay().syncExec(new Runnable() {
      public void run() {
        if (busy) {
          _styledText.setCursor(_waitCursor);
        }
        else {
          _styledText.setCursor(_arrowCursor);
        }
      }
    });
  }

  /**
   * Shows a modal dialog without halting the thread of execution.
   * @param title Title of the dialog box
   * @param msg Message to display
   */
  public void showInfoDialog(final String title, final String msg) {
    _styledText.getDisplay().asyncExec(new Runnable() {
      public void run() {
        MessageDialog.openInformation(_styledText.getShell(),
                                      title, msg);
      }
    });
  }

  /**
   * Shows a modal dialog to confirm an operation.
   * @param title Title of the dialog box
   * @param msg Message to display
   * @return Whether the user clicked yes or not
   */
  public boolean showConfirmDialog(final String title, final String msg) {
    return MessageDialog.openQuestion(_styledText.getShell(),
                                      title, msg);
  }

  /**
   * Gives the focus to this component.
   */
  public void setFocus() {
    _styledText.setFocus();
  }

  /**
   * Add a menu item to both the toolbar menu and context menu.
   * @param action Menu item to add
   */
  public void addMenuItem(Action action) {
    addToolbarMenuItem(action);
    addContextMenuItem(action);
  }
    public void addAction(String op, Action action) {
	_bars.setGlobalActionHandler(op, action); 
	addToolbarMenuItem(action);
	addContextMenuItem(action);
    }
  /**
   * Add a menu item to the toolbar.
   * @param action Menu item to add
   */
  public void addToolbarMenuItem(Action action) {
    _toolbarMenu.add(action);
  }

  public void addSelectionListener(SelectionListener listener) { 
    _styledText.addSelectionListener(listener);
  }

  /**
   * Add a menu item to the context menu.
   * @param action Menu item to add
   */
  public void addContextMenuItem(Action action) {
    _contextMenu.add(action);
  }
    	    
}
