package au.moodflip.cardgame.model;

public class CardPlayHistoryItem {
	public CardPlayHistoryItem(String date, String points, String complete){
		this.date = date;
		this.points = points;
		this.complete = complete;
	}
	
	private String date;
	private String points;
	private String complete;
	
	public String getDate() {
		return date;
	}
	public String getPoints() {
		return points;
	}
	public String getComplete() {
		return complete;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("date:" + date + " ");
		buffer.append("pts:" + points + " ");
		buffer.append("complete:" + complete + " ");
		return buffer.toString();
	}
}
