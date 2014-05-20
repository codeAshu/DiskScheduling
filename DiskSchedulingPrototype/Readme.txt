# Group 1 (Disk scheduling)

1.Unzip the .zip file.

2.Create a database.

3.Goto the file named : "DbController.java" in the package called "edu.iiit.controller".
  and change the name of the database,username and password in the following string

 "jdbc:mysql://localhost:3306/godam","root","qwe123""

4.In your database create a table as : 
CREATE TABLE `legacy` (
  `type` int(11) NOT NULL,
  `avg` int(11) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `head` int(10) DEFAULT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

5.In eclipse first compile the code and run it.