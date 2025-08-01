@echo off
mvn clean compile
mvn exec:java -Dexec.mainClass="com.abc.payroll.Main"
pause