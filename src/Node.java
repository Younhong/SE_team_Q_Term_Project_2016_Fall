import java.util.ArrayList;

public class Node {
	
	// node object�� mapping �� node type���� ������ Enum list
	public enum NodeType{
		// TO-DO 
			//implement the details
			//add more types (�� ������..)
		HEADER, LIST, Q_BLOCK, BLOCK, HORIZONTAL_, ORDER, UNORDER, ITEM_LIST;	
	    static {
	    }
	}
	
	//ATTRIBUTES!!!!!
	static ArrayList<Token> tokens; // token ����Ʈ
	private NodeType nodeType;	// node�� type
	public String tempS; // divideToken method�� parameter�� ���� update�� string�̴�.
	
	
	
	//OPERATIONS!!!!!!! ��ȯ������ �Ͻ� �κ�!! 
	//  �ʿ信 ���� parameter�� return type �˾Ƽ� �ٲٽø� �� �� ���ƿ�!!������
	public void divideToken(String s) // string s�� ��� ������Ʈ ��. 
	{
	
		
		tempS = ""; //���ο� string���� ������Ʈ: �ֱٿ� ������ token�� string�� �� �������� ��ü string ������
	}
	
	public void setNodeType(NodeType nt)
	{
		this.nodeType = nt;
	}
	
	public NodeType getNodeType()
	{
		return nodeType;
	}

	public Node createNode(String s)	// Node object �����ϴ� op. 
	{
		//TO-DO �����Ұ�*
		return new Node(); 	
	}

	
	
	public static void main(String[] args)
	{
		
		Node node = new Node();
	}

}
