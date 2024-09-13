document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("eliminarForm");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const odontologoId = document.getElementById("odontologoId").value;

        // Asegurarse de que el usuario quiere eliminar el odontologo
        if (!confirm("¿Estás seguro de que deseas eliminar este odontologo?")) {
            return;
        }

        // Enviar la solicitud al servidor para eliminar el odontologo
        fetch(`"odontologo/eliminar/{id}"`, { // 
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            if (response.ok) {
                alert("Odontologo eliminado con éxito");
                form.reset(); // Limpiar el formulario
            } else {
                alert("Error eliminando odontologo");
            }
        })
        .catch(error => {
            console.error("Error eliminando odontologo:", error);
            alert("Ocurrió un error al eliminar el odontologo.");
        });
    });
});
