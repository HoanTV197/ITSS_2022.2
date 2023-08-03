package main.java.utils;

public class Notification {

	private String fromId;
	private String toId;
	private String content;
	
	public Notification(String fromId, String toId, String content) {
		super();
		this.fromId = fromId;
		this.toId = toId;
		this.content = content;
	}
	
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
