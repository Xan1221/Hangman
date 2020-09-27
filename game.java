
import java.io.File;
import java.util.Scanner;

public class game {

	private String randomMovie;
	private String ranmov;
	private String blank;
	private String blankWithSpaces;
	
	private char guess;
	

	private int numberOfGuesses=10;
	private int numberOfWrongGuesses=0;

	private char guessed[] = new char[26];
	private int k =0;

	game() {
		ranmov = "";
		blank = "";
		blankWithSpaces="";
		
	}

	public void playGame()  throws Exception {
		
		
		

		gameMessage();
		generateRandomMovie();
		generateBlank();
		generateBlankWithSpaces();
	
		
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		
		
		while (numberOfGuesses != 0) {
			System.out.println(blank);
			System.out.println("The film with spaces is: "+blankWithSpaces);
			
			
			printGuessedLetters();
			System.out.println("Please enter your guess you have " + numberOfGuesses + " left");
			
			
			
			guess = scanner.next().toLowerCase().charAt(0);
			System.out.println("You have guessed " + guess);

			while (checkMultipleGuess(guess)) {
				System.out.println("Please enter another guess as you can't guess the same thing twice");
				System.out.println("Please enter your guess you have " + numberOfGuesses + " left");
				guess = scanner2.next().toLowerCase().charAt(0);
			}
			
			checkGuess(guess);
			

			if (checkWin()) {
				scanner.close();
				scanner2.close();
				return;
			}
		}

		System.out.println("Sorry you loose... The correct movie was " + randomMovie);
		scanner.close();
		scanner2.close();
		return;
	}

	private void checkGuess(char guess) {
		boolean correctGuess = false;
		for (int i = 0; i < ranmov.length(); i++) {
			if (ranmov.charAt(i) == guess) {
				blank = blank.substring(0, i) + guess + blank.substring(i + 1);
				correctGuess = true;

			}
		}
		
		for (int i = 0; i < randomMovie.length(); i++) {
			if(randomMovie.charAt(i)==guess) {
				blankWithSpaces = blankWithSpaces.substring(0, i) + guess + blankWithSpaces.substring(i + 1);
			}
		}
		
		
		guessed[k] = guess;
		k++;
		if (correctGuess == false) {
			numberOfGuesses--;
			numberOfWrongGuesses++;
		}
	}

	private boolean checkMultipleGuess(char guess) {
		for (int i = 0; i<k; i++) {
			if (guess == guessed[i]) {
				return true;
			}
		}
		return false;
	}

	private void gameMessage() {
		System.out.println("Welcome to movie hangman! Try to guess the name of the movie!");
		System.out.println("You have 10 guesses good luck!");
	}

	private void generateRandomMovie() throws Exception {

		String arr[] = new String[100];
		int i = 0;
		File file = new File("movies.txt");
		Scanner fileScanner = new Scanner(file);

		while (fileScanner.hasNextLine()) {
			arr[i] = fileScanner.nextLine();
			i++;
		}

		fileScanner.close();

		int random = (int) (Math.random() * i);
		randomMovie = arr[random];
		ranmov = randomMovie.replaceAll(" ", "");
	}

	private void generateBlank() {
		blank = "";

		for (int i = 0; i < ranmov.length(); i++) {
			blank = blank + "_";
		}

	}
	
	private void generateBlankWithSpaces() {
		 blankWithSpaces="";
		for(int i=0; i<randomMovie.length(); i++) {
			if(randomMovie.charAt(i)==' ') {blankWithSpaces=blankWithSpaces+" ";}
			else {blankWithSpaces = blankWithSpaces + "_";}
		}
			}
	
 private void printGuessedLetters(){
	 System.out.print("The letter/s you have already guessed:" );
	 for(int i=0; i<k; i++) {
		if(i==k-1) {
			System.out.print(guessed[i]+".");
		}
		else{
			System.out.print(guessed[i]+",");
		}
	 }
 System.out.println("\n");
 }

	private boolean checkWin() {
		if (ranmov.equals(blank)) {
			System.out.println("Congrats, you win. The correct movie was " + randomMovie);
			System.out.println("You made " + numberOfWrongGuesses + " wrong guesses and had " + numberOfGuesses
					+ " guesses to spare");
			return true;
		}
		return false;
	} 

}
