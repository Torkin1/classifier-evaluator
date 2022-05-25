package it.torkin;

import java.io.File;

import org.junit.Test;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.rules.ZeroR;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestWalkForward {

    @Test
    public void test() throws Exception{
        
        Instances datatset = new DataSource(new File("/run/media/daniele/614F7D537CF390D4/corsi/ISW2/hw_falessi/buggynessCrawler/datasets/Torkin1_avro_dataset_50%.csv").getAbsolutePath()).getDataSet();
        datatset.setClassIndex(datatset.numAttributes() - 1);
        WalkForward walkForward = new WalkForward(
            ZeroR.class,
            datatset
        );

        walkForward.next();
        
    }
    
}
