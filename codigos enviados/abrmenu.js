/*Este script, checa se existe algum parâmetro passado após # na URL, se houver, ele abre este item;
*/
/* <![CDATA[ */
function abre_menu(){
var url=window.location.href.split('#');
if(url[1]!==undefined){
abreitem(url[1]);
}
}
abre_menu();/* ]]> */
/*Este script, checa se existe algum parâmetro passado após # na URL, se houver, ele abre este item;
*/