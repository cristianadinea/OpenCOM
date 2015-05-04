package components.inc;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import components.IOutput;

public class Increment extends OpenCOMComponent implements IUnknown,
		IMetaInterface, ILifeCycle, IOutput, IIncrement {

	public Increment(IUnknown mpIOCM) {
		super(mpIOCM);
	}

	@Override
	public boolean shutdown() {
		return true;
	}

	@Override
	public boolean startup(Object data) {
		return true;
	}

	@Override
	public void DisplayMessage(String message) {
		System.out.println("Mesaj de la incrementare : " + message);
	}

	@Override
	public int inc(int x) {		
		return x+1;
	}

}
