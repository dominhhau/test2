package model;

public class GuestBookEntry{
	Integer id;
	
	String name;
	
	String message;
	
	public GuestBookEntry(Integer id, String name, String message)
	{
		this.id=id;
		this.name=name;
		this.message=message;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}