import java.io.File;
import java.util.Scanner;

public class CustomerTrackingSystem {
    //current indexes of orders, retailCustomers, corporateCustomers, operators arrays
    private static int orderIndex = 0, rcIndex =0, ccIndex=0, opIndex=0;
    private static int isAllDigits(String str) {
        // Expression to match digits only
        String regex = "\\d+";
        return (str.matches(regex)) ? 1 : 0; 
    }
    private static int biggerThanIntMax(String input) {
        String maxValueStr = String.valueOf(Integer.MAX_VALUE);
        
        if (input.length() > maxValueStr.length()) {
            return 1; // The input has more digits, so it must be greater
        } else if (input.length() < maxValueStr.length()) {
            return 0; // The input has less digits, so it must be smaller
        } else {
            // Compare each digit
            for (int i = 0; i < input.length(); i++) {
                int inputDigit = Character.getNumericValue(input.charAt(i));
                int maxValueDigit = Character.getNumericValue(maxValueStr.charAt(i));

                if (inputDigit > maxValueDigit) {
                    return 1;
                } else if (inputDigit < maxValueDigit) {
                    return 0;
                }
                // If digits are equal, continue to the next digit
            }
            // If all digits are equal, the input is equal to Integer.MAX_VALUE
            return 0;
        }
    }
    private static void readFile(Scanner fileScanner, Order[] orders, RetailCustomer[] retailCustomers, CorporateCustomer[] corporateCustomers, Operator[] operators){
        try {            
            while (fileScanner.hasNextLine()) {
                // For each type semicolon amount must be fixed
                int semicolonAmount=0;
                String line = fileScanner.nextLine();
                String[] data = line.split(";");
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ';') {
                        semicolonAmount=semicolonAmount+1;
                    }
                }
                String type = data[0]; // First string which determines the type
                int valid =1; // Flag to determine if the string/integer is valid
                if ("order".equals(type)){
                    // For orders: there must be 6 data, 5 semicolon, none of the datas can be null.
                    // 2nd, 3rd, 4th, and 5th data strings should correspond with integer values so these strings must not have any nondigit character
                    // and these strings' numeric value must be smaller than integer.MAX_VALUE
                    if(data.length==6 && semicolonAmount==5 && data[1]!=null && data[2]!=null && data[3]!=null && data[4]!=null && data[5]!=null
                        && isAllDigits(data[2])==1 && isAllDigits(data[3])==1 && isAllDigits(data[4])==1 && isAllDigits(data[5])==1 
                        && biggerThanIntMax(data[2])==0 && biggerThanIntMax(data[3])==0 && biggerThanIntMax(data[4])==0 && biggerThanIntMax(data[5])==0){
                        //create new order object to push it into orders array later(only if its valid)
                        Order or= new Order();
                        or.product_name = data[1];
                        or.count = Integer.parseInt(data[2]);
                        or.total_price = Integer.parseInt(data[3]);
                        or.status = Integer.parseInt(data[4]);
                        or.customer_ID= Integer.parseInt(data[5]);
                        //if any of the strings has less than 1 character or any of the integer values except status is smaller than 1, then this object is invalid
                        if (or.product_name.length()<1 ||or.status>3 || or.count<1 || or.total_price<1 || or.status<0|| or.customer_ID<1) valid= 0;
                        if(valid==1){
                            orders[orderIndex] =or;
                            orderIndex++;
                        }
                    }
                }
                else if ("retail_customer".equals(type)){
                    // For retail customers: there must be 7 data, 6 semicolon, none of the datas can be null.
                    // 5th and 6th data strings should correspond with integer values so these strings must not have any nondigit character
                    // and these strings' numeric value must be smaller than integer.MAX_VALUE
                    if(data.length==7 && semicolonAmount==6 && data[1]!=null && data[2]!=null && data[3]!=null && data[4]!=null && data[5]!=null && data[6]!=null
                        && isAllDigits(data[5])==1 && isAllDigits(data[6])==1 && biggerThanIntMax(data[5])==0 && biggerThanIntMax(data[6])==0){
                        RetailCustomer rc= new RetailCustomer();
                        rc.name = data[1];
                        rc.surname = data[2];
                        rc.address = data[3];
                        rc.phone = data[4];
                        rc.ID= Integer.parseInt(data[5]);
                        rc.operator_ID = Integer.parseInt(data[6]);
                        // Check if an operator or customer with rc.ID is already defined
                        // If so then flag the object as invalid
                        for(CorporateCustomer cc: corporateCustomers){
                            if(cc!=null && rc.ID == cc.ID) valid=0;
                        }
                        for(RetailCustomer r: retailCustomers){
                            if(r!=null && rc.ID == r.ID) valid=0;
                        }
                        for(Operator op: operators){
                            if(op!=null && rc.ID == op.ID) valid=0;
                        }
                        //if any of the strings has less than 1 character or any of the integer values is smaller than 1, then this object is invalid
                        if (rc.name.length()<1 || rc.surname.length()<1 || rc.address.length()<1 || rc.phone.length()<1 || rc.ID<1 || rc.operator_ID<1) valid = 0;
                        if(valid==1){
                            retailCustomers[rcIndex]=rc;
                            rcIndex++;
                        }
                    }
                }
                //Same as retail customer, only difference is an extra string is added
                else if ("corporate_customer".equals(type)){
                    if(data.length==8 && semicolonAmount==7 && data[1]!=null && data[2]!=null && data[3]!=null && data[4]!=null && data[5]!=null && data[6]!=null && data[7]!=null
                        && isAllDigits(data[5])==1 && isAllDigits(data[6])==1 && biggerThanIntMax(data[5])==0 && biggerThanIntMax(data[6])==0){
                        CorporateCustomer cc= new CorporateCustomer();
                        cc.name = data[1];
                        cc.surname = data[2];
                        cc.address = data[3];
                        cc.phone = data[4];
                        cc.ID= Integer.parseInt(data[5]);
                        cc.operator_ID = Integer.parseInt(data[6]);
                        cc.company_name =data[7];
                        // Check if an operator or customer with cc.ID is already defined
                        for(CorporateCustomer c: corporateCustomers){
                            if(c!=null && cc.ID == c.ID) valid=0;
                        }
                        for(RetailCustomer rc: retailCustomers){
                            if(rc!=null && cc.ID == rc.ID) valid=0;
                        }
                        for(Operator op: operators){
                            if(op!=null && cc.ID == op.ID) valid=0;
                        }
                        if (cc.name.length()<1 || cc.surname.length()<1 || cc.address.length()<1 || cc.phone.length()<1|| cc.company_name.length()<1 || cc.ID<1 || cc.operator_ID<1) valid = 0;
                        if(valid==1){
                            corporateCustomers[ccIndex]=cc;
                            ccIndex++;
                        }
                    }
                }
                else  if ("operator".equals(type)){
                    if(data.length==7 && semicolonAmount==6 && data[1]!=null && data[2]!=null && data[3]!=null && data[4]!=null && data[5]!=null && data[6]!=null
                        && isAllDigits(data[5])==1 && isAllDigits(data[6])==1 && biggerThanIntMax(data[5])==0 && biggerThanIntMax(data[6])==0){
                        Operator operator = new Operator();
                        operator.name = data[1];
                        operator.surname = data[2];
                        operator.address = data[3];
                        operator.phone = data[4];
                        operator.ID= Integer.parseInt(data[5]);
                        operator.wage = Integer.parseInt(data[6]);
                        // Check if an operator or customer with operator.ID is already defined
                        for(CorporateCustomer cc: corporateCustomers){
                            if(cc!=null && operator.ID == cc.ID) valid=0;
                        }
                        for(RetailCustomer rc: retailCustomers){
                            if(rc!=null && operator.ID == rc.ID) valid=0;
                        }
                        for(Operator op: operators){
                            if(op!=null && operator.ID == op.ID) valid=0;
                        }
                        if (operator.name.length()<1 || operator.surname.length()<1 || operator.address.length()<1 || operator.phone.length()<1 || operator.ID<1 || operator.wage<1) valid = 0;
                        if(valid==1){
                            operators[opIndex]=operator;
                            opIndex++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    private static void findAndPrint(int inputID, Operator[] operators, RetailCustomer[] retailCustomers, CorporateCustomer[] corporateCustomers){
        int found = 0; // Flag to determine if the operator/customer with inputID is found
        if(found==0){ //If not found 
            for(RetailCustomer rc: retailCustomers){
                if (rc!= null && found==0 && rc.ID==inputID){
                    // A customer with this ID has been found
                    System.out.println("*** Customer Screen ***\n--------------------------------------------");
                    rc.print_customer();
                    found =1; //There can be only one customer/operator with a specific ID so set flag to 1
                }
            }
        }
        if(found==0){ //If not found then check corporate customers
            for(CorporateCustomer cc: corporateCustomers){
                if (cc!= null && found==0 && cc.ID==inputID){
                    System.out.println("*** Customer Screen ***\n--------------------------------------------");
                    cc.print_customer();
                    found =1;
                }
            }
        }
        if(found==0){ //If still not found then check oporetors
            for(Operator op : operators){
                if (op!= null && found==0 && op.ID==inputID){
                    op.print_operator();
                    found = 1;
                }
            }
        }
        if(found==0){ //Finally if the flag is still zero then there is no operator/customer with this ID
            System.out.println("No operator/customer was found with ID " + inputID + ". Please try again.");
        }
    }
    public static void main(String[] args) {
        int MAX_SIZE = 100;
        int j=0;

        // Arrays to store data
        Operator[] operators = new Operator[MAX_SIZE];
        RetailCustomer[] retailCustomers = new RetailCustomer[MAX_SIZE];
        CorporateCustomer[] corporateCustomers = new CorporateCustomer[MAX_SIZE];
        Order[] orders = new Order[MAX_SIZE];
        try {
            Scanner fileScanner = new Scanner(new File("content.txt"));
    
            // Read data from the text file
            readFile(fileScanner, orders, retailCustomers, corporateCustomers, operators);
    
            // After reading data, define orders and customers for each customer and operator
            for(RetailCustomer rc: retailCustomers){
                if(rc!=null) rc.define_orders(orders);
            }
            for(CorporateCustomer cc: corporateCustomers){
                if(cc!=null) cc.define_orders(orders);
            }
            //operators[i].defineCustomers(Customer[] c) takes 1 parameter which is a Customer array
            //so retailCustomers and corporateCustomers arrays must be copied to an array with type of their superClass 
            Customer[] customersList = new Customer[retailCustomers.length+corporateCustomers.length];
            for(int i=0; i<retailCustomers.length; i++)
                if(retailCustomers[i]!= null){
                    customersList[j]=retailCustomers[i];
                    j++;
                }
            for(int i=0; i<corporateCustomers.length ; i++)
                if(corporateCustomers[i]!= null){
                    customersList[j]=corporateCustomers[i];
                    j++;
                }
            for(Operator op: operators){
                if(op!=null) op.define_customers(customersList);
            }
    
            try{
                // Take input from the user
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter your ID...");
                int inputID = scanner.nextInt();
                //find operator/customer with the ID that matches with inputID and print its details
                findAndPrint(inputID, operators, retailCustomers, corporateCustomers);
                scanner.close();
                fileScanner.close();
            } catch (Exception e){
                System.out.println("Invalid input entered. Terminating..");
            }
    
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}