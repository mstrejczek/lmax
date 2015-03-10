package lmax.resource;

public class ResourceFactory {

	private boolean sleep;

	private Resource sleepingResource = new SleepingResource();
	private Resource workingResource = new WorkingResource();

	public ResourceFactory(boolean sleep) {
		this.sleep = sleep;
	}

	public Resource getResource() {
		if (sleep) {
			return sleepingResource;
		} else {
			return workingResource;
		}
	}
}
