/**
 * 
 */
package components.dec;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import components.IOutput;

/**
 * @author cristiana dinea
 *
 */
public class Decrement extends OpenCOMComponent implements IUnknown, IOutput,
		IMetaInterface, ILifeCycle, IDecrement {

	/**
	 * @param mpIOCM
	 */
	public Decrement(IUnknown mpIOCM) {
		super(mpIOCM);
	}

	/* (non-Javadoc)
	 * @see components.IOutput#DisplayMessage(java.lang.String)
	 */
	@Override
	public void DisplayMessage(String message) {
		System.out.println("Mesaj de la decrementare : " + message);
	}

	/* (non-Javadoc)
	 * @see OpenCOM.ILifeCycle#shutdown()
	 */
	@Override
	public boolean shutdown() {
		return true;
	}

	/* (non-Javadoc)
	 * @see OpenCOM.ILifeCycle#startup(java.lang.Object)
	 */
	@Override
	public boolean startup(Object data) {		
		return true;
	}

	/* (non-Javadoc)
	 * @see components.dec.IDecrement#dec(int)
	 */
	@Override
	public int dec(int x) {		
		return x-1;
	}

}
