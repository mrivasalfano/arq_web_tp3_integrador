class Matricula extends CRUDGenerico {
    constructor() {
        super('matriculas');
        this.container = document.querySelector('.main');
    }

    mostrarMatriculas(matriculas) {
        let filas = '';

        matriculas.forEach(mat => {
			const estudiante = mat.estudiante.nombres + ' ' + mat.estudiante.apellido;
			let graduado = 'SÍ';
			const fecha = new Date(mat.fechaInscripcion);
			const inscripcion = fecha.getUTCDay() + '/' + fecha.getUTCMonth() + '/' + fecha.getUTCFullYear();
			
			if(mat.graduado == 0)
				graduado = 'NO';
				
            filas += `	<tr>
							<td>${mat.id}</td>
							<td>${mat.carrera.nombre}</td>
							<td>${estudiante}</td>
							<td>${inscripcion}</td>
							<td>${graduado}</td>
						</tr>`;
        });



        this.container.innerHTML =
            `<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">id</th>
							<th scope="col">Carrera</th>
							<th scope="col">Estudiante</th>
							<th scope="col">Inscripción</th>
							<th scope="col">Graduado</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
    }

	mostrarReporte(reporte) {
        let filas = '';

        reporte.forEach(rep => {
            filas += `	<tr>
							<td>${rep.carrera.nombre}</td>
							<td>${rep.cantEgresados}</td>
							<td>${rep.cantInscriptos}</td>
							<td>${rep.anio}</td>
						</tr>`;
        });

        this.container.innerHTML =
            `<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Carrera</th>
							<th scope="col">Graduados</th>
							<th scope="col">Inscriptos</th>
							<th scope="col">Año</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
    }

	getReporte(){
		this.getModular(`/reporte`).then( response => this.mostrarReporte(response))
								   .catch(err => alert(err));
	}
	
	matricularEstudiante(idEstudiante, idCarrera) {
		this.postModular('', {idEstudiante : idEstudiante, idCarrera: idCarrera})
		.then(() => console.log('Matriculado!'))
		.catch(err => alert(err));
	}
	
	
	
	mostrarData(response){
		console.log('MATRICULA');
		console.log(response);
	}
}