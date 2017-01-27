var CHAT = (function(chat){
    $(function(){
        $.ajaxSetup({contentType: 'application/json'});
        init();
        
    });

    function init(){
    	attachListenerToSendBtn();
        displayAllMessages();
        displayAllUsers();
        autoCompleteSender();
        autoCompleteRecvi();
        getMessagesFromRemote();
        deleteAllMessages();
    };
    

    function displayAllMessages(){
        $.get('cars', function( data ) {
        	
            for(var i=0;i<data.length;i++){
                appendMessage(data[i]);
            }
        });
    };
    function displayAllUsers(){
        $.get('api/users', function( data ) {
            for(var i=0;i<data.length;i++){
                appendUser(data[i]);
            }
        });
    };
    function attachListenerToSendBtn(){
        $('#send-btn').click(function(){
            message = {
                senderId: $('#sender-id').val(),
                receiverId: $('#receiver-id').val(),
                body: $('#content').val()
              
            };
            $.get('api/users', function( data ) {
            	var found1 = $.inArray(message.senderId, data);
            	var found2 = $.inArray(message.receiverId, data);
            	if(found1>0 && found2>0 ){
            		$.post('message', JSON.stringify(message), function(data){
                        console.log('message successfully sent ' + JSON.stringify(data));
                        appendMessage(data);
                    }, 'json');
            		
            		
            	}else{
            		alert("Wprowadz poprawnych uzytwkonikow")
            		 
            	}
            });
            $('#sender-id').val("");
            $('#receiver-id').val("");
            $('#content').val("");
           
        });
       
        
    };
    
    function autoCompleteSender(){
    	$(function () {
    	   
    	    $.getJSON("api/users", function (data) {
    	       
    	        $("#sender-id").autocomplete({
    	            source: data
    	        });
    	    });
    	});
    	
    };
    function autoCompleteRecvi(){
    	$(function () {
    
    	    $.getJSON("api/users", function (data) {
    	       
    	        $("#receiver-id").autocomplete({
    	            source: data
    	        });
    	    });
    	});
    	
    };
    function getMessagesFromRemote(){
    	
    	    setInterval(function(){ 
    	    	
    	    	
    	    		$.get('message/remote', function( data ) {
    	             for(var i=0;i<data.length;i++){
    	                 appendMessage(data[i]);
    	                 console.log("getFromRemoteSUcces")
    	             }
    	         });
    	    
    	    
    	    
    	    }, 3000);
  
    };
 

    function appendMessage(data){
       
            $('.list-group').append(data);
     
    };
   

    function appendUser(user){
       
    	$('.offers list shop').append('<div class="offers list shop"> <article class="offer-item"> <div class="offer-item__photo "> <a class="offer-item__photo-link" title="'
    			+ array[0].title+ '"href="https://www.otomoto.pl/oferta/opel-meriva-1-4-turbo-140-km-cosmo-serwisowana-w-aso-1-rok-gwarancji-ID6yI8iR.html" style="background-image:url('
    			+ array[0].imgaUrl+');"></a> </div> <div class="offer-item__content"> <div class="offer-item__title"> <h2 class="offer-title offer-title--short"> <a data-ad-id="0" class="offer-title__link" href="https://www.otomoto.pl/oferta/opel-meriva-1-4-turbo-140-km-cosmo-serwisowana-w-aso-1-rok-gwarancji-ID6yI8iR.html" title="'
    			+ array[0].title + '">'
    			
    	
    	
    	);
    };
    function deleteAllMessages(){
        
        $("#delete-all-btn").click(function(){
     	   $.get("message/delete",function(){
     		   location.reload();
     		   
     	   });
     	   
        });
 };
    
 

    function formatDate(epochDate){
        return new Date(epochDate);
    };

    return {};
})(CHAT || {});