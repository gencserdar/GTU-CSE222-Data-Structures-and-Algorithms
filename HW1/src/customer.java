class Customer extends Person {
    Order[] orders;
    int operator_ID;
    int orderAmount; //I added this variable to simplify printing process

    void define_orders(Order[] orderList) {
        int count=0;
        //find order amount of this customer
        for(Order o : orderList){
            if (o!=null && o.customer_ID==this.ID) count=count+1;
        }
        orderAmount=count;
        //set orders array length
        orders = new Order[orderAmount];
        count=0;
        for(int i=0; i<orderList.length; i++)
            //if ID's match then copy order into orders array
            if (orderList[i]!=null && orderList[i].customer_ID==this.ID){
                orders[count]=orderList[i];
                count++;
            };
    }
    void print_customer(){
        System.out.println("Name & Surname: " + this.name + " " + this.surname);
        System.out.println("Address: " + this.address);
        System.out.println("Phone: " + this.phone);
        System.out.println("ID: " + this.ID);
        System.out.println("Operator ID: " + this.operator_ID);
        if(orderAmount>0)
            print_orders();
        else
            System.out.println("This customer doesn't have any order.\n-------------------------------------------");

    }
    void print_orders(){
        int no= 1;
        for(Order or : orders){
            System.out.print("Order #" + no + " => ");
            or.print_order();
            no++;
        }
        System.out.println("-------------------------------------------");
    }
}