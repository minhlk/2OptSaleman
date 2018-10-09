import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cities implements ICities{
    String path;
    List<Point2D> cities;
    public Cities(String path){
        this.path = "./input/" + path;
        readCities();
    }

    private void readCities()  {
        this.cities = new ArrayList<>();
        String[] currentRow;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(this.path)));
            int numOfCities = 0, count = 0;
            boolean isPoint = false;
            while((currentRow = bf.readLine().split(" "))[0] != null)

            if(currentRow[0].trim().equals("NODE_COORD_SECTION")){
                isPoint = true;
            }
            else if(currentRow[0].trim().equals("EOF")){
                if(count != numOfCities)
                    throw new Exception("ERROR LOADING CITIES");
                break;
            }
            else if(currentRow[0].trim().equals("DIMENSION")){
                numOfCities = Integer.parseInt(currentRow[2]);
            }
            else if(isPoint){
                count++;
                cities.add(new Point2D.Float(Float.parseFloat(currentRow[1].trim()),Float.parseFloat(currentRow[2].trim())));
            }


        } catch (Exception e) {
            e.printStackTrace();
            this.cities = new ArrayList<>();
        }
    }
    public static double getCitiesTraversalLength(List<Point2D> cities) {
        double length = 0;
        if(cities.size() == 0) return length;
        Point2D prev = cities.get(cities.size() - 1);
        for(int i = 0 ; i < cities.size() ; i++){
            length += cities.get(i).distance(prev);
            prev = cities.get(i);

        }
        return length;
    }
    @Override
    public double getCitiesTraversalLength() {

        double length = 0;
        if(this.cities.size() == 0) return length;
        Point2D prev = cities.get(cities.size() - 1);
        for(int i = 0 ; i < cities.size() ; i++){
            length += cities.get(i).distance(prev);
            prev = cities.get(i);

        }
        return length;
    }

    @Override
    public List<Point2D> generateRandomRoute(int startCity) {
        List<Point2D> tCities = new ArrayList<>();
        for(int i = 0 ; i < cities.size(); i++){
            tCities.add(cities.get(i));
        }
       List<Point2D> result = new ArrayList<>();
       Point2D prev = tCities.remove(startCity - 1);
       result.add(prev);
       while(tCities.size() > 0){
           double minDistance = Double.MAX_VALUE;
           int minPos = -1;
           for(int i = 0 ; i < tCities.size() ; i++){
               if(prev.distance(tCities.get(i)) <  minDistance){
                   minDistance = prev.distance(tCities.get(i));
                   minPos = i;
               }
           }
           if(minPos != -1){
               prev = tCities.remove(minPos);
               result.add(prev);

           }
       }
       this.cities = result;
       return result;
    }

    @Override
    public List<Point2D> getCities() {
        return cities;
    }
    @Override
    public void setCities(List<Point2D> cities) {
        this.cities = cities;
    }
}
    interface ICities {
        double getCitiesTraversalLength();
        List<Point2D> generateRandomRoute(int startCity);
        List<Point2D> getCities();
        void setCities(List<Point2D> cities);
    }
