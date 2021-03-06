package EntityShifterPKG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * This class takes care of processing the map file and shifting the entities
 */
public class MapProcessor
{
    //This string will hold the name of the map file (or the path)
    private String mapFile;

    //These integers will hold the shift values on the respective axis
    private int xShift, yShift, zShift;

    //This scanner will perform scans on the map file
    private Scanner mapScanner;

    //This String array will hold the lines of a map file
    private String[] lines;

    //This scanner will check that the map file is from Reflex, the boolean will hold whether or not it is
    private Scanner reflexValidator;
    public boolean reflexValidated = false;

    //This will take care of writing the new file
    private FileWriter mapWriter;

    /**
     * This is the constructor for the map processor
     * @param mapFile The map file to be processed
     */
    public MapProcessor(String mapFile) throws IOException
    {
        validateReflexMapFile(mapFile);

        if (reflexValidated)
        {
            //Set the map file parameter
            this.mapFile = mapFile;

            //Initialize the map scanner
            mapScanner = new Scanner(new File(mapFile));

            //Tell the user everything is honky dory for the scanner
            System.out.println("\nLoaded your file: " + mapFile);

            //The name of the file with the modification in the name
            String partialFileName = mapFile.substring(0, mapFile.length() - 4);

            //Initialize the file writer
            mapWriter = new FileWriter(partialFileName + "_EntityShifted.map");

            //Tell the user everything is honky dory for the writer
            System.out.println("Initialized empty map file for writing: " + partialFileName + "_EntityShifted.map");

            lines = new String[lineCount()];
            System.out.println("Created array of lines with size " + lines.length + "\n");
        }
    }

    /**
     * Perform tasks and call helper methods necessary to shift every entity in the map by a determined integer amount
     * @param xShift The amount to shift in the X axis
     * @param yShift The amount to shift in the Y axis
     * @param zShift The amount to shift in the Z axis
     * @throws IOException
     */
    public void shiftEntities(int xShift, int yShift, int zShift) throws IOException
    {
        createLinesArray();
        
        //Close mapScanner (not necessary after this point)
        mapScanner.close();

        //This process will find the start and end of a vertex group (in between "vertices" and "faces") and call the helper method
        for (int i = 0; i < lines.length; i++)
        {
            //If the line contains the tokens we are looking for
            if (lines[i].contains("Vector3 position"))
            {
                //Run through the process to shift the entities
                processEntity(i, xShift, yShift, zShift);
            }

            //Else, write the line normally
            else
            {
                mapWriter.write(lines[i] + "\n");
            }
        }

        //Close mapWriter
        mapWriter.close();
    }

    /**
     * This method will get called during construction to determine the size of the lines array
     * @return The number of lines in the map file
     * @throws IOException
     */
    private int lineCount() throws IOException
    {
        //This integer will hold the amount of lines
        int lines = 0;

        //Create a temporary scanner to iterate through the map file
        Scanner lineCounter = new Scanner(new File(mapFile));

        //Iterate through the map file and increment the counter per each line
        while(lineCounter.hasNextLine())
        {
            lineCounter.nextLine();
            lines++;
            //System.out.println("I am at line" + lines);
        }
        
        //Close lineCounter
        lineCounter.close();

        //Spit out the amount of lines
        return lines;
    }

    /**
     * This method will populate the lines array with each line of the map file
     */
    private void createLinesArray()
    {
        //This will iterate through the lines array
        for (int i = 0; i < lines.length; i++)
        {
            //Stop at end-of-File
            if (mapScanner.hasNextLine())
            {
                //Add the current line to the lines array
                lines[i] = mapScanner.nextLine();
            }
        }
    }

    /**
     * When called, this method will shift all entities
     */
    private void processEntity(int line, int xShift, int yShift, int zShift) throws IOException
    {
        //These doubles will hold the coordinates of a vertex
        double vertX, vertY, vertZ;

        //Tell user where the extraction is happening
        System.out.println("Processing Vector3 at Line: " + line + "\n");

        

        //This will be the new line written to the map file, holding shifted and formatted values
        String newLine;

        //This scanner will take care of reading each line of the vertex group (each lines holds an x, y, z)
        //Feed the line into the scanner
        Scanner vertScanner = new Scanner(lines[line]);

        //These two statements force the scanner to throw out the "Vector3" and "position" tokens, queueing up the doubles
        vertScanner.next(); vertScanner.next();

        //Extract 3 doubles and throw them into the container doubles
        vertX = Double.parseDouble(String.valueOf(vertScanner.nextDouble())) + xShift;
        vertY = Double.parseDouble(String.valueOf(vertScanner.nextDouble())) + yShift;
        vertZ = Double.parseDouble(String.valueOf(vertScanner.nextDouble())) + zShift;

        //Format the values into the new line
        newLine = String.format("\t\tVector3 position %.6f %.6f %.6f\n", vertX, vertY, vertZ);

        //System.out.println(newLine);

        //Write the new line into the map file
        mapWriter.write(newLine);
        
        //Close vertScanner
        vertScanner.close();
    }

    /**
     * This method will perform a validation check to make sure the map file is from Reflex Arena
     * @param mapToCheck The name of the map file to validate
     * @throws FileNotFoundException
     */
    private void validateReflexMapFile(String mapToCheck) throws FileNotFoundException
    {
        //Initialize the map scanner
        reflexValidator = new Scanner(new File(mapToCheck));

        if (reflexValidator.next().contains("reflex"))
        {
            reflexValidated = true;
            System.out.println("\nThis is a valid Reflex Arena map file");
        }

        else
        {
            System.out.println("\nThis is not a Reflex Arena map file");
        }
    }
}
