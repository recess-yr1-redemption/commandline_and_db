import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Command {

    public void register() throws FileNotFoundException, IOException {
        Scanner c = new Scanner(System.in);
        System.out.println(" ");

        System.out.println("Enter Username");
        String name = c.nextLine();
        System.out.println("--------------------------");
        System.out.println("  ");

        System.out.println("Enter Password");
        String password = c.nextLine();
        System.out.println("--------------------------");
        System.out.println("  ");

        System.out.println("Enter Product");
        String product = c.nextLine();
        System.out.println("--------------------------");
        System.out.println("  ");

        System.out.println("Enter Date of Birth");
        String date_of_birth = c.nextLine();
        System.out.println("--------------------------");
        System.out.println("  ");


        File f = new File("participants.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        Object[] lines = br.lines().toArray();
         
        int exist = 0;
        int id = 0;
        for(int i=0; i<lines.length; i++){
            String line = lines[i].toString().trim();
            String[] column =  line.split(",");
            id = Integer.parseInt(column[0]) +1;

            if(name.equalsIgnoreCase(column[1])){
                exist = 1;
                System.out.println("----------------------------------");
                System.out.println("User alredy Exist, Try other names");
                System.out.println("----------------------------------");
            }
        }
        br.close();

        if(exist == 0){

            BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
            PrintWriter pw = new PrintWriter(bw);
            pw.println(id+","+name+","+password+","+product+","+date_of_birth);
            pw.flush();
            pw.close();
            bw.close();

            System.out.println("Registration successfull");
            System.out.println("Here are your details");
            System.out.println(" ");
            System.out.println("------------------------------------------------");
            System.out.println("[id | name | password | product | date_of_birth]");
            System.out.println("------------------------------------------------");
            System.out.println("[ "+id+" | "+name+" |   "+password+" |     "+product+" |        "+date_of_birth+" ] ");
            System.out.println("---------------------------------------------------");
            System.out.println(" ");
        }
        c.close();
    }

    public void updateDesc() throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("  ");

        System.out.println("Enter Username Please");
        String uname = sc.nextLine();
        System.out.println("--------------------------");

        System.out.println("  ");
        System.out.println("Enter Product Name");
        String product_name = sc.nextLine();
        System.out.println("--------------------------");

        System.out.println(" ");
        System.out.println("Enter the Product Description");
        String description = sc.nextLine();
        System.out.println("--------------------------");
        
        System.out.println(" ");
        System.out.println("Enter the Product Price");
        int price = sc.nextInt();
        System.out.println("--------------------------");
        
        System.out.println(" ");
        System.out.println("Enter The total number of Products");
        int total = sc.nextInt();
        System.out.println("--------------------------");

        

        File k = new File("participants.txt");
        BufferedReader kr = new BufferedReader(new FileReader(k));
        Object[] obj = kr.lines().toArray();

        int available = 0;
        int track = 0;
        int pro_exist = 0;
        int proid = 0;

        for(int i=0; i<obj.length; i++){
            String single = obj[i].toString().trim();
            String[] row =  single.split(",");
            proid = Integer.parseInt(row[0]) + 1;

            if(product_name.equalsIgnoreCase(row[3]) && uname.equalsIgnoreCase(row[1])) {
                String user = row[1];
                File pr = new File("products.txt");

                BufferedReader ur = new BufferedReader(new FileReader(pr));
                Object[] ob = ur.lines().toArray();

                for(int j=0; j<ob.length; j++){
                    String li = ob[j].toString().trim();
                    String[] order =  li.split(",");
                    proid = Integer.parseInt(order[0]) + 1;

                    if(product_name.equalsIgnoreCase(order[2])){
                        pro_exist = 1;
                        track = 1;
                        System.out.println("------------------------------------------------------------");
                        System.out.println("Product already Exist, You can only post one kind of product");
                        System.out.println("------------------------------------------------------------");
                    }

                }
                ur.close();

                if(pro_exist == 0){

                    BufferedWriter wr = new BufferedWriter(new FileWriter(pr,true));
                    PrintWriter pw = new PrintWriter(wr);
                    pw.println(proid+","+user+","+product_name+","+total+","+price+","+description);
                    pw.flush();
                    pw.close();
        
                    available = 1;
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Product Description Updated Successfully in products.txt");
                    System.out.println("---------------------------------------------------------"); 
                    System.out.println("------------------------------------------------");
                    System.out.println("[id | name | product | total | price | description ]");
                    System.out.println("------------------------------------------------");
                    System.out.println("[ "+proid+" | "+user+" |   "+product_name+"   | "+total+"  | "+price+"  |  "+description+" ]");
                    System.out.println("---------------------------------------------------");
                    System.out.println(" ");
                }    
            }
        }
        kr.close();
        sc.close();

        if(available==0 && track==0){
            System.out.println("-------------------------------------------------");
            System.out.println("Either Product Doesn't Exist Or Username Is Wrong");
            System.out.println("-------------------------------------------------");
        }
        
    }

    public void checkRank() throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Please Enter Username");
        String part = in.nextLine();
        System.out.println("------------------------------");

        System.out.println(" ");
        System.out.println("Enter 1 to view previous performances");
        System.out.println("Enter 2 to view request current performances");
        System.out.print("Enter Choice: ");
        int choice = in.nextInt();

        //counting total number of perticipants
        File k = new File("participants.txt");
        BufferedReader krr = new BufferedReader(new FileReader(k));
        Object[] obj2 = krr.lines().toArray();

        int counter = 0;
        for(int count=0; count<obj2.length;count++){
            counter++;
            //System.out.println(counter);
        }
        krr.close();


        File per = new File("performance.txt");
        BufferedReader read = new BufferedReader(new FileReader(per));
        Object[] list = read.lines().toArray();

        int avail = 0;
        for(int i=0; i<list.length; i++){
            String perf = list[i].toString().trim();
            String[] ranks =  perf.split(",");
            
            if(part.equalsIgnoreCase(ranks[1]) && choice == 1){
                String uid = ranks[0];
                int position = Integer.parseInt(ranks[2]);
                int points = Integer.parseInt(ranks[3]);
                int goods = Integer.parseInt(ranks[4]);
                int returns = Integer.parseInt(ranks[5]);
                String date = ranks[6];

                File rp = new File("response.txt");
                BufferedWriter bf = new BufferedWriter(new FileWriter(rp,true));
                PrintWriter write = new PrintWriter(bf);
                write.println(part+","+java.time.LocalTime.now()+", Resquest Seen");
                write.flush();
                write.close();
                
                avail = 1;
                System.out.println("---------------------------------------------------------------------");
                System.out.println("[ UserID | UserName | Rank | Points | Products Left | Return Buyers | Date     ]");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("[ "+uid+"         "+part+"     "+position+"/"+counter+"     "+points+"          "+goods+"             "+returns+"            "+date+" ]");
                System.out.println("-----------------------------------------------------------------------");

                
            }
        }
        read.close();
        in.close();

        if(avail==0 && choice == 2){
            File rq = new File("request.txt");
            BufferedWriter buff = new BufferedWriter(new FileWriter(rq,true));
            PrintWriter pt = new PrintWriter(buff);
            pt.println(part+","+java.time.LocalTime.now()+", Resquesting for ranks");
            pt.flush();
            pt.close();

            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("Hello "+part+", These are the possible reasons we have for not having your Performance Results");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("1. You could have entered wrong username, this is because we have no records with this username");
            System.out.println(" ");
            System.out.println("2. If you initially registered, then wait for less than 5 minutes as we still contacting the server for your performances");
            System.out.println("Hopefully, within 5 mins your results shall be out and check again withe choice 1");
            System.out.println(" ");
            System.out.println("3. If after 5 minutes you can't access your results, This shows your not registered initally");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Thank you, we are committed to serving you always");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
        }
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file1 = new File("participants.txt");
        File file2 = new File("products.txt");
        File file3 = new File("performance.txt");
        File file4 = new File("request.txt");
        File file5 = new File("response.txt");

        if(file1.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as participants.txt"));
            System.out.println("---------------------------------------");
        }else {
            file1.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as participant.txt");
            System.out.println("------------------------------------------------------");
        }

        if(file2.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as products.txt"));
            System.out.println("-----------------------------------");
        }else {
            file2.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as products.txt");
            System.out.println("-------------------------------------------------");
        }

        if(file3.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as performance.txt"));
            System.out.println("--------------------------------------");
        }else {
            file3.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as performance.txt");
            System.out.println("-----------------------------------------------------");
        }

        if(file4.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as request.txt"));
            System.out.println("--------------------------------------");
        }else {
            file4.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as request.txt");
            System.out.println("-----------------------------------------------------");
        }

        if(file5.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as respose.txt"));
            System.out.println("--------------------------------------");
        }else {
            file5.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as response.txt");
            System.out.println("-----------------------------------------------------");
        }

        System.out.println("Please follow this menu in trying work with the command-line");
        System.out.println(" ");
        System.out.println("Register");
        System.out.println("Post_product");
        System.out.println("Performance");
        System.out.println("-------------------------------------------------------------");
        System.out.println("  ");
        

        Command arg = new Command();

        Scanner n = new Scanner(System.in);
        String comand = null;
        System.out.println("Enter the menu item of choice!");

        while(n.hasNextLine()){
            //Enter Keyboard
            comand = n.nextLine();

            if(comand.equalsIgnoreCase("Register")){
                arg.register();

            }else if(comand.equalsIgnoreCase("Post_product")){
                arg.updateDesc();

            }else if(comand.equalsIgnoreCase("Performance")){
                arg.checkRank();

            }else{
                System.out.println("Invalid Command!");
                System.out.println("-----------------------");
                System.out.println(" ");
                System.out.println("Please follow this menu in trying work with the command-line");
                System.out.println(" ");
                System.out.println("Register");
                System.out.println("Post_product");
                System.out.println("Performance");
                System.out.println("-------------------------------------------------------------");
                System.out.println("  ");

                System.out.println("Enter the menu item of choice!");
                
            }


        }
        n.close();
        
        
    }
    
}
