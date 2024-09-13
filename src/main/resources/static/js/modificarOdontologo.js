document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("modificarForm");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const odontologoId = document.getElementById("odontologoId").value; // 
        const apellido = document.getElementById("apellido").value;
        const nombre = document.getElementById("nombre").value;
        const matricula = document.getElementById("dni").value;
        
        // Datos del formulario con el odontologo a modificar
        const datosFormulario = {
            id: odontologoId,
            nombre,
            apellido,
            matricula,
            
        };

        // Enviar los datos al servidor
        fetch(`odontologo/modificar`, {  
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datosFormulario)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            alert("Odontologo modificado con éxito");
            form.reset(); // Limpiar el formulario
        })
        .catch(error => {
            console.error("Error modificando odontologo:", error);
            alert("Ocurrió un error al modificar el odontologo.");
        });
    });
});
