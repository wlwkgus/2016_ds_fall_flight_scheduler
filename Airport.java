// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport
{
    public String portSymbol;
    public Date connectTimeInDate;

    public Airport(String port, String connectTime) {
        portSymbol = port;
        connectTimeInDate = new Date(connectTime);


    }	// constructor

    public void print() {}

}
