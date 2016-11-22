import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MDParser{
	
// ATTRIBTUES

	static Document doc = new Document();  //Document Object
	public static boolean startB = false;		 
	public static boolean endB = false;
	
	public static ArrayList<String> stringList = new ArrayList<>();
	
	// temporary variables 
	public static String nodeString = null;
	public static String curLine = null;
	public static String nextLine = null;
	public static String prevLine = null;
	public static Node.NodeType nodeType = null;
	
	
	// testing
//	static String path = "C:" + File.separator + "Users" + File.separator + "Eunbee" + File.separator + "workspace" + File.separator + "file.txt";
//	static File f = new File(path);
	
	
// CONSTRUCTOR
	public MDParser(File file)
	{
		parser(file);
	}
// OPERATIONS 
	
	public static void comparePN(String line)
	{
		// update.
		prevLine = nextLine;	
		nextLine = line;
		
		System.out.println("prev: " + prevLine + "\n next: " + nextLine);
		
		// check if nextLine starts the node.
		if(isStart(nextLine))
		{
			System.out.println("This is a start\n\n");
			return;		// action: ���� nodeString���� new node �����ϰ�  NodeArr�� ����, nextLine�� ����� nodeString�� ����
		}
		else
			System.out.println("This is not a start\n\n");
			
		// check if nextLine ends the node.	
		if(isEnd(nextLine))
		{
			System.out.println("This is an end\n\n");
			return;		// action: ���� nodeString�� �ڽ��� ���ϰ�  �� nodeString���� new node ����, NodeArr�� �߰�.
		}
		else
			System.out.println("This is not an end\n\n");
		
	

			// RULES!!!!!!  ���߿� �����մϴپ�.... 	
	
		
	}
	
	public static boolean isStart(String line)
	{		
		// HEADERS : #���� �����ϴ� header��
		if(line.startsWith("# ")||line.startsWith("## ")||line.startsWith("### ")
					||line.startsWith("#### ")||line.startsWith("##### ")||line.startsWith("###### "))
		{
			// Node ����(����������  NodeString���� ) 
			createNode(nodeString, nodeType);	
			
			// ���ο� Node ����
			nodeString = nodeString + line; 	// nodeString update
			nodeType = Node.NodeType.HEADER;						// nodeType ����
			
			return true;
		}

		// ORDER/ UNORDER LIST: ���� ù ���� ��츸!
		else if(prevLine == null || nodeString == null)
		{
			if(line.startsWith("* "))	// Unordered lists
			{
				createNode(nodeString, nodeType);	// node ����
				nodeString = nodeString + line; 	// nodeString update
				nodeType = Node.NodeType.UNORDER;						// nodeType ����
			}
			else if(line.startsWith("1.")||line.startsWith("2.")||line.startsWith("3.")	// Ordered Lists
					||line.startsWith("4.")||line.startsWith("5.")||line.startsWith("6.")
					||line.startsWith("7.")||line.startsWith("8.")||line.startsWith("9."))
			{
				createNode(nodeString, nodeType);	// node ����
				nodeString = nodeString + line; 	// nodeString update
				nodeType = Node.NodeType.ORDER;								// nodeType ����
			}
			else
			{
				nodeString = nodeString + line; 	// nodeString update
			}
			return true;
		}
		else
			return false;

	}
	
	
	public static boolean isEnd(String line)
	{
		int count_1 = 0; //counts '='
		int count_2 = 0; //counts '-'

		for(int i =0; i < line.length(); i++)
		{
			if(line.charAt(i) == '=')
			{
				
				count_1++;
			}
		}
		
		for(int i =0; i < line.length(); i++)
		{
			if(line.charAt(i) == '=')
			{
				count_2++;
			}
		}
		if(count_1 == line.length() || count_2 == line.length())
		{
			nodeString = prevLine + line; 	// nodeString update
			nodeType = Node.NodeType.HEADER;						// nodeType ����
			System.out.println("��������Ϸ�");
			
			createNode(nodeString, nodeType);	// node ����
			return true;
		}
		else
			return false;
	}
	
	// new node ����, array�� �߰�
	public static void createNode(String ns, Node.NodeType type)
	{
		//create Node and set its type.
		Node node = new Node(ns);
		
		node.setNodeType(type);
		
		
		//dd it to the nodeArray in Document Object.
		doc.nodes.add(node);
		System.out.println("��������Ϸ�");
		
		//initialize all temp variables.
		initializeAll();
	}
	
	// Flush method. Empty all temps.
	public static void initializeAll()
	{
		startB = false;
		endB = false;
		nodeString = null;
		curLine = null;
		nextLine = null;
		prevLine = null;
		nodeType = null;
	}

	
	public void parser(File Inputfile) {
		System.out.println("������� ����\n");
		
		String line = null;
		// ���� �� �پ� �о stringList array�� ����
		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int count = 0;
			while((line = bufferedReader.readLine()) != null) 
			{
				stringList.add(line);
				System.out.println("line num: " +  line.length());
				count++;
			} 
			
			
			System.out.println("string: " + stringList.size());
			
			bufferedReader.close();
		} catch(IOException ex) {
			System.out.println("Error reading file '" + Inputfile ); 
		}
		
		
		// String array �� string ����  parse ����
		for(int i = 0; i <stringList.size();i++)
		{
			comparePN(stringList.get(i));
		}
		
		
		
		
	}
	
	public static void main(String args[])
	{
		//testing
		//MDParser mdp = new MDParser(f);
		
			
		
	}
}
