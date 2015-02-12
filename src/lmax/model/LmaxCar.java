package lmax.model;

public class LmaxCar {

    private final long startTime = System.currentTimeMillis();
    private PaddedBoolean chassis = new PaddedBoolean();
    private PaddedBoolean rearAxle = new PaddedBoolean();
    private PaddedBoolean frontLeftSuspension = new PaddedBoolean();
    private PaddedBoolean frontRightSuspension = new PaddedBoolean();
    private PaddedBoolean frontLeftWheel = new PaddedBoolean();
    private PaddedBoolean frontRightWheel = new PaddedBoolean();
    private PaddedBoolean rearLeftWheel = new PaddedBoolean();
    private PaddedBoolean rearRightWheel = new PaddedBoolean();
    private PaddedBoolean body = new PaddedBoolean();

}
