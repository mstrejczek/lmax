package lmax.processors;

import lmax.model.Car;

@FunctionalInterface
public interface CarProcessor {
    void process(Car car);
}
