/******************************************************************************
Create a simple 5-question quiz with three choices 
that will display the score of the user. 
Make use of control structures, array and function in your Java program.

*******************************************************************************/
import java.util.Scanner;
import java.util.Random;

class TwelveTP1
{
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    int score = 0;
	    int totalScore = 0;
	    String[][] arr = {{"He is the oldest of the Armstrong siblings.",
	            "Steve\nLittle John\nBig Bert\nMark\nJaime","Steve","1"},
	            {"It forms the head of Voltes V.",
	            "Volt Crewzer\nVolt Panzer\nVolt Frigate","Volt Crewzer","2"},
	            {"The commander of Earth International Defense Force.",
	            "General Robinson\nGeneral Olivier Armstrong\nAdmiral Reinhard Von Lohengramm","General Robinson","3"},
	            {"The Earth defense base of the ultra electro magnetic machine Voltes V.",
	            "Camp Big Falcon\nKukuroo Mountain\nImpel Down","Camp Big Falcon","4"},
	            {"The final weapon used by Voltes V when dealing with beast fighter.",
	            "Laser Sword\nRocket Punch\nDeath Blow","Laser Sword","5"}};
	    
	    shuffle(arr);
	           
		int items = arr.length;
		System.out.println("A 5-question multiple choices Quiz.");
		System.out.println("-----------------------------------");
		System.out.println("\nPress \n2 = second chance\nU = unlimited chance\nany key = 1 chance");
		System.out.print("\n> ");
		
		String answer = scanner.nextLine().trim().toUpperCase();
		int chance = 1;
		if (answer.length() == 1) {
		    if (answer.charAt(0) == '2') {
		        chance = 2;
		    } else if (answer.charAt(0) == 'U') {
		        chance = 0;
		    }
		}

		for (int i = 0; i < items; i++) {
		    totalScore += Integer.parseInt(arr[i][3]);
		    int attempt = chance;
		    while(true) {
		        System.out.print(getQuestion(arr, i, i+1));    
		        int tmpScore = getScore(arr, i, scanner.nextLine().trim().toUpperCase());
		        
		        if (tmpScore == 0 && attempt == 1) {
		            break;
		        } else if (tmpScore == 0 && attempt == 2) {
		            --attempt;
		        } else if (tmpScore > 0) {
		            score += tmpScore;
		            break;
		        }
		        System.out.print("\nTry. Again");
		    }
		}
		
		System.out.println(getRemark(score, totalScore));
	}
	
	public static void shuffle(String[][] arr) {
	    Random random = new Random();
	    
	    //Shuffle the rows of the array. This will shuffle the sequence of questions.
	    for (int i = 0; i < arr.length; i++) {
	        int j = random.nextInt(arr.length);
	        String[] temp = arr[j].clone();
	        arr[j] = arr[i].clone();
	        arr[i] = temp.clone();
	    }
	    
	    //Shuffle the choices for each question.
	    for (int i = 0; i < arr.length; i++) {
	        String newChoices = "";
	        
	        String[] choices = arr[i][1].split("\n");
	        for (int j = choices.length - 1; j > 0; j--) {
	            int k = random.nextInt(j + 1);
	            String temp = choices[j];
	            choices[j] = choices[k];
	            choices[k] = temp;
	        }
	        
	        newChoices = choices[0];
	        for (int j = 1; j < choices.length; j++) {
	            newChoices += "\n" + choices[j];
	        }
	        
	        arr[i][1] = newChoices;
	    }
	}
	
	public static String getQuestion(String[][] arr, int index, int qCount) {
	    int point = Integer.parseInt(arr[index][3]);
	    String[] choices = arr[index][1].split("\n");
	    String text = "\n";
	    text += (qCount) + ". ";
	    text += arr[index][0];
	    text += "(" + point;
	    text += point > 1 ? " points)" : " point)";

	    for (int i = 0; i < choices.length; i++) {
	        text += "\n   ";
	        text += (char) (65 + i);
	        text +=  ". " + choices[i];
	    }

	    text += "\n\n Answer > ";
	    return text;
	}
	
	public static int getScore(String[][] arr, int index, String answer) {
	    String[] choices = arr[index][1].split("\n");
	    String correctAnswer = arr[index][2];
	    int userAnswer = answer.charAt(0) - 65; 
	    if (userAnswer >= choices.length || userAnswer < 0) {
	        return 0;
	    }
	    return correctAnswer.equals(choices[userAnswer]) ? Integer.parseInt(arr[index][3]) : 0;
	}
	
	public static String getRemark(int score, int totalScore) {
	    String text = "\n" + score + "/" + totalScore;
	    text += (score == totalScore) ? " Perfection!!!" : " You will do better next time.";
	    return text;
	}
}

