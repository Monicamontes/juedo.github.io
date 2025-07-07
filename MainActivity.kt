
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Juego de Memoria - Dino</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background: linear-gradient(to right, #1e3c72, #2a5298);
      color: white;
      text-align: center;
      padding: 20px;
    }

    h1 {
      margin-bottom: 20px;
    }

    .grid {
      display: grid;
      grid-template-columns: repeat(4, 100px);
      gap: 15px;
      justify-content: center;
      margin-top: 20px;
    }

    .card {
      width: 100px;
      height: 100px;
      border: none;
      background: none;
      padding: 0;
    }

    .card img {
      width: 100%;
      height: 100%;
      border-radius: 10px;
      cursor: pointer;
    }

    #portal {
      font-size: 1.5em;
      margin: 20px 0;
      padding: 15px;
      background: rgba(255, 255, 255, 0.1);
      border: 2px dashed white;
      border-radius: 15px;
      display: none;
    }

    .oculto {
      display: none;
    }
  </style>
</head>
<body>

  <h1>🧠 Juego de Memoria: Era de Dinosaurios 🦖</h1>
  <div id="portal">🌀 ¡Ganaste! Has activado el portal prehistórico 🦕</div>
  <div id="grid" class="grid"></div>

  <script>
    const imagenes = [
      "img/dino1.png",
      "img/dino2.png",
      "img/dino3.png",
      "img/dino4.png",
      "img/dino5.png",
      "img/dino6.png"
    ];

    let cartas = [];
    let primeraCarta = null;
    let bloqueo = false;

    function iniciarJuego() {
      const grid = document.getElementById("grid");
      const portal = document.getElementById("portal");
      portal.style.display = "none";

      const baraja = [...imagenes, ...imagenes]
        .map((src, index) => ({ id: index, src, descubierta: false, emparejada: false }))
        .sort(() => Math.random() - 0.5);

      cartas = baraja;

      grid.innerHTML = "";

      baraja.forEach((carta, index) => {
        const boton = document.createElement("button");
        boton.className = "card";
        const img = document.createElement("img");
        img.src = "img/oculta.png";
        img.dataset.index = index;
        boton.appendChild(img);
        boton.onclick = () => manejarClic(index);
        grid.appendChild(boton);
      });
    }

    function manejarClic(indice) {
      if (bloqueo) return;
      const carta = cartas[indice];
      if (carta.descubierta || carta.emparejada) return;

      const img = document.querySelectorAll("img")[indice];
      img.src = carta.src;
      carta.descubierta = true;

      if (primeraCarta === null) {
        primeraCarta = indice;
      } else {
        const primera = cartas[primeraCarta];
        if (primera.src === carta.src) {
          carta.emparejada = true;
          primera.emparejada = true;
          primeraCarta = null;
          if (cartas.every(c => c.emparejada)) {
            mostrarPortal();
          }
        } else {
          bloqueo = true;
          setTimeout(() => {
            carta.descubierta = false;
            primera.descubierta = false;
            img.src = "img/oculta.png";
            document.querySelectorAll("img")[primeraCarta].src = "img/oculta.png";
            primeraCarta = null;
            bloqueo = false;
          }, 800);
        }
      }
    }

    function mostrarPortal() {
      const portal = document.getElementById("portal");
      portal.style.display = "block";
      portal.style.opacity = 0;
      setTimeout(() => {
        portal.style.transition = "opacity 1s";
        portal.style.opacity = 1;
      }, 100);
    }

    iniciarJuego();
  </script>

</body>
</html>
