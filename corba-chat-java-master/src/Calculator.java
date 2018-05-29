import CalculatorApp.CalculatorPOA;
import CalculatorApp.CalculatorPackage.ScriptException;
import org.omg.CORBA.Object;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Calculator extends CalculatorPOA {

    @Override
    public int sum(int n1, int n2) {
        return (n1 + n2);
    }

    @Override
    public int sub(int n1, int n2) {
        return (n1 - n2);
    }

    @Override
    public int mul(int n1, int n2) {
        return (n1 * n2);
    }

    @Override
    public double div(int n1, int n2) {
        return (n1 / n2);
    }

    @Override
    public double eval(String expr) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            return Double.valueOf(engine.eval(expr).toString());
        } catch (javax.script.ScriptException e) {
            throw new ScriptException(e.getMessage());
        }
    }
}
