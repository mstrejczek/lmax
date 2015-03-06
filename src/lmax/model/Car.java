package lmax.model;

public interface Car {

    boolean isReady();

    void installChassis();

    String getFinishMsg(long initialTime);

    void installRearAxle();

    void installFrontLeftSuspension();

    void installFrontRightSuspension();

    void installWheel(boolean front, boolean left);

    void installBody();

}
