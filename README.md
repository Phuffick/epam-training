# epam-training
EPAM training (spring 2021) in VSU lab

Task 1 (OOP):
	Create console application which mets the following requerements:

	1. Use OOP parts: classes, inheritance, polymorphism, incapsulation.
	2. Each class must have a meaningful name and informative composition.
	3. Inheritance should be used when it's makes sense.
	4. Use java code convention while coding,
	5. Classes should be intelligently packaged.
	6. Console interaction and console menu should be kept to a minimum.
	7. Files can be used to store initialization parameters.

	Assighnment number - 3:
	Household appliances. Determine the hierarchy of electrical appliances.
	Plug some into a socket. Calculate power consumption. 
	Sort the appliances based on power. 
	Find a device in the apartment that matches the specified range of parameters.

Task 2 (strings):
	Create a program for processing a programming book using classes (if necessary) to represent:
	symbols, words, sentences, punctuations, etc.
	In all tasks with the formation of text, replace tabs and execute spaces with one space.
	The program should treat the email address and phone numbers in the +XXX(XX)XXX-XX-XX format as separate words.

	Assighnment number - 3:
	Find a word in the first sentence that is never been used in any others sentences.

Task 3 (XML):
	1. Develop an XML document for your assighnment, describe it using XSD. 
	   Create an XML file corresponding to the developed XSD schema.
	2. Developing XSD use simple and complex types, templates and limit values, 
	   it is obligatory using attributes and ID type.
	3. Create a Java class (classes) according to the corresponding developed schema.
	4. Create a Java application to parse an XML document and initialize a collection 
	   of information objects from an XML file. For parsing use SAX, DOM or StAX parser. 
	   Use comparator interface to sort objects.
	5. Check the correctness and validity of an XML document using XSD.

	Assighnment number - 3: 
	Tariffs of mobile companies. Mobile companies tariffs can have the following structure:
	-Name – tariff name.
	-Operator name – the name of the mobile operator to which the tariff belongs.
	-Payroll – mounth paytoll (0 – n BYN).
	-Call prices (should be a few) – calls prices: innet (0 – n BYN/min), outnet (0 – n BYN/min), 
	 landline (0 – n BYN/min).
	-SMS price – sms price (0 – n BYN).
	-Parameters (hould be a few) – favorite cell number (0 – n), tariffication (12-second, 1-minute), 
	 connection fee (0 – n BYN).

Task 4 (DB):
	Develop a subsystem for working with the database of the proposed subject area:
	1. Develop a database schema in accordance with the subject area of ​​your option. 
	   Create sql scripts for creating a database, database user, creating tables, filling tables, 
	   deleting data, deleting tables, deleting a database, updating data, requests for data selection.
	2. Store information about the subject area in the database, for access use the JDBC API using 
	   a connection pool that you have to develop yourself. MySQL is used as a DBMS.
	3. Based on the entities of the domain, create classes that describe them.
	4. Classes and methods should have names that reflect their functionality 
	   and should be well structured by packages.
	5. The formatting of the code must comply with the Java Code Convention.
	6. The application must support work with the Cyrillic alphabet (be multilingual), 
	   including when storing information in the database.
	7. Perform event logging, that is, process information about emerging exceptions 
	   and events in the system using Log4j 2.
	8. The code must contain comments.
	   Hospital system. The doctor determines the diagnosis, makes an appointment to the Patient 
	   (procedures, medications, operations). The appointment can be made by a Nurse 
	   (procedures, medications) or a Doctor (any appointment). The patient can be discharged 
	   from the Hospital, and the final diagnosis is recorded.

Task 5(Web-Application):
	Build a web system (for the subject area in accordance with the task 4 option) that supports 
	the specified functionality:
	1. The application interface must support Cyrillic (multilingual).
	2. The application architecture must follow the MVC pattern.
	3. When implementing business logic algorithms, use GoF templates.
	4. Using servlets and JSPs, implement the functionality proposed 
	   in the formulation of a specific task.
	5. In JSP pages, use the JSTL library and develop your own tags.
	6. When developing business logic, use sessions and filters.
	7. The code must contain comments.