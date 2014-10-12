package au.moodflip.cardgame.model;

import java.beans.PropertyEditorSupport;

public class TaskPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		System.out.println("taskpropertyeditor Str->Obj:" + text);
		Task task = new Mission();
		setValue(task);
	}
	
//	@Override
//    public String getAsText() {
//		System.out.println("taskpropertyeditor Obj->Str: " + this.getValue().getClass().getName());
////        Mission m = (Mission) this.getValue(); this.
//		if (this.getValue() instanceof Mission)
//			System.out.println("instance of mission");
//		if (this.getValue() instanceof Task)
//			System.out.println("instance of task");
//        return "thisIsATest";
//    }
}
