package brain;

import OpenCOM.ILifeCycle;
import OpenCOM.IOpenCOM;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOM;
import components.ICalculator;

public class ExpressionInterpreter {

	public static OpenCOM runtime;
	public static IOpenCOM pIOCM;
	public static IUnknown pCalcIUnk;
	public static ILifeCycle pILife;
	public static ICalculator pICalc;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (init()) {
			System.out.println("Am initializat tot\n");
			pICalc.setLimit(400);
			Interpretor a = new Interpretor("1 + 2 * ( 7 * 10 ++ - ( 2 + 4 - 5 -- )  + ( 3 -- ) / 1 )");
	        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Rezultatul este : " + a.interpret());
	        
	        pICalc.setLimit(40);
	        a = new Interpretor("1 + 2 * ( 7 * 10 ++ - ( 2 + 4 - 5 -- )  + ( 3 -- ) / 1 )");
	        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Rezultatul este : " + a.interpret());
	        
	        pICalc.setLimit(27);
			a = new Interpretor("5 ++ + 2 * ( 7 * 2 ++ - ( 2 + 4 - ( 2 + 6 ++ + ( 5 -- ) ) )  + ( 3 -- ) / 1 )");
	        System.out.println(a.interpret());
			
			
			
			System.out.println("Am terminat tot\n");
		}
		
		
	}

	public static boolean init() {
		
		// Create the OpenCOM runtime & Get the IOpenCOM interface reference
        runtime = new OpenCOM();
        pIOCM =  (IOpenCOM) runtime.QueryInterface("OpenCOM.IOpenCOM");
		
        // Create the Calculator component
        pCalcIUnk = (IUnknown) pIOCM.createInstance("components.Calculator", "Calculator");
        pILife =  (ILifeCycle) pCalcIUnk.QueryInterface("OpenCOM.ILifeCycle");
        pILife.startup(pIOCM);
        
        // Get the Calculator Interface
        pICalc =  (ICalculator) pCalcIUnk.QueryInterface("components.ICalculator");
        
		return true;
	}

}
