 * Tellez Campos Luis Felipe
 * Toral Maldonado Rosa Guadalupe


#INSTRUCCIONES

El archivo a ingresar debe contener las especificaciones de la m�quina en un solo rengl�n.
Debe estar descrito de la siguiente manera:
	|Conjunto de transisiones(|Estado(i),S�mbolo(i),Estado(i+1),S�mbolo(i+1),Movimiento|)|Cinta|N�mero de estados|Alfabeto Sigma|Alfabeto Gama|Estado Inicial|Posici�n inicial

Por ejemplo:
	|q0,0,q1,1,R|q0,1,h,1,R|q1,0,q2,0,R|q1,1,q1,1,R|q2,0,q2,1,L|q2,1,q0,1,L|0000000000000|3|01|01|q0|5

Todos los estados, excepto el estado final, debe estar escrito por un caracter cualquiera seguidp de un n�mero.
El estado final debe insertarse como una h.

Para leer la direcci�n del archivo esta debe escribirse siguiendo el siguiente ejemplo:
C:/Users/Las lokis/Desktop/prueba.txt

Los archivos deben estar en formato txt.
Hay dos archivos de ejmplo disponibles:
Maquina2g.txt
Maquina1.txt

Una vez ingresada la direcci�n debe presionarse la tecla enter.

BOTONES

Limpiar: libera el contenido de la ventana.
Siguiente: Al dar click, muestra un solo paso.
Saltar Pasos: Pide un n�mero al usuario y se salta ese n�mero de pasos.
Salir: cierra la ventana.

NOTA
Los archivos Maquina1 y Maquina2 son ejemplos de M�quinas de Turring