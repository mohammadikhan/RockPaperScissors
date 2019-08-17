import java.util.Random;


public class StrategicMoves {
	
	public static double champions = 0;
	public static double dethrones = 0;
	public static double ties = 0;
	public static double playerChoice = 0;
	public static double lastWeaponUsed = 0;
	
	
	
	//This method allows for Durant to choose his weapon randomly (Take Durant as the Computer)
	public static int getDurantChoice (Random random) {
		int durantWeapon = 0;
		durantWeapon = random.nextInt(3) + 1;
		
		return durantWeapon;
	}
		
	
	//This method allows for Kawhi to choose his weapon randomly (Take Kawhi as the Player since he is representing the North this year)
	public static int getKawhiChoice (Random random) {
		
		int kawhiWeapon = 0;
		kawhiWeapon = random.nextInt(3) + 1;
		
		return kawhiWeapon;
	}
	public static int archiveWinner (double durantWeapon, double kawhiWeapon) {
		int champion = 0;	
		//If Durant plays rock and Kawhi plays paper, Kawhi Wins
		if ((durantWeapon == 1)&&(kawhiWeapon == 2)) {
			champion = 2;
			System.out.println("Durant played rock. Kawhi Played paper. Kawhi Wins!");
			
		//If Durant plays rock and Kawhi plays scissors, Durant Wins	
		}else if ((durantWeapon == 1)&&(kawhiWeapon == 3)) {
			champion = 1;
			System.out.println("Durant played rock. Kawhi played scissors. Durant Wins!");
			
		//If Durant plays paper and Kawhi plays rock, Durant Wins	
		}else if ((durantWeapon == 2)&&(kawhiWeapon == 1)) {
			champion = 1;
			System.out.println("Durant played paper. Kawhi played rock. Durant Wins!");
			
		//If Durant plays paper and Kawhi plays scissors, Kawhi wins	
		}else if ((durantWeapon == 2)&&(kawhiWeapon == 3)) {
			champion = 2;
			System.out.println("Durant played paper. Kawhi played scissors. Kawhi Wins!");
			
		//If Durant plays scissors and Kawhi plays rock, Kawhi wins
		}else if ((durantWeapon == 3)&&(kawhiWeapon == 1)) {
			champion = 2;
			System.out.println("Durant played scissors. Kawhi played rock. Kawhi Wins!");
			
		//If Durant plays scissors and Kawhi plays paper, Durant wins	
		}else if ((durantWeapon == 3)&&(kawhiWeapon == 2)){
			champion = 1;
			System.out.println("Durant played scissors. Kawhi played paper. Durant Wins!");
			
		//If Durant and Kawhi play the same weapon, then there is a draw 	
		}else if ((durantWeapon == kawhiWeapon)) {
			champion = 0;
			System.out.println("Durant and Kawhi played the same weapon. It's A Draw!");
		}	
		
		return champion;
	}
	
	
	public static double strategicMoveThatWouldWorkOne (double durantWeaponSelected, double champion, Random random) {
	//This strategy is essentially for when you lose.
	//If you lose, then in the next round, play the weapon that wasn't used in the last round	
	//For example, if Kawhi played scissors and Durant played rock, play what wasn't played in the last round
	//In this case paper, thinking that Durant will play rock again since he won the previous match.	
		
		double nextWeaponSelection = 0;
		
		//If there is a tie, get Kawhi to choose a random weapon
		if(champion == 0) {
			nextWeaponSelection = getKawhiChoice(random);
		
		//when durant wins play what wasn't used in that round
		}else if (champion == 1) {
			nextWeaponSelection = lastWeaponUsed;
		
		//when kawhi wins play what you just used and add one to it
		//if kawhi won with scissors, then add one (which would become rock) and play that weapon	
		}else if (champion == 2) {
			nextWeaponSelection = lastWeaponUsed + 1;
			if(nextWeaponSelection == 4) {
				nextWeaponSelection = 1;
			}
		}
		return nextWeaponSelection;
	}
	
	public static double strategicMoveThatWouldWorkTwo (double durantWeaponSelected, double champion, Random random) {
	//This strategy is a more of a behind the back technique
	//If you win, then in the next round, play what your opponent just lost with
	//In this case, if Kawhi wins with paper, that means Durant played rock
	//So in the next round, play rock	
		
		 double nextWeaponSelection = 0;
		 
		 //If there is a tie, get Kawhi to choose a weapon randomly
		 if (champion == 0) {
			 nextWeaponSelection = getKawhiChoice(random);
			 
		//If Kawhi wins, then play what Durant just lost with
		 }else if (champion == 2) {
			 nextWeaponSelection = durantWeaponSelected;
		
		//If Durant wins, then get him to use a random weapon 
		 }else if (champion == 1) {
			 nextWeaponSelection = getDurantChoice(random);
		 }
		 return nextWeaponSelection;
		 
		 
	}
	
	public static void main(String[] args) {
		for (int kareem = 0; kareem < 6; kareem++) {
			Random random = new Random();
			double durantWeapon = 0;
			double kawhiWeapon = 0;
			double durantWeaponSelected = 0;
			double champion = 0;
			
			double matches = 10;
			double championshipOdds = 0;
			double championshipNonOdds = 0;
			
			//Strategy One vs Random
			if (kareem == 0) {
				System.out.println("Kawhi using using the first game plan against Durant (Strategy One vs. Random):");
				for(int jordan = 0; jordan < matches; jordan ++) {
					durantWeapon = getDurantChoice(random);
					if (jordan == 0) {
						kawhiWeapon = getKawhiChoice(random);
					}else {
						kawhiWeapon = strategicMoveThatWouldWorkOne(durantWeaponSelected, champion, random);
					}
					lastWeaponUsed = kawhiWeapon;
					durantWeaponSelected = durantWeapon;
					champion = (archiveWinner(durantWeapon, kawhiWeapon));
					
					if (champion == 0) {
						ties++;
					}else if (champion == 1) {
						dethrones++;
					}else if (champion == 2) {
						champions++;
					}
					
					championshipOdds = champions/matches*100;
					championshipNonOdds = dethrones/matches*100;
				}
			}
			
			//Strategy Two vs Random
			if (kareem == 1) {
				System.out.println("Kawhi using using the second game plan against Durant (Strategy Two vs. Random):");
				for(int lebron = 0; lebron < matches; lebron ++) {
					durantWeapon = getDurantChoice(random);
					if (lebron == 0) {
						kawhiWeapon = getKawhiChoice(random);
					}else {
						kawhiWeapon = strategicMoveThatWouldWorkTwo(durantWeaponSelected, champion, random);
					}
					lastWeaponUsed = kawhiWeapon;
					durantWeaponSelected = durantWeapon;
					champion = (archiveWinner(durantWeapon, kawhiWeapon));
					
					if (champion == 0) {
						ties++;
					}else if (champion == 1) {
						dethrones++;
					}else if (champion == 2) {
						champions++;
					}
					
					championshipOdds = champions/matches*100;
					championshipNonOdds = dethrones/matches*100;
				}
			}
			
			//Strategy One vs Strategy One
			if (kareem == 2) {
				System.out.println("Kawhi using using the third game plan against Durant (Strategy One vs. Strategy One):");
				for(int harden = 0; harden < matches; harden ++) {
					durantWeapon = strategicMoveThatWouldWorkOne(durantWeaponSelected, champion, random);
					if (harden == 0) {
						kawhiWeapon = getKawhiChoice(random);
					}else {
						kawhiWeapon = strategicMoveThatWouldWorkOne(durantWeaponSelected, champion, random);
					}
					lastWeaponUsed = kawhiWeapon;
					durantWeaponSelected = durantWeapon;
					champion = (archiveWinner(durantWeapon, kawhiWeapon));
					
					if (champion == 0) {
						ties++;
					}else if (champion == 1) {
						dethrones++;
					}else if (champion == 2) {
						champions++;
					}
					
					championshipOdds = champions/matches*100;
					championshipNonOdds = dethrones/matches*100;
				}
			}
			
			//Strategy one vs Strategy Two
			if (kareem == 3) {
				System.out.println("Kawhi using using fourth game plan against Durant (Strategy One vs. Strategy Two):");
				for(int jordan = 0; jordan < matches; jordan ++) {
					durantWeapon = strategicMoveThatWouldWorkOne(durantWeaponSelected, champion, random);
					if (jordan == 0) {
						kawhiWeapon = getKawhiChoice(random);
					}else {
						kawhiWeapon = strategicMoveThatWouldWorkTwo(durantWeaponSelected, champion, random);
					}
					lastWeaponUsed = kawhiWeapon;
					durantWeaponSelected = durantWeapon;
					champion = (archiveWinner(durantWeapon, kawhiWeapon));
					
					if (champion == 0) {
						ties++;
					}else if (champion == 1) {
						dethrones++;
					}else if (champion == 2) {
						champions++;
					}
					
					championshipOdds = champions/matches*100;
					championshipNonOdds = dethrones/matches*100;
				}
			}
			
			//Random vs Random
			if (kareem == 4) {
				System.out.println("Kawhi using using the fifth plan against Durant (Random vs. Random):");
				for(int curry = 0; curry < matches; curry++) {
					durantWeapon = getDurantChoice(random);
					if (curry == 0) {
						kawhiWeapon = getKawhiChoice(random);
					}else {
						kawhiWeapon = getKawhiChoice(random);
					}
					lastWeaponUsed = kawhiWeapon;
					durantWeaponSelected = durantWeapon;
					champion = (archiveWinner(durantWeapon, kawhiWeapon));
					
					if (champion == 0) {
						ties++;
					}else if (champion == 1) {
						dethrones++;
					}else if (champion == 2) {
						champions++;
					}
					
					championshipOdds = champions/matches*100;
					championshipNonOdds = dethrones/matches*100;
				}
			}
			
			//Strategy 2 vs Strategy 2
			if (kareem == 5) {
				System.out.println("Kawhi using the sixth game plan against Durant (Strategy Two vs. Strategy Two):");
				for(int jordan = 0; jordan < matches; jordan ++) {
					durantWeapon = strategicMoveThatWouldWorkTwo(durantWeaponSelected, champion, random);
					if (jordan == 0) {
						kawhiWeapon = getKawhiChoice(random);
					}else {
						kawhiWeapon = strategicMoveThatWouldWorkTwo(durantWeaponSelected, champion, random);
					}
					lastWeaponUsed = kawhiWeapon;
					durantWeaponSelected = durantWeapon;
					champion = (archiveWinner(durantWeapon, kawhiWeapon));
					
					if (champion == 0) {
						ties++;
					}else if (champion == 1) {
						dethrones++;
					}else if (champion == 2) {
						champions++;
					}
					
					championshipOdds = champions/matches*100;
					championshipNonOdds = dethrones/matches*100;
				}
			}
			System.out.println("\nKawhi was the RPS Champion " +champions + " times");
			System.out.println("Kawhi was a loser " +dethrones + " times");
			System.out.println("Kawhi pulled off a draw against the snake " +ties + " times");
			System.out.println("Kawhi's odds of winning the RPS Championship are: " + championshipOdds + " %");
			System.out.println("Kawhi's odds of not winning the RPS Championship are: " + championshipNonOdds + " %");
			System.out.println();
			
			champions = 0;
			dethrones = 0;
			ties = 0;
			championshipOdds = 0;
		}
	}
}
		