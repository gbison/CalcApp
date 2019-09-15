package com.sgmediasoft.CalcApp;

/*
    Description: Designed to provide basic input/output of normal arithamtic operations.
    Date: 9/2/2019
    Class: CIT238 (SKCTCS)
    Author: George Ison
 */

import android.content.Context;
import android.widget.Toast;

public class Calculate {
    //================================================================================
    //region Properties
    //================================================================================
    /*
        Here lets thing in terms of straight Java, even though we are in Android context.

        We create a public class just like we would in a Java console application and we setup
        both private and public variables and methods in order to protect and process calculations.

     */
    private int value;
    private int firstValue;
    private int nextValue;
    private int lastValue;
    private char currentOperation;
    private char lastOperation;

    //Lets keep a copy of our MainActivities context in case we need to TOAST! :-)
    protected Context MainContext;
    //endregion

    //================================================================================
    //region Constructors
    //================================================================================
    public Calculate(){}
    //This constructor does everything in one swoop! Works, but hard to UnitTest.
    public Calculate(int num1, int num2, char operation){

        firstValue = num1;
        nextValue = num2;
        lastOperation = currentOperation;
        currentOperation = operation;

        if(num1 !=  0 || num2 != 0) {
            switch (operation) {
                case '+':
                    Add();
                    break;
                case '-':
                    Subtract();
                    break;
                case '*':
                    Multiply();
                    break;
                case '/':
                    Divide();
                    break;

                default:
            }
        }
    }
    //endregion

    //================================================================================
    //region Accessors
    //================================================================================

    // We should all know what getters and setters are at this point, if you do not contact me
    //immediately because your Java skills need refreshing.
    protected int getValue(){
        return value;
    }

    protected int getFirstValue(){
        return firstValue;
    }

    protected int getNextValue(){
        return nextValue;
    }

    protected char getCurrentOperation(){
        return currentOperation;
    }

    protected char getLastOperation(){
        return lastOperation;
    }

    protected void setFirstValue(int value)
    {
        this.firstValue = value;
    }

    protected void setNextValue(int value)
    {
        this.nextValue = value;
    }
    //endregion

    //================================================================================
    //region Methods
    //================================================================================
    //These are simple class methods that let us perform operations on the data in our class.
    protected float Add(){
        lastOperation = currentOperation;
        lastValue = value;
        value = firstValue + nextValue;
        currentOperation = '+';
        return value;
    }

    protected float Subtract(){
        lastOperation = currentOperation;
        lastValue = value;
        value = firstValue - nextValue;
        currentOperation = '-';
        return value;
    }

    protected float Multiply(){
        lastOperation = currentOperation;
        lastValue = value;
        value = firstValue * nextValue;
        currentOperation = '*';
        return value;
    }

    //Pay particular attention here to the use of Try Catch, also to the Toast call and the console
    //output for use in debugging if something crashes.
    protected float Divide(){
        lastOperation = currentOperation;
        lastValue = value;

        //Prepare for the user to try and divide by 0!!
        try
        {
            //this should keep us from ever triggering the exception.
            if(nextValue != 0)
                value = firstValue / nextValue;
        }
        catch(ArithmeticException err) {
            //Present a toast to the user and tell them they messed up!
            String flash = "ERROR: You cannot divide by 0!";
            Toast.makeText(MainContext, flash, Toast.LENGTH_LONG).show();

            //dump to console so we can debug if needed.
            System.out.print(err.getMessage());
            System.out.println("------------------------------------------------------");
            err.printStackTrace();
        }

        currentOperation = '/';
        return value;
    }
    //endregion
}
