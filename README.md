# AI-fitness
🧠 Weak AI Fitness Assistant (Java)

Warning:

In mainOne.java:
- In OrginalTableSign() method, check the path of tablesign.txt carefully before you run, make sure it is right by clicking on the properties of "tablesign.txt" file.
  
In Infoclient.java:
- In tablesign() method, check the path of tablesign.txt carefully before you run, make sure it is right by clicking on the properties of "tablesign.txt" file.

📘 Overview

The Weak AI Fitness Assistant is a Java-based personal fitness tool that simulates intelligent decision-making to help users track, analyze, and improve their health.
This project demonstrates the use of Weak AI principles, object-oriented programming, and data persistence to provide personalized fitness insights — all without external APIs or full autonomy.

⚙️ Features

🧩 Weak AI logic simulation — generates adaptive health recommendations using rule-based reasoning.

🔐 Custom encryption/decryption system — securely stores user credentials and personal data.

📊 Metric analysis — automatically computes and interprets:

BMI (Body Mass Index)

WHR (Waist-to-Hip Ratio)

WHtR (Waist-to-Height Ratio)

TDEE (Total Daily Energy Expenditure)

BMR (Basal Metabolic Rate)

Body Fat Percentage

💾 File-based data persistence — user information and results are saved and retrieved through structured .txt files.

👤 Interactive user interface (CLI) — users can sign up, log in, and view personalized reports directly from the console.

🧬 Technical Highlights

Language: Java

Concepts Used: OOP (Encapsulation, Inheritance, Polymorphism), File I/O, Exception Handling, Encryption Logic

Core Classes:

Infoclient.java — defines user structure and handles data encryption/decryption.

mainOne.java — controls the overall program flow (sign-in, sign-up, and menu interactions).

Architecture: Modular and reusable design, separating UI, logic, and data handling for scalability.

🚀 How It Works

The program starts with mainOne.java, prompting the user to log in or sign up.

On successful authentication, Infoclient.java loads the user’s encrypted data.

The system then computes fitness metrics based on user input and generates tailored recommendations.

Results and updates are stored securely in .txt files for future sessions
