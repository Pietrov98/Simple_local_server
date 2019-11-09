package Request;

import java.io.IOException;

public abstract class Request implements IRequest{
	
	protected Byte type;
	protected byte[] data;
	protected IRequest next;
	
	public IRequest handleRequest(byte[] class1)
	{
		return (IRequest) this.request(class1);
	}
	
	public abstract IRequest request(byte[] codedContent);
	
	public byte[] getData()
	{
		return this.data;
	}
	public abstract byte[] code(String content) throws IOException;
}
