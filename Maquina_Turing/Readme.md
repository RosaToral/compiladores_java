 * Tellez Campos Luis Felipe
 * Toral Maldonado Rosa Guadalupe
 
 El algoritmo es para una máquina de Turing que funciona como busy beaver. Fue escrito por el profesor René MacKinney Romero y editado por nosotros para que funcione con cualquier autómata que se le agregue por medio de una interfaz gráfica


# INSTRUCCIONES

Debe tener instalado JDK ya que elejecutable es un archivo .jar

Debe ejecutar el archivo MaquinaTuringFinal.jar (puede ser dando doble clic)

El archivo a ingresar debe contener las especificaciones de la máquina en un solo renglón.

Debe estar descrito de la siguiente manera:
	|Conjunto de transisiones(|Estado(i),Símbolo(i),Estado(i+1),Símbolo(i+1),Movimiento|)|Cinta|Número de estados|Alfabeto Sigma|Alfabeto Gama|Estado Inicial|Posición inicial

Por ejemplo:
	|q0,0,q1,1,R|q0,1,h,1,R|q1,0,q2,0,R|q1,1,q1,1,R|q2,0,q2,1,L|q2,1,q0,1,L|0000000000000|3|01|01|q0|5

Todos los estados, excepto el estado final, debe estar escrito por un caracter cualquiera seguidp de un número.

El estado final debe insertarse como una h.

Para leer la dirección del archivo esta debe escribirse siguiendo el siguiente ejemplo:

C:/Users/Las lokis/Desktop/prueba.txt

Los archivos deben estar en formato txt.

Hay dos archivos de ejmplo disponibles:

- Maquina2g.txt

- Maquina1.txt

Una vez ingresada la dirección debe presionarse la tecla enter.

BOTONES

Limpiar: libera el contenido de la ventana.

Siguiente: Al dar click, muestra un solo paso.

Saltar Pasos: Pide un número al usuario y se salta ese número de pasos.

Salir: cierra la ventana.

# NOTA

Los archivos Maquina1 y Maquina2 son ejemplos de Máquinas de Turing
