# Introduction
This repo provides a sample on writing to and reading from MongoDB Atlas. 

It forms partially of my VLDB - TPCTC 2018 work.

The drivers used are bson-3.6.3, mongodb-driver-3.6.3, and mongodb-driver-core-3.6.3. The Altas uses WiredTiger 3.6. 

# Miscellaneous tools

## AWS Authentication: Give a running script highest clearance:
~~~~
sudo chmod 777 run.sh
~~~~

## Java: Install Java on Ubuntu 
You can use the following commands to install java on Ubuntu. Up till 2018 Feb, the latest version of Java is Java 9. It looks like java 10 will be released in 2018.
~~~~
(sudo add-apt-repository ppa:webupd8team/java)
sudo add-apt-repository ppa:linuxuprising/java
sudo apt update; 
sudo apt install oracle-java10-installer
~~~~

## Keep Process Running: How to keep process running after you close the ssh terminal?
Here is a reference link: https://askubuntu.com/questions/8653/how-to-keep-processes-running-after-ending-ssh-session
~~~~
tmux
~~~~
To Exit:
~~~~
ctrl b + d
~~~~

## MongoDB: start MongoDB on Windows 10
Firstly, open cmd window (win+r, and then type in cmd).
The following starts the mongoDB, and then direct the file path to my directory.
~~~~
"C:\Program Files\MongoDB\Server\3.6\bin\mongod.exe" --dbpath C:\Users\LocalAdmin\Desktop\Workspace_mongodb\mongodb01
~~~~
Secondly, open Studio 3T or Robomongo, if you prefer a GUI.
