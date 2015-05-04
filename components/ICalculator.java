package components;

import OpenCOM.IUnknown;

public interface ICalculator extends IUnknown {

	public int dec(int x);
	
	public int inc(int x);
	
	public int divide(int x, int y);
	
	public int multiply(int x, int y);
	
	public int subtract(int x, int y);
	
	public int add(int x, int y);
	
	public void setLimit(int limit);

}
