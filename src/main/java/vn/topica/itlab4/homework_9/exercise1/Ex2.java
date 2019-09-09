package vn.topica.itlab4.homework_9.exercise1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ex2 {

	public static void main(String[] args) {
		ProcessInput input=new ProcessInput();
		ProcessOutput output=new ProcessOutput();
		List<Product> listP=input.readInput();
		//process standardize field owner of product
		for(Product p : listP) {
			p.setOwner(input.standardizeString(p.getOwner()));
		}
		//sort by warrantyYear
		Collections.sort(listP, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.getWarrantyYear() > p2.getWarrantyYear() ? -1 : 1;
            }
        });
		//write data to output1.txt
		output.writeOutputFile(listP);


	}
}
