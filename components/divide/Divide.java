/**
 * 
 */
package components.divide;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import components.IOutput;

/**
 * @author cristiana dinea
 *
 */
public class Divide extends OpenCOMComponent implements IUnknown,
		IMetaInterface, IOutput, ILifeCycle, IDivide {

	/**
	 * @param mpIOCM
	 */
	public Divide(IUnknown mpIOCM) {
		super(mpIOCM);
	}

	/* (non-Javadoc)
	 * @see components.IOutput#DisplayMessage(java.lang.String)
	 */
	@Override
	public void DisplayMessage(String message) {
		System.out.println("Mesaj de la impartire : " + message);
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
	 * @see components.divide.IDivide#divide(int, int)
	 */
	@Override
	public int divide(int x, int y) {
		return x / y;
	}

}
