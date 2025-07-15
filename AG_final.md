<u>Algoritmos genéticos y su aplicación a la optimización<u>

**Algoritmos genéticos**

Los algoritmos genéticos (AG) son una variante de búsqueda local
estocástica inspirada en la evolución biológica. A diferencia de otros
métodos sistemáticos de búsqueda, los AG operan directamente sobre una
población de soluciones, sin necesidad de mantener trayectorias desde un
estado inicial. Esto los hace especialmente útiles para problemas de
optimización en los que no importa el camino recorrido, sino únicamente
el estado final (por ejemplo: diseño de circuitos, layout de fábricas,
problemas de scheduling, entre otros).

Por lo tanto podemos definirlos como:

- Algoritmos de búsqueda local

- Técnica de búsqueda local para encontrar soluciones a problemas de
  optimización

Y sus ventajas y desventajas son:

- Ventajas:

  - Menos memoria

  - Usos de esquemas en las soluciones

<!-- -->

- Desventajas:

  - Si hay restricciones duras pueden no encontrar una solución

**Descripción de los algoritmos genéticos (AG)**

- Iterativo

- Parte de una población inicial (estado)

- Cada iteración:

  - Se aplica una función de idoneidad (fitness) sobre los otros
    individuos, y se les asigna un valor

  - Se seleccionan los más aptos

  - Se aplican operadores evolutivos sobre los más aptos, seleccionando
    los nuevos

  - Los nuevos son la siguiente iteración

  - Se repite hasta cumplir la condición de parada del algoritmo

**Conceptos y analogía con la naturaleza**

- Población: modelo del estado del algoritmo genético.
  Compuesto por individuos (soluciones), en cada iteración se actualiza
  la población.

- Individuo: posible solución al problema, cada individuo se representa
  como una instancia

- Gen: cada uno de los parámetros que definen una solución

- Genotipo: una instancia de la solución, representa a un individuo en
  particular.

- Fenotipo: "expresión observable" del genotipo (un
  individuo)

- Operadores evolutivos: Se aplican a los individuos de una población
  para generar la siguiente Generación. (mejorar las soluciones
  disponibles)

- Puede involucrar: un único individuo de la población o
  múltiples individuos

![](media/image1.png){width="4.958333333333333in"
height="2.001649168853893in"}

**Elementos**

- Cómo funciona la función de idoneidad (fitness)

<!-- -->

- Debería devolver los valores más altos para individuos "más aptos"

- Se encarga de representar los requerimientos a los que el algoritmo
  debe adaptarse

- Permite determinar la calidad de la solución que representa cada
  individuo

- No solo indicar que tan buena es la solución, sino cuan cerca está de
  la solución óptima.

<!-- -->

- Selección de padres

<!-- -->

- Selección del grupo de individuos que participan del proceso de
  reproducción

- Se simula el proceso de selección natural

- Hay varias estrategias para el proceso de selección

- Selección proporcional: se realiza de acuerdo a la probabilidad que
  tiene en cuenta el valor obtenido en la función fitness, se calcula la
  probabilidad de que un individuo sea seleccionado.

- Selección por torneos: k soluciones al azar y se las compara entre sí.
  Se elige aquella con mejor valor de fitness, es muy útil cuando la
  población es muy grande.

<!-- -->

- Cruzamiento

  - Se eligen n/2 pares de individuos (n es el total de la población),
    mediante el proceso de selección descripto. Por cada par se genera
    aleatoriamente un punto de cruce, se cortan los 2 individuos del par
    en el punto cruce y se invierten sus partes

- Mutación

  - Una vez aplicado el crossover, se seleccionan cero o más elementos
    del genoma de cada individuo de acuerdo a una probabilidad p y se
    los modifica aleatoriamente

- Reemplazo

  - Luego el crossover se siguen estrategias para los individuos
    recientemente obtenidos.

    1.  Reemplazo basado en la edad

    2.  Reemplazo basado en el fitness

    3.  Reemplazo de los peores individuos

    4.  Elitismo (mantener los mejores)

<!-- -->

- Condiciones de parada

  - Convergencia: la diferencia de calidad entre una población y la
    anterior es menor que un determinado umbral. La **calidad** puede
    medirse de distintas maneras, típicamente con valores de fitness.

  - Tiempo: Se para el algoritmo al cabo de un cierto tiempo

  - Cantidad de Iteraciones: Se para luego de un cierto número de
    iteraciones

  - Condiciones especiales e hibridas: por ej. Si hay restricciones
    duras, aunque cumpla el criterio de parada, puede continuar con el
    algoritmo hasta que se haya satisfecho estas restricciones. También
    pueden combinarse criterios anteriores.

**Teoría de esquemas (schema theory)**

Se introduce el concepto de esquema (schema) como una subestructura
dentro del genotipo que puede representar características útiles. Por
ejemplo, en un problema como las 8 reinas, un esquema podría ser "las
tres primeras reinas en columnas donde no se atacan". Si los individuos
que contienen ese esquema tienen mejor fitness, el número de ellos
aumentará generación tras generación.

**Comparaciones y rendimiento**

Si bien los AG no siempre superan a otros métodos como el hill climbing
estocástico, su principal ventaja radica en:

- La búsqueda paralela sobre múltiples regiones del espacio de
soluciones.
- Su capacidad de recombinar bloques útiles gracias al crossover.
- La posibilidad de escapar de óptimos locales mediante mutación.

No obstante, su rendimiento puede variar dependiendo de cómo se
represente la solución, cómo se defina la función de fitness, y qué
estrategias de selección/crossover se usen. Se requiere una ingeniería
cuidadosa para que funcionen bien en la práctica.

**Aplicación de los algoritmos genéticos al proyecto TETRIS**

Este trabajo forma parte del desarrollo de un proyecto de inteligencia
artificial enfocado en diseñar un agente que juegue Tetris, utilizando
distintas técnicas de búsqueda local, entre ellas el algoritmo genético.
El objetivo principal es optimizar la manera de jugar a través de la
mejor colocación posible de las piezas, maximizando la puntuación, las
líneas eliminadas y la duración del juego antes de perder.

Los algoritmos genéticos se utilizan aquí para evolucionar funciones de
evaluación que guíen las decisiones del agente. Cada individuo en la
población representa un conjunto de parámetros (pesos) que determinan la
calidad de una posible posición para cada pieza, considerando
características como:

- Altura máxima del tablero

- Número de huecos generados

- Cantidad de líneas eliminadas

- Espacios inaccesibles

El fitness de cada individuo se mide al simular partidas con esa
configuración y registrar el puntaje, líneas eliminadas y duración del
juego. A través de operadores de selección, cruzamiento y mutación, se
generan nuevas configuraciones que progresivamente pueden mejorar el
desempeño del agente.

Finalmente, la comparación de métricas (puntaje, cantidad de líneas,
número de piezas colocadas) entre los agentes implementados permitirá
determinar la eficacia del enfoque genético dentro del proyecto. Este
enfoque no sólo complementa la estrategia general, sino que aporta una
base sólida de aprendizaje y exploración heurística.

![](media/image2.png){width="5.982658573928259in"
height="2.7114752843394574in"}

Como conclusión, los algoritmos genéticos representan una gran
herramienta de optimización inspirada en los principios de la evolución
natural. A través del uso de una población de soluciones, operadores
como la selección, el cruzamiento y la mutación, y una función de
fitness que guía la evolución, estos algoritmos permiten explorar de
manera eficaz espacios de búsqueda complejos, adaptándose a entornos
dinámicos y no lineales.

En el contexto del proyecto de desarrollo de un agente inteligente para
jugar Tetris, se ha podido justificar cómo los algoritmos genéticos
pueden ser aprovechados para evolucionar funciones heurísticas que guíen
las decisiones del agente.
