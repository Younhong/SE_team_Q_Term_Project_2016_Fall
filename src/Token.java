
public class Token {
	
	// Token type���� ������ Enum list
	public enum TokenType{
		// TO-DO 
			// implement the details
			// add more types (�� ������..)
		PLAIN, STYLE, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	    static {
	    }
	}
	
	//ATTRIBUTES!!!
	private TokenType tokenType;	// token�� type
	public String newString; // markdown syntax�� ���ŵ� raw string�̴�.
		
	
	//OPERATIONS!!!
	public void setTokenType(TokenType tt)
	{
		this.tokenType = tt;
	}
	
	public TokenType getNodeType()
	{
		return tokenType;
	}

	public void simplifyString(String s)//md syntax�� ���ְ�(�����ϴ�)method�̴�.
	{
		// TO-DO
			// implement the details.
		newString = "";
	}
	
	public Token createToken(String s)
	{
		// TO-DO
			// ������ ��.
		return new Token();
	}
	
	
	
	
	
	
	public static void main(String[] args)
	{
		//TO-DO 
		Token token = new Token();
	}

}
