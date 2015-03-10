package lmax.resource;

public class SleepingResource implements Resource {

    @Override
    public boolean useResource(long time) {
		try {
		    Thread.sleep(time);
		} catch (InterruptedException e) {
		    throw new IllegalStateException(e);
		}
		return true;
    }

}
