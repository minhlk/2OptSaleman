import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TwoOpt implements ITwoOpt{
    List<Point2D> cities;

    @Override
    public void Execute(List<Point2D> cities) {
        List<Point2D> newRoute;
        double bestDistance = Length.routeLength(cities);
        int improve = 0;
        int iterations = 0;
        long comparisons = 0;
        double newDistance;
        int swap = 1;

        while(swap != 0){
            swap = 0;
            for (int i = 1; i < cities.size() - 2; i++) {
                for (int j = i + 1; j < cities.size() - 1; j++) {
                    comparisons++;
                    if ((cities.get(i).distance(cities.get(i - 1)) + cities.get(j + 1).distance(cities.get(j))) >=
                            (cities.get(i).distance(cities.get(j + 1)) + cities.get(i - 1).distance(cities.get(j)))) {

                        newRoute = Swap(cities,i,j);
                        newDistance = Cities.getCitiesTraversalLength(newRoute);
                        if(newDistance < bestDistance){
                            swap++;
                            improve++;
                            bestDistance = newDistance;
                            cities = newRoute;
                        }
                    }


                }

            }
            iterations++;

        }
        System.out.println("Total comparisons made: " + comparisons);
        System.out.println("Total improvements made: " + improve);
        System.out.println("Total iterations made: " + iterations);
        System.out.println("");
        this.cities = cities;

    }

    @Override
    public List<Point2D> Swap(List<Point2D> cities, int startCity, int endCity) {
        List<Point2D> result = new ArrayList<>();
        for(int i = 0 ; i < startCity ; i++){
            result.add(cities.get(i));
        }
        int count = 0;
        for(int i = startCity ; i <= endCity && i < cities.size(); i++){
            result.add(cities.get(endCity - count));
            count++;
        }
        for(int i = endCity + 1 ; i < cities.size() ; i++){
            result.add(cities.get(i));
        }

        return result;
    }

    public List<Point2D> getCities() {
        return cities;
    }



}
interface ITwoOpt{
    void Execute(List<Point2D> cities);
    List<Point2D> Swap(List<Point2D> cities, int startCity , int endCity);
}
