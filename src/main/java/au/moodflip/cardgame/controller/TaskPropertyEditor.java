package au.moodflip.cardgame.controller;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.moodflip.cardgame.model.Mission;
import au.moodflip.cardgame.model.Task;

public class TaskPropertyEditor extends PropertyEditorSupport {
	private static final Logger logger = LoggerFactory.getLogger(TaskPropertyEditor.class);
	
	/** card stores a List<Task> because it needs to store both CardSurvey and Mission instances.
	  * We intercept the str->obj bind so that instead of return Task, it returns Mission instances
	  */
	@Override
	public void setAsText(String text) {
		logger.info("taskpropertyeditor Str->Obj:" + text);
		Task task = new Mission();
		task.setTaskId(Long.valueOf(text));
		setValue(task);
	}
	
	// taskId enables correctly updating persist instances
	@Override
    public String getAsText() {
		logger.info("taskpropertyeditor Obj->Str: " + this.getValue().getClass().getName());
		String ret = "";
		if (this.getValue() instanceof Mission){
			Mission m = (Mission)this.getValue();
			ret = String.valueOf(m.getTaskId());
		}
		return ret;
    }
}