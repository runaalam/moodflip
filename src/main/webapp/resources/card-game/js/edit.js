/**
 * Edit.js 
 * Add/remove mission text areas and adjusts attributes so it plays nice with Spring binding to Mission class 
 */



$(document).ready(function() {
		$("#missionsDiv").on('click', "#addMissionBtn", function(){
			var $newMission = $(".aMissionDiv:last").clone();
			var missionCount = $(".aMissionDiv").length + 1; // mission count including the one we add in here
			
			// Changes label, textarea attrs with new mission number  
			$newMission.find("input").filter(":hidden").attr("name", function(i, old){ return old.replace(/[\d]+/g, missionCount-1) });
			$newMission.find("input").filter(":hidden").attr("id", function(i, old){ return old.replace(/[\d]+/g, missionCount-1) });
//			$newMission.find("input").filter(function() { return this.id.match(/^missions\d+\.taskId$/); }).attr("value", function(i, old){ return +old + 1 });
//			$newMission.find("input").filter(function() { return this.id.match(/^missions\d+$/); }).removeAttr("value")
			$newMission.find("input").filter(":hidden").attr("value", 0);
			$newMission.find("label").attr("for", function(i, old){ return old.replace(/[\d]+/g, missionCount) }); 
			$newMission.find("label").text(function(i, old){ return old.replace(/[\d]+/g, missionCount) });
			$newMission.find("textarea").attr("id", function(i, old){ return old.replace(/[\d]+/g, missionCount) });
			// name attr is zero based array so missionCount - 1
			$newMission.find("textarea").attr("name", function(i, old){ return old.replace(/[\d]+/g, missionCount-1) });
			$newMission.find("textarea").text(""); 
			$("#missionBtnDiv").remove(); // delete buttons on prev mission div 
			$newMission.appendTo($("#missionsDiv")).hide().slideDown();
			if (missionCount == 2){
				$("#delMissionBtn").removeAttr("disabled");
			}
		});
		
		$("#missionsDiv").on('click', "#delMissionBtn", function(){
			$(".aMissionDiv:nth-last-child(2)").append($("#missionBtnDiv")); // add add/del button in 2nd last mission box
			var missionCount = $(".aMissionDiv").length - 1; // mission count after deleting this one
			if (missionCount == 1){
				$("#delMissionBtn").attr("disabled", "disabled");	// disable delete button
			}
			$(".aMissionDiv:last").slideUp(null, function(){ $(this).remove(); });
			
		});
 
});

jQuery.expr[':'].regex = function(elem, index, match) {
    var matchParams = match[3].split(','),
        validLabels = /^(data|css):/,
        attr = {
            method: matchParams[0].match(validLabels) ? 
                        matchParams[0].split(':')[0] : 'attr',
            property: matchParams.shift().replace(validLabels,'')
        },
        regexFlags = 'ig',
        regex = new RegExp(matchParams.join('').replace(/^\s+|\s+$/g,''), regexFlags);
    return regex.test(jQuery(elem)[attr.method](attr.property));
}
