class Order {
    String product_name;
    int count;
    int total_price;
    int status;
    int customer_ID;
    void print_order(){
        System.out.print("Product Name: " + product_name + " - Count: " + count + " - Total price: " + total_price + " - Status: " + 
                        (status==0 ?"Initialized." : status==1 ?"Processing." : status==2 ?"Completed." : status==3 ?"Cancelled." : "Unknown: " + status) + "\n");
    }
}