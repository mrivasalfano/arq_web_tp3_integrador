class Estudiante extends CRUDGenerico {
    constructor() {
        super('estudiantes');
        this.listaEstudiante = document.querySelector('.estudiantes-container__lista');
    }

	async getEstudianteLibreta(numero){
		this.getModular(`/lu/${numero}`).then(estudiantes => console.log(estudiantes))
										.catch(err => alert(err));
	}
	
	async getEstudiantesGenero(genero){
		this.getModular(`/genero/${genero}`).then( estudiantes => console.log(estudiantes))
											.catch(err => alert(err));
	}

    mostrarEstudiantes(estudiantes) {
        this.listaEstudiante.innerHTML = '<ul>';

        estudiantes.forEach(est => {
            this.listaEstudiante.innerHTML += `<li>${est.nombres} ${est.apellido} - ${est.nroLibretaUni}</li>`;
        });

        this.listaEstudiante.innerHTML += '</ul>';
    }

}