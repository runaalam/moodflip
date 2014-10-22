package au.moodflip.cardgame.model;

import java.io.Serializable;

import javax.persistence.Entity;
//
@Entity
public class CardSurvey extends Task implements Serializable{
	private static final long serialVersionUID = 1L;
	public enum Answer{
		COMPLETELY_DISAGREE("Completely Disagree"), 
		DISAGREE("Disagree"), 
		NEUTRAL("Neutral"), 
		AGREE("Agree"), 
		COMPLETELY_AGREE("Completely Agree");
		
		private final String text;
		private Answer(final String text){
			this.text = text;
		}
		
		public String getText(){
			return this.text;
		}
	}
	
	public CardSurvey() { }
	public CardSurvey(String q){
		setQuestion(q);
	}
	
	public String getQuestion() { return question; }
	public void setQuestion(String question) { this.question = question; }
	private String question;

	public Answer getAnswer() { return answer; }
	public void setAnswer(Answer answer) { this.answer = answer; }
	private Answer answer;
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("taskId[" + getTaskId() + "] ");
		buffer.append("question[" + question + "] ");
//		buffer.append("answer[" + answer.getText() + "]");
		return buffer.toString();
	}
}
