package ar.com.comunidadesfera.observatorio;

import org.eclipse.jdt.internal.ui.actions.OpenTypeAction;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.newsourcepage.GenerateBuildPathActionGroup;
import org.eclipse.jdt.ui.actions.CCPActionGroup;
import org.eclipse.jdt.ui.actions.GenerateActionGroup;
import org.eclipse.jdt.ui.actions.JavaSearchActionGroup;
import org.eclipse.jdt.ui.actions.OpenProjectAction;
import org.eclipse.jdt.ui.actions.OpenViewActionGroup;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

public class ProveedorDeAccionesObservatorio extends CommonActionProvider {

	private OpenViewActionGroup openViewGroup;

	private boolean inViewPart = false;
	
	public ProveedorDeAccionesObservatorio() {
	}
	
	@Override
	public void init(ICommonActionExtensionSite aSite) {
	
		super.init(aSite);
		
		if (aSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			
			ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) aSite.getViewSite();

			this.inViewPart = (workbenchSite.getPart() != null) && (workbenchSite.getPart() instanceof IViewPart); 
					
			if (this.inViewPart) {
				
				IViewPart viewPart = (IViewPart) workbenchSite.getPart();

				this.openViewGroup = new OpenViewActionGroup(viewPart, aSite.getStructuredViewer());
			}
		}
	}


	@Override
	public void setContext(ActionContext context) {
		super.setContext(context);
		
		if (this.inViewPart) {
			
			this.openViewGroup.setContext(context);
		}
	}
	
	@Override
	public void fillContextMenu(IMenuManager menu) {
		
		if (this.inViewPart) {
			
			this.openViewGroup.fillContextMenu(menu);
		}
	}
	
	@Override
	public void dispose() {
	
		if (this.inViewPart) {
			
			this.openViewGroup.dispose();
		}
		
		super.dispose();
	}
}
