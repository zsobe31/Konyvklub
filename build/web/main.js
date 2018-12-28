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

function belep(){
    var a = document.getElementById("nev").value;
    var b = document.getElementById("jelszo").value;
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"login", "nev":a, "jelszo":b},
       success:function(valasz){
           valasz.result;
            if (valasz.success == 1) {
                window.location = "konyvklub.html";
            }
       },
       error:function(){
           alert("Belépés hiba");
       }
    });
}

function regisztral(){
    var a = document.getElementById("ujnev").value;
    var b = document.getElementById("ujjelszo").value;
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"ujTag", "nev":a, "jelszo":b},
       success:function(valasz){
           valasz.result;
       },
       error:function(){
           //alert("reg hiba");
       }
    });
}


