# TaskManager Project

## Overview

TaskManager is a Java command-line application for managing tasks. The project is built with a layered architecture and includes a separate algorithm assignment, which is **not part of the main application code**.

---

## Project Structure

```
TaskManager/
├── algorithm/           # Contains the algorithm assignment (not related to the app logic)
│   └── AlgorithmSolution.java
├── src/                 # Main application source code
│   └── com/taskmanager/...
├── tasks.json           # Data file for tasks (JSON format)
├── README.md            # Project documentation
```

- **`src/`**: Contains all the code for the TaskManager app (model, repository, service, CLI).
- **`algorithm/`**: Contains a standalone Java file for the algorithm assignment.  
  *You can place any algorithm solution here. It is not connected to the rest of the project and can be compiled and run independently.*
- **`tasks.json`**: Stores the tasks data in JSON format.
- **`README.md`**: This documentation file.

---

## How to Run

1. **Compile the project**  
   Open a terminal in the project root and run:
   ```sh
   javac -d out src/com/taskmanager/*.java
   ```

2. **Run the application**  
   From the project root, run:
   ```sh
   java -cp out com.taskmanager.Main
   ```

3. **Run the algorithm assignment**  
   Go to the `algorithm` folder and compile/run the file:
   ```sh
   cd algorithm
   javac AlgorithmSolution.java
   java AlgorithmSolution
   ```

---

## Manual JSON Parsing

The project uses **manual JSON parsing** for reading and writing tasks to `tasks.json`.  
**Note:** This approach is intentionally simple and does not handle all edge cases (such as nested objects, special characters, or arrays inside objects).  
For real-world projects, it is recommended to use a dedicated library like Jackson or Gson.

---

## Architectural Principles

- **Layered architecture:**  
  The code is organized into clear layers:
  - **Model:** Represents the data (`Task`).
  - **Repository:** Handles data access and persistence.
  - **Service:** Contains business logic.
  - **CLI (Main):** Handles user interaction.

- **Separation of concerns:**  
  The algorithm assignment is kept in a separate folder (`algorithm`) to avoid mixing with the main application logic.

- **Extensibility:**  
  The project structure makes it easy to add new features, replace the data source, or change the user interface.

---

## Additional Notes

- You can edit the `README.md` directly on GitHub for documentation updates.
- The project is ready for further development, testing, and review.
