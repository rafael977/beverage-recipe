package recipegenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import de.dfki.mycbr.core.similarity.AmalgamationFct;


public class GUIold{

	public JPanel 		leftFrame,rightFrame, Inframe, Inframe1, Inframe11,Inframe12,Inframe2,
	Inframe21,Inframe22,Inframe23, Outframe, Buttonframe, Labelframe, Fieldframe, AdaptionFrame,
	StepFrame,AdaptFrame,AcceptFrame,CaseFrame,innerStepFrame,InnerInframe11,InnerInframe12, AdaptionIngredientFrame;
	public JTextField   InputMileage;
	public JEditorPane  Output,Info,StepPane;
	public JLabel       labelDishType, labelStaple, labelVegetable, labelMeat, labelSeafood, labelFruit, labelCookTime, 
	labelVegetarian, labelTypeOfCuisine, labelFlavour, labelDifficultyLevel,
	labelNumberofcases, OutputlabelAmalgam,labelStaple2,labelVegetable2,labelMeat2,labelSeafood2,labelFruit2;
	public JButton      SubmitQuery, AcceptButton, AdaptButton;
	public JScrollPane  Scroll,Scroll2;
	public Recommender  remy;
	public JComboBox comboBoxDishType, comboBoxStaple, comboBoxVegetable, comboBoxMeat, comboBoxSeafood, comboBoxFruit, comboBoxVegetarian,
	comboBoxTypeOfCuisine, comboBoxFlavour, comboBoxDifficultyLevel, comboBoxCookTime, comboBoxNumberofcases;
	
	public JComboBox[] comboBoxStaple2,comboBoxVegetable2,comboBoxMeat2,comboBoxSeafood2,comboBoxFruit2;
	public JTable table;
	
	public Object[] columnTitle = {"Case Index" , "Food Name","Dish type","Cuisine","Flavour","Time","Similarity"};  
	public Object[][] tableData =   {  }; 
	public Map<String, String> FoodName_Step;
	
	public String staples[],vegetables[],meats[],seafoods[],fruits[],newContent;
	int selectedRowIndex=0;
	
	public GUIold() { 
		remy = new Recommender();
		remy.loadengine();
		System.out.println("load");

		// Dish Type
		comboBoxDishType = new JComboBox();  
		comboBoxDishType.addItem("Appetizer");
		comboBoxDishType.addItem("Dessert");
		comboBoxDishType.addItem("Drinks");
		comboBoxDishType.addItem("Main course");
		comboBoxDishType.addItem("Salad");
        comboBoxDishType.addItem("Soup");  
 	
		labelDishType = new JLabel("Dish Type: ");

		// Staple
		comboBoxStaple = new JComboBox();  
		comboBoxStaple.addItem(""); 
		comboBoxStaple.addItem("beehoon");
		comboBoxStaple.addItem("bread");
		comboBoxStaple.addItem("macaroni");
		comboBoxStaple.addItem("noodles");
		comboBoxStaple.addItem("pasta");
		comboBoxStaple.addItem("pastry block");
		comboBoxStaple.addItem("Prawn fritters");
		comboBoxStaple.addItem("ramen");
		comboBoxStaple.addItem("rice");
		comboBoxStaple.addItem("rolled oats");
		comboBoxStaple.addItem("tortilla wraps ");
		
		labelStaple = new JLabel("Staple: ");
				
		// Vegetable
		comboBoxVegetable = new JComboBox();  
        comboBoxVegetable.addItem("");  	
        comboBoxVegetable.addItem("asparagus");
        comboBoxVegetable.addItem("basil"); 
        comboBoxVegetable.addItem("bean");
        comboBoxVegetable.addItem("broccoli");
        comboBoxVegetable.addItem("cabbage");
        comboBoxVegetable.addItem("carrot"); 
        comboBoxVegetable.addItem("chilli");
        comboBoxVegetable.addItem("chives"); 
        comboBoxVegetable.addItem("cinnamon"); 
        comboBoxVegetable.addItem("coriander"); 
        comboBoxVegetable.addItem("corn");
        comboBoxVegetable.addItem("cucumber");
        comboBoxVegetable.addItem("eggplant"); 
        comboBoxVegetable.addItem("ginger");
        comboBoxVegetable.addItem("ginseng");
        comboBoxVegetable.addItem("lemongrass");
        comboBoxVegetable.addItem("lettuce"); 
        comboBoxVegetable.addItem("loofah");
        comboBoxVegetable.addItem("minit");
        comboBoxVegetable.addItem("mushroom");
        comboBoxVegetable.addItem("nutmeg"); 
        comboBoxVegetable.addItem("okras"); 
        comboBoxVegetable.addItem("onion");
        comboBoxVegetable.addItem("papardelle"); 
        comboBoxVegetable.addItem("potato");
        comboBoxVegetable.addItem("pumpkin");
        comboBoxVegetable.addItem("radish"); 
        comboBoxVegetable.addItem("shallot"); 
        comboBoxVegetable.addItem("spinach"); 
        comboBoxVegetable.addItem("tapioca"); 
        comboBoxVegetable.addItem("Tofu"); 
        comboBoxVegetable.addItem("tomato"); 
        comboBoxVegetable.addItem("turmeric"); 
        comboBoxVegetable.addItem("turnip"); 
        
        
		labelVegetable = new JLabel("Vegetable: ");

		// Meat
		comboBoxMeat = new JComboBox();  
		comboBoxMeat.addItem("");
		comboBoxMeat.addItem("anchovies");
		comboBoxMeat.addItem("bacon");
		comboBoxMeat.addItem("beef");
		comboBoxMeat.addItem("chicken");
		comboBoxMeat.addItem("clams");
		comboBoxMeat.addItem("duck");
		comboBoxMeat.addItem("egg");  
		comboBoxMeat.addItem("foie gras");
		comboBoxMeat.addItem("ham"); 
		comboBoxMeat.addItem("oxtail"); 
		comboBoxMeat.addItem("pork");
		comboBoxMeat.addItem("sausage");
		comboBoxMeat.addItem("sea bass fillets");
		comboBoxMeat.addItem("turkey");
		
		labelMeat = new JLabel("Meat: ");
		
		// Seafood 
		comboBoxSeafood = new JComboBox();  
		comboBoxSeafood.addItem("");
		comboBoxSeafood.addItem("bonito");
		comboBoxSeafood.addItem("clams");
		comboBoxSeafood.addItem("cod");  	
		comboBoxSeafood.addItem("crabmeat");  	
		comboBoxSeafood.addItem("dashi");  	
		comboBoxSeafood.addItem("fish");  	
		comboBoxSeafood.addItem("oysters"); 
		comboBoxSeafood.addItem("paste");
		comboBoxSeafood.addItem("prawn");
		comboBoxSeafood.addItem("salmon");  	
		comboBoxSeafood.addItem("sauce");  	
		comboBoxSeafood.addItem("scallops");  	
		comboBoxSeafood.addItem("seaweed");  	
		comboBoxSeafood.addItem("shrimp");
		comboBoxSeafood.addItem("squids");
		
		labelSeafood = new JLabel("Seafood: ");
		
		// Fruit 
		comboBoxFruit = new JComboBox();  
		comboBoxFruit.addItem("");
		comboBoxFruit.addItem("apple");  
		comboBoxFruit.addItem("avocado");
		comboBoxFruit.addItem("banana");
		comboBoxFruit.addItem("blueberry");
		comboBoxFruit.addItem("cherries");
		comboBoxFruit.addItem("guava");  
		comboBoxFruit.addItem("lemon");
		comboBoxFruit.addItem("limes");
		comboBoxFruit.addItem("mango");
		comboBoxFruit.addItem("melon");
		comboBoxFruit.addItem("orange");
		comboBoxFruit.addItem("papaya");
		comboBoxFruit.addItem("pineapple"); 
		comboBoxFruit.addItem("plum");
		comboBoxFruit.addItem("raisins");
		comboBoxFruit.addItem("strawberries");
		comboBoxFruit.addItem("tamarind");
		comboBoxFruit.addItem("yuzu");
		
		labelFruit = new JLabel("Fruit: ");
		
		// Cook Time 
		comboBoxCookTime = new JComboBox(); 
		comboBoxCookTime.addItem("5");  
		comboBoxCookTime.addItem("10");
		comboBoxCookTime.addItem("15");  
		comboBoxCookTime.addItem("20");
		comboBoxCookTime.addItem("25");  
		comboBoxCookTime.addItem("30");
		comboBoxCookTime.addItem("35");  
		comboBoxCookTime.addItem("40");
		comboBoxCookTime.addItem("45");  
		comboBoxCookTime.addItem("50");
		comboBoxCookTime.addItem("55");  
		comboBoxCookTime.addItem("60");
		labelCookTime = new JLabel("Cook Time (min): ");
		
		// Vegetarian
		comboBoxVegetarian = new JComboBox(); 
		comboBoxVegetarian.addItem("NO");
        comboBoxVegetarian.addItem("YES");         	
		labelVegetarian = new JLabel("Vegetarian: ");
		
		// Type of Cuisine
		comboBoxTypeOfCuisine = new JComboBox(); 
		comboBoxTypeOfCuisine.addItem("Asian");
		comboBoxTypeOfCuisine.addItem("Chinese");
		comboBoxTypeOfCuisine.addItem("European");
		comboBoxTypeOfCuisine.addItem("Indian");
		comboBoxTypeOfCuisine.addItem("Indonesian");
		comboBoxTypeOfCuisine.addItem("Japanese");
		comboBoxTypeOfCuisine.addItem("Malay");
		comboBoxTypeOfCuisine.addItem("Western");
		
		labelTypeOfCuisine = new JLabel("Type of Cuisine: ");
		
		// Flavour
		comboBoxFlavour = new JComboBox();  
		comboBoxFlavour.addItem("Spicy");  
		comboBoxFlavour.addItem("No Spicy"); 	
		labelFlavour = new JLabel("Flavour: ");
		
		// Difficulty Level
		comboBoxDifficultyLevel = new JComboBox();  
		comboBoxDifficultyLevel.addItem("1");  
		comboBoxDifficultyLevel.addItem("2"); 
		comboBoxDifficultyLevel.addItem("3");
		labelDifficultyLevel = new JLabel("Difficulty Level: ");

		// Number Of Cases
		comboBoxNumberofcases = new JComboBox();  
		comboBoxNumberofcases.addItem("1");  
		comboBoxNumberofcases.addItem("2"); 
		comboBoxNumberofcases.addItem("3");  
		comboBoxNumberofcases.addItem("4");
		comboBoxNumberofcases.addItem("5");  
		comboBoxNumberofcases.addItem("6");  
		comboBoxNumberofcases.addItem("7"); 
		comboBoxNumberofcases.addItem("8");  
		comboBoxNumberofcases.addItem("9");
		comboBoxNumberofcases.addItem("10");
		labelNumberofcases = new JLabel("Number of Cases:");

		
		// Submit
		SubmitQuery = new JButton("Submit Query");
		SubmitQuery.setToolTipText("Press me to process the Query.");
		
		// Retrived Cases
		
	
		// table
		 	
		
		//table = new JTable(tableData,columnTitle);
		//table = new JTable();
		
	//	Output = new JEditorPane("text/html","<b>Welcome to a small CBR recommender demo</b>");
	//	Output.setEditable(false); 

		
		DefaultTableModel model = new DefaultTableModel(tableData, columnTitle) {

		  public boolean isCellEditable(int row, int column) {
		    return false;
		  }
		};

		table = new JTable(model);
		
		table.addMouseListener(new MouseListener() {

			  public void mouseClicked(MouseEvent e) {	
				  // Copy Case Variables to Adaption Panel
				  copyToAdaptionPanel();
					
			  }

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			});

		Scroll = new JScrollPane(table);          						                
		Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//Scroll.setBorder(BorderFactory.createTitledBorder("User dialog:"));
		Scroll.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		Scroll.getViewport().setPreferredSize(new Dimension(450,150));

		//comboBoxAmalgam.setText(remy.myConcept.getActiveAmalgamFct().getName());

		SubmitQuery.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
		
				tableData = remy.solveOuery(comboBoxVegetable.getSelectedItem()+"",
						comboBoxDishType.getSelectedItem()+"",
						comboBoxStaple.getSelectedItem()+"",
						comboBoxMeat.getSelectedItem()+"",
						comboBoxSeafood.getSelectedItem()+"",
						comboBoxFruit.getSelectedItem()+"",
						comboBoxVegetarian.getSelectedItem()+"",
						comboBoxTypeOfCuisine.getSelectedItem()+"",
						comboBoxFlavour.getSelectedItem()+"",
						Integer.valueOf(comboBoxDifficultyLevel.getSelectedItem()+""),						
						Integer.valueOf(comboBoxCookTime.getSelectedItem()+""),		
						Integer.valueOf(comboBoxNumberofcases.getSelectedItem()+"")
						);
				//Output.setText(recomendation);
				fillTable(table,tableData);
				table.setRowSelectionInterval(0,0);
				copyToAdaptionPanel();
			}
		});
		
		//Output.setText(remy.displayAmalgamationFunctions());
			
		//Inframe = new JPanel(); 
		//Inframe.setLayout(new GridLayout(2,1));
		
		Inframe1 = new JPanel(); 
		Inframe1.setLayout(new GridLayout(1,2));
		
		InnerInframe11 = new JPanel();                                 
		InnerInframe11.setLayout(new GridLayout(6,2));
		InnerInframe11.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		
		Inframe11 = new JPanel();                                 
		Inframe11.setLayout(new GridLayout(1,1));
		Inframe11.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "1.General Input"));
		
		Inframe11.add(InnerInframe11);
		
		InnerInframe11.add(labelVegetarian);
		InnerInframe11.add(comboBoxVegetarian);
		
		InnerInframe11.add(labelDishType);                          
		InnerInframe11.add(comboBoxDishType);
						
		InnerInframe11.add(labelTypeOfCuisine);
		InnerInframe11.add(comboBoxTypeOfCuisine);
		
		InnerInframe11.add(labelFlavour);
		InnerInframe11.add(comboBoxFlavour);
			
		InnerInframe11.add(labelDifficultyLevel);
		InnerInframe11.add(comboBoxDifficultyLevel);
		
		InnerInframe11.add(labelCookTime);
		InnerInframe11.add(comboBoxCookTime);
		
		
		
		Inframe12 = new JPanel();                                 
		Inframe12.setLayout(new GridLayout(1,1));
		Inframe12.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "2.Ingredients"));
		
		
		InnerInframe12 = new JPanel();                                 
		InnerInframe12.setLayout(new GridLayout(5,2));
		InnerInframe12.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		Inframe12.add(InnerInframe12);
					
		InnerInframe12.add(labelStaple); 
		InnerInframe12.add(comboBoxStaple);
		
		InnerInframe12.add(labelVegetable); 
		InnerInframe12.add(comboBoxVegetable);
		
		InnerInframe12.add(labelMeat);
		InnerInframe12.add(comboBoxMeat);
		
		InnerInframe12.add(labelSeafood);
		InnerInframe12.add(comboBoxSeafood);
			
		InnerInframe12.add(labelFruit);
		InnerInframe12.add(comboBoxFruit);
		
		Inframe2 = new JPanel();                                 
		Inframe2.setLayout(new BoxLayout(Inframe2, BoxLayout.Y_AXIS));
		Inframe2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "3.Retrievel"));
		
		//Inframe21 = new JPanel();                                 
		//Inframe21.setLayout(new GridLayout(4,1));
		
		Inframe21 = new JPanel();                                 
		Inframe21.setLayout(new GridLayout(1,2));
		Inframe21.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		Inframe22 = new JPanel();                                 
		Inframe22.setLayout(new GridLayout(1,1));
		Inframe22.setBorder(new EmptyBorder(20, 90, 20, 90));

		//Inframe23 = new JPanel();                                 
		//Inframe23.setLayout(new GridLayout(4,1));
		
		Inframe21.add(labelNumberofcases);
		Inframe21.add(comboBoxNumberofcases);
				
		Inframe22.add(SubmitQuery);
		
		Inframe1.add(Inframe11);
		Inframe1.add(Inframe12);
		
		Inframe2.add(Inframe21);
		Inframe2.add(Inframe22);
		//Inframe2.add(SubmitQuery);
		//Inframe2.add(Inframe23);
		
		//Inframe.add(Inframe1);
		//Inframe.add(Inframe2);
		
		CaseFrame = new JPanel(); 
		CaseFrame.setLayout(new GridLayout(1,1));
		CaseFrame.setBorder(new EmptyBorder(10, 10, 10, 10));
		CaseFrame.add(Scroll);
		
		Outframe = new JPanel();                           
		//Outframe.setSize(new Dimension(350,200));
		Outframe.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "4.Retrived Cases"));
		Outframe.setLayout(new GridLayout(1,1));
		Outframe.add(CaseFrame);
		//Outframe.add(AccpetButton);
		//Outframe.add(AdaptButton);

		leftFrame = new JPanel(); 
		leftFrame.setLayout(new GridLayout(3,1));
		leftFrame.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		leftFrame.add(Inframe1);
		leftFrame.add(Inframe2);
		leftFrame.add(Outframe);
		
		
/////////////////////////////////////////////////////////////////////////////////////////////		
		int len = 3;
		
		// Staple
		comboBoxStaple2 = new JComboBox[len];  
		for(int i=0; i<comboBoxStaple2.length; i++) {
			comboBoxStaple2[i] = new JComboBox();
			comboBoxStaple2[i].addItem(""); 
			comboBoxStaple2[i].addItem("beehoon");
			comboBoxStaple2[i].addItem("bread");
			comboBoxStaple2[i].addItem("macaroni");
			comboBoxStaple2[i].addItem("noodles");
			comboBoxStaple2[i].addItem("pasta");
			comboBoxStaple2[i].addItem("pastry block");
			comboBoxStaple2[i].addItem("Prawn fritters");
			comboBoxStaple2[i].addItem("ramen");
			comboBoxStaple2[i].addItem("rice");
			comboBoxStaple2[i].addItem("rolled oats");
			comboBoxStaple2[i].addItem("tortilla wraps ");
			
			//comboBoxStaple2[i].addActionListener(this);
		}
		 	
		labelStaple2 = new JLabel("Staple: ");
			
		
		// Vegetable
		comboBoxVegetable2 = new JComboBox[len];  
		for(int i=0; i<comboBoxVegetable2.length; i++) {
			comboBoxVegetable2[i] = new JComboBox();  
	        comboBoxVegetable2[i].addItem("");  	
	        comboBoxVegetable2[i].addItem("asparagus");
	        comboBoxVegetable2[i].addItem("basil"); 
	        comboBoxVegetable2[i].addItem("bean");
	        comboBoxVegetable2[i].addItem("broccoli");
	        comboBoxVegetable2[i].addItem("cabbage");
	        comboBoxVegetable2[i].addItem("capsicum");
	        comboBoxVegetable2[i].addItem("carrot");
	        comboBoxVegetable2[i].addItem("celery");
	        comboBoxVegetable2[i].addItem("chilli");
	        comboBoxVegetable2[i].addItem("chillis");
	        comboBoxVegetable2[i].addItem("chives"); 
	        comboBoxVegetable2[i].addItem("cinnamon"); 
	        comboBoxVegetable2[i].addItem("coriander"); 
	        comboBoxVegetable2[i].addItem("corn");
	        comboBoxVegetable2[i].addItem("cucumber");
	        comboBoxVegetable2[i].addItem("eggplant"); 
	        comboBoxVegetable2[i].addItem("galangal");
	        comboBoxVegetable2[i].addItem("ginger");
	        comboBoxVegetable2[i].addItem("ginseng");
	        comboBoxVegetable2[i].addItem("lemongrass");
	        comboBoxVegetable2[i].addItem("lettuce"); 
	        comboBoxVegetable2[i].addItem("loofah");
	        comboBoxVegetable2[i].addItem("minit");
	        comboBoxVegetable2[i].addItem("mushroom");
	        comboBoxVegetable2[i].addItem("nutmeg"); 
	        comboBoxVegetable2[i].addItem("okras"); 
	        comboBoxVegetable2[i].addItem("onion");
	        comboBoxVegetable2[i].addItem("papardelle"); 
	        comboBoxVegetable2[i].addItem("parsley");
	        comboBoxVegetable2[i].addItem("potato");
	        comboBoxVegetable2[i].addItem("pumpkin");
	        comboBoxVegetable2[i].addItem("radish"); 
	        comboBoxVegetable2[i].addItem("shallot"); 
	        comboBoxVegetable2[i].addItem("spinach"); 
	        comboBoxVegetable2[i].addItem("tapioca"); 
	        comboBoxVegetable2[i].addItem("Tofu"); 
	        comboBoxVegetable2[i].addItem("tomato"); 
	        comboBoxVegetable2[i].addItem("turmeric"); 
	        comboBoxVegetable2[i].addItem("turnip"); 
	        
	        //comboBoxVegetable2[i].addActionListener(this);
	         	
		}
		labelVegetable2 = new JLabel("Vegetable: ");

		// Meat
		comboBoxMeat2 = new JComboBox[len]; 
		for(int i=0; i<comboBoxMeat2.length; i++) {
			comboBoxMeat2[i] = new JComboBox();
			comboBoxMeat2[i].addItem("");
			comboBoxMeat2[i].addItem("anchovies");
			comboBoxMeat2[i].addItem("bacon");
			comboBoxMeat2[i].addItem("beef");
			comboBoxMeat2[i].addItem("chicken");
			comboBoxMeat2[i].addItem("Chicken");
			comboBoxMeat2[i].addItem("clams");
			comboBoxMeat2[i].addItem("duck");
			comboBoxMeat2[i].addItem("egg");  
			comboBoxMeat2[i].addItem("eggs");
			comboBoxMeat2[i].addItem("fish");
			comboBoxMeat2[i].addItem("foie gras");
			comboBoxMeat2[i].addItem("ham"); 
			comboBoxMeat2[i].addItem("oxtail"); 
			comboBoxMeat2[i].addItem("pork");
			comboBoxMeat2[i].addItem("sausage");
			comboBoxMeat2[i].addItem("sea bass fillets");
			comboBoxMeat2[i].addItem("turkey");
			
			//comboBoxMeat2[i].addActionListener(this);
			 
		}
		labelMeat2 = new JLabel("Meat: ");
		
		// Seafood 
		comboBoxSeafood2 = new JComboBox[len]; 
		for(int i=0; i<comboBoxSeafood2.length; i++) {
			comboBoxSeafood2[i] = new JComboBox();
			comboBoxSeafood2[i].addItem("");
			comboBoxSeafood2[i].addItem("bonito");
			comboBoxSeafood2[i].addItem("clams");
			comboBoxSeafood2[i].addItem("cod");  	
			comboBoxSeafood2[i].addItem("crabmeat");  	
			comboBoxSeafood2[i].addItem("dashi");  	
			comboBoxSeafood2[i].addItem("fish");  	
			comboBoxSeafood2[i].addItem("oysters"); 
			comboBoxSeafood2[i].addItem("paste");
			comboBoxSeafood2[i].addItem("prawn");
			comboBoxSeafood2[i].addItem("prawns");
			comboBoxSeafood2[i].addItem("salmon");  	
			comboBoxSeafood2[i].addItem("sauce");  	
			comboBoxSeafood2[i].addItem("scallops");  	
			comboBoxSeafood2[i].addItem("seaweed");  	
			comboBoxSeafood2[i].addItem("shrimp");
			comboBoxSeafood2[i].addItem("shrimps");
			comboBoxSeafood2[i].addItem("squids");
				
			//comboBoxSeafood2[i].addActionListener(this);
		}
		labelSeafood2 = new JLabel("Seafood: ");
		
		// Fruit 
		comboBoxFruit2 = new JComboBox[len];  
		for(int i=0; i<comboBoxFruit2.length; i++) {
			comboBoxFruit2[i] = new JComboBox();  
			comboBoxFruit2[i].addItem("");
			comboBoxFruit2[i].addItem("apple");  
			comboBoxFruit2[i].addItem("avocado");
			comboBoxFruit2[i].addItem("banana");
			comboBoxFruit2[i].addItem("blueberry");
			comboBoxFruit2[i].addItem("cherries");
			comboBoxFruit2[i].addItem("guava");  
			comboBoxFruit2[i].addItem("lemon");
			comboBoxFruit2[i].addItem("limes");
			comboBoxFruit2[i].addItem("mango");
			comboBoxFruit2[i].addItem("mangoes");
			comboBoxFruit2[i].addItem("melon");
			comboBoxFruit2[i].addItem("orange");
			comboBoxFruit2[i].addItem("papaya");
			comboBoxFruit2[i].addItem("pineapple"); 
			comboBoxFruit2[i].addItem("plum");
			comboBoxFruit2[i].addItem("raisins");
			comboBoxFruit2[i].addItem("strawberries");
			comboBoxFruit2[i].addItem("tamarind");
			comboBoxFruit2[i].addItem("yuzu");
			
			//comboBoxFruit2[i].addActionListener(this);
		}
		labelFruit2 = new JLabel("Fruit: ");
		
		for(int i=0;i<3;i++) {			  
			  comboBoxStaple2[i].setEnabled(false);
			  comboBoxVegetable2[i].setEnabled(false);
			  comboBoxMeat2[i].setEnabled(false);
			  comboBoxSeafood2[i].setEnabled(false);
			  comboBoxFruit2[i].setEnabled(false);
		  }
		
		
		// Adapt
		AdaptButton = new JButton("Adapt");
		AdaptButton.setToolTipText("Press me to adapt.");
		AdaptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				adaptIngredients();
			} 
			
		});
		
		
		// Step
		StepPane = new JEditorPane("text/html","<b>Welcome to a small CBR recommender demo</b>");
		StepPane.setEditable(false);                

		Scroll2 = new JScrollPane(StepPane);          						                
		Scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//Scroll2.setBorder(BorderFactory.createTitledBorder("User dialog:"));
		Scroll2.setBorder(new EmptyBorder(10, 10, 10, 10));
		Scroll2.getViewport().setPreferredSize(new Dimension(450,150));
		
		// Accept
		AcceptButton = new JButton("Accept");
		AcceptButton.setToolTipText("Press me to accept.");
		
		AdaptionFrame = new JPanel();                                 
		AdaptionFrame.setLayout(new BorderLayout());
		AdaptionFrame.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "5.Adaption"));
		
		AdaptionIngredientFrame = new JPanel();                                 
		AdaptionIngredientFrame.setLayout(new GridLayout(5,4));
		AdaptionIngredientFrame.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		AdaptionIngredientFrame.add(labelStaple2);
		for(int i=0; i<comboBoxStaple2.length; i++) {
			AdaptionIngredientFrame.add(comboBoxStaple2[i]);
		}
		
		AdaptionIngredientFrame.add(labelVegetable2);
		for(int i=0; i<comboBoxVegetable2.length; i++) {
			AdaptionIngredientFrame.add(comboBoxVegetable2[i]);
		}
		
		AdaptionIngredientFrame.add(labelMeat2);
		for(int i=0; i<comboBoxMeat2.length; i++) {
			AdaptionIngredientFrame.add(comboBoxMeat2[i]);
		}
		
		AdaptionIngredientFrame.add(labelSeafood2);
		for(int i=0; i<comboBoxSeafood2.length; i++) {
			AdaptionIngredientFrame.add(comboBoxSeafood2[i]);
		}
		
		AdaptionIngredientFrame.add(labelFruit2);
		for(int i=0; i<comboBoxFruit2.length; i++) {
			AdaptionIngredientFrame.add(comboBoxFruit2[i]);
		}
		
		AdaptFrame = new JPanel();                                 
		AdaptFrame.setLayout(new GridLayout(1,1));
		AdaptFrame.setBorder(new EmptyBorder(0, 90, 8, 90));
		AdaptFrame.add(AdaptButton);
		
		AdaptionFrame.add(AdaptionIngredientFrame, BorderLayout.CENTER);
		AdaptionFrame.add(AdaptFrame, BorderLayout.PAGE_END);
		//AdaptionFrame.add(AdaptionIngredientFrame);
		//AdaptionFrame.add(AdaptFrame);
		
		AcceptFrame = new JPanel();                                 
		AcceptFrame.setLayout(new GridLayout(1,1));
		AcceptFrame.setBorder(new EmptyBorder(8, 90, 0, 90));
		AcceptFrame.add(AcceptButton);
		
		innerStepFrame = new JPanel();
		innerStepFrame.setLayout(new BorderLayout());
		innerStepFrame.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		innerStepFrame.add(Scroll2, BorderLayout.CENTER);
		innerStepFrame.add(AcceptFrame, BorderLayout.PAGE_END);
		//innerStepFrame.add(Scroll2);
		//innerStepFrame.add(AcceptFrame);
		
		StepFrame = new JPanel();                                 
		StepFrame.setLayout(new GridLayout(1,1));
		StepFrame.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "6.Step"));
		
		StepFrame.add(innerStepFrame);
		
		rightFrame = new JPanel(); 
		rightFrame.setLayout(new GridLayout(2,1));
		rightFrame.setBorder(new EmptyBorder(10, 0, 10, 10));
		
		rightFrame.add(AdaptionFrame);
		rightFrame.add(StepFrame);
		
		
		
		

		
/////////////////////////////////////////////////////////////////////////////////////////////
		
		JFrame Main = new JFrame("Recipe Recommender");   
		
		Main.getContentPane().setLayout(new BorderLayout());
		Main.getContentPane().add(leftFrame, BorderLayout.WEST);
		Main.getContentPane().add(rightFrame, BorderLayout.CENTER);			
		
		Main.addWindowListener(new WindowAdapter() {       

			public void windowClosing(WindowEvent e) {     

				System.exit(0);                             
			}
		});
		
		Main.pack();
		Main.setSize(1150,800);
		Main.setVisible(true);                               
	}                                                      

	public void fillTable(JTable table, Object[][] tableData){
		  DefaultTableModel tableModel = (DefaultTableModel) table
		  .getModel();
		  tableModel.setRowCount(0);// clear
		  
		  // fill in data
		  for(int i=0;i<tableData.length;i++){
			  String[] arr=new String[tableData[i].length];
			  
			  for(int j=0;j<tableData[i].length;j++) {				    
				    arr[j]=(String) tableData[i][j];				 
			  }
		    
			  // insert row
			  tableModel.addRow(arr);
		  }
		  
		  // update table
		  table.invalidate();
		}
	
	public void copyToAdaptionPanel() {
		int selectRows=table.getSelectedRows().length;					
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		if(selectRows==1){
		  selectedRowIndex = table.getSelectedRow();   
		  System.out.println("select Row "+selectedRowIndex);
		  
		  for(int i=0;i<3;i++) {
			  
			  comboBoxStaple2[i].setSelectedItem("");
			  comboBoxVegetable2[i].setSelectedItem("");
			  comboBoxMeat2[i].setSelectedItem("");
			  comboBoxSeafood2[i].setSelectedItem("");
			  comboBoxFruit2[i].setSelectedItem("");
			  
			  comboBoxStaple2[i].setEnabled(false);
			  comboBoxVegetable2[i].setEnabled(false);
			  comboBoxMeat2[i].setEnabled(false);
			  comboBoxSeafood2[i].setEnabled(false);
			  comboBoxFruit2[i].setEnabled(false);
		  }
		 
		  staples = ((String) tableData[selectedRowIndex][7]).split(";");
		  if(!staples[0].equals("_unknown_")) {
			  for(int i=0;i<staples.length;i++) {
				  if(i>2) {
					  break;
				  }
				  comboBoxStaple2[i].setEnabled(true);
				  comboBoxStaple2[i].setSelectedItem(staples[i]);						
			  }
		  }
		  
		  vegetables = ((String) tableData[selectedRowIndex][8]).split(";");
		  if(!vegetables[0].equals("_unknown_")) {
			  for(int i=0;i<vegetables.length;i++) {
				  if(i>2) {
					  break;
				  }
				  comboBoxVegetable2[i].setEnabled(true);
				  comboBoxVegetable2[i].setSelectedItem(vegetables[i]);						
			  }
		  }
		  
		  meats = ((String) tableData[selectedRowIndex][9]).split(";");
		  if(!meats[0].equals("_unknown_")) {
			  for(int i=0;i<meats.length;i++) {
				  if(i>2) {
					  break;
				  }
				  comboBoxMeat2[i].setEnabled(true);
				  comboBoxMeat2[i].setSelectedItem(meats[i]);						
			  }
		  }
		  
		  seafoods = ((String) tableData[selectedRowIndex][10]).split(";");
		  if(!seafoods[0].equals("_unknown_")) {
			  for(int i=0;i<seafoods.length;i++) {
				  if(i>2) {
					  break;
				  }
				  comboBoxSeafood2[i].setEnabled(true);
				  comboBoxSeafood2[i].setSelectedItem(seafoods[i]);						
			  }
		  }
		  
		  
		  fruits = ((String) tableData[selectedRowIndex][11]).split(";");
		  if(!fruits[0].equals("_unknown_")) {
			  for(int i=0;i<fruits.length;i++) {
				  if(i>2) {
					  break;
				  }
				  comboBoxFruit2[i].setEnabled(true);
				  comboBoxFruit2[i].setSelectedItem(fruits[i]);						
			  }
		  }
		  
		  // Copy Step
		  
		  String content = FoodName_Step.get((String) tableData[selectedRowIndex][1]);
		  
		  String regEx="\""; 
		  Pattern p=Pattern.compile(regEx);
		  Matcher m=p.matcher(content);
		  
		  newContent =m.replaceAll("");
		
		  StepPane.setText(newContent);
		  //System.out.println(newContent);
		  
		}
		
	}
	
	public void adaptIngredients() {
		String content = new String(newContent);
		for(int i=0;i<3;i++) {
			
			if(comboBoxStaple2[i].isEnabled()) {
				System.out.print(staples[i]+"=>");
				System.out.println(comboBoxStaple2[i].getSelectedItem().toString());
				
				content = content.replace(staples[i],comboBoxStaple2[i].getSelectedItem().toString());	
			}
			
			if(comboBoxVegetable2[i].isEnabled()) {
				System.out.print(vegetables[i]+"=>");
				System.out.println(comboBoxVegetable2[i].getSelectedItem().toString());
				
				content = content.replace(vegetables[i],comboBoxVegetable2[i].getSelectedItem().toString());
			}
			
			if(comboBoxMeat2[i].isEnabled()) {
				System.out.print(meats[i]+"=>");
				System.out.println(comboBoxMeat2[i].getSelectedItem().toString());
				content = content.replace(meats[i],comboBoxMeat2[i].getSelectedItem().toString());
			}
			
			if(comboBoxSeafood2[i].isEnabled()) {
				System.out.print(seafoods[i]+"=>");
				System.out.println(comboBoxSeafood2[i].getSelectedItem().toString());
				content = content.replace(seafoods[i],comboBoxSeafood2[i].getSelectedItem().toString());
			}
			
			if(comboBoxFruit2[i].isEnabled()) {
				System.out.print(fruits[i]+"=>");
				System.out.println(comboBoxFruit2[i].getSelectedItem().toString());
				content = content.replace(fruits[i],comboBoxFruit2[i].getSelectedItem().toString());
			}
				
		}
		StepPane.setText(content);
	}

/*	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(newContent == null) 
			return; 
		
		String content = new String(newContent);

		for(int i=0;i<3;i++) {
			
			
			if (e.getSource() == comboBoxMeat2[i]) {

					
			}
			
			if (e.getSource() == comboBoxSeafood2[i]) {
				
					
			}
			
			
			if (e.getSource() == comboBoxFruit2[i]) {
				
					
			}
			
			StepPane.setText(content);
		}
		
	}
*/
	
	public void readFromCsv() {
		String[] foodName;
		String[] step;
		try { 
			foodName = new String[100];
			int index = 0;
            BufferedReader reader = new BufferedReader(new FileReader(CBREngine.data_path+"FoodName.csv"));
            reader.readLine();
            String line = null; 
            while((line=reader.readLine())!=null){ 
                String item[] = line.split("，");
                 
                String last = item[item.length-1];
               
                foodName[index++] = last;
                //System.out.println(last); 
            } 
            //System.out.println(foodName[0]+" "+foodName[99]);
            
            
            step = new String[100];
			index = -1;
            reader = new BufferedReader(new FileReader(CBREngine.data_path+"Step.csv"));
            reader.readLine();
            line = null; 
            while((line=reader.readLine())!=null){ 
                String item[] = line.split("，");
                 
                String last = item[item.length-1];
                
                if(last.charAt(0) == '\"') {
                	index++;
                	step[index]="";
                	//System.out.println("-------"+index+"-----------"); 
                }
                
                step[index] += (last+"<br/><br/>");
                //System.out.println(last); 
            } 
            //System.out.println("\n\n"+step[0]+"\n"+index);
            
            FoodName_Step =
                    new HashMap<String, String>();
            
            for(int i=0;i<100;i++) {      	
            	FoodName_Step.put(foodName[i], step[i]);
    		   
            }
		    
		    
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
		
		
	}
//	
//	public static void main(String[] args) {                
//		
//		GUI mygui = new GUI();
//		mygui.readFromCsv();
//	}
	
}




