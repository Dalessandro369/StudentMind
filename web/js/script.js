/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Editer(id) {
            $(document).ready(function(){
                $(".editer").hide();
                $("#lineCafe"+id+" .annuler , #lineCafe"+id+" .modifier").show();
                $("#cafeNom"+id).removeAttr("disabled");
                $("#cafeDescription"+id).removeAttr("disabled");
                $("#cafeType"+id).removeAttr("disabled");
                $("#cafeStock"+id).removeAttr("disabled");
                $("#cafePrix"+id).removeAttr("disabled");
            });
}

function Supprimer() {
    alert("e");
}

function Modifier() {
 
}
