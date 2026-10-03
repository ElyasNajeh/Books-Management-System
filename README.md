# Books Management System

A JavaFX desktop application for maintaining, searching, sorting, and reviewing a library book collection.

## Features

- Add, update, delete, search, and display books.
- Sort books by title, author, or publication year and filter recently active titles.
- Load book records from a text file and save the current collection to a text file.
- View counts and minimum/maximum publication and author statistics.

## Technologies & Tools

- Java 21: application language and target runtime.
- JavaFX 21: desktop user interface, controls, and observable collections.
- Maven and Maven Wrapper: dependency management, builds, and application launch.

## Data Structures

- `ObservableList<Book>` stores the live collection and keeps JavaFX tables synchronized.
- `ArrayList<Book>` is used for independent sorted views of the collection.
- `Book` objects hold the ID, title, author, category, publication year, and ISBN.

## Prerequisites

- JDK 21 or newer with `JAVA_HOME` configured. Maven is downloaded automatically by the wrapper.

## Getting Started

```bash
git clone <repository-url> Books-Management-System
cd Books-Management-System
./mvnw clean verify
./mvnw javafx:run
```

On Windows PowerShell, use `./mvnw.cmd clean verify` and `./mvnw.cmd javafx:run`.

The sample import file is `data/books.txt`. Each line uses the format `book ID, title, author, category, year, ISBN`.

## Project Structure

- `src/main/java/application/` contains the existing JavaFX classes.
- `src/main/resources/application/` contains the background and UI icons.
- `data/books.txt` contains sample book records for the Load Items action.
- `pom.xml` and `.mvn/` configure the reproducible Maven build.

