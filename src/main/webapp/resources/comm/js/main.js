var moodFlip = angular.module('moodFlip', ['ngSanitize']);

var appUrl = "/moodflip";

moodFlip.filter('markdown', function() {

    var converter = new Showdown.converter();

    return function(markdown) {

        if (!markdown)
            return ;

        return converter.makeHtml(markdown) ;
    }
}) ;