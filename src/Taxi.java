public class Taxi {
    public static void main(String[] args) {
        System.out.println(escolheTaxi("2.50", "1.00", "3.00", "0.75"));
    }
    public static String escolheTaxi(String tf1,String vqr1,String tf2,String vqr2) {
        // If the companies offer the same price to the trip, return "Tanto faz"
        if (tf1.equalsIgnoreCase(tf2) && vqr1.equalsIgnoreCase(vqr2)) return "Tanto faz";

        // Parsing String into Double to all variables
        Double fix1 = Double.valueOf(tf1);
        Double var1 = Double.valueOf(vqr1);
        Double fix2 = Double.valueOf(tf2);
        Double var2 = Double.valueOf(vqr2);

        // Calculating the minimum distance where prices are equal
        Double distance = distance(fix1, var1, fix2, var2);

        // If the distance is negative, one company has both a higher fixed rate and a higher cost per kilometer than the other.
        if(distance < 0) {
            //
            if (fix1 < fix2 && var1 < var2){
                return "Empresa 1";
            }
            else if (fix1 > fix2 && var1 > var2){
                return "Empresa 2";
            }
        }

        // Checking which company has the lowest price for the shortest trip. If one company is cheaper in the shortest trip, the other one is cheapest in the longest trip
        String company1 ="";
        String company2 ="";

        Double xpto = calc(fix1, var1, distance - 1);
        Double ypto = calc(fix2, var2, distance - 1);

        if (xpto > ypto){
            company1 = "2";
            company2 = "1";
        }
        else if (xpto < ypto){
            company1 = "1";
            company2 = "2";
        }

        return "Empresa " + company1 + " quando a distância < " + distance +
                ", Tanto faz quando a distância = " + distance +
                ", Empresa " + company2 + " quando a distância > " + distance;
    }

    // Method to calculate the price of the taxi trip
    private static Double calc(Double fix, Double var, Double distance){
        return fix + var * distance;
    }

    // Method to calculate the distance that equalize the price between booth companies
    private static Double distance(Double tf1,Double vqr1,Double tf2,Double vqr2){
        Double total = (tf2 - tf1) / (vqr1 - vqr2);
        String totalToString = String.format("%.2f", total).replace(',', '.');
        return Double.parseDouble(totalToString);
    }
}