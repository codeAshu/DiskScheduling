package edu.iiitb.model;

public class legacyModel {

	private int type;
	private int avg;
	private int no;
	private int head;

	public legacyModel(int type, int avg, int no, int head)
	{
		this.type =type;
		this.avg = avg;
		this.no = no;
		this.head = head;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}
}
