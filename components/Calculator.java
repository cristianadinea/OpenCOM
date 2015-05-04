/**
 * 
 */
package components;

import brain.ExpressionInterpreter;
import OpenCOM.IConnections;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OCM_SingleReceptacle;
import OpenCOM.OpenCOMComponent;

import components.dec.IDecrement;
import components.divide.IDivide;
import components.inc.IIncrement;
import components.multiply.IMultiply;
import components.subtract.ISubtract;
import components.sum.ISum;

/**
 * @author cristiana dinea
 * 
 */
public class Calculator extends OpenCOMComponent implements ICalculator,
		IUnknown, ILifeCycle, IConnections, IMetaInterface {

	public int limita = 0;
	boolean once = false;
	public OCM_SingleReceptacle<ISum> m_PSR_ISum;
	public OCM_SingleReceptacle<ISubtract> m_PSR_ISubtract;
	public OCM_SingleReceptacle<IMultiply> m_PSR_IMultiply;
	public OCM_SingleReceptacle<IDivide> m_PSR_IDvide;
	public OCM_SingleReceptacle<IIncrement> m_PSR_Increment;
	public OCM_SingleReceptacle<IDecrement> m_PSR_IDecrement;

	/**
	 * @param mpIOCM
	 */
	public Calculator(IUnknown mpIOCM) {
		super(mpIOCM);
		m_PSR_ISubtract = new OCM_SingleReceptacle<ISubtract>(ISubtract.class);
		m_PSR_ISum = new OCM_SingleReceptacle<ISum>(ISum.class);
		m_PSR_IMultiply = new OCM_SingleReceptacle<IMultiply>(IMultiply.class);
		m_PSR_IDvide = new OCM_SingleReceptacle<IDivide>(IDivide.class);
		m_PSR_Increment = new OCM_SingleReceptacle<IIncrement>(IIncrement.class);
		m_PSR_IDecrement = new OCM_SingleReceptacle<IDecrement>(
				IDecrement.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ICalculator#add(int, int)
	 */
	@Override
	public int add(int x, int y) {
		// build the adder
		IUnknown adder = (IUnknown) ExpressionInterpreter.pIOCM.createInstance(
				"components.sum.Sum", "Sum");
		ILifeCycle pILife = (ILifeCycle) adder
				.QueryInterface("OpenCOM.ILifeCycle");
		pILife.startup(ExpressionInterpreter.pIOCM);

		long connID = ExpressionInterpreter.runtime.connect(
				ExpressionInterpreter.pCalcIUnk, adder, "components.sum.ISum");

		int rez = m_PSR_ISum.m_pIntf.add(x, y);
		System.out.println("Adunarea a calculat : " + rez);

		if (rez > limita) {
			System.out.println("Overflow\n");
			ExpressionInterpreter.runtime.disconnect(connID);
			rez = subtract(x, y);
		}

		return rez;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ICalculator#dec(int)
	 */
	@Override
	public int dec(int x) {

		// build the decrementer
		IUnknown decrementer = (IUnknown) ExpressionInterpreter.pIOCM
				.createInstance("components.dec.Decrement", "Decrement");
		ILifeCycle pILife = (ILifeCycle) decrementer
				.QueryInterface("OpenCOM.ILifeCycle");
		pILife.startup(ExpressionInterpreter.pIOCM);

		long connID = ExpressionInterpreter.runtime.connect(
				ExpressionInterpreter.pCalcIUnk, decrementer,
				"components.dec.IDecrement");

		int rez = m_PSR_IDecrement.m_pIntf.dec(x);
		System.out.println("Decrementarea a calculat : " + rez);

		if (rez > limita) {
			System.out.println("Overflow\n");
			ExpressionInterpreter.runtime.disconnect(connID);
			once = true;
			rez = divide(x, 10);
		}

		return rez;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ICalculator#divide(int, int)
	 */
	@Override
	public int divide(int x, int y) {
		
		// build the divider
		IUnknown divider = (IUnknown) ExpressionInterpreter.pIOCM
				.createInstance("components.divide.Divide", "Divide");
		ILifeCycle pILife = (ILifeCycle) divider
				.QueryInterface("OpenCOM.ILifeCycle");
		pILife.startup(ExpressionInterpreter.pIOCM);

		long connID = ExpressionInterpreter.runtime.connect(
				ExpressionInterpreter.pCalcIUnk, divider,
				"components.divide.IDivide");

		int rez = m_PSR_IDvide.m_pIntf.divide(x, y);
		System.out.println("Impartirea a calculat : " + rez);

		if (rez > limita) {
			System.out.println("Overflow\n");						
			rez = m_PSR_IDvide.m_pIntf.divide(rez, 10);
			ExpressionInterpreter.runtime.disconnect(connID);
		}

		return rez;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ICalculator#inc(int)
	 */
	@Override
	public int inc(int x) {

		// build the incrementer
		IUnknown incrementer = (IUnknown) ExpressionInterpreter.pIOCM
				.createInstance("components.inc.Increment", "Increment");
		ILifeCycle pILife = (ILifeCycle) incrementer
				.QueryInterface("OpenCOM.ILifeCycle");
		pILife.startup(ExpressionInterpreter.pIOCM);

		long connID = ExpressionInterpreter.runtime.connect(
				ExpressionInterpreter.pCalcIUnk, incrementer,
				"components.inc.IIncrement");

		int rez = m_PSR_Increment.m_pIntf.inc(x);
		System.out.println("Incrementarea a calculat : " + rez);

		if (rez > limita) {
			System.out.println("Overflow\n");
			ExpressionInterpreter.runtime.disconnect(connID);
			rez = dec(x);
		}

		return rez;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ICalculator#multiply(int, int)
	 */
	@Override
	public int multiply(int x, int y) {

		// build the multiplier
		IUnknown divider = (IUnknown) ExpressionInterpreter.pIOCM
				.createInstance("components.multiply.Multiply", "Multiply");
		ILifeCycle pILife = (ILifeCycle) divider
				.QueryInterface("OpenCOM.ILifeCycle");
		pILife.startup(ExpressionInterpreter.pIOCM);

		long connID = ExpressionInterpreter.runtime.connect(
				ExpressionInterpreter.pCalcIUnk, divider,
				"components.multiply.IMultiply");

		int rez = m_PSR_IMultiply.m_pIntf.multiply(x, y);
		System.out.println("Inmultirea a calculat : " + rez);

		if (rez > limita) {
			System.out.println("Overflow\n");
			ExpressionInterpreter.runtime.disconnect(connID);
			once = true;
			rez = divide(x, 10);
		}

		return rez;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see components.ICalculator#subtract(int, int)
	 */
	@Override
	public int subtract(int x, int y) {

		IUnknown subtracter = (IUnknown) ExpressionInterpreter.pIOCM
				.createInstance("components.subtract.Subtract", "Subtract");
		ILifeCycle pILife = (ILifeCycle) subtracter
				.QueryInterface("OpenCOM.ILifeCycle");
		pILife.startup(ExpressionInterpreter.pIOCM);

		long connID = ExpressionInterpreter.runtime.connect(
				ExpressionInterpreter.pCalcIUnk, subtracter,
				"components.subtract.ISubtract");

		int rez = m_PSR_ISubtract.m_pIntf.subtract(x, y);
		System.out.println("Scaderea a calculat : " + rez);

		if (rez > limita) {
			System.out.println("Overflow\n");
			ExpressionInterpreter.runtime.disconnect(connID);
			once = true;
			rez = divide(rez, 10);
		}

		return rez;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OpenCOM.ILifeCycle#shutdown()
	 */
	@Override
	public boolean shutdown() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OpenCOM.ILifeCycle#startup(java.lang.Object)
	 */
	@Override
	public boolean startup(Object data) {
		return true;
	}

	public String display(String message) {
		System.out.println("Mesaj de la calculator\n");
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OpenCOM.IConnections#connect(OpenCOM.IUnknown, java.lang.String,
	 * long)
	 */
	@Override
	public boolean connect(IUnknown pSinkIntf, String riid, long provConnID) {
		if (riid.toString().equalsIgnoreCase("components.sum.ISum")) {
			return m_PSR_ISum.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.toString().equalsIgnoreCase(
				"components.subtract.ISubtract")) {
			return m_PSR_ISubtract.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.toString().equalsIgnoreCase(
				"components.multiply.IMultiply")) {
			return m_PSR_IMultiply.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.toString()
				.equalsIgnoreCase("components.divide.IDivide")) {
			return m_PSR_IDvide.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.toString()
				.equalsIgnoreCase("components.inc.IIncrement")) {
			return m_PSR_Increment.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.toString()
				.equalsIgnoreCase("components.dec.IDecrement")) {
			return m_PSR_IDecrement.connectToRecp(pSinkIntf, riid, provConnID);
		}

		System.out.println("Operatie nesuportata\n");
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OpenCOM.IConnections#disconnect(java.lang.String, long)
	 */
	@Override
	public boolean disconnect(String riid, long connID) {
		if (riid.toString().equalsIgnoreCase("components.sum.ISum")) {
			return m_PSR_ISum.disconnectFromRecp(connID);
		} else if (riid.toString().equalsIgnoreCase(
				"components.subtract.ISubtract")) {
			return m_PSR_ISubtract.disconnectFromRecp(connID);
		} else if (riid.toString().equalsIgnoreCase(
				"components.multiply.IMultiply")) {
			return m_PSR_IMultiply.disconnectFromRecp(connID);
		} else if (riid.toString()
				.equalsIgnoreCase("components.divide.IDivide")) {
			return m_PSR_IDvide.disconnectFromRecp(connID);
		} else if (riid.toString()
				.equalsIgnoreCase("components.inc.IIncrement")) {
			return m_PSR_Increment.disconnectFromRecp(connID);
		} else if (riid.toString()
				.equalsIgnoreCase("components.dec.IDecrement")) {
			return m_PSR_IDecrement.disconnectFromRecp(connID);
		}

		System.out.println("Deconectare nereusita\n");
		return false;
	}

	@Override
	public void setLimit(int limit) {
		this.limita = limit;
		
	}

}
