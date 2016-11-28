// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight
{
    public String source;
    public String destination;
    public String deptTime;
    public String arrivTime;

  // constructor
    public Flight(String src, String dest, String stime, String dtime) {
        source = src;
        destination = dest;
        deptTime = stime;
        arrivTime = dtime;
    }

    public void print() {
        System.out.print(source);
        System.out.print("->");
        System.out.print(destination);
        System.out.print(":");
        System.out.print(deptTime);
        System.out.print(" ");
        System.out.println(arrivTime);
    }

}
