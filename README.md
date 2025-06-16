# 🏃‍♂️ Training Log Application

A personal workout tracking app built in Java, designed to help athletes log and reflect on their daily training.

---

## 🎯 Project Overview

The Training Log Application allows users to record individual workouts by entering key metrics such as distance, duration, type of training, and a custom description. Originally built for endurance athletes, the app is equally useful for anyone seeking a simple and organized way to track exercise over time.

---

## 🧠 Motivation

As a competitive Track & Field athlete, keeping a training log is a daily habit and a valuable tool. I built this project to reflect how endurance athletes structure their training, with support for mileage, perceived effort, and qualitative notes — all within a lightweight desktop app.

---

## 🛠️ Technologies Used

- **Java** – Core language
- **Java Swing & AWT** – GUI design and event handling
- **JUnit** – Unit testing framework
- **Object-Oriented Design** – Encapsulation, abstraction, and modularity
- **File I/O** – Save/load training data to and from disk

---

## 📋 Core Features

- 📝 Add a new workout with:
  - Type of exercise (e.g., Run, Swim, Cross-training)
  - Distance
  - Duration
  - Effort rating (user-defined)
  - Custom workout description

- ❌ Remove the most recent workout
- 💾 Save the entire log to file
- 📂 Load a previously saved log
- 🧾 View a readable log history of all added sessions

---

## 👤 Target Users

This application was built primarily for:
- **Endurance athletes** (e.g., runners, swimmers, cyclists)
- Fitness-focused users looking to maintain a basic training history

---

## 🧪 Sample Session Log

```
Thu Mar 31 17:19:24 PDT 2022 – Added new session to training log  
Thu Mar 31 17:19:27 PDT 2022 – Removed most recent session from training log  
Thu Mar 31 17:19:46 PDT 2022 – Added new session to training log  
```

---

## 🧩 Design Notes

The application was built using clean object-oriented programming principles, with an emphasis on modular class design and test-driven development. The GUI was initially implemented as a single class, but future refactoring could involve separating each panel into its own class for improved maintainability.

---

## 📚 Course Context

This project was created for an **Introduction to Software Construction** course which focused on object-oriented design, modularity, and software engineering best practices. The project was built **while learning Java for the first time**, and helped solidify my understanding of core programming concepts, GUI development, and test-driven development in a real-world application.

---

## 🚀 Potential Future Features

- Weekly/monthly summary statistics (e.g., total mileage, time spent training)
- Graphs to visualize effort ratings, distance trends, or training volume
- Calendar-based UI for browsing and editing workouts
- Export logs to CSV or JSON for use in other tools
- 📥 **Strava Integration** – Import activities directly from Strava using their API to auto-fill the training log and reduce manual entry
