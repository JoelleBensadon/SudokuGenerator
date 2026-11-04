# Sudoku Generator – AWS Lambda

## Project Description

This project is a Sudoku board generator written in Java and deployed using AWS Lambda.

The program generates a randomized, valid 9×9 Sudoku board. Each row, column, and 3×3 box contains the numbers 1–9 without repetition.

The project uses Java 21, Gradle, and AWS Lambda. A Function URL allows users to generate Sudoku boards through an HTTP request without running the Java program locally.

## How It Works

The project contains two main Java classes:

- **SudokuGenerator.java** – Generates a valid Sudoku board using recursion and backtracking. It also contains methods for removing numbers and printing a board.
- **SudokuHandler.java** – Connects the generator to AWS Lambda and returns the generated board as JSON.

Each time the Lambda function is invoked, it generates a new randomized Sudoku board.

## Using the AWS Lambda Function

1. Open the AWS Lambda Function URL in a web browser.
2. The Lambda function automatically generates a Sudoku board.
3. The board is returned as a JSON array containing nine rows of nine numbers.
4. Refresh the page to generate another board.

### Example Response

```json
[
  [1, 2, 6, 5, 3, 9, 7, 4, 8],
  [4, 3, 8, 6, 7, 1, 2, 5, 9],
  [5, 7, 9, 8, 4, 2, 1, 3, 6],
  [8, 4, 1, 7, 9, 5, 3, 6],
  [9, 5, 3, 2, 1, 6, 8, 7, 4],
  [7, 6, 2, 4, 8, 3, 9, 1, 5],
  [6, 8, 5, 1, 2, 7, 4, 9, 3],
  [3, 1, 4, 9, 5, 8, 6, 2, 7],
  [2, 9, 7, 3, 6, 4, 5, 8, 1]
]
```

## Running Locally

1. Clone the GitHub repository.
2. Open the project in IntelliJ IDEA.
3. Install Java 21.
4. Run the `SudokuGenerator` class to display a Sudoku board in the console.

## Building and Deploying to AWS Lambda

Generate the deployable JAR file:

```bash
./gradlew clean shadowJar
```

The JAR will be created in the `build/libs/` directory.

To deploy:

1. Open AWS Lambda and create a function using the Java 21 runtime.
2. Upload the generated JAR file.
3. Under Runtime settings, set the handler to:

   `SudokuPackage.SudokuHandler`

4. Under Configuration, create a Function URL.
5. Set the authorization type to `NONE` for public access.
6. Open the Function URL to generate a Sudoku board.

**Note:** A Function URL with authorization set to `NONE` is publicly accessible. Only enable this for an endpoint intended to be public.

## Technologies Used

- Java 21
- Gradle
- AWS Lambda
- Gson
- Gradle Shadow Plugin
