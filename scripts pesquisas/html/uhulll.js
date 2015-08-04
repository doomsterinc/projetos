var troll = prompt("Você esta caminhando pela floresta, cuidando da sua vida, e encontra um troll! Você luta com ele (FIGHT), paga a ele (PAY), ou foge (RUN)?").toUpperCase();

switch(troll) {
  case 'FIGHT':
    var strong = prompt("Que corajoso! Você é forte, sim (YES) ou não (NO)?").toUpperCase();
    var smart = prompt("Você é inteligente?").toUpperCase();
    if(strong === 'YES' || smart === 'YES') {
      console.log("Você precisa apenas de um dos dois! Você vence o troll -- bom trabalho!");
    } else {
      console.log("Voce não é forte NEM inteligente? Bem, se fosse mais inteligente, provavelmente nao teria tentado lutar com um troll. Voce perdeu!");
    }
    break;
  case 'PAY':
    var money = prompt("Tudo bem, vamos pagar ao troll. Voce tem dinheiro, sim (YES) ou nao (NO)?").toUpperCase();
    var dollars = prompt("Você tem dinheiro de troll?").toUpperCase();
    if(money === 'YES' && dollars === 'YES') {
      console.log("Ótimo! Você paga ao troll e continua seu passeio.");
    } else {
      console.log("Droga! Este troll só aceita dinheiro de troll. Você leva uma surra!");
    }
    break;
  case 'RUN':
    var fast = prompt("Vamos dar o fora! Voce é rapido, sim (YES) ou não (NO)?").toUpperCase();
    var headStart = prompt("Você comecou com vantagem?").toUpperCase();
    if(fast === 'YES' || headStart === 'YES') {
      console.log("Você conseguiu fugir -- por pouco! Você vai viver para passear pela floresta outro dia.");
    } else {
      console.log("Você não é rapido e não comecou com vantagem? Não tinha a menor chance! O troll devora você.");
    }
    break;
  default:
    console.log("Não entendi sua escolha. Clique em Executar e tente novamente, dessa vez escolhendo FIGHT, PAY, ou RUN!");
}