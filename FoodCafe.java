import java.util.*;

public class FoodCafe {
    static Scanner sc = new Scanner(System.in); 	//Static scanner input
    static ListQueue productionLine = new ListQueue();// The cooking queue
    static int currentTime = 0;
    static int itemOrderNum = 0;
    static int count = -1;
    static int totalCust = 0;

    public static void main(String []args) {
        System.out.print("\n------------ SETUP SIMULATION ENVIRONMENT-------------\n");
        //input the simulation time
        System.out.print("Input simulation length (mins:)");
        int simLength = sc.nextInt();
        System.out.println("\n----------------- START SIMULATION -------------------");

        //Order process
        while (currentTime < simLength) {
            //input the food item number
            System.out.print("Order item [1-coffee,2-Soft Drink, 3-Hot Dog, 4-French fries, 0-Finish]:");
                int inputFood = inputData();
                ordering(inputFood);
        }
        //show the END OF SIMULATION
        System.out.println("\n----------------- END OF SIMULATION ------------------");
        System.out.println("Number of customer served: " + simLength + " minutes");
        System.out.println("Item ordered during the simulation : " + totalCust + " customer(s)");
        System.out.println("Item ordered during the simulation: " + itemOrderNum);
        System.out.println("Outstanding item at the end of simulation: " + productionLine.count());
        System.out.println("-----------------------------------------------------");
    }


    private static int inputData() {
        int inputFood = -1;
        try {
            inputFood = sc.nextInt();
            if (inputFood < 0 || inputFood > 4) {
                throw new InvalidRangeInputException();
            }
        }catch (InputMismatchException e) {
            System.out.println("Wrong input!");
            sc.next();
            return inputFood;
        }catch (InvalidRangeInputException e) {
            System.out.println("invalid choice!");
            return inputFood;
        }
        return inputFood;
    }


    private static void ordering (int inputFood) {
        try {
            if (inputFood == 0) {
                if (count >= 0) {
                    count = -1;
                    totalCust++;
                }
                currentTime++;

                // Find the Food that remainCookTime = 0 in the ListQueue, and remove it from ListQueue;
                Object a = productionLine.front();
                String frontItem = a.toString();
                if ((getFirstNum(frontItem)) == 0) {
                    productionLine.dequeue();
                }
                Object c = productionLine.front();
                String d = c.toString();
                int zeroItem = (getFirstNum(d) - 1);
                if (zeroItem == 0) {
                    productionLine.dequeue();
                } else if (zeroItem == 1) {
                    productionLine.dequeue();
                    String test = (d);
                    test = test.replace("2", "1");
                    productionLine.addToHead(test);//send back the item to ListQueue
                } else if (zeroItem == 2) {
                    productionLine.dequeue();
                    String test = (d);
                    test = test.replace("3", "2");
                    productionLine.addToHead(test);//send back the item to ListQueue
                }

                System.out.println("------------------------------------------------------");
                System.out.println("After " + currentTime + " minute(s):");
                System.out.println("Cooking queue# " + productionLine );
                System.out.println("-----------------------------------------------------");

            }// When the staff choose 1
            if (inputFood == 1) {
                FoodItem coffee = new FoodItem("Coffee", 1);
                productionLine.enqueue(coffee);
                itemOrderNum++;
                count++;
            } // When the staff choose 2
            if (inputFood == 2) {
                itemOrderNum++;
                count++;
            } // When the staff choose 3
            if (inputFood == 3) {
                FoodItem hotDog = new FoodItem("Hot Dog", 2);
                productionLine.enqueue(hotDog);
                itemOrderNum++;
                count++;
            } // When the staff choose 4
            if (inputFood == 4) {
                FoodItem frenchFries = new FoodItem("French fries", 3);
                productionLine.enqueue(frenchFries);
                itemOrderNum++;
                count++;
            }
        }catch (EmptyListException e){
            System.out.println("------------------------------------------------------");
            System.out.println("After " + currentTime + " minute(s):");
            System.out.println("Cooking queue# [" + productionLine + "]");
            System.out.println("-----------------------------------------------------");
        }
    }


    // Find the First Number of a String
    private static int getFirstNum(String s){
        if (s==null || s.length()==0){
            return -1;
        }
        char c = s.charAt(0);
        if (c>='0' && c<='9'){
            return c-'0';
        }else {
            return getFirstNum(s.substring(1));
        }
    }
}
