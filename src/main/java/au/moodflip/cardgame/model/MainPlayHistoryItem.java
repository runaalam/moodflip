package au.moodflip.cardgame.model;

public class MainPlayHistoryItem {
	public MainPlayHistoryItem(String cardId, String title, String points, String attempts, String completions){
		this.cardId = cardId;
		this.title = title;
		this.points = points;
		this.attempts = attempts;
		this.completions = completions;
	}
	private String cardId;
	private String title;
	private String points;
	private String attempts; 
	private String completions;
	
	
	public String getCardId() {
		return cardId;
	}


	public String getTitle() {
		return title;
	}


	public String getPoints() {
		return points;
	}


	public String getAttempts() {
		return attempts;
	}


	public String getCompletions() {
		return completions;
	}


	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("id:" + cardId + " ");
		buffer.append("title:" + title + " ");
		buffer.append("pts:" + points + " ");
		buffer.append("attempts:" + attempts + " ");
		buffer.append("completions:" + completions);
		return buffer.toString();
	}
}
