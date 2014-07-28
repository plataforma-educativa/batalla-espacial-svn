package ar.com.comunidadesfera.observatorio;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class AsistenteNuevoProyecto extends Wizard implements INewWizard {

	private static final String ASISTENTE_TITULO = "Crear nuevo proyecto";
	private static final String PAGINA_NOMBRE = "Proyecto";
	private static final String PAGINA_NOMBRE_TITULO = "Crear proyecto";
	private static final String PAGINA_NOMBRE_DESCRIPCION = "Crea un nuevo proyecto Java";
	
	
	private WizardNewProjectCreationPage pagina;
	
	public AsistenteNuevoProyecto() {

		this.setWindowTitle(ASISTENTE_TITULO);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	
	}

	@Override
	public boolean performFinish() {

		return true;
	}

	@Override
	public void addPages() {
	
		super.addPages();
		
	    this.pagina = new WizardNewProjectCreationPage(PAGINA_NOMBRE);
	    this.pagina.setTitle(PAGINA_NOMBRE_TITULO);
	    this.pagina.setDescription(PAGINA_NOMBRE_DESCRIPCION);
	 
	    this.addPage(this.pagina);
	}
}
