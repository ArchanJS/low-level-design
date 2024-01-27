// Calculator App: Design a simple calculator app that can perform basic arithmetic operations like addition, subtraction, multiplication, and division. You can extend it to include functionalities like memory storage, decimal support, and basic operators like modulo or square root. This exercise focuses on data structures (like storing operands and operators) and basic control flow for different operations.

package lld;

public class Calculator {
    private int result;
    public Calculator(){
        result=0;
    }
    public void getResult(){
        System.out.println(result);
    }
    public void add(int value1, int value2){
        result = value1+value2;
    }
    public void subtract(int value1, int value2){
        result=value1 - value2;
    }
    public void multiply(int value1, int value2){
        result=value1*value2;
    }
    public void divide(int value1, int value2){
        result=value1/value2;
    }
    public void modulo(int value1, int value2){
        result=value1%value2;
    }
    public void squareRoot(int value){
        result=(int)Math.sqrt(value);
    }
    public void calculateResult(String operation, int noOfParams, int[] params){
        operation=operation.toLowerCase();
        switch (operation){
            case "add":
            add(params[0],params[1]);
            break;
            case "subtract":
            subtract(params[0],params[1]);
            break;
            case "multiply":
            multiply(params[0],params[1]);
            break;
            case "divide":
            divide(params[0],params[1]);
            break;
            case "modulo":
            modulo(params[0],params[1]);
            break;
            case "square root":
            squareRoot(params[0]);
            break;
            default:
            return;

        }
        this.getResult();
    }

}
