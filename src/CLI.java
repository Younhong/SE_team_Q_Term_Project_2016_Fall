import java.io.*;
import java.util.Scanner;

// help class 
public class CLI extends Main {
    	private static void inputParser(String[] input){
	        	String[] inputFile = new String[10];
	        	String outputFile ="";
	        	 //  String style;
	        	int styleVariable=1;
	        	int inputIndex=-1;
	        	int outputIndex=-1;
	        	int styleIndex=-1;
	        	int numOfOption = 0;
	        	int numOfOutput = 0;
	        	boolean order;

		int n=0;
		for(int i=0;i<input.length;i++) {
	        		if(input[i].contains(".md")){
				if(input[i].substring(input[i].length()-3, input[i].length()).equals(".md")){
	        				inputFile[n]=input[i];
	        			} else{
					System.out.println("inappropriate file extension");
					helpMe();
					System.exit(0);
	        			}
	        		}else if(input[i].contains("-")){
				styleIndex = i;
				numOfOption++;
				if(numOfOption==1){
					switch(input[i]){
						case "-p": //plain option
							styleVariable=1;
							break;
						case "-f": //fancy option
							styleVariable=2;
							break;
						case "-s": //slide option
							styleVariable=3;
							break;
						default:
							System.out.println("Possible Style Options are : -p -f -s");
							helpMe();
							System.exit(0);
					}
				}else{
					System.out.println("only one option is allowed");
					helpMe();
					System.exit(0);
				}
	        		}else if(input[0].contains(".md")){
	        			outputIndex = i;
	        			numOfOutput++;
	        			if (numOfOutput==1){
					// < > : " / \ | ? *
					if (
						 //input[i].contains("\u003C") || // <
						 //input[i].contains("\u003D") || // >
						 input[i].contains("\u003A") || // :
						 input[i].contains("\"") || // "
						 //\u0022
						 input[i].contains("\u002F") || // /
					 	input[i].contains("\\") || // \
						 //\u005C
						 //input[i].contains("\u007C") || // |
					 	input[i].contains("\u003F") || // ?
						 input[i].contains("\u002A") ) // *
					 {
						System.out.println("Invalid character in output file name");
				 		System.out.println("Refrain from using the following characters: \u003C \u003D \u003A \" \u002F \\ \u003F \u002A \n");
				 		helpMe();
				 		System.exit(0);
		 			}
					if (
						input[i].equalsIgnoreCase("CON") ||
						input[i].equalsIgnoreCase("PRN") ||
						input[i].equalsIgnoreCase("AUX") ||
						input[i].equalsIgnoreCase("NUL")
					) {
						System.out.println("illegal output file name");
						helpMe();
						System.exit(0);
					}
		        			if (outputIndex == -1) {
		                                            	System.out.println("No output file name");
						helpMe();
						System.exit(0);
					}
		        			if((input[i].length() >= 5) && input[i].substring(input[i].length()-5, input[i].length()).equals(".html")){
		        				outputFile=input[i];
		        			}else{
		        				outputFile=input[i]+".html";
		              			}
		              		} else {
		              			System.out.println("Only one output is allowed");
					helpMe();
					System.exit(0);
		              		}
	        		}
	        		else{
	           			System.out.println(".md file does not exist.");
	           			System.exit(0);
	        		}
		}
	        	if(inputIndex!=-1){
	           		//order check
	            		if (styleIndex==-1){
	              			order = inputIndex < outputIndex;
	            		}
	            		else{
	               		order = (inputIndex < styleIndex) && (styleIndex < outputIndex);
     			}
      			if(order){
	             			//file export
	            			try {
	               			File file = new File(outputFile);
	               			if (file.exists()) {
	               				System.out.println("Output file already exists");
	               				System.exit(0);
	               			}
	               			file.createNewFile();
	            			} catch(IOException io) {
	              	 			System.out.println("cannot create html file");
	    				}
	      				System.out.println("file export executed");
	              			System.out.println(inputIndex +" "+ styleIndex+" " + outputIndex);
	             			for(int i=0;i<input.length;i++){
	             				System.out.println(input[i]);
	              			}
	             			for(int i=0;i<n;i++){
	              				System.out.println(inputFile[i]);
	             			}
	             			System.out.println(outputFile);
	            		}
	        		else{
	             			System.out.println("Syntax Error : [inputFileName], [style], [outputFileNmae]");
	            		}
        		}
    	}
}