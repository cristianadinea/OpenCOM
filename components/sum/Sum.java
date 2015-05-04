/**
 * 
 */
package components.sum;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import components.IOutput;

/**
 * @author cristiana dinea
 *
 */
public class Sum extends OpenCOMComponent implements IUnknown, ISum, IOutput,
		IMetaInterface, ILifeCycle {
	
	public Sum(IUnknown pRuntime) {
        super(pRuntime);
	}

	/* (non-Javadoc)
	 * @see components.sum.ISum#add(int, int)
	 */
	@Override
	public int add(int x, int y) {
		return x + y;
	}

	/* (non-Javadoc)
	 * @see components.IOutput#DisplayMessage(java.lang.String)
	 */
	@Override
	public void DisplayMessage(String message) {
		System.out.println("Mesaj de la adunare : " + message);
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
