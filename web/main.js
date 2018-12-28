function betolt(){
    var DIV = document.getElementById("konyvek");
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"konvyLista"},
       success:function(valasz){
            for (var i = 0; i < valasz.length; i++) {
                DIV.innerHTML += "<div>";
                    DIV.innerHTML += "<span>" + valasz[i].id + "</span>";
                    DIV.innerHTML += "<span>" + valasz[i].cim + "</span>";
                    DIV.innerHTML += "<span>" + valasz[i].szerzo + "</span>";
                    DIV.innerHTML += "<span>" + valasz[i].mufaj + "</span>";
                DIV.innerHTML += "</div>";
            }
       },
       error:function(){
           alert("Könyv hiba");
       }
    });
}

// új
function ujkonyv(){
    var uja = document.getElementById("konyvcim").value;
    var ujb = document.getElementById("konyvszerzo").value;
    var ujc = document.getElementById("konyvmufaj").value;
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"ujKonyv", "cim":uja, "szerzo":ujb, "mufaj":ujc},
        success:function(valasz){
            valasz.success;
        },
        error:function(){}
    });
    setTimeout(function()
    { location.reload(true); }, 1000);
}

function modkonyv(){
    var moda = document.getElementById("modid").value;
    var modb = document.getElementById("modcim").value;
    var modc = document.getElementById("modszerzo").value;
    var modd = document.getElementById("modmufaj").value;
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"modKonyv", "id":moda, "cim":modb, "szerzo":modc, "mufaj":modd},
       success:function(valasz){
         valasz.success;
       },
       error:function(){}
    });
    setTimeout(function()
    { location.reload(true); }, 1000);
}

function torolkonyv(){
    var torola = document.getElementById("konyvid").value;
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"torolKonyv", "id":torola},
       success:function(valasz){
           valasz.success;
       },
       error:function(){}
    });
    setTimeout(function()
    { location.reload(true); }, 1000);
}


