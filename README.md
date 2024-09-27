# Producer-Consumer

### Introduction

---

This project implements a Producer-Consumer system using various design patterns to ensure efficient and flexible code. The project involves designing and simulating a factory where machines and queues interact to process products. The application utilizes WebSocket for real-time updates and maintains a simple design for ease of use and understanding. Threads are employed to handle concurrent operations efficiently.

---
https://github.com/user-attachments/assets/0970e9ef-fce3-430f-aedc-7bac42597035


### Design Features

- **Factory Simulation:** Allows users to design a factory layout with machines and queues.
- **Asynchronous Processing:** Uses a queue to manage the production and consumption of products asynchronously.
- **Real-Time Updates:** Frontend is updated in real-time using WebSocket.
- **Control Options:** Users can start, stop, replay, and create new simulations.
- **Thread Management:** Ensures efficient handling of concurrent operations during the simulation.

---

### Front End Design

- **Color Scheme:** The colors used are suitable and provide clear visual distinction between different elements.
- **Time Left Display:** Each machine shows the remaining time to complete the current product.
- **Queue Information:** The number of products in each queue is displayed, helping users monitor the flow of production.

---

### Design Patterns Used

### Observer Design Pattern

- **Purpose:** To allow an object (subject) to notify dependent objects (observers) of state changes.
- **Implementation:**
    - An interface declares the update method.
    - The subject calls this method to notify changes.
    - Promotes flexibility and decouples subjects from observers.

### Memento Design Pattern

- **Purpose:** To capture and externalize an object's internal state, allowing it to be restored later.
- **Implementation:**
    - Originator saves its state to a Memento.
    - Caretaker maintains a history of Mementos.
    - Facilitates undo functionality and separates state management concerns.

### Singleton Design Pattern

- **Purpose:** To ensure a single instance of a class.
- **Implementation:**
    - Used for MachineManager, QueueManager, and SimulationManager.
    - Private constructors and synchronized getInstance() methods.
    - Ensures efficient resource usage and streamlined management.

### Producer-Consumer Design Pattern

- **Purpose:** To manage asynchronous processing between producers and consumers.
- **Implementation:**
    - Producers add items to a queue.
    - Consumers pull items from the queue.
    - Scheduler sends updates at regular intervals.

---

### Design Decisions

- **Modular Design:** The project is broken into parts to ease programming.
- **Observer Integration:** Models for machines, queues, and products contain observers to track changes.
- **WebSocket Usage:** Keeps the frontend updated in real-time.
- **Thread Management:** Threads are used to handle concurrent operations during the simulation.
- **Factory Layout:** Users can design the factory by adding machines and queues and connecting them.

---

### User Guide

![toolbar](https://github.com/user-attachments/assets/02c875b6-76d7-403e-a288-bcc281ec70a4)

### Constructing the Factory

1. **Add Machine:** Select "Add Machine" and click on the screen to place it.
2. **Add Queue:** Select "Add Queue" and click on the screen to place it.
3. **Connect Items:** Select "Connect Items" and click on the shapes to connect machines and queues.

### Running the Simulation

1. **Play:** Starts the simulation and input stream.
2. **Stop:** Pauses the simulation. Click "Play" to resume.
3. **Replay:** Replays the last simulation.
4. **New Simulation:** Starts a new simulation and resets the factory layout.

---

### UML Diagram
![uml](https://github.com/user-attachments/assets/28fc9095-dc64-4486-bcf4-5934b93c50d9)

---

### Snapshots

![p1](https://github.com/user-attachments/assets/3829f4e1-96b3-4318-a861-1d0c867bea07)

![p2](https://github.com/user-attachments/assets/e10740af-5175-49a8-8f0e-da455eb0805b)

---

### Getting Started

### Prerequisites

- [Node.js](https://nodejs.org/)
- A web browser (preferably Google Chrome)

---

### Steps to Run the Code

1. **Run the Backend:**
    - Open a terminal.
    - Navigate to the backend directory.
    - Run the backend server:
        
        ```
        sh
        Copy code
        npm start
        ```
        
    - The backend will run on port 8080.
2. **Run the Frontend:**
    - Open a new terminal.
    - Navigate to the frontend directory.
    - Run the frontend server:
        
        ```
        sh
        Copy code
        npm run serve
        ```
        
    - The frontend will run on port 8081.
3. **Access the Application:**
    - Open a web browser.
    - Navigate to [localhost:8081](http://localhost:8081/).
