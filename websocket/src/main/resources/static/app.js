var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#respuesta").html("");
}

function connect() {
    var socket = new SockJS('/miServicio');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connectado: ' + frame);

        var canal=$("#canal")["0"].options[$("#canal")["0"].options.selectedIndex].value
        console.log(canal);
        stompClient.subscribe(canal, function (respuesta) {
            muestraRespuesta(JSON.parse(respuesta.body).content);
        });
    });
}

function muestraRespuesta(respuesta) {
    $("#respuesta").append("<tr><td>" + respuesta + "</td></tr>");
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var destino=$("#destino")["0"].options[$("#destino")["0"].options.selectedIndex].value
    console.log(destino);
    stompClient.send(destino, {}, JSON.stringify({'texto': $("#mensaje").val()}));
}


$(function () {
    $("form").on('submit', function (e) {
    	//No submitimos el formulario
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

