package lmax.resource;

public class WorkingResource implements Resource {

    @Override
    public void useResource(long time) {
	for (int i = 0; i < time; i++) {
	    for (int j = 0; j < time; j++) {
		// ??
	    }
	}
    }
}
