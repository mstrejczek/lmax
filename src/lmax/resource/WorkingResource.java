package lmax.resource;

import java.util.Random;

public class WorkingResource implements Resource {

	@Override
	public boolean useResource(long time) {
		Random random = new Random();
		int sum = 0;
		for (int i = 0; i < time * 100000; i++) {
			sum += random.nextInt(16);
		}
		return sum % 2 == 0;
	}
}
