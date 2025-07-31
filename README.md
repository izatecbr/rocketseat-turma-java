catalogar / listar
* validar se o arquivo existe
* validar se temos permissões correspondentes
* extensao
* validacao tamanho
* formatar data
* formatar valor monetario
* forma de pagamento inexistente
* layout inconsiste
* delimitador
* posições com conformidade ao layout

layout já possui falha

idTerminal;sequencia;data;valor;formaPagamento
T01;1;17/05/25;199,90;CCR
T01;2;17/05/25;45,50;DIN
T02;3;17/05/25;120,00;PIX
T02;4;17/05/25;120.00;DEB
T03;5;17/05/25;89.00;CCR
T03;6;17/05/25;10.00;DIN
T01;7;18/05/25;350.00;PIX
T02;8;18/05/25;50.00;DIN
T04;9;18/05/25;60.00;DEB
T05;10;18/05/25;99.90;CCR


1. inconsistencia no layout
2. bug antes do deploy
3. numero sequencial repetido (algoritimos de checagem)
4. tratamento da formatacao (AAAA-MM-DD)
5. datas incosistentes de forma temporal
6. valores igual a zero ou negativos
7. Parse Exception ou NumberFormatException -> Linha Tal está com o valor informado indevidamente, padrão de formatação 0.00
8.  


especificação, dominio tecnologia, registrar as ocorrencias (log)
