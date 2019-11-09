package Request;

import java.io.IOException;
import java.nio.charset.Charset;

public class StringRequest extends Request{
	
	//public IRequest next = new JPGRequest();;
	
	public StringRequest() 
	{
		this.type = 48; // 0 to bedzie string , 1 to bedzie jpg
		this.next = new JPGRequest(); // tu bedzie JPGRequest
	}
	
	@Override
	public IRequest request(byte[] codedContent) //dekoduje
	{
		
		if(!this.type.equals(codedContent[0]))
		{	
			return (IRequest) this.next.handleRequest(codedContent);
		}
		else
		{
			System.out.println("tekst");
			this.data = codedContent;
		    for(int i = 1; i < codedContent.length; i++)
		    {
		    	System.out.print((char)codedContent[i]);
		    }
		    System.out.println();
			return this;
		}
	}

	@Override
	public byte[] code(String content) throws IOException 
	{
		String tekst = "0" + content;
		byte[] bytes = tekst.getBytes(Charset.forName("UTF-8"));
		this.data = bytes;
	    return bytes;
	}


}
