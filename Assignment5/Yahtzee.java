/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		
		while (true) {
			if (nPlayers <= MAX_PLAYERS) break;
			nPlayers = dialog.readInt("You can enter up to " + MAX_PLAYERS + " number of players. Enter number of players");
		}
		
		playerNames = new String[nPlayers];
		categoryScores = new int[nPlayers + 1][N_CATEGORIES + 1];
		selectedCategories = new int[nPlayers + 1][N_CATEGORIES + 1];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for (int i = 0; i < N_SCORING_CATEGORIES; ++i) {
			for (int j = 1; j <= nPlayers; ++j) {
				firstRoll(j);
				secondAndThirdRoll(j);
				selectCategory(j);
			}
		}
		calculateResult();
		calculateWinner();
	}
	
	private void firstRoll(int playerNum) {
		display.printMessage(playerNames[playerNum - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(playerNum);
		for (int i = 0; i < N_DICE; ++i) {
			dieResult[i] = rgen.nextInt(1, 6);
		}
		display.displayDice(dieResult);

	}
	
	private void secondAndThirdRoll(int playerNum) {
		for (int i = 0; i < 2; ++i) {
			display.printMessage("Select the dice you wish to re-roll asnd click \"Roll Again\".");
			display.waitForPlayerToSelectDice();
			for (int j = 0; j < N_DICE; ++j) {
				if (display.isDieSelected(j) == true) {
					dieResult[j] = rgen.nextInt(1, 6);
				}
			}
			display.displayDice(dieResult);
		}
	}
	
	private void selectCategory(int playerNum) {
		display.printMessage("Select a category for this roll.");
		while (true) {
			int selectCategory = display.waitForPlayerToSelectCategory();
			if (selectedCategories[playerNum][selectCategory] == 0) {
				selectedCategories[playerNum][selectCategory] = 1;
				calculateCategoryScore(playerNum, selectCategory);
				break;
			}
			display.printMessage("You have already selected this category. Please select another one.");
		}
	}
	
	private void calculateCategoryScore(int playerNum, int category) {
		if (checkCategory(dieResult, category) == true) {
			int score = calCategoryScore(dieResult, playerNum, category);
			display.updateScorecard(category, playerNum, score);
		} else {
			categoryScores[playerNum][category] = 0;
			display.updateScorecard(category, playerNum, 0);
		}
		int totalScore = calTotalScores(playerNum);
		display.updateScorecard(TOTAL, playerNum, totalScore);
	}
	
	private boolean checkCategory(int[] dice, int category) {
		boolean isMatch = false;
		if (category >= ONES && category <= SIXES || category == CHANCE) {
			isMatch = true;
		} else {
			int[] dieCount = new int[6 + 1];
			for (int i = 0; i < N_DICE; ++i) {
				dieCount[dice[i]] += 1;
				
			}
			if (category == THREE_OF_A_KIND) {
				for (int i = 1; i <= 6; ++i) {
					if (dieCount[i] >= 3) {
						isMatch = true;
						break;
					}
				}
			} else if (category == FOUR_OF_A_KIND) {
				for (int i = 1; i <= 6; ++i) {
					if (dieCount[i] >= 4) {
						isMatch = true;
						break;
					}
				}
			} else if (category == YAHTZEE) {
				for (int i = 1; i <= 6; ++i) {
					if (dieCount[i] == 5) {
						isMatch = true;
						break;
					}
				}
			} else if (category == FULL_HOUSE) {
				for (int i= 1; i <= 6; ++i) {
					for (int j = 1; j <= 6 && j != i; ++j) {
						if (dieCount[i] == 3 && dieCount[j] == 2) {
							isMatch = true;
							break;
						}
					}
				}
			} else if (category == LARGE_STRAIGHT) {
				if (dieCount[1] == 1 && dieCount[2] == 1 && dieCount[3] == 1 && dieCount[4] == 1 && dieCount[5] == 1) {
					isMatch = true;
				} else if (dieCount[2] == 1 && dieCount[2] == 3 && dieCount[3] == 1 && dieCount[4] == 1 && dieCount[5] == 1 && dieCount[6] == 1) {
					isMatch = true;
				}
			} else if (category == SMALL_STRAIGHT) {
				for (int i = 1; i <= 3; ++i) {
					if (dieCount[i] >= 1 && dieCount[i + 1] >= 1 && dieCount[i + 2] >= 1 && dieCount[i + 3] >= 1) {
						isMatch = true;
						break;
					}
				}
			}
		}
		return isMatch;
	}

	private int calCategoryScore(int[] die, int playerNum, int category) {
		int score = 0;
		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i < N_DICE; ++i) {
				if (die[i] == category) {
					score += category;
				}
			}
		} else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND || category == CHANCE) {
			for (int i = 0; i < N_DICE; ++i) {
				score += die[i];
			}
		} else if (category == FULL_HOUSE) {
			score = 25;
		} else if (category == SMALL_STRAIGHT) {
			score = 30;
		} else if (category == LARGE_STRAIGHT) {
			score = 40;
		} else if (category == YAHTZEE) {
			score = 50;
		}
		categoryScores[playerNum][category] = score;
		return score;
	}
	
	private int calTotalScores(int playerNum) {
		int totalScore = 0;
		int upperScore = 0;
		int lowerScore = 0;
		for (int i = ONES; i <= SIXES; ++i) {
			upperScore += categoryScores[playerNum][i];
		}
		for (int i = THREE_OF_A_KIND; i <= CHANCE; ++i) {
			lowerScore += categoryScores[playerNum][i];
		}
		totalScore = upperScore + lowerScore;
		categoryScores[playerNum][UPPER_SCORE] = upperScore;
		categoryScores[playerNum][LOWER_SCORE] = lowerScore;
		categoryScores[playerNum][TOTAL] = totalScore;
		return totalScore;
	}

	private void calculateResult() {
		for (int i = 1; i <= nPlayers; ++i) {
			if (categoryScores[i][UPPER_SCORE] >= 63) {
				categoryScores[i][UPPER_BONUS] = 35;
			}
			categoryScores[i][TOTAL] += categoryScores[i][UPPER_BONUS];
			display.updateScorecard(UPPER_SCORE, i, categoryScores[i][UPPER_SCORE]);
			display.updateScorecard(LOWER_SCORE, i, categoryScores[i][LOWER_SCORE]);
			display.updateScorecard(UPPER_BONUS, i, categoryScores[i][UPPER_BONUS]);
			display.updateScorecard(TOTAL, i, categoryScores[i][TOTAL]);
		}
	}
	
	private void calculateWinner() {
		int winnerScore = 0;
		int winnerNum = 0;
		
		for (int i = 1; i <= nPlayers; ++i) {
			if (categoryScores[i][TOTAL] >= winnerScore) {
				winnerScore = categoryScores[i][TOTAL];
				winnerNum = i - 1;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winnerNum] + ", you're the winner with a total score of " +
				winnerScore + "!");
	}
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private int[] dieResult = new int[N_DICE];
	private int[][] categoryScores;
	private int[][] selectedCategories;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
