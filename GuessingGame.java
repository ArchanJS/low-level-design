// Guessing Game: Imagine a game where the computer secretly picks a number between 1 and 100, and the user has to guess it within a limited number of attempts. Implement the logic for the computer to generate the random number, track the user's guesses, provide feedback (higher/lower), and determine if the user guessed correctly within the limit. This exercise practices random number generation, conditional statements, and loop structures.

package lld;

public class GuessingGame {
    private int number;
    private boolean isOver;
    private int lowerBound;
    private int upperBound;
    public GuessingGame(){
        number=(int)Math.round(Math.random()*100);
        isOver=false;
        lowerBound=1;
        upperBound=100;
    }
    public void compareInput(int input){
        if(isOver) {
            System.out.println("Game is already over");
            return;
        }
        if(input == number){
            isOver=true;
            System.out.println("You win");
            return;
        }
        if(number > input) System.out.println("You have entered a greater number");
        else System.out.println("You have entered a smaller number");
    }
    public void playGame(int input) throws IllegalArgumentException {
        if(input < lowerBound || input >= upperBound) throw new IllegalArgumentException("Entered number should be >=1 and <100");
        compareInput(input);
    }
}
