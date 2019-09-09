package vn.topica.itlab4.homework_9.exercise1;

import java.util.List;

public class Ex4 {
	public static void main(String[] args) {
		ProcessInput input=new ProcessInput();
		ProcessOutput output=new ProcessOutput();
		List<Product> listP=input.readInput();
		String str="";
		//process standardize field owner of product
		for(Product p : listP) {
			p.setOwner(input.standardizeString(p.getOwner()));
			//add all field owner of list product to a string
			str+=p.getOwner()+" ";
		}
		//find name popular
		List<String> listStr=input.findPopularSubstring(str);
		String stringOutput="";
		for(String st : listStr) {
			stringOutput+=st+"\n";
		}
		//write name popular to output1.txt
		output.writeOutputFile(stringOutput);
	}
}
