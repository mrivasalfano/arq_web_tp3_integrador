class Carrera extends CRUDGenerico {
    constructor() {
        super('carreras');
        this.container = document.querySelector('.main');
    }

    mostrarCarreras(carreras) {
        let filas = '';

        carreras.forEach(carr => {
            filas += `	<tr>
							<td>${carr.id}</td>
							<td>${carr.nombre}</td>
						</tr>`;
        });



        this.container.innerHTML =
            `<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">id</th>
							<th scope="col">Nombre</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
    }

    mostrarCarrera(carrera) {
        let filas = `	<tr>
							<td>${carrera.id}</td>
							<td>${carrera.nombre}</td>
						</tr>`;

        this.container.innerHTML =
            `<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">id</th>
							<th scope="col">Nombre</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
    }

    mostrarReporte(reporte) {
        reporte.carrera.forEach(rep => {
            rep['cantidad'] = reporte.cantidad[rep.id];
        });
        
		const carreras = reporte.carrera;
		let filas = '';

        carreras.forEach(carr => {
            filas += `	<tr>
							<td>${carr.id}</td>
							<td>${carr.nombre}</td>
							<td>${carr.cantidad}</td>
						</tr>`;
        });



        this.container.innerHTML =
            `<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">id</th>
							<th scope="col">Nombre</th>
							<th scope="col">Cantidad</th>
						</tr>
					</thead>
					<tbody class="bg-light">
						${filas}
					</tbody>
				</table>`;
    }

    getReporte(filtro) {
        this.getModular(`/reporte?filtro=${filtro}`).then(reporte => this.mostrarReporte(reporte))
            .catch(err => alert(err));
    }

    getCarreraId(id) {
        this.getModular(`/${id}`).then(carrera => this.mostrarCarrera(carrera))
            .catch(err => alert(err));
    }

    getCarreraEstudiantes(id) {
        this.getModular(`/${id}/estudiantes?ciudad=Bolivar`).then(response => {
				let estudiante = new Estudiante();
				estudiante.mostrarEstudiantes(response);
			})
            .catch(err => alert(err));
    }
}