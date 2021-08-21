function login(){
    var data=Object();
    data.username=$('#username').val();
    data.password=$('#password').val();


    $.ajax({
        url: '/loginUser',
        dataType: 'JSON',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
    //    processData: false,
   //     async: false,
        success: function( data){

            console.log(data);
            sessionStorage.setItem("username", data.username);

            if(data.role=="PARTY")
                location.href= "/partyDashboard";
            else if(data.role=="BANK")
                location.href= "/bankDashboard";
        },
        error: function(e){
            console.log( e );

        }
    });
}
