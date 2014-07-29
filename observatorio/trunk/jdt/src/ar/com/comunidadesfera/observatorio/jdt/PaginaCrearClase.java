/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Eclipse Foundation - modified from org.eclipse.jdt.ui.wizards.NewClassWizardPage
 *     IDE4EDU - modified from org.eclipse.soc.ide4edu.ui.view.NewLiteClassWizardPage
 *******************************************************************************/

package ar.com.comunidadesfera.observatorio.jdt;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.ui.IJavaHelpContextIds;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

/**
 * Adaptación de la página del Wizard: org.eclipse.jdt.ui.wizards.NewClassWizardPage
 * para minimizar la información solicitada al usuario.
 */
public class PaginaCrearClase extends NewTypeWizardPage {
    
    private final static String PAGE_NAME = "NewClassWizardPage"; //$NON-NLS-1$
    
    public PaginaCrearClase() {
        super(true, PAGE_NAME);
        
        setTitle(NewWizardMessages.NewClassWizardPage_title); 
        setDescription(NewWizardMessages.NewClassWizardPage_description); 
    }
    
    /**
     * The wizard owning this page is responsible for calling this method with the
     * current selection. The selection is used to initialize the fields of the wizard 
     * page.
     * 
     * @param selection used to initialize the fields
     */
    public void init(IStructuredSelection selection) {
        IJavaElement javaElement = getInitialJavaElement(selection);
        initContainerPage(javaElement);
        initTypePage(javaElement);
        doStatusUpdate();
    }
    
    private void doStatusUpdate() {
        // status of all used components
        IStatus[] status = new IStatus[] {
            fContainerStatus,
            isEnclosingTypeSelected() ? fEnclosingTypeStatus : fPackageStatus,
            fTypeNameStatus,
            fModifierStatus,
            fSuperClassStatus,
            fSuperInterfacesStatus
        };
        
        // the mode severe status will be displayed and the OK button enabled/disabled.
        updateStatus(status);
    }
    
    protected void handleFieldChanged(String fieldName) {
        super.handleFieldChanged(fieldName);
        
        doStatusUpdate();
    }
    
    public void createControl(Composite parent) {
        initializeDialogUnits(parent);
        
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setFont(parent.getFont());
        
        int nColumns = 4;
        
        GridLayout layout= new GridLayout();
        layout.numColumns= nColumns;        
        composite.setLayout(layout);
        
        // pick & choose the wanted UI components
        
        //createContainerControls(composite, nColumns);    
        createPackageControls(composite, nColumns);    
        //createEnclosingTypeControls(composite, nColumns);
                
        createSeparator(composite, nColumns);
        
        createTypeNameControls(composite, nColumns);
        //createModifierControls(composite, nColumns);
            
        //createSuperClassControls(composite, nColumns);
        //createSuperInterfacesControls(composite, nColumns);
                
        //createMethodStubSelectionControls(composite, nColumns);
        
        //createCommentControls(composite, nColumns);
        //enableCommentControl(true);
        
        setControl(composite);
            
        Dialog.applyDialogFont(composite);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, IJavaHelpContextIds.NEW_CLASS_WIZARD_PAGE);    
    }
    
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            setFocus();
        } else {
            IDialogSettings dialogSettings = getDialogSettings();
            if (dialogSettings != null) {
                IDialogSettings section = dialogSettings.getSection(PAGE_NAME);
                if (section == null) {
                    section = dialogSettings.addNewSection(PAGE_NAME);
                }
            }
        }
    }    
    
}
