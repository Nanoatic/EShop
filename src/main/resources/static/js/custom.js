$(document).ready(function() {
    $('#regForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {

            rpassword: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    },
                    callback: {
                        callback: function(value, validator, $field) {
                            var password = $field.val();
                            if (password == '') {
                                return true;
                            }

                            var result  = zxcvbn(password),
                                score   = result.score,
                                message = result.feedback.warning || 'The password is weak';

                            // Update the progress bar width and add alert class
                            var $bar = $('#strengthBar');
                            switch (score) {
                                case 0:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '1%');
                                    break;
                                case 1:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '25%');
                                    break;
                                case 2:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '50%');
                                    break;
                                case 3:
                                    $bar.attr('class', 'progress-bar progress-bar-warning')
                                        .css('width', '75%');
                                    break;
                                case 4:
                                    $bar.attr('class', 'progress-bar progress-bar-success')
                                        .css('width', '100%');
                                    break;
                            }

                            // We will treat the password as an invalid one if the score is less than 3
                            if (score < 3) {
                                return {
                                    valid: false,
                                    message: message
                                }
                            }

                            return true;
                        }
                    }
                }
            }
        }
    });
});
$(document).ready(function() {
    $('#passForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {

            newpassword: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    },
                    callback: {
                        callback: function(value, validator, $field) {
                            var password = $field.val();
                            if (password == '') {
                                return true;
                            }

                            var result  = zxcvbn(password),
                                score   = result.score,
                                message = result.feedback.warning || 'The password is weak';

                            // Update the progress bar width and add alert class
                            var $bar = $('#rstrengthBar');
                            switch (score) {
                                case 0:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '1%');
                                    break;
                                case 1:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '25%');
                                    break;
                                case 2:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '50%');
                                    break;
                                case 3:
                                    $bar.attr('class', 'progress-bar progress-bar-warning')
                                        .css('width', '75%');
                                    break;
                                case 4:
                                    $bar.attr('class', 'progress-bar progress-bar-success')
                                        .css('width', '100%');
                                    break;
                            }

                            // We will treat the password as an invalid one if the score is less than 3
                            if (score < 3) {
                                return {
                                    valid: false,
                                    message: message
                                }
                            }

                            return true;
                        }
                    }
                }
            }
        }
    });
});
$(document).ready(function(){
    var date_input=$('input[name="date"]'); //our date input has the name "date"
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options={
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    };
    date_input.datepicker(options);
});
$(document).ready(function() {
    $('#resetForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {

            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    },
                    callback: {
                        callback: function(value, validator, $field) {
                            var password = $field.val();
                            if (password == '') {
                                return true;
                            }

                            var result  = zxcvbn(password),
                                score   = result.score,
                                message = result.feedback.warning || 'The password is weak';

                            // Update the progress bar width and add alert class
                            var $bar = $('#resetstrengthBar');
                            switch (score) {
                                case 0:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '1%');
                                    break;
                                case 1:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '25%');
                                    break;
                                case 2:
                                    $bar.attr('class', 'progress-bar progress-bar-danger')
                                        .css('width', '50%');
                                    break;
                                case 3:
                                    $bar.attr('class', 'progress-bar progress-bar-warning')
                                        .css('width', '75%');
                                    break;
                                case 4:
                                    $bar.attr('class', 'progress-bar progress-bar-success')
                                        .css('width', '100%');
                                    break;
                            }

                            // We will treat the password as an invalid one if the score is less than 3
                            if (score < 3) {
                                return {
                                    valid: false,
                                    message: message
                                }
                            }

                            return true;
                        }
                    }
                }
            }
        }
    });
});
function initialize() {
    var mapOptions = {
        zoom: 15,
        center: new google.maps.LatLng(35.886767, 10.534198),
        mapTypeId: google.maps.MapTypeId.ROAD,
        scrollwheel: false
    };
    var map = new google.maps.Map(document.getElementById('map'),
        mapOptions);

    var myLatLng = new google.maps.LatLng(35.886767, 10.534198);
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map
    });
}

google.maps.event.addDomListener(window, 'load', initialize);

simpleCart.bind("afterCreate", function(){
    $cart_table = $(".simpleCart_items table")
    $cart_table.addClass("table")
});
simpleCart.currency({
    code: "TND" ,
    name: "Tunisian Dinar" ,
    symbol: " DT" ,
    delimiter: " " ,
    decimal: "." ,
    after: true ,
    accuracy: 2
});
simpleCart({
    checkout: {
        type: "PayPal" ,
        email: "nanoaticorgix@hotmail.com"
    } ,
    currency: 	"TND",

    cartStyle : "table",
    cartColumns: [

        /* Picture (same for every product right now) */
        { view: function( item, column) {
                return " <img src='"+ item.get("thumb")+"' alt=\"notfound\"> ";
            }, label: "Product" ,attr : "thumb" },
        /* Name */
        { attr: "name", label: false },


        /* Quantity */
         {attr:'quantity', label: "Quantity" } ,
        { view: "decrement" , label: false , text: "<i class=\"fa fa-minus\"></i>" } ,
        { view: "increment" , label: false , text: "<i class=\"fa fa-plus\"></i>" } ,

        /* Price */
        { attr: "price" , label: "Unit price", view: 'currency' } ,
        /* Total */
        { attr: "total" , label: "Total", view: 'currency' } ,
        /* Remove */
        { view:  'remove'
            , text: "Remove" , label: false , text : '<i class="fa fa-trash-o"></i>'}
    ]

});

