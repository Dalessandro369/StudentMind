/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Editer(id) {
    $(document).ready(function(){
        $(".editer").hide(); //tous ceu qui on la class editer son cacher .show pour montrer .-> class #->id   ,-> ou
        $(".supprimer").hide();
        $("#ligne"+id+" .annuler").show();
        $("#ligne"+id+" .modifier").show();
        $("#nom"+id).removeAttr("disabled");
        $("#des"+id).removeAttr("disabled");
        $("#famille"+id).removeAttr("disabled");
    });
    document.getElementById("id"+id).value = "Modifier"
}


function Supprimer(id) {
    $(document).ready(function(){
        $(".editer").hide();
        $(".supprimer").hide();
        $("#ligne"+id+" .annuler").show();
        $("#ligne"+id+" .modifier").show();
        $("#ligne"+id+" .suppresionConfirmation").show();     
    });   
    document.getElementById("id"+id).value = "Supprimer"
//
}

function Modifier(id) {
    $(document).ready(function(){
        $("form[name='ligne"+id+"']").submit();
    }); 
}

function Ajouter() {
    $(document).ready(function(){
        $("form[name='ajouter']").submit();
    }); 
}

function Annuler(id) {
    $(document).ready(function(){        
        $("#des"+id).attr("disabled","disabled");
        $("#nom"+id).attr("disabled","disabled");
        $("#famille"+id).attr("disabled","disabled");
        $(".annuler , .modifier").hide();   
        $("#ligne"+id+" .suppresionConfirmation").hide();       
        $(".editer").show();
        $(".supprimer").show();
    });
}

function inputRecherche(){
    var valeurRecherche = document.getElementById("tags").value;
    
    id = valeurRecherche.split('-')[0];
    id = id.split(' ')[0];
    
    
    
   document.getElementById("tags").value = id;
    
    
    
    $(document).ready(function(){
        $("form[name='user']").submit();
    });  
    

}
