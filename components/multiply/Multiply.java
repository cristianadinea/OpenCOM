/**
 * 
 */
package components.multiply;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import components.IOutput;
import components.multiply.IMultiply;

/**
 * @author cristiana dinea
 *
 */
public class Multiply extends OpenCOMComponent implements IUnknown, IOutput,
		IMetaInterface, ILifeCycle, IMultiply {

	/**
	 * @param mpIOCM
	 */
	public Multiply(IUnknown mpIOCM) {
		super(mpIOCM);
	}

	/* (non-Javadoc)
	 * @see components.IOutput#DisplayMessage(java.lang.String)
	 */
	@Override
	public void DisplayMessage(String message) {
		System.out.println("Mesaj de la inmultire : " + message);
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
	 * @see components.multiply.IMultiply#multiply(int, int)
	 */
	@Override
	public int multiply(int x, int y) {
		return x * y;
	}

}
