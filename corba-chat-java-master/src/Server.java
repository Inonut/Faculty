import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import CalculatorApp.CalculatorHelper;

import java.util.Properties;

public class Server {

	public static void main(String args[]) {

		try {

			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort","1051");

			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();

			Calculator calcImpl = new Calculator();

            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(calcImpl);
			CalculatorApp.Calculator mRef = CalculatorHelper.narrow(ref);
 
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "calculator";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, mRef);

			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
