class Matricula extends CRUDGenerico {
    constructor() {
        super('matriculas');
        this.listaMatricula = document.querySelector('.matriculas-container__lista');
    }

    mostrarMatriculas(matriculas) {
        this.listaMatricula.innerHTML = '<ul>';

        matriculas.forEach(m => {
            this.listaMatricula.innerHTML += `<li>${m.estudiante.nombres} ${m.estudiante.apellido} - ${m.carrera.nombre}</li>`;
        });

        this.listaMatricula.innerHTML += '</ul>';
    }

	getReporte(){
		this.getModular(`/reporte`).then( response => this.mostrarData(response));
	}
	
	matricularEstudiante(idEstudiante, idCarrera) {
		this.postModular('', {idEstudiante : idEstudiante, idCarrera: idCarrera})
		.then(() => console.log('Matriculado!'));
	}
	
	
	
	mostrarData(response){
		console.log('MATRICULA');
		console.log(response);
	}
}