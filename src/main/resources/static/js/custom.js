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
        type: "SendForm" ,
        url: "/basket",
        method : "POST"
    } ,
    currency: 	"TND",
    cartStyle : "table",
    cartColumns: [

        /* Picture (same for every product right now) */
        { view: function( item, column) {
                return " <img src='"+ item.get("thumb")+"' alt=\"notfound\"> ";
            }, label: "Product" ,attr : "thumb" },
        /* Name */
        { attr: "name", label: false  ,view : function (item,col) {
            return "<a href='/product-details?id="+item.get("product")+"' >"+item.get("name")+"</a>";

            }},

        {view : function (item,col) {
                return "<input type=\"hidden\" name='ids[]' value='"+item.get("product")+"' />"
            } , attr : "product"},
        /* Quantity */
        {view : function (item,col) {
                return "<input type=\"hidden\" name='qts[]' value='"+item.quantity()+"' />"

            } ,label : false},
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

function updateShipping(x) {
    simpleCart.shipping(function () {
        return x;
    })
    simpleCart.save();
    simpleCart.update();
}
$(document).ready(function () {
    var rad = $("[name='delivery']");
    var prev = null;
    for(var i = 0; i < rad.length; i++) {
        var x =function() {
            (prev)? console.log(prev.value):null;
            if(this !== prev) {
                prev = this;
            }
            switch(this.value) {
                case "Cockroach delivery service":{
                    updateShipping(5)
                    break;
                }
                case "DoggyDoggo postcat":{

                    updateShipping(15)
                    break;

                }
                case "Railrats company":{

                    updateShipping(30)
                    break;
                }

            }
        };
        rad[i].onclick = x;
    }
})
