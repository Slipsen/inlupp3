package org.ioopm.calculator.ast;
import java.math.*;
public   abstract class SymbolicExpression  {
    private String name;
    private String[] subExpressions;
    private int priority = 0;
    
    public SymbolicExpression(){
    
    }
    public SymbolicExpression(int priority){
        this.priority = priority;
    }
    public double getValue(){
        throw new RuntimeException("getValue() called on expression with no value");
    }
    public int getPriority(){
        return priority;
    }
    // public abstract SymbolicExpression eval();
    
    /** 
     * @param e object to be compared
     * @return true if objects are equal false if not, including possible sub-objects
     */
    public abstract SymbolicExpression eval(Environment e) throws IllegalAssignmentException ;

    /**
     * 
     * @param adds parameters to both ends of the object-string if the priority of the object is higher than than this object
     * @return
     */
    public String getParantheses(SymbolicExpression e){
        String str = e.toString();
        if(this.getPriority()<e.getPriority()){
            str= "(" + str + ")";
        }
        return str;
    }

    /**
     * @param Object the object to be compared
     * @eturn true if they are the same
     */
    public abstract boolean equals(Object e);
    //     throw new RuntimeException("Function not defined yet");
    // }

    /**
     * tells if class is a Constant
     * 
     * @return false for all Classes that has not updated it to return true
     */
    public boolean isConstant(){
        return false; 
    }
    /**
     * checks if object is a Command 
     * 
     * @return false unless class has modified method to return true
     */
    public boolean isCommand(){
        return false; 
    }

    /**
     * 
     * @return Class name if it has a name, throws an error if it does not
     */
    public  String getName()
    {
        throw new RuntimeException("getName() called on expression with no operator");
    }

    /**
     * checks if the var user is trying to define  define already exists  in the left term including by any variables there. Will check all Assignment objects in tree
     *
     *  
     * @param vars environment holding all defined variables
     * @return true if variable is found, else false 
     */
    abstract boolean varExists(Environment vars, SymbolicExpression sy);


    /// Returns e.g., "Constant(42)" if name is "Constant" and subExpressions is ["42"]

}
