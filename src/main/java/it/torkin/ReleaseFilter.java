package it.torkin;

import java.util.HashSet;
import java.util.Set;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.instance.RemoveWithValues;
    
/**Selects only instances of given i-release */
public class ReleaseFilter extends RemoveWithValues{
    
    /**
     * @return i-release name of dataset */
    private String getReleaseName(Instances data, int i) {
        String name;
        Set<String> seen = new HashSet<>();
        for (Instance datum : data) {
            name = datum.stringValue(new CustomInstanceHelper(datum).getReleaseAttribute());
            seen.add(name);
            if (seen.size() == i + 1) {
                return name;
            }
        }
        throw new IndexOutOfBoundsException(i);
    }    
    
    /**
     * @param dataset dataset of instances of resources at a given release
     * @param i index of release in time-ordered release list 
     * @throws Exception
     */
    public ReleaseFilter(Instances dataset, int releaseIndex) throws Exception{
        
        if (dataset.isEmpty()){
            throw new IllegalArgumentException("dataset must contain at least one instance");
        }
        
        super.setInputFormat(dataset);

        String releaseName = getReleaseName(dataset, releaseIndex);
        Attribute release = new CustomInstanceHelper(dataset.get(0)).getReleaseAttribute();
        String[] options = {"-C", String.valueOf(release.index() + 1), "-L", String.valueOf(release.indexOfValue(releaseName) + 1)};
        super.setOptions(options);
    }
    
}
