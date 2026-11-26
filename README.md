# Triqui en Java

Proyecto de Triqui para consola, realizado con clases, arreglos, matrices, condicionales y ciclos.

## Clases

- `Inicio`: menú, lectura de datos y comunicación con el usuario.
- `GestorJugadores`: registro, búsqueda y clasificación de jugadores.
- `Jugador`: datos y estadísticas de cada jugador.
- `Maquina`: decisiones sencillas para jugar contra el usuario.
- `Partida`: turnos y resultado de una partida.
- `Tablero`: matriz de casillas y validación de movimientos.
- `Casilla`: ficha almacenada en cada posición.


## Ejecutar

1. Abrir una terminal en esta carpeta.
2. Compilar: `javac *.java`
3. Ejecutar: `java Inicio`


## Cambios de revisión

- Se ajustaron los nombres de clases a PascalCase y los métodos a camelCase.
- La victoria suma 2 puntos y el empate suma 1 punto (según la regla indicada durante la revisión; no se verificó contra el PDF de la asignatura).
- Los códigos de jugador deben ser positivos.
- Se muestran de nuevo los jugadores al seleccionar el segundo jugador en PvP.
- Los movimientos inválidos ahora distinguen coordenadas fuera del tablero y casillas ocupadas.
- La máquina muestra la fila y columna de su movimiento.
- La lectura de números conserva `try/catch` para manejar letras, texto vacío u otras entradas que no sean números enteros.

La máquina utiliza heurísticas sencillas y no garantiza ganar todas las partidas.
