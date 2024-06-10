class Operator extends Person {
    int wage;
    Customer[] customers;
    int customerAmount; //I added this variable to simplify printing process

    void define_customers(Customer[] customerList) {
        int count=0;
        //find customer amount of this operator
        for(Customer c : customerList){
            if (c!=null && c.operator_ID==this.ID) count=count+1;
        }
        customerAmount=count;
        //set customers array length
        customers = new Customer[customerAmount];
        count=0;
        for(int i=0; i<customerList.length; i++)
            //if ID's match then copy customer into customers array
            if (customerList[i]!=null && customerList[i].operator_ID==this.ID){
                customers[count]=customerList[i];
                count++;
            };
    }
    void print_customers() {
        int no= 1;
        for(Customer c : customers){
            // If c is a RetailCustomer instance 
            if (c instanceof RetailCustomer){
                RetailCustomer rc = (RetailCustomer) c;
                System.out.println("Customer #" + no + " (a retail customer) :");
                rc.print_customer();
                no++;
            }
            // If c is a CorporateCustomer instance 
            else if (c instanceof CorporateCustomer){
                CorporateCustomer cc = (CorporateCustomer) c;
                System.out.println("Customer #" + no + " (a corporate customer) :");
                cc.print_customer();
                no++;
            }
        }
    }
    void print_operator(){
        System.out.println("*** Operator Screen ***");
        System.out.println("--------------------------------------------");
        System.out.println("Name & Surname: " + this.name + " " + this.surname);
        System.out.println("Address: " + this.address);
        System.out.println("Phone: " + this.phone);
        System.out.println("ID: " + this.ID);
        System.out.println("Wage: " + this.wage);
        System.out.println("--------------------------------------------");
        if(customerAmount>0)
            print_customers();
        else
            System.out.println("This operator doesn't have any customer.\n--------------------------------------------");
    }
}