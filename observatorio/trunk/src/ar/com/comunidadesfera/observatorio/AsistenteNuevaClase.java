package ar.com.comunidadesfera.observatorio;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class AsistenteNuevaClase extends Wizard implements INewWizard {

	public AsistenteNuevaClase() {

	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	
	}

	@Override
	public boolean performFinish() {
		
		return false;
	}

}
