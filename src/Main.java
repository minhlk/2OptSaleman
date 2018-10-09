public class Main {

public static void main(String[] args){
        Timer  timer = new Timer();
        Cities city = new Cities("rl5915.tsp");
        System.out.println("Initial cities length : " + city.getCitiesTraversalLength());
        System.out.println("Initial cities time taken : " + timer.getTimeTaken());
        //choose start city is 1
        city.generateRandomRoute(1);
        System.out.println("Optimize routes length : " + city.getCitiesTraversalLength());
        System.out.println("Optimize routes time taken : " + timer.getTimeTaken());
        System.out.println("-----------------");
        TwoOpt twoOpt = new TwoOpt();
        twoOpt.Execute(city.getCities());
        System.out.println("TwoOpt routes length : " + Cities.getCitiesTraversalLength(twoOpt.getCities()));
        System.out.println("TwoOpt routes time taken : " + timer.getTimeTaken());

    }
}
