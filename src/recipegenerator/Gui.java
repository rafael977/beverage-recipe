package recipegenerator;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import commonUtils.*;

public class Gui extends JFrame {
	private JList alcoholicLiqueurList = new JList(Utils.extractListFromXml(Constants.XML_PATH_ALCOHOLICLIQUEUR));
	private JList nonalcoholicLiqueurList = new JList(Utils.extractListFromXml(Constants.XML_PATH_NONALCOHOLICLIQUEUR));
	private JList fruitList = new JList(Utils.extractListFromXml(Constants.XML_PATH_FRUIT));
	private JList flavourList = new JList(Utils.extractListFromXml(Constants.XML_PATH_FLAVOUR));
	
	private Gui(){
		super("Cocktail recipe generator");
		setLayout(new FlowLayout());
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout( );
		
		// Set window properties
		setGuiProperties();		
	}
	
	private JScrollPane createAlcoholicLiqueurListPanel(){
		alcoholicLiqueurList.setVisibleRowCount(Constants.LIST_VISIBLE_COUNT);
		alcoholicLiqueurList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	 	return new JScrollPane(alcoholicLiqueurList);
	}
	
	private void setGuiProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	// Initialize GUI
	public static void createAndShowGui() {		
		new Gui();
    }
}
