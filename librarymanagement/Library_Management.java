package librarymanagement;

import java.util.*;

// Main Class
class Library_Management {
    public static void main(String[] args) throws Exception {
        Bookstore bs = new Bookstore(); // Library Class Object.
        Credentials cr = new Credentials(); // Credentials Class Object.

        Scanner sc = new Scanner(System.in);
        System.out.println("*********🙏🙏🙏🙏🙏🙏🙏🙏**********");
        System.out.println("Welcome to My Library 📚🙏");
        System.out.println("Please give acknolwdgement SignIn or Signup.......Typedown👇");
        String Acknowledgement = sc.nextLine().toLowerCase(); // Acknowledegement for whether signin or signup.
        int uid;
        String uname;
        String upwd;
        String re_enter;

        // ---Logic for SIGNIN---------
        String response = " ";
        if (Acknowledgement.equals("signin")) {
            System.out.println("Admin account or Member account: ");
            String type_of_account = sc.nextLine();
            if (type_of_account.equals("admin")) {
                for (int i = 0; i < 3; i++) {
                    System.out.print("Enter the passkey:  ");
                    String passkey = sc.nextLine();
                    if (passkey.equals("#*J@n@kiR@m_Libr@ry*#")) {
                        for (int j = 1; i <= 5; i++) {
                            System.out.println("Sucessfully Entered in to admin account.........");
                            System.out.print("Enter the User_id:-  ");
                            uid = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter the username:-  ");
                            uname = sc.nextLine();
                            System.out.print("Enter the password:-  ");
                            upwd = sc.nextLine();
                            if (cr.getcredentials(uid, uname, upwd)) {
                                System.out.println("-----------------Login Successful🔓------------------");
                                System.out.print("Do you wanna access my Books, Enter Yes or No: ");
                                response = sc.nextLine().toLowerCase();
                                if (response.equals("yes")) {
                                    System.out.print(
                                            "Do you wanna Add,read and remove the books, Enter Add or Read or Remove: ");
                                    String operation = sc.nextLine().toLowerCase();
                                    if (operation.equals("add")) {
                                        bs.SetBookdetails();
                                    } else if (operation.equals("read")) {
                                        bs.getBookdetails();
                                    } else if (operation.equals("remove")) {
                                        bs.removeBookdetails();
                                    } else {
                                        System.out.println("Enter valid operation......");
                                    }
                                }
                                break;
                            } else {
                                System.out.println("Invalid credentials, Try Again...");
                                if ((5 - j) == 0) {
                                    System.out.println("Sorry you chances are over application terminated");
                                } else {
                                    System.out.println((5 - j) + " attempts left");
                                }
                            }
                        }
                        break;
                    } else {
                        System.out.println("Entered passkey is wrong, Please try again...");
                    }
                }
            } else if (type_of_account.equals("member")) {
                for (int i = 1; i <= 5; i++) {
                    System.out.print("Enter the User_id:-  ");
                    uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter the username:-  ");
                    uname = sc.nextLine();
                    System.out.print("Enter the password:-  ");
                    upwd = sc.nextLine();
                    if (cr.getcredentials(uid, uname, upwd)) {
                        System.out.println("-----------------Login Successful🔓------------------");
                        System.out.print("Do you wanna access my Books, Enter Yes or No: ");
                        response = sc.nextLine().toLowerCase();
                        if (response.equals("yes")) {
                            System.out.print("Do you wanna add or read the books, Enter Add or Read: ");
                            String operation = sc.nextLine().toLowerCase();
                            if (operation.equals("add")) {
                                bs.SetBookdetails();
                            } else if (operation.equals("read")) {
                                bs.getBookdetails();
                            } else {
                                System.out.println("Enter the valid Operation...............");
                            }
                        }
                        break;
                    } else {
                        System.out.println("Invalid credentials, Try Again...");
                        if ((5 - i) == 0) {
                            System.out.println("Sorry you chances are over application terminated");
                        } else {
                            System.out.println((5 - i) + " attempts left");
                        }
                    }

                }
            }

        }

        // ---Logic for SIGNUP----

        else if (Acknowledgement.equals("signup")) {
            System.out.print("Which account do you wanna create Admin or Member: ");
            String Type_of_Account = sc.nextLine().toLowerCase();
            if (Type_of_Account.equals("admin")) { // Admin Account creation
                for (int i = 0; i < 3; i++) {
                    System.out.println("Enter the PassKey:  ");
                    String PassKey = sc.nextLine();
                    if (PassKey.equals("#*J@n@kiR@m_Libr@ry*#")) {
                        System.out.println("You can create the Admin account now.....🙏🤗");
                        System.out.print("Create User_ID:-  ");
                        uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Create username:-  ");
                        uname = sc.nextLine();
                        System.out.println("Note: 🔐Password should be more than 5 and less than 20 characters.......");
                        for (int j = 1; i <= 10; i++) {
                            System.out.print("Create a Password:-  ");
                            upwd = sc.nextLine();
                            if (upwd.length() > 5 && upwd.length() <= 20) {
                                System.out.print("Re-enter the password:- ");
                                re_enter = sc.nextLine();
                                if (upwd.equals(re_enter)) {
                                    final String type = "Admin";
                                    cr.setcredentials(uid, uname, upwd, type);
                                    break;
                                } else {
                                    System.out.println("password and re-entered password both should be same...");
                                }
                            } else {
                                System.out.println((10 - j) + " Attempts left");
                            }
                        }
                        break;
                    } else {
                        System.out.println("please try again, The Entered passKey is wrong.....");
                    }
                }
            } else if (Type_of_Account.equals("member")) { // Member Account creation
                System.out.print("Create User_ID:-  ");
                uid = sc.nextInt();
                sc.nextLine();
                System.out.print("Create username:-  ");
                uname = sc.nextLine();
                System.out.println("Note: 🔐Password should be more than 5 and less than 20 characters.......");
                for (int i = 1; i <= 5; i++) {
                    System.out.print("Create a Password:-  ");
                    upwd = sc.nextLine();
                    if (upwd.length() > 5 && upwd.length() <= 20) {
                        System.out.print("Re-enter the password:- ");
                        re_enter = sc.nextLine();
                        if (upwd.equals(re_enter)) {
                            final String type = "Member";
                            cr.setcredentials(uid, uname, upwd, type);
                            break;
                        } else {
                            System.out.println("password and re-entered password both should be same...");
                        }
                    } else {
                        System.out.println((5 - i) + " Attempts left");
                    }
                }
            }
        } else {
            System.out.println("Select from signin or signup 😏");
        }
    }
}