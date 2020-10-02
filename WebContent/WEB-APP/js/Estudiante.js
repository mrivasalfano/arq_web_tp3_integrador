class Estudiante extends CRUDGenerico {
    constructor() {
        super('estudiantes');
        this.container = document.querySelector('.main');
    }

	async getEstudianteLibreta(numero){
		this.getModular(`/lu/${numero}`).then(estudiante => this.mostrarEstudiante(estudiante))
										.catch(err => alert(err));
	}
	
	async getEstudiantesGenero(genero){
		this.getModular(`/genero/${genero}`).then( estudiantes => this.mostrarEstudiantes(estudiantes))
											.catch(err => alert(err));
	}

    mostrarEstudiantes(estudiantes) {
		let filas = '';
		
		estudiantes.forEach(est => {
			filas += `	<tr>
							<td>${est.id}</td>
							<td>${est.nombres}</td>
							<td>${est.apellido}</td>
							<td>${est.edad}</td>
							<td>${est.genero}</td>
							<td>${est.dni}</td>
							<td>${est.nroLibretaUni}</td>
						</tr>`;
		});
		
        this.container.innerHTML = 
			`<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">id</th>
							<th scope="col">Nombre</th>
							<th scope="col">Apellido</th>
							<th scope="col">Edad</th>
							<th scope="col">Género</th>
							<th scope="col">DNI</th>
							<th scope="col">Nro. libreta</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
    }

	mostrarEstudiante(est) {
		let filas = `<tr>
						<td>${est.id}</td>
						<td>${est.nombres}</td>
						<td>${est.apellido}</td>
						<td>${est.edad}</td>
						<td>${est.genero}</td>
						<td>${est.dni}</td>
						<td>${est.nroLibretaUni}</td>
					</tr>`;
		
        this.container.innerHTML = 
			`<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">id</th>
							<th scope="col">Nombre</th>
							<th scope="col">Apellido</th>
							<th scope="col">Edad</th>
							<th scope="col">Género</th>
							<th scope="col">DNI</th>
							<th scope="col">Nro. libreta</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
    }

}