import java.util.*;

public class Main{
    static Scanner s=new Scanner(System.in);
    static String Admin_name ="Shan";
    static String Admin_Password="2910";
    static int Admin_attempt=1;
    static int money[]=new int[4];
    static int State = 1;
    static Main[] atm;
    static int Current_User = 0;

    // Object creaction For User
    public String User_Name, User_Pin;
    public int User_Balance = 0;
    public int User_attempt;
    public ArrayList<String> User_Statement;

    // Constructor to initilize the objects
    Main(String Name, String Pin, int Balance) {
        this.User_Name = Name;
        this.User_Pin = Pin;
        this.User_Balance = Balance;
        this.User_attempt = 1;
    }
    public static  void Adminlogin(){
        while(Admin_attempt<=3){
            System.out.print("\033[H\033[2J");
            System.out.println("Enter the Admin Id :" );
            String id=s.next();
            s.nextLine();
            System.out.println("Enter the password : ");
            String  pas=s.next();
            if(Admin_name.equals(id) && Admin_Password.equals(pas)){
                  Admin();
                  break;
            }
            else{
                Admin_attempt+=1;
            }
        }
        if(Admin_attempt==4){
            System.out.print("\033[H\033[2J");
            System.out.println("Your Account Has been Locked  :-)");
            main(null);
        }
    }
    public static void Admin(){
        int i=1;
        System.out.print("\033[H\033[2J");
        while(i!=0){
            System.out.println("Welcome to Admin \n 1.Deposit \n 2.Check Balance \n 3.Exit");
            int o=s.nextInt();
          switch(o){
            case 1:
                System.out.print("Enter 2000 count : ");
                money[0] += s.nextInt() * 2000;
                System.out.println();
                System.out.print("Enter 500 count :");
                money[1] += s.nextInt() * 500;
                System.out.println();
                System.out.print("Enter 200 count : ");
                money[2] += s.nextInt() * 200;
                System.out.println();
                System.out.print("Enter 100 count : ");
                money[3] += s.nextInt() * 100;
                System.out.println("\033[H\033[2J");
                break;
            case 2:
                System.out.println("2000 count : " + (money[0] / 2000) + " Amount Present : " + money[0]);
                System.out.println("500 count : " + (money[1] / 500) + " Amount Present : " + money[1]);
                System.out.println("200 count : " + (money[2] / 200) + " Amount Present : " + money[2]);
                System.out.println("100 count : " + (money[3] / 100) + " Amount Present : " + money[3]);
                System.out.println("Total Amount Present : " + (money[0] + money[1] + money[2] + money[3]));
                break;

            // Exit to the main menu
            case 3:
                main(null);
                i = 0;
                break;

            // None of the input matches
            default:
                System.out.println("Enter the valid case !");

            }
        }
    }
    public static void Userlogin(){
        int k=0;
        while (atm[Current_User].User_attempt <= 3 && k != 0) {
            System.out.print("\033[H\033[2J");
            System.out.print("\t Welcome User Panel \nEnter the User Id : ");
            String User_Id = s.next();
            s.nextLine();
            System.out.print("\nEnter the User Password : ");
            String User_Password = s.next();
            s.nextLine();

            // Checks the user id and Password
            for (int i = 0; i < 2; i++) {
                // System.out.println("-------");
                // System.out.println(atm[i].User_Name);
                if (atm[i].User_Name.equals(User_Id) && atm[i].User_Pin.equals(User_Password)) {
                    Current_User = i;
                    User();
                    k += 1;
                    break;
                }
            }
            atm[Current_User].User_attempt += 1;
        }

        // If the attemp is more than 3 the account has been locked :-)
        if (atm[Current_User].User_attempt == 4) {
            System.out.println("Your Account Has been Locked  :-)");
            main(null);
        }
    }
    public static void User(){
        int i = 1;
        while (i != 0) {
            System.out.println(
                    "\t Welcome User Panel \n 1 => Deposit Money \n 2 => Check Balance\n 3 => Widthdraw Money\n 4 => Mini Statement\n 5 => Change Pin \n 6 => Back to Main Menu");
            int option = s.nextInt();
            switch (option) {

                // Deposit money
                case 1:
                    System.out.print("Enter 2000 count : ");
                    int x = s.nextInt() * 2000;
                    atm[Current_User].User_Balance += x;
                    money[0] += x;
                    System.out.println();
                    System.out.print("Enter 500 count :");
                    x = s.nextInt() * 500;
                    atm[Current_User].User_Balance += x;
                    money[1] += x;
                    System.out.println();
                    System.out.print("Enter 200 count : ");
                    x = s.nextInt() * 200;
                    atm[Current_User].User_Balance += x;
                    money[2] += x;
                    System.out.println();
                    System.out.print("Enter 100 count : ");
                    x = s.nextInt() * 100;
                    atm[Current_User].User_Balance += x;
                    money[3] += x;
                    String date = java.time.LocalDateTime.now() + "---Deposit---" + atm[Current_User].User_Balance;
                    atm[Current_User].User_Statement.add(date);
                    System.out.println("\033[H\033[2J");
                    break;

                // Check Balance
                case 2:
                    System.out.println("User Name : " + atm[Current_User].User_Name);
                    System.out.println("User Balance :" + atm[Current_User].User_Balance);
                    break;

                // Widthdraw Money
                case 3:
                    System.out.print("\033[H\033[2J");
                    System.out.print("Enter the Amount to be WidthDrawn : ");
                    int Withdraw_Amount = s.nextInt();
                    if (Withdraw_Amount <= (money[0] + money[1] + money[2] + money[3])) {
                        if (Withdraw_Amount <= atm[Current_User].User_Balance) {
                            With_draw(Withdraw_Amount);
                        } else {
                            System.out.println("Insufficient Amount In your Account !");
                        }

                    } else {
                        System.out.println("InSufficient Amount in ATM !");
                    }
                    break;

                // Mini Statement
                case 4:
                    System.out.println("Mini Statement !");
                    for (int k = atm[Current_User].User_Statement.size() - 1; k >= 0; k--) {
                        System.out.println(atm[Current_User].User_Statement.get(k));
                    }
                    break;

                // Change Pasword
                case 5:
                    System.out.print("Enter the New Password : ");
                    String New_Pin = s.next();
                    s.nextLine();
                    atm[Current_User].User_Pin = New_Pin;
                    System.out.println("Pin has been Changed !");
                    break;

                // Exit to the main menu
                case 6:
                    main(null);
                    break;

                // Default Statement
                default:
                    System.out.println("Enter the valid case !");
            }
        }
    }
    public static void With_draw(int  Widthdraw_Amount){
        int temp = Widthdraw_Amount;
        int presentCount1[] = { money[0] / 2000, money[1] / 500, money[2] / 200, money[3] / 100 };
        int presentCount[] = new int[4];
        for (int i = 0; i < 4; i++) {
            presentCount[i] = presentCount1[i];
        }
        if (Widthdraw_Amount % 10 == 0 && Widthdraw_Amount % 100 == 0) {
            while (Widthdraw_Amount >= 2000 && presentCount[0] > 0) {
                Widthdraw_Amount -= 2000;
                presentCount[0]--;
            }
            while (Widthdraw_Amount >= 500 && presentCount[1] > 0) {
                Widthdraw_Amount -= 500;
                presentCount[1]--;
            }
            while (Widthdraw_Amount >= 200 && presentCount[2] > 0) {
                Widthdraw_Amount -= 200;
                presentCount[2]--;
            }
            while (Widthdraw_Amount >= 100 && presentCount[3] > 0) {
                Widthdraw_Amount -= 100;
                presentCount[3]--;
            }

            if (Widthdraw_Amount == 0) {
                money[0] = presentCount[0] * 2000;
                money[1] = presentCount[1] * 500;
                money[2] = presentCount[2] * 200;
                money[3] = presentCount[3] * 100;
                atm[Current_User].User_Balance -= temp;
                String date = java.time.LocalDateTime.now() + "---Widthdraw---" + atm[Current_User].User_Balance;
                atm[Current_User].User_Statement.add(date);
                System.out.println("Widthdraw Successfull !");
            } else {
                System.out.println("Sorry for the inconvinience !");
            }
        }
    }
    public static void main(String[] args){
        int i=1;
        if (State == 1) {
            atm = new Main[2];
            atm[0] = new Main("Vishnu", "1234", 5300);
            atm[0].User_Statement = new ArrayList<>();
            atm[1] = new Main("David", "1234", 8000);
            atm[1].User_Statement = new ArrayList<>();
            State = 0;
        }
        while(i!=0){
            System.out.println("Welcome to ATM \n 1.Admin \n 2.User \n 3.Exit");
            int y=s.nextInt();
            if(y==1){
                Adminlogin();
                break;
            }
            else if(y==2){
                Userlogin();
                break;
            }
            else if(y==3){
                i=0;
                System.out.print("Thank you for visiting");
                System.exit(0);
                break;
            }
            else{
                System.out.print("\033[H\033[2J");
                System.out.println("Please Enter the valid Case ! ");
                break;
            }
        }
    }
}