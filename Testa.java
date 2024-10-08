import static org.junit.jupiter.api.Assertions.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import org.ioopm.calculator.ast.CommandException;
import org.ioopm.calculator.ast.SymbolicExpression;
import org.ioopm.calculator.ast.Variable;
import org.ioopm.calculator.parser.CalculatorParser;
import org.junit.jupiter.api.Test;

public class Testa{
    private CalculatorParser cp = new CalculatorParser();
    public Testa(){
    }
    void newString(String str){
    
    }
    /**
     * Tests if a a symbolic expression evals to what is expected 
     * 
     * @param expected  The expression to be evaluated
     * @param  e        The expression we expect to be
     * @return         prints what we got and what we expected of errort
     */
    public void testEvaluating(SymbolicExpression expected, SymbolicExpression e) {
        SymbolicExpression r = null;
        boolean result = false; 
       try{ 
        r = e.eval(null);; 
        if (r.equals(expected)) {
            result =  true;
        } else {
            result = false; 
        }
            } catch(Exception er){
                result = false; 
            }
        assertTrue(result,"Error: expected '" + expected + "' but got '" + e + "'");
    }

    void testParse(String read, String result, CalculatorParser parser){
        SymbolicExpression se = new Variable("faulty reading");; 
        boolean threwException = false;
        try{
            se = parser.parseExpression(result);
        }
        catch (Exception e){
            threwException = true; 

        }
        assertFalse(threwException,"Threw an exception when parsing " + read);
        System.out.println(se +" " + result);
        assertTrue(se.toString().equals(result), "read "+ se + " as " + se + " instead of " + result );
    }
    @Test
    public void testReading(){
        CalculatorParser readParser = new CalculatorParser();
        testParse("1 + 3 + 4 + 5", "13.0", readParser);
        testParse("1 + 3 + 4 + 5 = x", "13.0", readParser);
        testParse("y", "13.0", readParser);
        testParse("y + y", "26.0", readParser);
        testParse ("y+y+y = y ", "39.0",readParser);
        testParse("y", "39.0", readParser);
        testParse("y y", "3.0", readParser);
        System.out.println("hello");
    }

    public void shitTest(){
        assertTrue(false);
    }

    public void parseText(String read, String compare, CalculatorParser cp){
        try{
            SymbolicExpression se = cp.parseExpression(read);
            assertTrue(se.toString().equalsIgnoreCase(compare), "Parsed string was supposed to equal "+ compare + " but we got "+ se.toString() + "and " + read + " was read");
        }catch (CommandException e){
            if(e.getCommand().getName().equals(compare)){
                assertTrue(true);
            }
            else {
                assertTrue(false);
            }
        }
        catch (Exception e){
            assertTrue(false,"Text " + read + " could not be read error was " + e);
        }
    }
    @Test
    public void testParser1(){
        try{
        File object = new File("test.txt");
        Scanner reader = new Scanner(object);
        while(reader.hasNextLine()){
            String toTest = reader.nextLine().trim();
            String toCompare = reader.nextLine().trim();
            parseText(toTest, toCompare, cp);
        }
        reader.close();

        }
        
        catch(FileNotFoundException e){
            System.out.println("Something went wrong");
        }

    }






    public static void main(String[] args){
        //someMathTests();
    }
}

    // SymbolicExpression se = new Multiplication(new Addition(new Constant(5), new Constant(2)), new Constant(2));
    // System.out.println(se);
    // System.out.println(se.eval(vars).getValue());
    // se = new Assignment(new Multiplication(new Addition(new Constant(5), new Constant(1)), new Cos(new Addition(new Constant(3.1415),new Constant(3.1415)))),new Variable("y"));
    // System.out.println(se);
    // System.out.println(se.eval(vars));
    // System.err.println(new Multiplication(new Variable("y"), new Variable("y")).eval(vars));
    // vars.put(new Variable("y"),new Constant(1));
    // System.out.println(vars.get(new Variable("y")));
    // HashMap<String,SymbolicExpression> map = new HashMap<>();
    // map.put("y", new Constant(3));
    // Variable var1 = new Variable("x");
    // Variable var2 = new Variable( "x");
    // System.out.println(var2.hashCode() + " " + var2 + " hashcode ofvar 2 " + var1 + " " + var1.hashCode() );
    // System.out.println(map.get("y"));}