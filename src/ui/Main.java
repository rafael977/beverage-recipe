package ui;

import java.io.File;

import javax.swing.JFrame;

import commonUtils.Constants;
import commonUtils.Utils;
import generator.CaseModel;
import generator.Retriever;

public class Main {

	public static void main(String[] args) {
//		Gui.createAndShowGui();
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		Retriever r = new Retriever();
		r.solveQuery(new CaseModel(new String[]{"white rum","amber rum"}, new String[]{"BLANK"}, new String[]{"lime"}, new String[]{"cane sugar syrup"},
				true, "high", 1));
	}

}
