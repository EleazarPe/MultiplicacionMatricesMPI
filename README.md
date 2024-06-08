# MultiplicacionMatricesMPI
Primero llenamos con números aleatorios la matrices utilizando la funcion iniciar, con numeros de 0-9. 
![image](https://github.com/EleazarPe/MultiplicacionMatricesMPI/assets/71235444/5deee0ad-39a2-4fe2-bef8-008deab37c17)

Tiene dos funcionamientos: de forma secuencial y de forma paralela. En el modo secuencial todo el trabajo se hace en un solo proceso y se mide el tiempo que tarda en completar la multiplicación. En cambio, el modo paralelo, utiliza MPI (Interfaz de Paso de Mensajes) para distribuir la carga de trabajo entre varios procesos.
![image](https://github.com/EleazarPe/MultiplicacionMatricesMPI/assets/71235444/c9b4bf00-3739-463f-8946-3c95f29fe5a9)

Se dividen las matrices entre los procesos disponibles, luego la multiplicación y finalmente se recogen los resultados en el proceso principal, midiendo también el tiempo total de ejecución.
![image](https://github.com/EleazarPe/MultiplicacionMatricesMPI/assets/71235444/f844ce49-3417-4497-91fc-b38e085fb035)
# Resultados:
## Secuencial:
![secuencial88](https://github.com/EleazarPe/MultiplicacionMatricesMPI/assets/71235444/f8a10ec0-9f4f-453c-a785-f6bf9e1717c9)
Se realiza la multiplicación de dos matrices de tamaño 8x8 en un solo hilo de ejecución. Las matrices A y B se llenan con números aleatorios, y el resultado se almacena en la matriz C. La operación completa tomó 19100 nanosegundos. Este tiempo muestra el rendimiento de la multiplicación cuando se realiza en un solo proceso, sin la sobrecarga causada por la coordinación entre múltiples procesos.
## Paralelo
![paralelo88](https://github.com/EleazarPe/MultiplicacionMatricesMPI/assets/71235444/d9987e98-1790-4395-847f-b74943264ec8)
Utilizando MPI para distribuir la multiplicación de matrices entre cuatro procesos. Aunque las matrices A y B también se llenan con números aleatorios y el resultado se almacena en la matriz C, la coordinación entre los procesos y la recolección de resultados crean una sobrecarga. La operación completa tomó 547800 nanosegundos, siendo un tiempo  mayor que en la ejecución secuencial. Por lo que, la sobrecarga de paralelización supera los beneficios de dividir el trabajo entre múltiples procesos.

# Conlcusion:
los resultados obtenidos muestran que para la multiplicación la ejecución secuencial es más eficiente que la paralela. La ejecución secuencial completó la tarea en menos tiempo, mientras que la ejecución paralela tomo mas tiempo debido a la sobrecarga de coordinación y comunicación entre los procesos. Estos resultados muestran que para tareas de este tipo, la simplicidad y menor sobrecarga de la ejecución secuencial la hacen mejor sobre la paralela.

# Relfexión 
NO RECOMIENDO MPI
1. Compatibilidad: estamos atados a utilizar versiones anteriores de Java como es java 8.
2. Tiempo de configuracion: Es necesario realizar una extensa configuracion para ejecutarlo.
3. Soporte: la gran mayoria de adaptaciones de MPI llevan sin soporte mas de 8 años.
4. Complejidad de uso: La curva de aprendizaje es alta, lo que demora mas tiempo para desarollo de tareas basicas. 
