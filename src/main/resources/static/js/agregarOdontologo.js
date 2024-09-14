const form = document.getElementById("agregarForm");

form.addEventListener("submit", function (event) {
  event.preventDefault();

    const matricula = document.getElementById("matricula").value;
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;

  // llamando al endpoint de agregar
  const datosFormulario = {
    matricula,
    nombre,
    apellido,
  };

  fetch(`odontologo/guardar`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datosFormulario),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontologo agregado con éxito");
      form.reset(); // Resetear el formulario
    })
    .catch((error) => {
      console.error("Error agregando odontologo:", error);
    });
});

















































/*
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("agregarForm");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const apellido = document.getElementById("apellido").value;
        const nombre = document.getElementById("nombre").value;
        const matricula = document.getElementById("matricula").value;

        // Datos del formulario
        const datosFormulario = {
            nombre,
            apellido,
            matricula
        };

        // Enviar los datos al servidor
        fetch(`odontologo/guardar`, {  // Cambia esta URL por el endpoint correcto en tu servidor
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datosFormulario)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            alert("Odontólogo agregado con éxito");
            form.reset(); // Limpiar el formulario
        })
        .catch(error => {
            console.error("Error agregando odontólogo:", error);
            alert("Ocurrió un error al agregar el odontólogo.");
        });
    });
});

*/