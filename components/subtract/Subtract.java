package components.subtract;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import components.IOutput;
import components.subtract.ISubtract;

public class Subtract extends OpenCOMComponent implements IUnknown, ISubtract,
		IOutput, IMetaInterface, ILifeCycle {

	public Subtract(IUnknown mpIOCM) {
		super(mpIOCM);		
	}

	@Override
	public int subtract(int x, int y) {
		return x - y;
	}

	@Override
	public void DisplayMessage(String message) {
		System.out.println("Mesaj de la scadere : " + message);
	}

	@Override
	public boolean shutdown() {
		return true;
	}

	@Override
	public boolean startup(Object data) {
		return true;
	}

}
