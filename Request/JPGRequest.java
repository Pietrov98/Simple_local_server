package Request;

import java.io.IOException;

public class JPGRequest extends Request{
	
	public JPGRequest() 
	{
		this.type = 49; // 1 to bedzie string , 0 to bedzie jpg
	}
	
	@Override
	public IRequest request(byte[] codedContent) 
	{
		if(!this.type.equals(codedContent[0]))
		{	
			this.next = new StringRequest();
			return (IRequest) this.next.handleRequest(codedContent);
		}
		else
		{
			System.out.println("zdjecie");
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
		String tekst = "1" + content;
		byte[] bytes = tekst.getBytes();
	    return bytes;
	}

}
