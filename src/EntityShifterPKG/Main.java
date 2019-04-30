package EntityShifterPKG;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main (String[] args) throws IOException
    {
        //argument 0: Map file name
        //argument 1: X shift
        //argument 2: Y shift
        //argument 3: Z shift

        //********CODE BELOW CHECKS FOR INPUT VALIDITY********

        //This will hold whether the user arguments are valid, if any checks below trip this to false, no execution code will run
        boolean argumentsValid = true;

        if (args.length > 4 || args.length < 4)
        {
            System.out.println("You have an incorrect number of arguments. The input format is as follows:\n" +
                    "ARGUMENT 0 (String): The name of a .map file (present in this directory)\n" +
                    "ARGUMENT 1 (Integer): The X shift amount\n" +
                    "ARGUMENT 2 (Integer): The Y shift amount\n" +
                    "ARGUMENT 3 (Integer): The Z shift amount");

            argumentsValid = false;
        }

        //Once we confirm that the user has the right amount of inputs, check them individually for validity
        else
        {
            //Create a scanner to check the individual inputs
            Scanner inputScanner;

            //This for loop will check each input
            for (int i = 0; i < args.length; i++)
            {
                //This string will hold the current argument
                String currentArg = args[i];
                //Set the scanner to scan the current argument
                inputScanner = new Scanner(currentArg);

                //If this is the first argument (argument 0), and it has any value
                if (i == 0 && inputScanner.hasNext())
                {
                    //Throw an error if the input does not have the .map file type
                    if (!inputScanner.next().contains(".map"))
                    {
                        System.out.println("ARGUMENT 0 Error: This is not a .map file");

                        argumentsValid = false;
                    }

                    //Else, it must be good
                    else
                    {
                        System.out.println("ARGUMENT 0 is good");
                    }
                }

                //If this is the second argument (argument 1)
                else if (i == 1)
                {
                    //If the argument is an integer, it must be good
                    if (inputScanner.hasNextInt())
                    {
                        System.out.println("ARGUMENT 1 is good");
                    }

                    else
                    {
                        System.out.println("ARGUMENT 1 Error: This is not an integer");

                        argumentsValid = false;
                    }
                }

                //If this is the third argument (argument 2)
                else if (i == 2)
                {
                    //If the argument is an integer, it must be good
                    if (inputScanner.hasNextInt())
                    {
                        System.out.println("ARGUMENT 2 is good");
                    }

                    else
                    {
                        System.out.println("ARGUMENT 2 Error: This is not an integer");

                        argumentsValid = false;
                    }
                }

                //If this is the fourth argument (argument 3)
                else if (i == 3)
                {
                    //If the argument is an integer, it must be good
                    if (inputScanner.hasNextInt())
                    {
                        System.out.println("ARGUMENT 3 is good");
                    }

                    else
                    {
                        System.out.println("ARGUMENT 3 Error: This is not an integer");

                        argumentsValid = false;
                    }
                }
            }
        }

        //**********CODE BELOW WILL PROCESS MAP FILE**********

        if (argumentsValid)
        {
            //The following 4 declarations will hold the arguments
            String mapFile = args[0];
            int xShift = Integer.parseInt(args[1]);
            int yShift = Integer.parseInt(args[2]);
            int zShift = Integer.parseInt(args[3]);

            //Create the map processor object and pass it the file
            MapProcessor cpm5Processor = new MapProcessor(mapFile);

            //Execute further instructions only if the file was validated
            if (cpm5Processor.reflexValidated)
            {
                cpm5Processor.shiftEntities(xShift, yShift, zShift);
            }
        }
    }
}
