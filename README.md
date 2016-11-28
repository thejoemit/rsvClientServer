# rsvClientServer

A Client/Server Remote Method Invocation based flight manager writen in Java. 
This code was created for submission to Assignment2 for NET4005 at Carleton University in Fall 2016.

# Usage

	rsvclient list <server_name>
    rsvclient reserve <server_name> <class> <passenger_name> <seat_number>
	rsvclient passengerlist <servername>
    rsvserver <server_name>

# Function descriptions

List
* AList() will calculate the availability distribution of the different seats and send a list of 5 values representing how many seats remain.
* BCAList() will return a variable list under 5 values of seat id that are currently avalible in business class
* ECAList() will return a variable list up to 25 values of seat id that are currently avalible in economy class
Reserve
* PNList() will return a list of all passengers names in parralel
* PSList() will return a list of all passengers seat id in parralel
* PCList() will return a list of all passengers class in parralel
PassengerList
* PMax() will return a integer value of the number of seats on the flight
* reservation() will send a request to reserve a seat.

# References:
http://www.cs.nott.ac.uk/~pszcmg/G53ACC/java-rmi/rmi-tutorial.html

# Creative Commons License

This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
You accept and agree to be bound by the terms and conditions of this Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International Public License ("Public License"). To the extent this Public License may be interpreted as a contract, You are granted the Licensed Rights in consideration of Your acceptance of these terms and conditions, and the Licensor grants You such rights in consideration of benefits the Licensor receives from making the Licensed Material available under these terms and conditions.
You may read the summarized license at: https://creativecommons.org/licenses/by-nc-sa/4.0/
You may read the full license at: https://github.com/thejoemit/Csocketdl/blob/master/LICENSE.md
