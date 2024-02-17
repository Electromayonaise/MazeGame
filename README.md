<!--horizontal divider(gradiant)-->
<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif">

<!--h1 without bottom border-->
<div id="user-content-toc">
  <ul align="center">
    <summary><h1 style="display: inline-block"><a href="https://github.com/Electromayonaise">Martín Gómez</a>, <a href="https://github.com/jul109">Julio Prado</a>, <a href="https://github.com/SrCracles">Alejandro Mejía</a></h1></summary>
  </ul>
</div>

<!--- snake -->
<div align="center">
  <img src="https://github.com/Electromayonaise/Electromayonaise/blob/main/Assets/github-contribution-grid-snake%20blacktest(1).svg" alt="snake" />
</div>

<!--h2 without bottom border-->
<div id="user-content-toc">
  <ul align="center">
     <summary><h2 style="display: inline-block">Codes: A00399958 ,A00399637,A00399937 </h2></summary>
    <body><h3 style="display: inline-block">Second integrative task of the discrete structures course</h3></body>
     <body><h3 style="display: inline-block"><a href="https://docs.google.com/document/d/1Sd4d1kTLxUdXZYQZJvbMPPqjIQ-U9kF1ZZahD_DQex0/edit?usp=sharing">DOCUMENTATION</a></h3></body>
       <body><h3 style="display: inline-block">Note: IDE Used: IntelliJ IDEA 2023.2.2</h3></body>
       <body><h3 style="display: inline-block">Project SDK: BellSoft Liberica version 21.0.1 (take this into account in order for the project to launch correctly) </h3></body>
        <body><h3 style="display: inline-block">Before making any use of the source code on this project, please read the license it is under</h3></body>
     
    
  </ul>
</div>

# About the project 

We want to create a maze game, where the player has to fight enemies on his way to the maze exit. The maze itself is to be modeled as a graph, where the player must go from an initial node to an end node. We want the game to be as interactive as possible, so the player will be something like a bomberman, where he can throw bombs to kill the enemies, and the enemies will follow him using the shortest possible path. 

## Short description of how the structures within the game work

- When the player presses the start game button, a maze will be generated in the form of a graph utilizing a randomized DFS algorithm, then, said graph is then translated into a map.
- The enemies follow the player by using the shortest path provided by a bfs search
- The player may choose the graph implementation (directed, not directed) and representation (adjacency list, adjacency matrix) when initializing the game, as per the given requirements. Said change does not have any visual change on the game, as methods within it work in all 4 possible cases

## Important comments about the project

### Final commit of first delivery:
- Commit ID: [54fbf95](https://github.com/Electromayonaise/MazeGame/commit/54fbf958dfa7e38b58d75a397b0878e424437060)

### About tests for the structures: 
Due to a problem with Maven dependencies, we where forced to develop the tests on the branch [tests](https://github.com/Electromayonaise/MazeGame/tree/tests), so please refer to said branch for structure testing
