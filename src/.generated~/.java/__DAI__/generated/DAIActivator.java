package __DAI__.generated;

import ej.wadapps.management.ActivitiesList;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.components.registry.BundleActivator;

public class DAIActivator implements BundleActivator {

	moc.lab.MainActivity moc__lab__MainActivity;

	@Override
	public void initialize() {
		this.moc__lab__MainActivity = new moc.lab.MainActivity();
	}

	@Override
	public void link() {
		ActivitiesList activitieslist = ServiceLoaderFactory.getServiceLoader().getService(ActivitiesList.class);
		activitieslist.add(this.moc__lab__MainActivity);

	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
		ActivitiesList activitieslist = ServiceLoaderFactory.getServiceLoader().getService(ActivitiesList.class);
		activitieslist.remove(this.moc__lab__MainActivity);

	}

}