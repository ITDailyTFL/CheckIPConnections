# CheckIPConnections
Purpose: Check if there's any specific computer not connected to the network 24/7 

API Used : In the original project It's being used the API from https://www.twilio.com , in case you choose to use another one
you will just need to modify the "Phone.java" class and that is It. 

How It works  : The software runs checking for Ip Addresses written on a TextFile and in case 
		     the PC does not establish connection back after 3 attempts as default, the app sends a SMS			
			message for a specific phoneNumber.


How to RUN:    You can either run in the background by opening the .jar file, you can check if it's
		   running on Task Manager, but to run in the CMD you just need to open the .bat file


How to configure The App:


Location: The directory should be C:\CheckIpConnections

inside of C:\CheckIpConnections  should include Ip_Adresses.txt and PhoneNumber.txt


In Ip_Adresses.txt, It must contain only TWO columns per line. One of the IP and other of the Name.
			the name is for identification and CAN NOT HAVE spaces.

EX : 
"
google.com  Google_Computer  
192.168.0.10 Test_PC
192.168.0.20 Canteen_PC
10.17.84.94 Joao_LAPTOP

"


In PhoneNumbr.
txt , It must contain only the phoneNumber with the countrydigit included.

EX : 
"
15196154641
"

Coded by Joao Marques
Project Manager Alex Gajovic

johnmarques.br2002@gmail.com
