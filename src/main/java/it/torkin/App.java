package it.torkin;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Hello world!
 *
 */
public class App 
{
    /** data loaded from given dataset file*/
    private static Instances dataset;
        
    private static void parseArgs(String[] args) throws Exception{

        // args[1]: path to file containing the dataset
        File datasetFile = new File(args[0]);
        if (datasetFile.exists()){
            dataset = new DataSource(datasetFile.getAbsolutePath()).getDataSet();
        } else {
            throw new UnableToFindDatasetFileException(datasetFile.getName());
        }
    }
    
    public static void main( String[] args ) throws Exception 
    {
        parseArgs(args);
        
    }
}
