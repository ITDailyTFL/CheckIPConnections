import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.io.*;
import java.util.Scanner;

public class Phone {

    //Here's are the variables being used for logging in the API (TWILLIO) through the app.
    // in case you are using another API, then you might have to use other configs to log in and actually use It.

    //in case you want to create an account at the same API as mine you can go in
    //https://www.twilio.com

    //Where is "sendMSM" being called? right at Main.java at line 45.

    public static final String ACCOUNT_SID = "your_Sid";
    public static final String AUTH_TOKEN = "your_Token";

    public static String phoneNumber;

    //setting phoneNumber that will be receiving the message.
    public static void setPhoneNumber(String addressFileTxt){
        File phoneTxtFile = new File(addressFileTxt);
        try {
            Scanner scanner   = new Scanner(phoneTxtFile);
            if(scanner.hasNextLine()){
                phoneNumber = scanner.nextLine();
                System.out.println("PhoneNumber Set : "+ phoneNumber);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void sendSMS(String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(""+phoneNumber),
                        "MGbbe115a7ebff842c88b2ba55d2b3c275",
                        msg)
                .create();

        System.out.println(message.getSid());
    }
}

