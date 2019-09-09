package vn.topica.itlab4.homework_9.exercise2;

public class Content {
	private short tags;
	private short length;
	private String value;
	public short getTags() {
		return tags;
	}
	public void setTags(short tags) {
		this.tags = tags;
	}
	public short getLength() {
		return length;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Content(short tags, String value) {
		super();
		this.tags = tags;
		this.length =  (short) value.length();
		this.value = value;
	}
	@Override
	public String toString() {
		return "Content [tags=" + tags + ", length=" + length + ", value=" + value + "]";
	}
	
	
	
}
