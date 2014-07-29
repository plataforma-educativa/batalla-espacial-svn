/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Eclipse Foundation - Modified from org.eclipse.jdt.internal.ui.wizards.NewClassCreationWizard
 *     IDE4EDU - modified from org.eclipse.soc.ide4edu.ui.view.NewLiteClassCreationWizard
 *******************************************************************************/
package ar.com.comunidadesfera.observatorio.jdt;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.wizards.NewElementWizard;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.ui.INewWizard;

/**
 * Adaptación del Wizard: org.eclipse.jdt.internal.ui.wizards.NewClassCreationWizard
 * para minimizar la información solicitada al usuario.
 */
public class AsistenteCrearClase extends NewElementWizard implements INewWizard {

    private PaginaCrearClase fPage;
    private boolean fOpenEditorOnFinish;
    
    public AsistenteCrearClase(PaginaCrearClase page, boolean openEditorOnFinish) {
        
        setDefaultPageImageDescriptor(JavaPluginImages.DESC_WIZBAN_NEWCLASS);
        setDialogSettings(JavaPlugin.getDefault().getDialogSettings());
        setWindowTitle(NewWizardMessages.NewClassCreationWizard_title);
        
        fPage = new PaginaCrearClase();
        fOpenEditorOnFinish = openEditorOnFinish;
    }
    
    public AsistenteCrearClase() {
        this(null, true);
    }
    
    public void addPages() {
        super.addPages();
        
        if (fPage == null) {
            fPage = new PaginaCrearClase();
        }
        
        fPage.init(getSelection());
        addPage(fPage);
    }
    
    protected boolean canRunForked() {
        
        return !fPage.isEnclosingTypeSelected();
    }

    protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {

        fPage.createType(monitor);
    }
        
    public boolean performFinish() {

        this.warnAboutTypeCommentDeprecation();
        boolean result = super.performFinish();
        if (result) {
            IResource resource = fPage.getModifiedResource();
            
            if (resource != null) {
                
                selectAndReveal(resource);
                if (fOpenEditorOnFinish) {
                    openResource((IFile) resource);
                }
            }    
        }
        return result;
    }

    public IJavaElement getCreatedElement() {
        
        return fPage.getCreatedType();
    }
}
