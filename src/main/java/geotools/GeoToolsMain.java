/*
 * the program reads a shape, iterates some of the contents that are read to a 
 * Feature collection and then displays the contents of the file in a Swing 
 * map frame
 * 
 */
package geotools;

import java.io.File;
import org.geotools.styling.Style;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.opengis.feature.simple.SimpleFeature;

/**
 *
 * @author saara
 */
public class GeoToolsMain {
    
    public static void main(String[] args) throws Exception {
        // display a data store file chooser dialog for shapefiles
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();
        
       // creating the SimpleFeatureCollection
        SimpleFeatureCollection collection = featureSource.getFeatures();
        
        // iteration 
        SimpleFeatureIterator i = collection.features();
        
        
         while (i.hasNext())
          {
            System.out.println("Next feature:"); 
            
            // 
            SimpleFeature f = i.next();
            
//            getAttributes 
              System.out.print("getAttributes: ");
              System.out.println(f.getAttributes());
              System.out.println("");
            
//            getDefaultGeometry
              System.out.print("getDefaultGeometry: ");
              System.out.println(f.getDefaultGeometry());
              System.out.println("");
              
//            getFeatureType
              System.out.print("getFeatureType: ");
              System.out.println(f.getFeatureType());
              System.out.println("");
              
//            getProperties
              System.out.print("getProperties: ");
              System.out.println(f.getProperties());
              System.out.println("");
              
//            getDefaultGeometryProperty  
              System.out.print("getDefaultGeometryProperty: ");
              System.out.println(f.getDefaultGeometryProperty());
              System.out.println("");

          }


//         Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("GeoToolsIntro");
        
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);

        // Now display the map
        JMapFrame.showMap(map);
    } 
}
