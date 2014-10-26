var moodFlip = angular.module('moodFlip', ['ngSanitize']);

moodFlip.filter('markdown', function() {

    var converter = new Showdown.converter();

    return function(markdown) {

        if (!markdown)
            return ;

        return converter.makeHtml(markdown) ;
    }
}) ;