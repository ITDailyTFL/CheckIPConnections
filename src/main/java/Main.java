import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //path to the file where we store all the ip addresses.
    private static String ipAdressFileText ="C:\\CheckIpConnections\\Ip_Adresses.txt";

    //path to the file where our phoneNumber is kept.
    private static String phoneNumberFile ="C:\\CheckIpConnections\\PhoneNumber.txt";

    private static ArrayList<MyIpAddress> myIpAddresses = new ArrayList<>();

    //this variables is SUPER important and you can change but I suggest 3 as  good number
    //sometimes when you ping a computer you get a "request time out", so adding 3 times to check avoid this kind
    //of issue from happening.
    private static int timesToCheckFailure = 3;


    public static void main(String[] args) {
        //setting phoneNumber that will be receiving the message according to the txt file address
        Phone.setPhoneNumber(phoneNumberFile);
        populateMyIpAdresses();

        while (true){
            Process process;
            boolean connection =true;
            try{

                for     (int i = 0; i< myIpAddresses.size(); i++){
                    MyIpAddress myIp = myIpAddresses.get(i);
                    process = Runtime.getRuntime().exec("ping "+myIpAddresses.get(i).getIp());
                    connection =  isPingWorking(process,myIp.getIp());
                    if(!connection){
                        System.out.println("From "+ myIp.timesChecked+" to "+ (timesToCheckFailure));
                        myIp.timesChecked++;
                        if(!myIpAddresses.get(i).getStatus().equals("Failed Connection")){
                            myIp.setStatus("Failed Connection");
                        }
                        if(myIp.timesChecked>timesToCheckFailure){
                            if(!myIp.isHasMessaged()){
                                //send msg
                                System.out.println("messaging being sent.");
                                Phone.sendSMS("Computer "+ myIpAddresses.get(i).getIp()+" "+myIpAddresses.get(i).getName()+" is off");
                                myIp.setHasMessaged(true);
                            }
                        }
                    }else{
                        if(myIp.getStatus().equals("Failed Connection")){
                            // pc that was OFF now is back on.
                            myIp.reset();
                        }
                    }
                    myIpAddresses.get(i).printStatus();
                }

            }catch (Exception e){
                System.out.println();
            }
        }
    }

    //Check if ping works by returning true or false.
    public static boolean isPingWorking(Process process, String ipTested) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        boolean connectionEstablished = true;
        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
            if(line.contains("Ping request could not find host")|| line.contains("Request timed out.")){
                connectionEstablished=false;
            }

        }
        return  connectionEstablished;
    }

    //purpose : Get the IP  Addresseson TXT File for the array.
    public static void populateMyIpAdresses(){


        File file = new File(ipAdressFileText);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int count=0;
        System.out.println("Computer List");
        while (scanner.hasNextLine()) {
            String address = scanner.next();
            String name = scanner.next();
            System.out.println("\t\t"+count+" - "+address + " - " + name);
            count++;
            MyIpAddress myIpObj = new MyIpAddress(address,1,"Connection OK",name);
            myIpAddresses.add(myIpObj);
        }
    }
}
