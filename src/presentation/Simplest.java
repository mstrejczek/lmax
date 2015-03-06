package presentation;

import lmax.model.Car;
import lmax.model.StandardCar;
import lmax.processors.Processor;
import lmax.resource.ResourceFactory;

public class Simplest {
    public static void main(String[] args) throws Exception {
	ResourceFactory resourceFactory = new ResourceFactory(true);

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

	for (long l = 0; l < 1000; l++) {
	    Car car = new StandardCar(l);
	    chassisProcessor.process(car);
	    rearAxleProcessor.process(car);
	    frontLeftSuspensionProcessor.process(car);
	    frontRightSuspensionProcessor.process(car);
	    rearRightWheelProcessor.process(car);
	    rearLeftWheelProcessor.process(car);
	    frontLeftWheelProcessor.process(car);
	    frontRightWheelProcessor.process(car);
	    bodyProcessor.process(car);
	    finishProcessor.process(car);
	}

    }
}
