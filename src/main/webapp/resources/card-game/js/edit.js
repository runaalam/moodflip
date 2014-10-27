/**
 * Edit.js 
 * Add/remove mission text areas and adjusts attributes so it plays nice with Spring binding to Mission class 
 * Form validator - client side
 */

$(document).ready(function() {
	// all mission textarea must have name='tasks[].text' for bootstrap validator to work. 
	$('textarea').filter(function(){ return this.name.match(/tasks\[\d+\].text/); }).attr("name", "tasks[].text");

	// dynamic add/remove mission textarea
		$("#missionsDiv").on('click', "#addMissionBtn", function(){
			var $newMission = $(".aMissionDiv:last").clone();
			var missionCount = $(".aMissionDiv").length + 1; // mission count including the one we add in here
			
			// Changes label, textarea attrs with new mission number
			// hidden input is the task element. ie task[0]
			$newMission.find("input").filter(":hidden").attr("name", function(i, old){ return old.replace(/[\d]+/g, missionCount-1) }); 
			$newMission.find("input").filter(":hidden").attr("id", function(i, old){ return old.replace(/[\d]+/g, missionCount-1) });
			$newMission.find("input").filter(":hidden").attr("value", 0); // set taskId to 0 for new missions
			$newMission.find("label").attr("for", function(i, old){ return old.replace(/[\d]+/g, missionCount) }); 
			$newMission.find("label").text(function(i, old){ return old.replace(/[\d]+/g, missionCount) });
			$newMission.find("textarea").attr("id", function(i, old){ return old.replace(/[\d]+/g, missionCount) });
			$newMission.find("textarea").attr("name", "tasks[].text");
			$newMission.find("textarea").text(""); 
			$("#missionBtnDiv").remove(); // delete buttons on prev mission div 
			$newMission.appendTo($("#missionsDiv")).hide().slideDown();
			if (missionCount == 2){
				$("#delMissionBtn").removeAttr("disabled");
			}
			
			$('.editCardForm').bootstrapValidator('addField', $newMission.find('textarea')); 
		});
		
		$("#missionsDiv").on('click', "#delMissionBtn", function(){
			$(".aMissionDiv:nth-last-child(2)").append($("#missionBtnDiv")); // add add/del button in 2nd last mission box
			$lastMission = $(".aMissionDiv:last");
			var missionCount = $(".aMissionDiv").length - 1; // mission count after deleting this one
			if (missionCount == 1){
				$("#delMissionBtn").attr("disabled", "disabled");	// disable delete button
			}
			$lastMission.slideUp(null, function(){ $(this).remove(); });
			$('.editCardForm').bootstrapValidator('removeField', $lastMission.find('textarea')); 
		});
		
	// form validation
	$('.editCardForm').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			title: {
				message: "This value is invalid",
				validators: {
					notEmpty:  {
						message: "Title cannot be empty"
					}
				}
			},
			level: {
				message: "This value is invalid",
				validators: {
					notEmpty: {
						message: "Level cannot be empty"
					},
					between: {
						min: 1,
						max: 7,
						message: "Level must be between 1 and 7 inclusive"
					},
					integer: {
						message: "Level must be an integer"
					}
				}
			},
			symptom: {
				message: "This value is invalid",
				validators: {
					notEmpty: {
						message: "A symptom must be chosen"
					}
				}
			},
			intro: {
				message: "This value is invalid",
				validators: {
					notEmpty: {
						message: "Introduction cannot be empty"
					}
				}
			},
			'tasks[].text': {
				message: "This value is invalid",
				validators: {
					notEmpty: {
						message: "Mission cannot be empty"
					}
				}
			},
			outro: {
				message: "This value is invalid",
				validators: {
					notEmpty: {
						message: "Completion message cannot be empty"
					}
				}
			}
		}
	})
	.on('success.form.bv', function(e) { // intercept when submit button clicked 
		$form = $(this).parent();
		$('textarea[name="tasks\\[\\].text"]').each(function(index){
			$(this).attr("name", "tasks[" + index + "].text"); // repopulate array with index before sending request
		});
		
		$form.submit();
// override submit here to make ajax call instead
//				function(e){
//			
//			e.preventDefault();
//		});
		
	});
});