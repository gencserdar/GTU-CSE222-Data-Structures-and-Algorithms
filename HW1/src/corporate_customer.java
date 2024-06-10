class CorporateCustomer extends Customer {
    String company_name;
    void print_customer(){
        System.out.println("Name & Surname: " + this.name + " " + this.surname);
        System.out.println("Address: " + this.address);
        System.out.println("Phone: " + this.phone);
        System.out.println("ID: " + this.ID);
        System.out.println("Operator ID: " + this.operator_ID);
        System.out.println("Company Name: " + this.company_name);
        if(orderAmount>0)
            print_orders();
        else
            System.out.println("This customer doesn't have any order.\n-------------------------------------------");
    }
}