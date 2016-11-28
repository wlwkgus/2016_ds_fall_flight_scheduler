// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.LinkedList;

public class Itinerary
{
    private LinkedList<Flight> path;
    private boolean isFound;
  // constructor
    Itinerary(LinkedList<Flight> path) {
        if(path.size() > 0){
            this.path = path;
            this.isFound = true;
        } else {
            this.isFound = false;
        }

    }

    public boolean isFound() {
        return isFound;
    }

    public void print() {
        if(!isFound){
            System.out.println("No Flight Schedule Found.");
            return;
        }
        Flight flt;
        for(int i=path.size()-1; i>=0; i--){
            flt = path.get(i);
            System.out.print("[");
            System.out.print(flt.source);
            System.out.print("->");
            System.out.print(flt.destination);
            System.out.print(":");
            System.out.print(flt.deptTime);
            System.out.print("->");
            System.out.print(flt.arrivTime);
            System.out.print("]");
        }
        System.out.println();
    }

}
