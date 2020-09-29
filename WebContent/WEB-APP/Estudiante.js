class Estudiante extends CRUDGenerico {
    constructor() {
        super('estudiantes');
        this.listaEstudiante = document.querySelector('.estudiantes-container__lista');
    }

    async getAll() {
        return await super.getAll();
    }

    async getEstudianteLibreta(numero) {
        const response = await fetch(this.BASEURI + "/" + this.RESOURCE + '/lu/' + numero);
        console.log(response);
        return await response.json();
    }

    mostrarEstudiantes(estudiantes) {
        this.listaEstudiante.innerHTML = '<ul>';

        estudiantes.forEach(est => {
            this.listaEstudiante.innerHTML += `<li>${est.nombres} ${est.apellido}</li>`;
        });

        this.listaEstudiante.innerHTML += '</ul>';
    }

}