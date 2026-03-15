# UrbanStore.pk – Java Desktop E-Commerce System

UrbanStore.pk is a **Java Swing based desktop e-commerce application** that allows users to browse products, add items to a cart, manage quantities, and checkout with a billing interface.

This project demonstrates the use of **Java Swing GUI, object-oriented programming, event handling, and simple cart management logic**.

---

# Features

• UrbanStore.pk branded desktop interface
• Product browsing with images
• Category filtering (Electronics, Clothing, Books, Home Decor)
• Product search bar
• Product hover UI effects
• Quantity selector (+ / −) for each product
• Live cart price updates
• Reset cart functionality
• Checkout and billing dashboard
• PKR (Pakistani Rupee) pricing support

---

# Technologies Used

Language:
Java (JDK 8 or higher)

GUI Framework:
Java Swing

IDE (Recommended):
BlueJ / IntelliJ 

Libraries:
Standard Java Libraries only

• javax.swing
• java.awt
• java.util
• javax.imageio

---

# Project Structure

UrbanStore/
│
├── assets/
│   └── products/
│       ├── ph1.jfif
│       ├── ph2.jpg
│       ├── rel.jfif
│       ├── samsung-1283938_1280.jfif
│       ├── clt1.png
│       ├── us.png
│       ├── tr.png
│       ├── jav.jpg
│       ├── download.png
│       ├── pyt.png
│       ├── tl.png
│       └── vs.jpg
│
├── UserDashboard.java
├── BillingDashboard.java
└── README.md

assets/products contains all product images used in the application.

---

# How to Run the Project (BlueJ)

Step 1
Clone the project or Download and extract zip.

Step 2
Install Postgree Sql and create a database ecom

Step 3
Install Java JDK.

Step 4
Install BlueJ.

Step 5
Open BlueJ.

Step 6
Click:

Project → Open Project Folder

Step 5
Select the folder and Compile the classes.

Step 7
Right click **loginframe class**.

Step 8
Select:

void main(String[] args)

The UrbanStore.pk application window will start.

---

# How to Run Using Command Line

Navigate to the project folder:

cd UrbanStore

Compile:

javac loginframe.java

Run:

java loginframe

The desktop GUI application will open.

---

PostgreSQL JDBC Driver Setup

Download the PostgreSQL JDBC driver.

Official site:
https://jdbc.postgresql.org/download/

Download file similar to:

postgresql-42.x.x.jar

Add the JAR file to your project classpath.

For BlueJ:

Open project

Go to Project → Preferences → Libraries

Add the PostgreSQL JDBC JAR file
---
Data Base:
Create database ecom.
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(255),
    email VARCHAR(150),
    mobile_number VARCHAR(15),
    dob DATE,
    address TEXT,
    pincode INTEGER
);
---
# Future Improvements

• Admin dashboard for product management
• Database integration (MySQL / PostgreSQL)
• User authentication system
• Order history tracking
• Product ratings and reviews
• Export invoice as PDF
• Payment gateway simulation

---

# Author

Project Name:
UrbanStore.pk Desktop E-Commerce System

Author: Farhan Ashraf

Developed for:
Java GUI Programming Practice / Academic Project

---

# License

This project is for educational and demonstration purposes.

You are free to modify and extend the project for learning or personal use.
