package presentation;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lmax.model.Car;
import lmax.model.CarEvent;
import lmax.model.LmaxCar;
import lmax.processors.Processor;
import lmax.resource.ResourceFactory;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class LmaxMain {
    public static void main(String[] args) throws Exception {
	ResourceFactory resourceFactory = new ResourceFactory(true);

	Executor executor = Executors.newCachedThreadPool();

	int bufferSize = 128;

	Disruptor<CarEvent> disruptor = new Disruptor<>(CarEvent::new, bufferSize, executor);

	Processor chassisProcessor = new Processor(resourceFactory, 10, ((Car car) -> car.installChassis()));
	Processor rearAxleProcessor = new Processor(resourceFactory, 10, ((Car car) -> car.installRearAxle()));

	Processor frontLeftSuspensionProcessor = new Processor(resourceFactory, 10,
		((Car car) -> car.installFrontLeftSuspension()));

	Processor frontRightSuspensionProcessor = new Processor(resourceFactory, 10,
		((Car car) -> car.installFrontRightSuspension()));

	Processor frontRightWheelProcessor = new Processor(resourceFactory, 10,
		((Car car) -> car.installWheel(true, false)));

	Processor frontLeftWheelProcessor = new Processor(resourceFactory, 10,
		((Car car) -> car.installWheel(true, true)));

	Processor rearRightWheelProcessor = new Processor(resourceFactory, 10,
		((Car car) -> car.installWheel(false, false)));

	Processor rearLeftWheelProcessor = new Processor(resourceFactory, 10,
		((Car car) -> car.installWheel(false, true)));

	Processor bodyProcessor = new Processor(resourceFactory, 10,
		((Car car) -> car.installBody()));

	long initialTime = System.currentTimeMillis();

	Processor finishProcessor =
		new Processor(resourceFactory, 0,
			((Car car) -> System.out.println(car.getFinishMsg(initialTime))));

	disruptor.handleEventsWith(chassisProcessor)
		.then(rearAxleProcessor, frontLeftSuspensionProcessor, frontRightSuspensionProcessor);

	disruptor.after(rearAxleProcessor).handleEventsWith(rearRightWheelProcessor, rearLeftWheelProcessor);

	disruptor.after(frontLeftSuspensionProcessor).handleEventsWith(frontLeftWheelProcessor);
	disruptor.after(frontRightSuspensionProcessor).handleEventsWith(frontRightWheelProcessor);

	disruptor.after(rearRightWheelProcessor,
		rearLeftWheelProcessor, frontLeftWheelProcessor, frontRightWheelProcessor)
		.handleEventsWith(bodyProcessor).then(finishProcessor);

	disruptor.start();

	RingBuffer<CarEvent> ringBuffer = disruptor.getRingBuffer();

	for (long l = 0; l < 1000; l++) {
	    ringBuffer.publishEvent((event, sequence, value) -> event.setCar(new LmaxCar(sequence)));
	}

    }
}
