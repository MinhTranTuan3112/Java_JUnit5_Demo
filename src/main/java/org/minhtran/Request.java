package org.minhtran;

import java.util.HashMap;
import java.util.Map;

public class Request {
    public String Cover;
    public boolean HasWindScreenRepair;
    public int NumOfAccidents;
    public double TotalMileage;
    public double EstimateValue;
    public String ParkingLocation;
    public double Premium = 0;
    public double Discount;

    public Request() {
    }

    public Request(String cover, boolean hasWindScreenRepair, int numOfAccidents, double totalMileage, double estimateValue, String parkingLocation) {
        Cover = cover;
        HasWindScreenRepair = hasWindScreenRepair;
        NumOfAccidents = numOfAccidents;
        TotalMileage = totalMileage;
        EstimateValue = estimateValue;
        ParkingLocation = parkingLocation;
    }

    @Override
    public String toString() {
        return "Request{" +
                "Cover='" + Cover + '\'' +
                ", HasWindScreenRepair=" + HasWindScreenRepair +
                ", NumOfAccidents=" + NumOfAccidents +
                ", TotalMileage=" + TotalMileage +
                ", EstimateValue=" + EstimateValue +
                ", ParkingLocation='" + ParkingLocation + '\'' +
                '}';
    }

    private final Map<String, Double> PremiumMap = new HashMap<String, Double>() {{
        put("No cover", 0.01);
        put("Roadside", 0.02);
        put("At home", 0.03);
        put("European", 0.04);
    }};

    public void calculatePremium() {
        if (EstimateValue < 100) {
            return;
        }
        try {
            if (HasWindScreenRepair) {
                this.Premium += 30;
            }
            this.Premium = Premium + PremiumMap.get(Cover) * Premium;
            if (this.NumOfAccidents == 0) {
                Discount += 0.3;
            }
            if (TotalMileage > 5000) {
                Premium += 50;
            }
            if (ParkingLocation.equalsIgnoreCase("Public Place")) {
                Premium += 30;
            }
            Premium = Premium - Premium * Discount;
        } catch (Exception ex) {
            System.out.println("Calculate premium error: " + ex.getMessage());
        }
    }
}
