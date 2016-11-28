import java.util.*;
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

    private LinkedList<Airport> portList;
//    private HashMap<String, LinkedList<Flight>> portFltListTable = new HashMap<>();
    private ArrayList< ArrayList<Flight> > portFltList = new ArrayList<>();
    private HashMap<String, Integer> symbolIndexTable = new HashMap<>();
    private String max = "9999";
    private Date maxDate = new Date(max, false);

  // constructor
    public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
        this.portList = portList;

        int index = 0;
        for(Airport port: portList){
            symbolIndexTable.put(port.portSymbol, index);
            index++;
            portFltList.add(new ArrayList<>());
        }

        for(Flight flt : fltList){
            portFltList.get(symbolIndexTable.get(flt.source)).add(flt);
        }
//        for(ArrayList<Flight> fltL : portFltList){
//            for(Flight flt : fltL){
//                flt.print();
//            }
//        }

    }

    private String getShortestPathSymbol(boolean[] SArray, PriorityQueue<SymbolTimePair> Q){
        while(true){
            SymbolTimePair temp = Q.poll();
            if(SArray[symbolIndexTable.get(temp.symbol)]) continue;
            if(temp.time.equals(maxDate)) return "";
            return temp.symbol;
        }
    }

    public Itinerary Schedule(String startSymbol, String endSymbol, String departure){
//        Dijkstra algorithm implementation
        try {
            boolean[] SArray = new boolean[portList.size()];
//            HashMap<String, Date> portTimeMemoizeTable = new HashMap<>();
            Date[] portTimeMemoizeArray = new Date[portList.size()];
//            HashMap<String, Flight> portPathTable = new HashMap<>();
//            ArrayList<Flight> portPathList = new ArrayList<>();
            Flight[] portPathArray = new Flight[portList.size()];
            PriorityQueue<SymbolTimePair> Q = new PriorityQueue<>();

            int index = 0;
            for (Airport port : portList) {
                SArray[index] = false;
                portTimeMemoizeArray[index] = maxDate;
                index++;
                // init priority Q
                Q.offer(new SymbolTimePair(port.portSymbol, maxDate));
            }

            String currentSymbol = startSymbol;
            Date canDepartureTime = new Date(departure, false);
            portTimeMemoizeArray[symbolIndexTable.get(currentSymbol)] = new Date(departure, false);

            SArray[symbolIndexTable.get(currentSymbol)] = true;

            while (!SArray[symbolIndexTable.get(endSymbol)]) {
                //            System.out.println(currentSymbol);
                for (Flight flt : portFltList.get(symbolIndexTable.get(currentSymbol))) {
                    Date deptTimeIn24Hours = new Date(flt.deptTime);
                    Date arrivTimeIn24Hours = new Date(flt.arrivTime, deptTimeIn24Hours);

                    for (; deptTimeIn24Hours.compareTo(canDepartureTime) < 0; deptTimeIn24Hours.plus(1440)) {
                        // 1440 minute = 1 day
                        //                    System.out.print(startSymbol + "/");
                        //                    System.out.print(endSymbol + " : ");
                        //                    System.out.print(arrivTimeIn24Hours.getString());
                        //                    System.out.print(" -> ");
                        arrivTimeIn24Hours.plus(1440);
                        //                    System.out.println(arrivTimeIn24Hours.getString());
                    }
                    //                System.out.print(flt.source + "/");
                    //                System.out.print(flt.destination + " : ");
                    //                System.out.print(deptTimeIn24Hours.getString());
                    //                System.out.print(" -> ");
                    //                System.out.println(arrivTimeIn24Hours.getString());

                    if (arrivTimeIn24Hours.compareTo(portTimeMemoizeArray[symbolIndexTable.get(flt.destination)]) < 0) {
//                        System.out.print(flt.source + " ");
//                        System.out.print(flt.destination + "/");
//                        System.out.print(canDepartureTime.getString() + ":");
//                        System.out.print(deptTimeIn24Hours.getString() + ":");
//                        System.out.println(arrivTimeIn24Hours.getString());

                        portTimeMemoizeArray[symbolIndexTable.get(flt.destination)] = arrivTimeIn24Hours;
                        // origin
                        Q.offer(new SymbolTimePair(flt.destination, arrivTimeIn24Hours));
                        portPathArray[symbolIndexTable.get(flt.destination)] = flt;
                    }

                }

                //            System.out.println(portTimeMemoizeTable);
                currentSymbol = getShortestPathSymbol(SArray, Q);
                if (currentSymbol.equals("")) {
                    System.out.println("No Connection Found!");
                    return new Itinerary(new LinkedList<>());
                }
                Date departureTime = portTimeMemoizeArray[symbolIndexTable.get(currentSymbol)];
//                System.out.println("departure : " + departureTime.getString());
                canDepartureTime = departureTime.plus(portList.get(symbolIndexTable.get(currentSymbol)).connectTimeInDate);
//                System.out.println("canDeparture : " + canDepartureTime.getString());
                SArray[symbolIndexTable.get(currentSymbol)] = true;
//                for(int i=0; i<portList.size(); i++){
//                    System.out.print(SArray[i] + " ");
//                }
//                System.out.println();

            }
            //        System.out.println(S);

            LinkedList<Flight> tempList = new LinkedList<>();
            Flight flt = portPathArray[symbolIndexTable.get(endSymbol)];
            while(true){
//                flt.print();
                tempList.add(flt);
                if(flt.source.equals(startSymbol)) break;
                flt = portPathArray[symbolIndexTable.get(flt.source)];
            }
            return new Itinerary(tempList);
        } catch(NullPointerException e) {
            return new Itinerary(new LinkedList<>());
        }


    }

}

