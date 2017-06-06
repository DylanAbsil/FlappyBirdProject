package __DAI__.generated;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.components.registry.BundleRegistry;
import ej.wadapps.management.ActivitiesScheduler;
import ej.wadapps.registry.SharedRegistryFactory;

public class MainActivityStandalone {

	public static void main(String[] args) {
		SharedRegistryFactory.getSharedRegistry().register(BundleRegistry.class, new StandaloneRegistry());

		// Start entry point.
		new DAIEntryPoint().start();

		// MainActivity
		ActivitiesScheduler activitiesScheduler = ServiceLoaderFactory.getServiceLoader()
				.getService(ActivitiesScheduler.class);
		moc.lab.MainActivity activity = new moc.lab.MainActivity();
		activitiesScheduler.show(activity);
	}

}