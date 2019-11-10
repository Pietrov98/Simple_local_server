package Request;

import java.io.IOException;

public class JPGRequest extends Request{
	
	public JPGRequest() 
	{
		this.type = 49; // 1 to bedzie zdjecie , 0 to bedzie string
	}
	
	@Override
	public IRequest request(byte[] codedContent) 
	{
		if(!this.type.equals(codedContent[0]))
		{	
			this.next = new StringRequest();
			return (IRequest) this.next.handleRequest(codedContent);
		}
		else if(this.type.equals(codedContent[0]))
		{
			System.out.println("Otrzymalem zdjecie");
			this.data = codedContent;
		    for(int i = 1; i < codedContent.length; i++)
		    {
		    	System.out.print((char)codedContent[i]);
		    }
		    System.out.println();
			return this;
		}
		return null;
	}

	@Override
	public byte[] code(String content) throws IOException
	{
		String tekst = "1" + content;
		byte[] bytes = tekst.getBytes();
	    return bytes;
	}

}
