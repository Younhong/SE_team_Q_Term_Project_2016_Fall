import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MDParser{
	
// ATTRIBTUES

	enum NodeType
	{
		PLAIN, HEADER, Q_BLOCK, LIST, BLOCK;	
	}
	
	
	static Document doc = new Document();  //Document Object
	public static boolean startB = false;		 
	public static boolean endB = false;
	
	public static ArrayList<String> stringList = new ArrayList<>();
	
	// temporary variables 
	public static String nodeString = null;
	public static String curLine = null;
	public static String nextLine = null;
	public static String prevLine = null;
	
	
	// Node Sytle attributes
	public static HeaderNode.NodeStyle nstyle;
/*	public static ListNode.ListNode nstyle;
	public static HeaderNode.NodeStyle nstyle;
	public static HeaderNode.NodeStyle nstyle;
*/
	
	// testing
	static String path = "C:" + File.separator + "Users" + File.separator + "Eunbee" + File.separator + "workspace" + File.separator + "file.txt";
	static File f = new File(path);
	
	
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
		
		System.out.println("\nprev: " + prevLine + "\n next: " + nextLine);
		
		// check if nextLine starts the node.
		if(isStart(nextLine))
		{
			System.out.println("This is a start\n");
			return;		// action: ���� nodeString���� new node �����ϰ�  NodeArr�� ����, nextLine�� ����� nodeString�� ����
		}


			
		// check if nextLine ends the node.	
		if(isEnd(nextLine))
		{
			System.out.println("This is an end\n");
			return;		// action: ���� nodeString�� �ڽ��� ���ϰ�  �� nodeString���� new node ����, NodeArr�� �߰�.
		}
	
		
	

			// RULES!!!!!!  ���߿� �����մϴپ�.... 	
	
		
	}

	
	public static boolean isStart(String line)
	{		
		// HEADERS : #���� �����ϴ� header��
		if(line.startsWith("# ")||line.startsWith("## ")||line.startsWith("### ")
					||line.startsWith("#### ")||line.startsWith("##### ")||line.startsWith("###### "))
		{
				
			// Node ����(����������  NodeString���� ) 		
			int hnum = HeaderNum(line, '#');			
			nstyle = HeaderNode.NodeStyle.values()[hnum];
			
			
			System.out.println("hn: " + hnum + ", and nstyle : " + nstyle);
			
			Node node = new HeaderNode(line,nstyle);
			
			// Array�� �߰�
			doc.nodes.add((Node)node);
			
			// ���ο� Node ����
			nodeString = nodeString + line; 	// nodeString update
			
			return true;
		}

	/*	// ORDER/UNORDER LIST: ���� ù ���� ��츸!
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
		
		*/
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
			if(line.charAt(i) == '-')
			{
				count_2++;
			}
		}
		if(count_1 == line.length() || count_2 == line.length())
		{
			if(count_1 == line.length()) // '='
			{
				nstyle = HeaderNode.NodeStyle.values()[0];
				
			}
			else if(count_2 == line.length())
			{
				nstyle = HeaderNode.NodeStyle.values()[1];
			}
			
			// Node ����		
			nodeString = prevLine + line; 	// nodeString update			
			Node node = new HeaderNode(line,nstyle);
			
			// Array�� �߰�
			doc.nodes.add((Node)node);
			
			return true;
		}
		else
			return false;
	}
	public static int HeaderNum( String s, char c ) // 
	  {
	    int counter = 0;
	    boolean foundOne = false;
	    for( int i = 0; i < s.length(); i++)
	    {
	      if( s.charAt(i) == c )
	      {
	        counter += 1;
	        foundOne = true;
	      }
	      else {
	        if(foundOne) break;
	      }
	    }
	    return counter;
	  }
	
	
	
/*	// new node ����, array�� �߰�
	public static void createNode(String ns, Node.NodeType type)
	{
		//create Node and set its type.
		Node node = new Node(ns);
		
		node.setNodeType(type);
		
		
		//dd it to the nodeArray in Document Object.
		doc.nodes.add(node);
		
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
*/
	
	public void parser(File Inputfile) {
		
		String line = null;
		// ���� �� �پ� �о stringList array�� ����
		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int count = 0;
			while((line = bufferedReader.readLine()) != null) 
			{
				stringList.add(line);
				count++;
			} 
			
			
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
		MDParser mdp = new MDParser(f);
		
			
		
	}
}
