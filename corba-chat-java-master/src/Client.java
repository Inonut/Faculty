import CalculatorApp.CalculatorHelper;
import CalculatorApp.Calculator;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

		try {

			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort","1051");

			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "calculator";
            Calculator calculator = CalculatorHelper.narrow(ncRef.resolve_str(name));

			System.out.println(String.format("21 * 2 = %d", calculator.mul(21, 2)));
			System.out.println(String.format("21 / 2 = %f", calculator.div(21, 2)));
			System.out.println(String.format("21 + 2 = %d", calculator.sum(21, 2)));
			System.out.println(String.format("21 - 2 = %d", calculator.sub(21, 2)));

			Scanner sc = new Scanner(System.in);
			List<String> tokens = new ArrayList<>();
            String token = "";
            System.out.println("Script: ----------type 'eval' for evaluation ------------");

			while(true) {
			    while(sc.hasNext()) {
			        token = sc.nextLine();
			        if(token.equalsIgnoreCase("eval")) {
			            break;
                    }
			        tokens.add(token);
                }
				if(token.equalsIgnoreCase("exit")) {
					break;
				}
				System.out.println(String.format("-------- %s", calculator.eval(tokens.stream().reduce("", (a, b) -> a.concat("\n").concat(b)))));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
