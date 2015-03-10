package lmax.processors;

import lmax.model.Car;
import lmax.model.CarEvent;
import lmax.resource.ResourceFactory;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Processor implements CarProcessor, EventHandler<CarEvent>,
		WorkHandler<CarEvent> {

	private ResourceFactory resourceFactory;
	private long time;
	private CarProcessor processor;
	private boolean resourceState = true;

	public Processor(ResourceFactory resourceFactory, long time,
			CarProcessor processor) {
		this.resourceFactory = resourceFactory;
		this.time = time;
		this.processor = processor;
	}

	@Override
	public void onEvent(CarEvent carEvent, long seq, boolean last)
			throws Exception {
		process(carEvent.getCar());

	}

	@Override
	public void process(Car car) {
		resourceState &= resourceFactory.getResource().useResource(time);
		processor.process(car);

	}

	@Override
	public void onEvent(CarEvent carEvent) throws Exception {
		process(carEvent.getCar());
	}

}
