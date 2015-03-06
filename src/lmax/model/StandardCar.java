package lmax.model;

import com.sun.xml.internal.txw2.IllegalSignatureException;

public class StandardCar implements Car {

    private final long startTime = System.currentTimeMillis();
    private final long serialNumber;
    private boolean chassis = false;
    private boolean rearAxle = false;
    private boolean frontLeftSuspension = false;
    private boolean frontRightSuspension = false;
    private boolean frontLeftWheel = false;
    private boolean frontRightWheel = false;
    private boolean rearLeftWheel = false;
    private boolean rearRightWheel = false;
    private boolean body = false;

    public StandardCar(long serialNumber) {
	this.serialNumber = serialNumber;
    }

    @Override
    public boolean isReady() {
	return chassis
		&& rearAxle
		&& frontLeftSuspension
		&& frontRightSuspension
		&& frontLeftWheel
		&& frontRightWheel
		&& rearLeftWheel
		&& rearRightWheel
		&& body;
    }

    @Override
    public void installChassis() {
	chassis = true;
    }

    @Override
    public void installRearAxle() {
	if (chassis) {
	    rearAxle = true;
	} else {
	    throw new IllegalSignatureException("chassis not ready");
	}
    }

    @Override
    public void installFrontLeftSuspension() {
	if (chassis) {
	    frontLeftSuspension = true;
	} else {
	    throw new IllegalSignatureException("chassis not ready");
	}
    }

    @Override
    public void installFrontRightSuspension() {
	if (chassis) {
	    frontRightSuspension = true;
	} else {
	    throw new IllegalSignatureException("chassis not ready");
	}
    }

    @Override
    public void installWheel(boolean front, boolean left) {
	if (front) {
	    if (left) {
		if (frontLeftSuspension) {
		    frontLeftWheel = true;
		} else {
		    throw new IllegalSignatureException("frontLeftSuspension not ready");
		}
	    } else {
		if (frontRightSuspension) {
		    frontRightWheel = true;
		} else {
		    throw new IllegalSignatureException("rightLeftSuspension not ready");
		}
	    }
	} else {
	    if (rearAxle) {
		if (left) {
		    rearLeftWheel = true;
		} else {
		    rearRightWheel = true;
		}
	    } else {
		throw new IllegalSignatureException("rearAxle not ready");
	    }
	}
    }

    @Override
    public void installBody() {
	if (frontLeftWheel
		&& frontRightWheel
		&& rearLeftWheel
		&& rearRightWheel) {
	    body = true;
	} else {
	    throw new IllegalSignatureException("wheels not ready");
	}
    }

    @Override
    public String getFinishMsg(long systemStartTime) {
	return "Car " + serialNumber + " isReady: " + isReady() + " in " + (System.currentTimeMillis() - startTime)
		+ "ms. Time from system start:" + (System.currentTimeMillis() - systemStartTime) + "ms";
    }

}
