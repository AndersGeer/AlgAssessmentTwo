package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import javax.tools.DocumentationTool.Location;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import models.User;

public class JSONSerializer implements Serializer
{
	private File file;
	private Stack<Object> stack = new Stack<Object>();

	public JSONSerializer(File file)
	{
		this.file = file;
	}

	public void push(Object o)
	{
		stack.push(o);
	}

	public Object pop()
	{
		return stack.pop(); 
	}


	public void read() throws Exception
	{
		ObjectInputStream is = null;

		try
		{
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.alias("user", User.class);
			is = xstream.createObjectInputStream(new FileReader(file));
			stack = (Stack) is.readObject();
		}
		finally
		{
			if (is != null)
			{
				is.close();
			}
		}
	}

	public void write() throws Exception
	{
		ObjectOutputStream os = null;

		try
		{
			XStream xstream = new XStream(new JettisonMappedXmlDriver());
			xstream.alias("user", User.class);
			os = xstream.createObjectOutputStream(new FileWriter(file));
			os.writeObject(stack);
		}
		finally
		{
			if (os != null)
			{
				os.close();
			}
		}
	}
}
